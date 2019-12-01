package com.benjamin;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    JedisPool pool;
    Jedis jedis;
    @Before
    public void setUp(){
        jedis = new Jedis("localhost");
    }


    /**
     * Redis存储初级的字符串
     * CRUD
     */
    @Test
    public void testBasicString(){
        //-----添加数据----------
        jedis.set("name","meepo");//向key-->name中放入了value-->meepo
        System.out.println(jedis.get("name"));//执行结果：meepo

        //-----修改数据-----------
        //1、在原来基础上修改
        jedis.append("name","dota");   //很直观，类似map 将dota append到已经有的value之后
        System.out.println(jedis.get("name"));//执行结果:meepodota

        //2、直接覆盖原来的数据
        jedis.set("name","poofu");
        System.out.println(jedis.get("name"));//执行结果：poofu

        //删除key对应的记录
        jedis.del("name");
        System.out.println(jedis.get("name"));//执行结果：null

        /**
         * mset相当于
         * jedis.set("name","meepo");
         * jedis.set("dota","poofu");
         */
        jedis.mset("name","meepo","dota","poofu");
        System.out.println(jedis.mget("name","dota"));

    }

    /**
     * jedis操作Map
     */
    @Test
    public void testMap(){
        Map<String,String> user=new HashMap<String,String>();
        user.put("name","meepo");
        user.put("pwd","password");
        jedis.hmset("user",user);
        //取出user中的name，执行结果:[meepo]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name");
        System.out.println(rsmap);

        //删除map中的某个键值
//        jedis.hdel("user","pwd");
        System.out.println(jedis.hmget("user", "pwd")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数1
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  [pwd, name]
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value  [meepo, password]

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }

    }
}
