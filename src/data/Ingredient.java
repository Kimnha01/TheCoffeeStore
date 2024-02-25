/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.HashMap;
import utilities.DataValidation;

/**
 *
 * @author Kim Nha
 */
public class Ingredient implements Serializable, Comparable {
    private String code, name, unit;
    
    public Ingredient() {}

    public Ingredient(String code, String name, String unit) {
        this.code = code;
        this.name = name;
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return code + "-" + name + "-" + "-" + unit;
    }
    
    public void showProfile(HashMap<Ingredient, Integer> map) {
        System.out.printf("| %-8s | %-20s| %8d | %-5s|\n", code, name, map.get(this), unit);
    }
    
    public void input(IngredientsStore store) {
        DataValidation.inputIngredientCode(this, store);
        DataValidation.inputIngredientName(this);
        DataValidation.inputIngredientUnit(this);
    }

    @Override
    public int compareTo(Object o) {
        Ingredient x = (Ingredient)o;
        return this.getName().compareToIgnoreCase(x.getName());
    }
}
