package com.example.jsf2;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloBean {

    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void sayHello() {
        this.message = "Hello, " + name + "!";
    }
}