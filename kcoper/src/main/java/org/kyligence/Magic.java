package org.kyligence;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.kyligence.Httptool.getOperations;
import static org.kyligence.Alert.sendMessage;
public class Magic {
    private static Logger logger = Logger.getLogger(Main.class);

    public static String escapeSpecialCharacters(String data) {

        data = data.replace("{", "").replace("\"","");


        return data;
    }
    private static JSONObject readJsonFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }


        public static String getDateToString(long time){
         Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return sf.format(d);
    }

    public static void a() throws Exception {
        File file = new File("res.json");
        ArrayList<String> id = new ArrayList<>();
        List<Content> res=new ArrayList<>();
        //如果文件不存在则 直接写入文件
        if (!file.exists()) {
            logger.info("res.json file is not exit,now create it");
            JSONObject operations = getOperations();
            FileUtils.writeStringToFile(file, operations.toString(), "UTF-8");
        } else {
            //读取文件有
            List<Content> content = JSON.parseArray(readJsonFile(file).getJSONArray("content").toString(),Content.class);
            for (Content operation_id : content) {
                id.add(operation_id.getId());
            }

            //拿现在api
            JSONObject operations1 = getOperations();
            List<Content> operations = JSON.parseArray(operations1.getJSONArray("content").toString(),Content.class);
            for (Content operation : operations) {
                if (!id.contains(operation.getId())){
                    res.add(operation);

                }
            }



            if (res.size() !=0){
                for (Content re : res) {
                    if(re.getStatus().contains("ERROR")) {
//                        System.out.println("有error");
//                        System.out.println(re);
                        logger.info("there has error events,to send messages,id is: " + re.getId());
//                        logger.info("event id: "+re.getId());
//                        System.out.println(re.getMessage());
                        Config config = new Config();
                        String message = null;
                        if (re.getMessage().length() > 500) {
                            message = re.getMessage().substring(0, 500);
                        }
                        sendMessage(config.getOpsat(), config.getOpsalerttitle(), config.getOpsfeishuurl(), re.getClusterName(), re.getId(),
                                re.getStatus(), getDateToString(re.getCreateTime()), re.getOperator(),
                                re.getDeployErrorType(), re.getOperation(), escapeSpecialCharacters(message), re.getRunningTime() / 60);
                        //sendMessage(config.getOpsat(),config.getOpsalerttitle(),config.getOpsfeishuurl(),
                        // re.getClusterName(),re.getId(),re.getStatus(),getDateToString(re.getCreateTime()),re.getOperator(),
                        // re.getDeployErrorType(),re.getOperation(),escapeSpecialCharacters(re.getMessage().substring(0,500)),re.getRunningTime()/60);//写入
                    }

                }
                FileUtils.writeStringToFile(file, operations1.toString(), "UTF-8");
            }else{

                FileUtils.writeStringToFile(file, operations1.toString(), "UTF-8");
                logger.info("there has no error events,to write current success");
            }
            }
            }

}
