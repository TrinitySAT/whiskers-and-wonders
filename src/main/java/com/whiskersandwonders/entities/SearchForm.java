package com.whiskersandwonders.entities;

public class SearchForm {
    private Long zipcode = null;
    private String age;
    private String size;
    private String type;
    private int page;

    public SearchForm() {

    }

    public SearchForm(String type, String age, String size, Long zipcode, int page) {
        this.zipcode = zipcode;
        this.age = age;
        this.size = size;
        this.type = type;
        this.page = page;
    }

    public Long getZipcode() {
        return zipcode;
    }
    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
