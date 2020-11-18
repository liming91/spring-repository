package com.ming.service.impl;

import com.ming.service.IListInjection;

import java.util.*;

/**
 * 注入复杂类型
 * 用set方法注入
 */
public class ListInjectionImpl implements IListInjection {

    private String [] myArray;

    private List<String> myList;

    private Set<String> mySet;

    private Map<String,String> myMap;

    private Properties myProper;

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProper(Properties myProper) {
        this.myProper = myProper;
    }

    @Override
    public void saveInjection() {
        System.out.println(Arrays.toString(myArray));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myProper);
    }
}
