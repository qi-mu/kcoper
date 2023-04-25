package org.kyligence;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        long pid = Long.valueOf(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        logger.info("application start,new process id is : " + pid);
        Config config=new Config();
        fixedTime(config.getOpsfixedtime());
    }



    public static void fixedTime(long period) {
        Runnable runnable = new Runnable() {
            int a=1;
            public void run() {
                try {
                    Magic c=new Magic();
                    c.a();
                    a=1;
                    logger.info("get operations success");
                    logger.info("sign is : "+a+" ok");
                } catch (Exception e) {
                    String message= e.getMessage();
                    logger.error(message);
                    int y=a++;
                    if (y%5==0){
                        logger.error("It has been more than 5 minutes with no results，sign is : "+y+" now send alert");
                        //发送告警 application get error
                    }}}};
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        //立即执行，并且每5秒执行一次
        ses.scheduleAtFixedRate(runnable, 0, period, TimeUnit.MILLISECONDS);
    }
    }


