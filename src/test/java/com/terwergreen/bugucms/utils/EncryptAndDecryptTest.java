package com.terwergreen.bugucms.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EncryptAndDecryptTest {

    @Test
    public void encrypt() {
        Map map = new HashMap();
        map.put("loginId", 1);
        map.put("loginName", "admin");
        map.put("role", "ROLE_ADMIN");
        System.out.println(EncryptAndDecrypt.encrypt(JSON.toJSONString(map)));
    }

    @Test
    public void decrypt() {
        Map map = new HashMap();
        map.put("loginId", 1);
        map.put("loginName", "admin");
        map.put("role", "ROLE_ADMIN");
        System.out.println(EncryptAndDecrypt.decrypt(EncryptAndDecrypt.encrypt(JSON.toJSONString(map))));
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("loginId", 1);
        map.put("loginName", "admin");
        map.put("role", "ROLE_ADMIN");
        System.out.println(EncryptAndDecrypt.encrypt(JSON.toJSONString(map)));
        System.out.println(EncryptAndDecrypt.decrypt(EncryptAndDecrypt.encrypt(JSON.toJSONString(map))));
    }
}