package com.wonders.health.tumor.busremind.vo;

import com.wonders.health.tumor.tumor.entity.*;
import lombok.Data;

@Data
public class BusRemindResultVo {

    private static final long serialVersionUID = 1L;

    private CancerPersonInfo cancerPersonInfo;      //基本信息
    private CrcFobt crcFobt;                        //大肠癌检查表
    private CrcFobtRemind fobtR;                    //大肠癌便隐血检查提醒表
    private CrcRegcase crcRegcase;                  //大肠癌初筛信息登记实体

    private CrcDiagCheckRemind crcDiag;             //大肠癌诊断检查提醒表实体
    private LicRegcase licRegcase;
    private LicDiagCheckRemind licDiag;             //肝癌诊断检查提醒表实体
    private ScRegcase scRegcase;
    private ScDiagCheckRemind scDiag;               //胃癌诊断检查提醒表实体
    private LucRegcase lucRegcase;
    private LucDiagCheckRemind lucDiag;             //肺癌诊断检查提醒表实体

}
