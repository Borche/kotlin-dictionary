package com.ab.ploy.models;


import com.faunadb.client.types.FaunaConstructor;
import com.faunadb.client.types.FaunaField;

public class Beer {
    @FaunaField
    private String name;
    @FaunaField
    private String alcohol;

    @FaunaConstructor
    public Beer(@FaunaField String name, @FaunaField String alcohol) {
        this.name = name;
        this.alcohol = alcohol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }
}
