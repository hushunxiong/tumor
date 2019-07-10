package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.gen.utils.XmlUtils;
import com.wonders.health.tumor.tumor.vo.XhCancelItem;
import com.wonders.health.tumor.tumor.vo.XhCancelReturnVo;
import com.wonders.health.tumor.tumor.vo.XhCancelVo;
import com.wonders.health.tumor.tumor.webservice.TechBookServiceSoap;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 徐汇胸科医院预约检查接口Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class XkyyReserveService {
    String url = "http://192.168.14.16/TechBookService/TechBookService.asmx?wsdl";

    /**
     * 代理模式调用
     */
    public boolean cancelReserve(String patid, String checkCode) throws Exception {
        XhCancelReturnVo returnVo = null;
        try {
            JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
            // 2.设置服务的发布接口，使用本地的代理接口
            factoryBean.setServiceClass(TechBookServiceSoap.class);
            // 3.设置服务的发布地址，表示去哪里获取服务
            factoryBean.setAddress(url);
            // 4.通过create方法返回接口代理实例
            TechBookServiceSoap service = (TechBookServiceSoap) factoryBean.create();
            String result = service.cancelBook(getCancelXml(patid,checkCode));
            if (StringUtils.isNotBlank(result)) {
                //解析xml

                returnVo = XmlUtils.fromXml(result, XhCancelReturnVo.class);
                if (returnVo != null) {
                    XhCancelVo cancelVo = returnVo.getItemList();
                    if (cancelVo != null) {
                        XhCancelItem item = cancelVo.getItem();
                        if (item != null) {
                            if (StringUtils.equals("true", item.getMessage())) {
                                return true;
                            }
                        }
                    }
                }

                /*
                String aa = result.substring(result.indexOf("<Success>") + 9,result.indexOf("</Success>"));
                if (StringUtils.equalsIgnoreCase("true", aa)) {
                    return true;
                }
                */
            }
        } catch (Exception e) {
            throw e;
        }

        return false;
    }

    private String getCancelXml(String patid, String checkCode) {
        //xml拼接
        StringBuffer sb = new StringBuffer();
        sb.append("<Request><PrePatUniqueNo>");
        sb.append(patid);
        sb.append("</PrePatUniqueNo><BookSID>");
        sb.append(checkCode);
        sb.append("</BookSID>");
        sb.append("<OperatorCode>fazb</OperatorCode><OperatorName></OperatorName>");
        sb.append("</Request>");
        return sb.toString();
    }
}
