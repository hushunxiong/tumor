package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.dao.CrcRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import com.wonders.health.tumor.tumor.service.CrcRegcaseService;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class Java8Class {
   public static void main(String args[]){
       List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8);
       List<Integer>list2=Arrays.asList();

       System.out.println("输出所有数据");
//       eval(list,n->true);
//       eval(list,n->n%2==0);
       list2=list.stream().filter(n->n>3).collect(
               Collectors.toList()
       );
       list2.stream().filter(n->true).forEach(System.out::print);

       List<Apple> appleList = new ArrayList<>();//存放apple对象集合

       Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
       Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
       Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
       Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

       appleList.add(apple1);
       appleList.add(apple12);
       appleList.add(apple2);
       appleList.add(apple3);
       List<Integer> ll=new ArrayList();
       List<Apple> list3=new ArrayList();

       Map<Integer,List<Apple>> map=appleList.stream().collect(Collectors.groupingBy(Apple::getId));

       map.keySet().forEach(key-> list3.addAll(map.get(key)));

       map.entrySet().iterator().forEachRemaining(item-> ll.add(item.getKey()));

       System.out.println(ll);
       System.out.println(list3);
       Map<Integer,Apple> appleMap=appleList.stream().collect(Collectors.toMap(Apple::getId,a->a,(k1,k2)->k1));
       System.out.println(appleMap);

       BigDecimal total=appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
       System.out.println(total);

       Optional<Apple>max=appleList.stream().collect(Collectors.maxBy(Comparator.comparing(Apple::getId)));
       max.ifPresent(list3::add);
       System.out.println(list3);
       list3.stream().forEach(System.out::print);
       List<Apple> lll=appleList.stream().collect(collectingAndThen(toCollection(()->new TreeSet<>(comparingLong(Apple::getId))),ArrayList::new));

       System.out.println(lll);

       LocalDateTime current=LocalDateTime.now();
       System.out.println(current);
       LocalDate dd= LocalDate.now();
       System.out.println(dd);
       int year=dd.getYear();
       System.out.println(year);
       CrcRegcaseService service=new CrcRegcaseService();


       System.out.println("NOW DATE");
       LocalDateTime cur=LocalDateTime.now();
       cur.plusDays(7);
       System.out.println(cur);

   }
    public static void eval(List<Integer>list,Predicate<Integer>predicate){
       for(Integer n:list){
           if(predicate.test(n)){
               System.out.println(n+"");
           }
       }

    }
    public static class Apple {
        private Integer id;

        private String name;
        private BigDecimal money;
        private Integer num;

        public Apple(Integer id, String name, BigDecimal money, Integer num) {
            this.id = id;
            this.name = name;
            this.money = money;
            this.num = num;
        }

        public Integer getId(){
            return this.id;
        }
        public BigDecimal getMoney(){
            return this.money;
        }
    }

}
