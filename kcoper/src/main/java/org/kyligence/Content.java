package org.kyligence;

import lombok.Data;

@Data
public class Content {
    private  String clusterName;
    private  long createTime;
    private  String deployErrorType;
    private  String id;
    private  String message;
    private  String operandClass;
    private  String operation;
    private  String operator;
    private  int runningTime;
    private  String status;

}
