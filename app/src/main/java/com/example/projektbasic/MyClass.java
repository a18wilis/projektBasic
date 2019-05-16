package com.example.projektbasic;

public class MyClass {
    private String name;
    private String origin;
    private String WCs;

    public MyClass(){
        name="Name not specified";
        origin="Origin not specified";
        WCs="Nº World Championships not specified";

    }

    public MyClass(String n, String o, String w){
        name = n;
        origin = o;
        WCs = w;
    }

    public String info(){
        String tmp=new String();
        tmp+=name+" is a driver from " +origin +".\n \n" +name +" has won a total of " +WCs +" world championships.";
        return tmp;
    }

    @Override
    public String toString() {
        return name;
    }
}
