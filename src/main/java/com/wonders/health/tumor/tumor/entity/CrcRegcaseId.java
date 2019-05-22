package com.wonders.health.tumor.tumor.entity;

import com.wonders.health.tumor.common.model.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CrcRegcaseId extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Length(max=5)
    @NotNull
    private String id;	//编号

    @Length(max=32)
    private String code;	// 机构代码

    @Length(max=120)
    private String name;	// 机构名称

    @Length(max=20)
    private String addressCounty;	// 机构所属区县


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrcRegcaseId) {
            CrcRegcaseId crcRegcaseId= (CrcRegcaseId) obj;
            return code.equalsIgnoreCase(crcRegcaseId.getCode().trim());
        }
        return false;
    }

//测试equals方法
//    public static void main(String[] args){
//        CrcRegcaseId p1=new CrcRegcaseId();
//        p1.setId("1");
//        p1.setCode("01");
//        p1.setAddressCounty("1213");
//        CrcRegcaseId p2=new CrcRegcaseId();
//        p2.setId("2");
//        p2.setCode("02");
//        p2.setAddressCounty("1213");
//        List<CrcRegcaseId> list = new ArrayList<CrcRegcaseId>();
//        list.add(p1);
//        list.add(p2);
//        CrcRegcaseId p3=new CrcRegcaseId();
//        p3.setId("1");
//        p3.setCode("02");
//        CrcRegcaseId p4=new CrcRegcaseId();
//        p4.setId("1");
//        p4.setCode("003");
//        System.out.println("是否包含p3："+list.contains(p3));
//        System.out.println("是否包含p4："+list.contains(p4));
//    }
}
