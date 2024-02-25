/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import utilities.DataValidation;
import utilities.Inputter;

/**
 *
 * @author Kim Nha
 */
public class IngredientsStore extends HashMap<Ingredient, Integer> {
    public IngredientsStore() {
        super();
    }
    
    public ArrayList<Ingredient> toList() {
        ArrayList<Ingredient> list = new ArrayList();
        for (Entry<Ingredient, Integer> e: entrySet())
            list.add(e.getKey());
        return list;
    }

    public Ingredient search(String code) {
        for (Entry<Ingredient, Integer> e: entrySet())
            if (code.equalsIgnoreCase(e.getKey().getCode()))
                return e.getKey();
        return null;
    }
    
    public boolean addIngredient() {
        boolean choice = true;
        while (choice) {
            Ingredient ing = new Ingredient();
            ing.input(this);
            int quantity = Inputter.getNonNegativeInteger("Enter quantity in store: ");
            put(ing, quantity);
            System.out.println("\nNew ingredient has been added successfully!\n");
            choice = Inputter.getYesOrNo("Do you want to continuously add new ingredient? (Y/N): ");
            System.out.println();
        }
        return true;
    }

    public void update() {
        if (isEmpty()) {
            System.out.println("The ingredients store is empty, nothing to update!");
            return;
        }
        String code = Inputter.getPatternString("Enter Ingredient_Code you want to update: ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
        System.out.println();
        Ingredient x = search(code);
        if (x == null)
            System.out.println("The ingredient does not exist!");
        else {
            System.out.println("Found!\n");
            DataValidation.updateIngredientName(x);
            DataValidation.updateIngredientQuantity(this, x);
            DataValidation.updateIngredientUnit(x);
            System.out.println("\nUpdate successfully!");
            System.out.println("\nThe ingredient after updating is:\n");
            System.out.println(getHeading());
            x.showProfile(this);
        }
    }

    public void delete() {
        if (isEmpty()) {
            System.out.println("The ingredients store is empty, nothing to delete!");
            return;
        }
        String code = Inputter.getPatternString("Enter Ingredient_Code you want to delete: ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
        System.out.println();
        Ingredient x = search(code);
        if (x == null) {
            System.out.println("The ingredient does not exist!");
            return;
        }
        System.out.println("Found!\n");
        boolean choice = Inputter.getYesOrNo("Do you ready to delete this ingredient? (Y/N): ");
        if (choice == false)
            System.out.println("\nDelete fail!");
        else {
            remove(x);
            System.out.println("\nDelete successfully!");
        }
    }
    
    public void printList(ArrayList<Ingredient> list, String msg, String emptyMsg) {
        if (list.isEmpty()) {
            System.out.println(emptyMsg);
            return;
        }
        System.out.println(msg + "\n");
        System.out.println(getHeading());
        for (int i = 1; i <= getHeading().length(); i++)
            System.out.print("-");
        System.out.println();
        for (Ingredient x: list)
            x.showProfile(this);
        for (int i = 1; i <= getHeading().length(); i++)
            System.out.print("-");
        System.out.println();
        System.out.println("Total: " + list.size() + " ingredient(s)");
    }
    
    public void showAllIngredients() {
        ArrayList<Ingredient> list = toList();
        Collections.sort(list);
        printList(list, "The list of ingredients in store is:", "The ingredients store is empty, nothing to show!");
    }
    
    public String getHeading() {
        return String.format("| %-8s | %-20s| %-8s | %-5s|", "CODE", "NAME", "QUANTITY", "UNIT");
    }

    public boolean isDrinkAvailable(Drink d, int quantity) {
        for (Map.Entry<Ingredient, Integer> e: d.getIngredientlist().entrySet()) 
            if (search(e.getKey().getCode()) == null || e.getValue() * quantity > this.get(search(e.getKey().getCode())))
        // for (Map.Entry<Ingredient, Integer> e: d.getIngredientlist().entrySet()) 
        //     if (this.get(e.getKey()) == null || e.getValue() * quantity > this.get(e.getKey()))
                return false;
        return true;
    }

    public boolean descreaseIngredients(Drink d, int quantity) {
        if (isDrinkAvailable(d, quantity)) {
            for (Map.Entry<Ingredient, Integer> e: d.getIngredientlist().entrySet()) 
                replace(search(e.getKey().getCode()), this.get(search(e.getKey().getCode())) - e.getValue() * quantity);
        }
        return true;
    }
}
