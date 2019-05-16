package com.example.projektbasic;

public class MyClass {
    private String name;
    private String origin;

    public MyClass(){
        name="Array 1 not specified";
        origin="Array 2 not specified";

    }

    public MyClass(String a1, String a2){
        name = a1;
        origin = a2;
    }

    public String info(){
        String tmp=new String();
        tmp+=name+" is a driver from " +origin;
        return tmp;
    }

    @Override
    public String toString() {
        return name;
    }
}
