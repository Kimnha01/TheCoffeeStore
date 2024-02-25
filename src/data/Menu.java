/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Collections;
import utilities.DataValidation;
import utilities.Inputter;

/**
 *
 * @author Kim Nha
 */
public class Menu extends ArrayList<Drink> {
    public Menu() {
        super();
    }

    public Drink search(String id) {
        for (Drink x: this)
            if (id.equalsIgnoreCase(x.getId()))
                return x;
        return null;
    }

    public boolean addDrink() {
        boolean choice = true;
        while (choice) {
            Drink d = new Drink();
            DataValidation.inputDrinkId(d, this);
            DataValidation.inputDrinkName(d);
            System.out.println("\nPlease input Ingredients for \"" + d.getName() + "\":");
            d.addIngredients();
            add(d);
            System.out.println("New drink has been added successfully!\n");
            choice = Inputter.getYesOrNo("Do you want to continuously add new Drink? (Y/N): ");
            System.out.println();
        }
        return true;
    }

    public void updateDrink() {
        if (isEmpty()) {
            System.out.println("The Drink list is empty! Nothing to update!");
            return;
        }
        String id = Inputter.getPatternString("Enter Drink_ID you want to update: ", "[\\w\\d]+[^_]", "The ID format does not contain special characters!");
        System.out.println();
        Drink x = search(id);
        if (x == null) {
            System.out.println("The drink does not exist!");
            return;
        }
        DataValidation.updateDrinkName(x);
        x.updateIngredient();
        System.out.println("\nUpdate Drink successfully!");
        System.out.println("\nHere is the drink after updating:");
        System.out.println();
        x.showProfile();
    }

    public void deleteDrink() {
        if (isEmpty()) {
            System.out.println("The Drink list is empty! Nothing to delete!");
            return;
        }
        String id = Inputter.getPatternString("Enter Drink_ID you want to delete: ", "[\\w\\d]+[^_]", "The ID format does not contain special characters!");
        Drink x = search(id);
        System.out.println();
        if (x == null) {
            System.out.println("The drink does not exist!\n");
            return;
        }
        System.out.println("Found!\n");
        boolean choice = Inputter.getYesOrNo("Do you ready to delete this drink? (Y/N): ");
        System.out.println();
        if (choice) {
            remove(x);
            System.out.println("Delete Drink successfullty!");
        }
        else
            System.out.println("Delete fail!");
    }
    
    public void printList(ArrayList<Drink> list, String msg, String emptyMsg) {
        if (list.isEmpty()) {
            System.out.println(emptyMsg);
            return;
        }
        System.out.println(msg + "\n");
        System.out.println(getHeading());
        String line = "";
        for (int i = 1; i <= getHeading().length(); i++)
            line += "-";
        System.out.println(line);
        for (Drink x: list) {
            System.out.println(x);
            System.out.println(line);
        }
        System.out.println("Total: " + list.size() + " drink(s)");
    }
    
    public void showMenu() {
        Collections.sort(this);
        printList(this, "The list of beverage recipes is:", "The Drink list is empty! Nothing to show!");
    }
    
    public String getHeading() {
        return String.format("| %-8s | %-35s| %-20s| %-15s|", "Drink_ID", "Drink_Name", "Ingredients", "Quantity");
    }
}
