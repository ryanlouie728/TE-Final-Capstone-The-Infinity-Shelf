package com.techelevator.model;

public class Count {
    private String name;
    private Integer count = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Count(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
}
