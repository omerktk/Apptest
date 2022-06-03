package com.zofa.apptest;

public class Product {
    public String pname;
    public String pdetail;
    public String pimage;


    public Product()
    {

    }

    public Product(String pname, String pdetail, String pimage) {
        this.pname = pname;
        this.pdetail = pdetail;
        this.pimage = pimage;
    }


    public String pname() {
        return pname;
    }

    public String pdetail() {
        return pdetail;
    }

    public String pimage() {
        return pimage;
    }
}