package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

public class QQQQ {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(3);

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.subList(i + 1, list.size()).contains(list.get(i))) {
                list.remove(list.get(i));
                i--;
            }
        }
        System.out.println(list.toString());

    }


}


class QQQQ1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(3);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 != 0) {
                list.remove(list.get(i));
                i--;
            }
        }
        System.out.println(list.toString());

    }
}

class QQQQ2{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(3);

        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!list1.contains(list.get(i))){
                list1.add(list.get(i));

            }
        }
        System.out.println(list1.toString());

    }
}



