package com.chuf.javase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTest {
    public static void main(String[] args) {
        String result = "{\"skuId\":\"413451097\",\"source\":\"1\",\"pageNo\":1,\"pageSize\":10,\"blurShopId\":\"\",\"blurShopName\":\"\",\"blurCityName\":\"\"}";
        Map data = new Gson().fromJson(result, HashMap.class);
        System.out.println(data);
        System.out.println(data.get("skuId"));

        Hello hello = new Hello();
        Hello hello1 = new Hello();
        List<Hello> list = new ArrayList<>();
        list.add(hello);
        list.add(hello1);
        list.forEach(hello2 -> hello2.setName("121"));
        System.out.println(list);
    }
}

class Hello {
    private String name;

    private String emen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmen() {
        return emen;
    }

    public void setEmen(String emen) {
        this.emen = emen;
    }
}
