package com.bestxiaole.system.controller;

import com.bestxiaole.server.service.ElasticsearchRestService;
import com.bestxiaole.server.vo.UserInfo;
import io.netty.util.internal.StringUtil;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/es")
public class EsController {
    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private ElasticsearchRestService elasticsearchRestService;

    @RequestMapping("/set")
    public void setes() {
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Date date = new Date();
            String name = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))) + "" + (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
            list.add(new UserInfo(String.valueOf(i), name + "二", "北京市", date));
        }
        Iterable iterable = template.save(list);
        System.out.println(iterable);
    }

    @RequestMapping("/setone")
    public void setten() throws InterruptedException {
        elasticsearchRestService.setListEs();
    }

    @RequestMapping("/getpage")
    public Object getes(int pageNo, int pageSize, String keyWord) {

//        int pageNo=1;//设置第几页
//        int pageSize = 100;// 设置每页数据量

        FieldSortBuilder sortBuilder = new FieldSortBuilder("name.keyword").order(SortOrder.ASC);//排序
        NativeSearchQuery nativeSearchQuery = null;
        if (StringUtil.isNullOrEmpty(keyWord) || keyWord.equals("")) {
            MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();//条件
            nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withSort(sortBuilder).build();
        } else {
            MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", keyWord);
            nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withSort(sortBuilder).build();
        }
        nativeSearchQuery.setMaxResults(pageSize);// 设置每页数据量

        long scrollTimeInMillis = 60 * 1000;//设置缓存内数据的保留时间
        //1、缓存第一页符合搜索条件的数据
        SearchScrollHits<UserInfo> searchScrollHits = template.searchScrollStart(scrollTimeInMillis, nativeSearchQuery, UserInfo.class, IndexCoordinates.of("userinfo_index"));
        String scrollId = searchScrollHits.getScrollId();

        int scrollTime = 1;
        while (searchScrollHits.hasSearchHits() && scrollTime < pageNo) {//2、判断searchScrollHits中是否有命中数据，如果为空，则表示已将符合查询条件的数据全部遍历完毕
            //3、根据上次搜索结果scroll_id进入下一页数据搜索
            searchScrollHits = template.searchScrollContinue(scrollId, scrollTimeInMillis, UserInfo.class, IndexCoordinates.of("userinfo_index"));//该方法执行后会重新刷新快照保留时间
            scrollId = searchScrollHits.getScrollId();
            scrollTime = scrollTime + 1;
        }

        List<String> scrollIds = new ArrayList<>();
        scrollIds.add(scrollId);
        template.searchScrollClear(scrollIds);// 清除 scroll
        List<UserInfo> out = new ArrayList<>();
        for (SearchHit<UserInfo> searchHit : searchScrollHits.getSearchHits()) {//4、 从缓存中读取数据
            UserInfo userInfo = searchHit.getContent();
            out.add(userInfo);
//            System.out.println(userInfo.getId()+" "+userInfo.getName()+" "+userInfo.getAddress());
        }
        return out;
    }
}
