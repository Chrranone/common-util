package com.anserlt.common.java.util.ztest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
            Integer i = it.next();
            if (i == 3) {
                int j = list.indexOf(i);
                list.set(j, 5);
            }
        }

        System.out.println(list);
    }

}
