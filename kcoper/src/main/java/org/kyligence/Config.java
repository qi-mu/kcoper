package org.kyligence;

import lombok.Data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


@Data
public class Config {

//public void readProperties() throws IOException {
//    AppletParameters hashMap=null;
//    String filePath="ops.properties";
//    BufferedReader bufferedReader=new BufferedReader((new FileReader(filePath)));
//    String config=bufferedReader.readLine();
//
//    while(config != null && !config.equals("")){
//        String[] splits = config.split("=");
//        hashMap.put(splits[0],splits[1]);
//        config = bufferedReader.readLine();
//    }
//    Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
//    for (Map.Entry<String, String> entry : entrySet) {
//        System.out.println(entry.getKey() + ": " + entry.getValue());
//    }
//
//}


    private  String opsurl;
    private  String opstoken;
    private  String opsfeishuurl;
    private  String opsalerttitle;
    private  long opsfixedtime;

    private  String opsat;



    public Config() throws IOException {
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("ops.properties"));
        properties.load(bufferedReader);
//        properties.load(this.getClass().getClassLoader().getResourceAsStream("./ops.properties"));
        opsurl=properties.getProperty("ops.url");
        opstoken=properties.getProperty("ops.token");
        opsfeishuurl=properties.getProperty("ops.feishu.webhook.url");
        opsalerttitle=properties.getProperty("ops.alert.title");
        opsfixedtime= Long.parseLong(properties.getProperty("ops.fixedtime"));
        opsat=properties.getProperty("ops.at");


    }

}
