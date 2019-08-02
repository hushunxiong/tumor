package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

/**
* @Description:    社区卫生服务中心肿瘤早发现进度表(社区登录的场合)
* @Author:         hushunxiong
* @CreateDate:     2019/7/19 18:22
* @UpdateRemark:
* @Version: 
*/
@Data
public class SeScheduleListVo{

    /**
     *  月份
     */
    private String month;

    /**
     *  筛查登记机构(名称)
     */
    private String regorg;

    /**
     *  筛查登记机构(代码)
     */
    private String regorgCode;

    /**
     * 登记人数
     */
    private String num;

    /**
     *合计
     */
    private  String total;
    /**
     *完成筛查人数
     */
    private String summary;

    /**
     筛查阳性数
     */
    private String regCaseNum;

    /**
     筛查阳性率
     */
    private String regCaseRate;

    /**
     *肠镜检查数
     */
    private String crcCheckNum;

    /**
     *肠镜检查率
     */
    private String crcCheckRate;

    /**
     *癌前期病变数
     */
    private String crcLesionNum;

    /**
     *癌前期病变率
     */
    private String crcLesionRate;

    /**
     *大肠癌病例数
     */
    private String crcCaseNum;

    /**
     *CT检查数
     */
    private String lucCheckNum;

    /**
     *CT检查率
     */
    private String lucCheckRate;

    /**
     *肺癌病例数
     */
    private String lucCaseNum;

    /**
     *胃镜检查数
     */
    private String scCheckNum;

    /**
     *胃镜检查率
     */
    private String scCheckRate;

    /**
     *胃癌病例数
     */
    private String scCaseNum;

    /**
     *肝癌病例数
     */
    private String licCaseNum;

    /**
     *确诊检查数
     */
    private String confirmeNum;

    /**
     *确诊检查率
     */
    private String confirmeRate;

    /**
     *确诊癌病例数
     */
    private String confirmeCancerNum;

    /**
     *确诊早期数
     */
    private String earlyDiagnosisNum;

    /**
     *确诊早期率
     */
    private String earlyDiagnosisRate;

}
