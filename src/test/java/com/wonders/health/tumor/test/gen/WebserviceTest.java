package com.wonders.health.tumor.test.gen;

import com.sun.org.apache.xml.internal.utils.ObjectPool;
import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import com.wonders.health.tumor.gen.utils.XmlUtils;
import com.wonders.health.tumor.tumor.vo.XhPatientParamVo;
import com.wonders.health.tumor.tumor.vo.XhPatientReturnVo;

/**
 * Created by suny on 2019/6/26.
 */
public class WebserviceTest {


    public static void main(String[] args) {
        XhPatientParamVo vo = new XhPatientParamVo();
        vo.setHzxm("王二");
        vo.setYbdm("72");
        vo.setSex("1");
        vo.setSfzh("310104198804181119");
        vo.setBirth("19880418");
        vo.setLxdh("13671112342");
        vo.setQxbm("1");
        vo.setSqbm("42503195300");
        System.out.println(XmlUtils.toXml(vo));

        StringBuffer sb = new StringBuffer();
        XhPatientReturnVo ob = new XhPatientReturnVo();
        sb.append("<response><resultCode>0</resultCode><resultMessage></resultMessage><result><patid>1223</patid><blh>445</blh></result></response>");
        ob = XmlUtils.fromXml(sb.toString(), XhPatientReturnVo.class);
        int i = 0;
    }



}
