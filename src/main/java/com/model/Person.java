package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private int id;
    private String isim;
    private String soyisim;

    public Person() {
    }

}