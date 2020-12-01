package xin.marico.facerecogition.test;

import redis.clients.jedis.Jedis;
import xin.marico.facerecogition.utils.UUIDUtils;

import java.util.Date;

public class RedisPub {
    private static Jedis jedis = new Jedis("localhost",6379);
    private static final String channel = "face_recogintion_queue0";
    public static void main(String[] args){
            for(int i = 0;i<10;i++){
                String message = UUIDUtils.getId()+"-"+UUIDUtils.getId();
                System.out.println(message);
                jedis.rpush(channel, message);
            }
    }
}
