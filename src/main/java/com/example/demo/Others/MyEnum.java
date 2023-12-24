package com.example.demo.Others;

public enum MyEnum {

    MANAGER("Трамвай", 1),
    DEVELOPER("Трамвай", 1),
    DESIGNE("Трамвай", 1);
    private String positionName;
    private int salary;

    MyEnum(String positionName, int salary) {
        this.positionName = positionName;
        this.salary = salary;
    }
}
