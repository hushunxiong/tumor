package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.gen.utils.XmlUtils;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.vo.XhPatientParamVo;
import com.wonders.health.tumor.tumor.vo.XhPatientResultVo;
import com.wonders.health.tumor.tumor.vo.XhPatientReturnVo;
import com.wonders.health.tumor.tumor.webservice.KWSServiceSoap;
import org.apache.axis.client.Call;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

/**
 * 徐汇胸科医院接口Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class XkyyRegisterService {
    String url = "http://192.168.14.16:8089/KWSService.asmx?wsdl";
    String namespace = "http://www.winning.com.cn/KWS";
    String methodName = "WebBusiness";
    String soapActionURI = "http://www.winning.com.cn/KWS/WebBusiness";

    /**
     * cxf基本参数类型调用
     */
    public XhPatientResultVo callApiByBasic(CancerPersonInfo info) throws Exception{
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient(url);
        XhPatientReturnVo returnVo = null;
        try {

            Object[] param = new String[2];
            param[0] = "B101";
            param[1] = getPatientXml(info);

            Object[] res = client.invoke("WebBusiness", param);

            String result = (String) res[0];

            if (StringUtils.isNotBlank(result)) {
                //解析xml
                returnVo = XmlUtils.fromXml(result, XhPatientReturnVo.class);
                if (StringUtils.equals("1", returnVo.getResultCode())) {
                    throw new Exception("调用徐汇胸科医院接口失败");
                }
            }
        } catch (Exception e) {
            throw e;
        }

        if (returnVo != null) {
            return returnVo.getResult();
        }
        return null;
    }


    public XhPatientResultVo callApiByProxy(CancerPersonInfo info) throws Exception {
        XhPatientReturnVo returnVo = null;
        try {
            JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
            // 2.设置服务的发布接口，使用本地的代理接口
            factoryBean.setServiceClass(KWSServiceSoap.class);
            // 3.设置服务的发布地址，表示去哪里获取服务
            factoryBean.setAddress(url);
            // 4.通过create方法返回接口代理实例
            KWSServiceSoap service = (KWSServiceSoap) factoryBean.create();
            String result = service.webBusiness("B101", getPatientXml(info));

            if (StringUtils.isNotBlank(result)) {
                //解析xml
                returnVo = XmlUtils.fromXml(result, XhPatientReturnVo.class);
                if (StringUtils.equals("1", returnVo.getResultCode())) {
                    throw new Exception("调用徐汇胸科医院接口失败");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        if (returnVo != null) {
            return returnVo.getResult();
        }
        return null;
    }

    private String getPatientXml(CancerPersonInfo info) {
        XhPatientParamVo vo = new XhPatientParamVo();

        vo.setHzxm(info.getName());
        if (StringUtils.equals("01", info.getPaymentSituation())) {
            vo.setYbdm("70");
        } else if (StringUtils.equals("02", info.getPaymentSituation())) {
            vo.setYbdm("72");
        } else {
            vo.setYbdm("");
        }
        vo.setSex(info.getGender());
        vo.setSfzh(info.getPersoncard());
        vo.setBirth(DateUtils.formatDate(info.getBirth(),"yyyyMMdd"));
        vo.setLxdh(StringUtils.isNotBlank(info.getMobile()) ? info.getMobile() : info.getTelephone());
        vo.setQxbm("1");
        vo.setSqbm(info.getRegorg());

        //xml拼接
        StringBuffer sb = new StringBuffer();
        sb.append("<request><partner></partner><sign></sign><timestamp></timestamp><operid>fazb</operid><password></password><kfsdm></kfsdm>");
        String xml = XmlUtils.toXml(vo);
        if (xml.indexOf("<params>") >=0) {
            xml = xml.substring(xml.indexOf("<params>"));
        }

        sb.append(xml);
        sb.append("</request>");
        return sb.toString();
    }
}
