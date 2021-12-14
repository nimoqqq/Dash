package com.chuf.javase;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GsonTest {
    public static void main(String[] args) {
        String result = "{\"skuId\":\"413451097\",\"source\":\"1\",\"pageNo\":1,\"pageSize\":10,\"blurShopId\":\"\",\"blurShopName\":\"\",\"blurCityName\":\"\"}";
        Map data = new Gson().fromJson(result, HashMap.class);
        System.out.println(data);
        System.out.println(data.get("skuId"));
    }
}
