package com.kandy.algorithm.week02.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 811. 子域名访问次数
 */
public class LC811子域名访问计数 {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> ans = new ArrayList();
        if (cpdomains == null || cpdomains.length == 0) {
            return ans;
        }
        //每个域名出现的次数
        Map<String, Integer> domainCountMap = new HashMap<>();
        //遍历每个域名
        for (String domain : cpdomains) {
            String[] cpdomain = domain.split(" ");
            if (cpdomain == null || cpdomain.length != 2) {
                continue;
            }
            //次数
            int count = Integer.parseInt(cpdomain[0]);

            String[] domains = cpdomain[1].split("\\.");

            int len = domains.length;
            //temp存每个子域名
            String temp = "";
            for (int i = len - 1; i >= 0; i--) {
                if (i == len - 1) {
                    temp = domains[len - 1];
                } else {
                    temp = domains[i] + "." + temp;
                }
                //统计每个子域名的访问次数
                domainCountMap.put(temp, domainCountMap.getOrDefault(temp, 0) + count);
            }
        }
        //封装结果返回
        for (Map.Entry<String, Integer> entry : domainCountMap.entrySet()) {
            ans.add(entry.getValue() + " " + entry.getKey());
        }
        return ans;
    }
}
