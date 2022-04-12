package com.example.demo;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
//        String reason = "所在区域尚未开放免费试,不在免费试开放的品类内,免费试合作库存已用完";
        String reason = "门店无营业执照,数据更新延迟,所在区域尚未开放免费试";
        List<String> list = Lists.newArrayList(reason.split(","));
        ArrayList<String> poiValidateReasonList = Lists.newArrayList("所在区域尚未开放免费试", "不在免费试开放的品类内", "免费试合作库存已用完");
        System.out.println(list.retainAll(poiValidateReasonList));
        System.out.println(StringUtils.join(list.toArray(),","));
    }

}
