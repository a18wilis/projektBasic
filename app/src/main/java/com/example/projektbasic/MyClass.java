package com.example.projektbasic;

public class MyClass {
    private String name;
    private String origin;
    private String WCs;
    private String fact;

    public MyClass(){
        name="Name not specified";
        origin="Origin not specified";
        WCs="Nº World Championships not specified";
        fact ="Fact not specified";

    }

    public MyClass(String n, String o, String w, String f){
        name = n;
        origin = o;
        WCs = w;
        fact = f;
    }

    public String info(){
        String tmp=new String();
        tmp+=name+" is a driver from " +origin +".\n \n" +name +" has won a total of " +WCs +" world championships.";
        return tmp;
    }

    public String fact(){
        String tmp= fact;
        return tmp;
    }

    @Override
    public String toString() {
        return name;
    }
}
