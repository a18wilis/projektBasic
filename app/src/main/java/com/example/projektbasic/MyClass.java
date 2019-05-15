package com.example.projektbasic;

public class MyClass {
    private String array1;
    private String array2;

    public MyClass(){
        array1="Array 1 not specified";
        array2="Array 2 not specified";

    }

    public MyClass(String a1, String a2){
        array1 = a1;
        array2 = a2;
    }

    public String info(){
        String tmp=new String();
        tmp+=array1+" + " +array2;
        return tmp;
    }

    @Override
    public String toString() {
        return array1;
    }
}
