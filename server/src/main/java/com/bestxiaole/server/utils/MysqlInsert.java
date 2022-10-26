package com.bestxiaole.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlInsert {
    static Connection conn = null;

    public static void initConn() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/bestxiaole?"
                + "user=root&password=253950672&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
        try {
            // 动态加载mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(int insertNum) throws SQLException {
        // 开时时间
        Long begin = System.currentTimeMillis();
        System.out.println("开始插入数据...");
        // 设置事务为非自动提交
        conn.setAutoCommit(false);
        PreparedStatement pst = (PreparedStatement)conn.prepareStatement("INSERT INTO ad (name,click_url,img_url,status,`order`, create_time, update_time) VALUES(?,?,?,?,?,?,?)");
        try {
            // 保存sql后缀
            for (int i = 1; i <= insertNum; i++) {
                String name = (char) (0x4e00 +(int)(Math.random()*(0x9fa5 - 0x4e00 + 1))) +""+(char) (0x4e00 +(int)(Math.random()*(0x9fa5 - 0x4e00 + 1)));
                pst.setString(1,name);
                pst.setString(2,"http://www.cainiaoxiaole.com/click_"+i);
                pst.setString(3,"http://www.cainiaoxiaole.com/"+i+".png");
                pst.setInt(4,1);
                pst.setInt(5,0);
                pst.setString(6,"2022-10-25 22:22:30");
                pst.setString(7,"2022-10-25 22:30:00");
                pst.addBatch();
            }
            // 执行操作
            pst.executeBatch();
            // 提交事务
            conn.commit();
            // 关闭连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        System.out.println("插入" + insertNum + "条数据数据完成！");
        System.out.println("耗时 : " + (end - begin) / 1000 + " 秒");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        initConn();
        insert(1000000);
    }

}
