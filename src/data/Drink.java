/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import UI.Tester;
import utilities.DataValidation;
import utilities.Inputter;

/**
 *
 * @author Kim Nha
 */
public class Drink implements Serializable, Comparable {
    private String id, name;
    private HashMap<Ingredient, Integer> ingredientlist = new HashMap();
    
    public Drink() {}

    public Drink(String id, String name) {
        this.id = id;
        this.name = name;
        this.ingredientlist = new HashMap();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Ingredient, Integer> getIngredientlist() {
        return ingredientlist;
    }

    @Override
    public String toString() {
        String result = "";
        int count = 0;
        for (Map.Entry<Ingredient, Integer> x: ingredientlist.entrySet()) {
            if (x.getKey().getName() == null)
                x.getKey().setName(x.getKey().getCode());
            count++;
            if (count == 1 && ingredientlist.size() == 1) {
                result += String.format("| %-8s | %-35s| %-20s| %-15s|", id, name, x.getKey().getName(), x.getValue() + " " + x.getKey().getUnit());
                return result;
            }
                
            if (count == 1) 
                result += String.format("| %-8s | %-35s| %-20s| %-15s|\n", id, name, x.getKey().getName(), x.getValue() + " " + x.getKey().getUnit());
            else if (count == ingredientlist.size())
                result += String.format("| %-8s | %-35s| %-20s| %-15s|", "", "", x.getKey().getName(), x.getValue() + " " + x.getKey().getUnit());
            else
                result += String.format("| %-8s | %-35s| %-20s| %-15s|\n", "", "", x.getKey().getName(), x.getValue() + " " + x.getKey().getUnit());
        }
        return result;
    }

    public Ingredient searchIngredient(String code) {
        for (Map.Entry<Ingredient, Integer> e: ingredientlist.entrySet())
            if (code.equalsIgnoreCase(e.getKey().getCode()))
                return e.getKey();
        return null;
    }

    public boolean addIngredients() {
        boolean choice = true;
        while (choice) {
            String code;
            do {
                code = Inputter.getPatternString("Enter Ingredient_Code: ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
                if (searchIngredient(code) != null)
                    System.out.println("The code has already existed! Try with another!");
            } while (searchIngredient(code) != null);
            int quantity = Inputter.getNonNegativeInteger("Enter quantity: ");
            Ingredient x = Tester.store.search(code);
            if (x != null) {
                DataValidation.inputIngredientUnit(x);
                ingredientlist.put(x, quantity);
            }
            else {
                Ingredient i = new Ingredient();
                i.setCode(code);
                DataValidation.inputIngredientUnit(i);
                ingredientlist.put(i, quantity);
            }
            System.out.println();
            choice = Inputter.getYesOrNo("Do you want to continuously add ingredient? (Y/N): ");
            System.out.println();
        }
        return true;
    }

    public void updateIngredient() {
        if (ingredientlist.isEmpty()) {
            System.out.println("You have not added any ingredients into this recipe!");
            return;
        }
        String code = Inputter.getPatternStringWithBlankPossible("Enter Ingredient_Code you want to update (Leave blank if not updating ingredients): ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
        System.out.println();
        if (code.isEmpty())
            return;
        Ingredient x = searchIngredient(code);
        if (x == null)
            System.out.println("The ingredient does not exist in this recipe!");
        else {
            int newQuantity = Inputter.getNonNegativeInteger("Enter Quantity_update: ");
            ingredientlist.replace(x, newQuantity);
        }
    }

    public boolean removeIngredient() {
        if (ingredientlist.isEmpty()) {
            System.out.println("You have not added any ingredients into this recipe!");
            return false;
        }
        String code = Inputter.getPatternString("Enter Ingredient_Code you want to remove: ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
        Ingredient x = searchIngredient(code);
        if (x == null)
            System.out.println("The ingredient does not exist in this recipe!");
        else {
            ingredientlist.remove(x);
            System.out.println("Remove successfully!");
        }
        return true;
    }

    public void showProfile() {
        String heading = String.format("| %-8s | %-35s| %-20s| %-15s|", "Drink_ID", "Drink_Name", "Ingredients", "Quantity");
        System.out.println(heading);
        for (int i = 1; i <= heading.length(); i++)
            System.out.print("-");
        System.out.println();
        System.out.println(this);
    }

    @Override
    public int compareTo(Object o) {
        Drink other = (Drink)o;
        return name.compareToIgnoreCase(other.name);
    }
}
