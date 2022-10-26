package com.bestxiaole.server.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisInsert {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("253950672");
        jedis.select(1);
        String key = "AD_LIST2";
        jedis.del(key);
        jedis.set(key,"88888888888888");
        System.out.println(jedis.get(key));
    }
}
