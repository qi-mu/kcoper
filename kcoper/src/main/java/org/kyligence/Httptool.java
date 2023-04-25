package org.kyligence;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;


public class Httptool {
    public static JSONObject getOperations() throws IOException {
        Config config=new Config();
        String url =config.getOpsurl();
        String token =config.getOpstoken();
        HttpResponse execute = HttpRequest.get(url)
                .header("Accept-Language", "zh-cn")
                .header("Connection", "keep-alive")
                .header("Authorization", token)
                .timeout(10000)
                .execute();
        return JSON.parseObject(execute.body());
    }

//    public static JSONObject getQueryHistory() {
//        String url = "http://159.27.10.221:8079/kylin/api/query/history_queries?realization=&query_status=&submitter=&project=tmp&limit=10&offset=0&start_time_from=1673452800000&start_time_to=1673539200000&latency_from=&latency_to=&server=&sql=";
//        String token = "Basic QURNSU46S3lsaW5AMjAyMg==";
//        HttpResponse execute = HttpRequest.get(url)
//                .header("Accept","application/vnd.apache.kylin-v4+json")
//                .header("Accept-Language", "zh-cn")
//                .header("Connection", "keep-alive")
//                .header("Authorization", token)
//                .timeout(60000)
//                .execute();
//        return JSON.parseObject(execute.body());
//    }
//   -H 'Accept: ' \
}
