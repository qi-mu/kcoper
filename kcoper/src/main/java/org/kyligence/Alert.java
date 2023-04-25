package org.kyligence;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Alert {
    private static Logger logger = Logger.getLogger(Main.class);

    public Alert() throws IOException {

    }

    public static void sendMessage(String opsat,String title,String WebHookUrl,String clusterName,String id,String status,String createTime,String operator,String deployErrorType,String operation,String message,int runningTime) {
       String c="{\n" +
               "\t\"msg_type\": \"interactive\",\n" +
               "\t\"card\": {\n" +
               "\t\t\"config\": {\n" +
               "\t\t\t\"wide_screen_mode\": true\n" +
               "\t\t},\n" +
               "\t\t\"header\": {\n" +
               "\t\t\t\"template\": \"blue\",\n" +
               "\t\t\t\"title\": {\n" +
               "\t\t\t\t\"tag\": \"plain_text\",\n" +
               "\t\t\t\t\"content\": \""+title+"\"\n" +
               "\t\t\t}\n" +
               "\t\t},\n" +
               "\t\t\"elements\": [{\n" +
               "\t\t\t\"tag\": \"markdown\",\n" +
               "\t\t\t\"content\": \"<at id="+opsat+"></at>\"\n" +
               "\t\t}, {\n" +
               "\t\t\t\"tag\": \"div\",\n" +
               "\t\t\t\"fields\": [{\n" +
               "\t\t\t\t\"is_short\": true,\n" +
               "\t\t\t\t\"text\": {\n" +
               "\t\t\t\t\t\"tag\": \"lark_md\",\n" +
               "\t\t\t\t\t\"content\": \"**Information：** \\n\\n**clusterName**:"+clusterName+"\\n\\n**id**:"+id+"\\n\\n**status**:"+status+"\\n\\n**ncreateTime**:"+createTime+"\\n\\n**operator**:"+operator+"\\n\\n**deployErrorType**:"+deployErrorType+"\\n\\n**operation**:"+operation+"\\n\\n**runningTime**:"+runningTime+"min \"" +
               "\t\t\t\t}\n" +
               "\t\t\t}, {\n" +
               "\t\t\t\t\"is_short\": true,\n" +
               "\t\t\t\t\"text\": {\n" +
               "\t\t\t\t\t\"tag\": \"lark_md\",\n" +
               "\t\t\t\t\t\"content\": \"**Error message：**\\n"+message+" \"\n" +
               "\t\t\t\t}\n" +
               "\t\t\t}]\n" +
               "\t\t}, {\n" +
               "\t\t\t\"tag\": \"hr\"\n" +
               "\t\t}, {\n" +
               "\t\t\t\"tag\": \"note\",\n" +
               "\t\t\t\"elements\": [{\n" +
               "\t\t\t\t\"tag\": \"plain_text\",\n" +
               "\t\t\t\t\"content\": \"support by kyligence.io\"\n" +
               "\t\t\t}]\n" +
               "\t\t}]\n" +
               "\t},\n" +
               "\t\"mock_data\": \"{}\",\n" +
               "\t\"variables\": []\n" +
               "}";

        logger.info("body: "+ c);
        String result = HttpRequest.post(WebHookUrl).body( JSON.toJSONString(JSON.parseObject(c)), "application/json;charset=UTF-8").execute().body();
        logger.info("send message done: "+ result);

    }



    public static void sendTest(String WebHookUrl) {
        String c="{\n" +
                "\t\"msg_type\": \"interactive\",\n" +
                "\t\"card\": {\n" +
                "\t\t\"elements\": [{\n" +
                "\t\t\t\"tag\": \"column_set\",\n" +
                "\t\t\t\"flex_mode\": \"none\",\n" +
                "\t\t\t\"background_style\": \"default\",\n" +
                "\t\t\t\"columns\": []\n" +
                "\t\t}, {\n" +
                "\t\t\t\"tag\": \"column_set\",\n" +
                "\t\t\t\"flex_mode\": \"none\",\n" +
                "\t\t\t\"background_style\": \"grey\",\n" +
                "\t\t\t\"columns\": [{\n" +
                "\t\t\t\t\"tag\": \"column\",\n" +
                "\t\t\t\t\"width\": \"weighted\",\n" +
                "\t\t\t\t\"weight\": 1,\n" +
                "\t\t\t\t\"vertical_align\": \"top\",\n" +
                "\t\t\t\t\"elements\": [{\n" +
                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
                "\t\t\t\t\t\"content\": \"**\uD83D\uDC31：**test\",\n" +
                "\t\t\t\t\t\"text_align\": \"left\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"tag\": \"column_set\",\n" +
                "\t\t\t\t\t\"flex_mode\": \"none\",\n" +
                "\t\t\t\t\t\"background_style\": \"default\",\n" +
                "\t\t\t\t\t\"columns\": []\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}]\n" +
                "\t\t}],\n" +
                "\t\t\"header\": {\n" +
                "\t\t\t\"template\": \"red\",\n" +
                "\t\t\t\"title\": {\n" +
                "\t\t\t\t\"content\": \"Test\",\n" +
                "\t\t\t\t\"tag\": \"plain_text\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"mock_data\": \"{}\",\n" +
                "\t\"variables\": []\n" +
                "}";
        logger.info("body: "+ c);
        String result = HttpRequest.post(WebHookUrl).body( JSON.toJSONString(JSON.parseObject(c)), "application/json;charset=UTF-8").execute().body();
        logger.info("send message done: "+ result);

    }
//    public static void sendMessage() {
//        String v="{\n" +
//                "\t\"msg_type\": \"interactive\",\n" +
//                "\t\"card\": {\n" +
//                "\t\t\"elements\": [{\n" +
//                "\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\"content\": \"**Query summary by day**\\n\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"tag\": \"column_set\",\n" +
//                "\t\t\t\"flex_mode\": \"bisect\",\n" +
//                "\t\t\t\"background_style\": \"grey\",\n" +
//                "\t\t\t\"horizontal_spacing\": \"default\",\n" +
//                "\t\t\t\"columns\": [{\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\",\n" +
//                "\t\t\t\t\t\"content\": \"Query Increase %\\n**${increase}**\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\",\n" +
//                "\t\t\t\t\t\"content\": \"Error Rate %\\n**${errorrate}**\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\",\n" +
//                "\t\t\t\t\t\"content\": \"Cache utilization %\\n**${utilization}**\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}]\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\"content\": \"**Details:**\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"tag\": \"column_set\",\n" +
//                "\t\t\t\"flex_mode\": \"none\",\n" +
//                "\t\t\t\"background_style\": \"grey\",\n" +
//                "\t\t\t\"columns\": [{\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**Time**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**Total**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**Success**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**avg**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**<1s**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**<3s**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**<10s**\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"**>10s**\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}],\n" +
//                "\t\t\t\"horizontal_spacing\": \"default\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"tag\": \"column_set\",\n" +
//                "\t\t\t\"flex_mode\": \"none\",\n" +
//                "\t\t\t\"background_style\": \"default\",\n" +
//                "\t\t\t\"columns\": [{\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${time}\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${total}\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${success}\",\n" +
//                "\t\t\t\t\t\"text_align\": \"center\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${avg}\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${1s}\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${3s}\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${10s}\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}, {\n" +
//                "\t\t\t\t\"tag\": \"column\",\n" +
//                "\t\t\t\t\"width\": \"weighted\",\n" +
//                "\t\t\t\t\"weight\": 1,\n" +
//                "\t\t\t\t\"vertical_align\": \"top\",\n" +
//                "\t\t\t\t\"elements\": [{\n" +
//                "\t\t\t\t\t\"tag\": \"markdown\",\n" +
//                "\t\t\t\t\t\"content\": \"${other}\"\n" +
//                "\t\t\t\t}]\n" +
//                "\t\t\t}],\n" +
//                "\t\t\t\"_varloop\": \"${group_table}\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"tag\": \"div\",\n" +
//                "\t\t\t\"text\": {\n" +
//                "\t\t\t\t\"content\": \"这是一段普通文本\uD83D\uDE04\",\n" +
//                "\t\t\t\t\"tag\": \"plain_text\"\n" +
//                "\t\t\t}\n" +
//                "\t\t}]\n" +
//                "\t},\n" +
//                "\t\"mock_data\": \"{\\\"increase\\\":\\\"\\\",\\\"errorrate\\\":\\\"\\\",\\\"utilization\\\":\\\"\\\",\\\"group_table\\\":[{\\\"time\\\":\\\"a\\\",\\\"total\\\":\\\"s\\\",\\\"success\\\":\\\"d\\\",\\\"avg\\\":\\\"f\\\",\\\"1s\\\":\\\"g\\\",\\\"3s\\\":\\\"h\\\",\\\"10s\\\":\\\"t\\\",\\\"other\\\":\\\"y\\\"}]}\",\n" +
//                "\t\"variables\": [{\n" +
//                "\t\t\"config\": \"{}\",\n" +
//                "\t\t\"create_time\": 1673446202,\n" +
//                "\t\t\"description\": \"group_table\",\n" +
//                "\t\t\"name\": \"group_table\",\n" +
//                "\t\t\"type\": \"DataLoop\",\n" +
//                "\t\t\"update_time\": 1673446202,\n" +
//                "\t\t\"variable_id\": \"7187342525233332226\"\n" +
//                "\t}, {\n" +
//                "\t\t\"config\": \"{}\",\n" +
//                "\t\t\"create_time\": 1673446202,\n" +
//                "\t\t\"description\": \"increase\",\n" +
//                "\t\t\"name\": \"increase\",\n" +
//                "\t\t\"type\": \"Text\",\n" +
//                "\t\t\"update_time\": 1673446202,\n" +
//                "\t\t\"variable_id\": \"7187342525233348610\"\n" +
//                "\t}, {\n" +
//                "\t\t\"config\": \"{}\",\n" +
//                "\t\t\"create_time\": 1673446202,\n" +
//                "\t\t\"description\": \"errorrate\",\n" +
//                "\t\t\"name\": \"errorrate\",\n" +
//                "\t\t\"type\": \"Text\",\n" +
//                "\t\t\"update_time\": 1673446202,\n" +
//                "\t\t\"variable_id\": \"7187342525233364994\"\n" +
//                "\t}, {\n" +
//                "\t\t\"type\": \"Text\",\n" +
//                "\t\t\"name\": \"utilization\",\n" +
//                "\t\t\"description\": \"utilization\",\n" +
//                "\t\t\"variable_id\": 0,\n" +
//                "\t\t\"config\": \"{}\"\n" +
//                "\t}]\n" +
//                "}";
//        String result = HttpRequest.post(WebHookUrl).body( JSON.toJSONString(JSON.parseObject(v)), "application/json;charset=UTF-8").execute().body();
//        logger.info("send message done: "+ result);
//
//    }


}




