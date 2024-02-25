/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import UI.Tester;
import java.util.HashMap;
import java.util.Map;
import utilities.FileManager;
import utilities.Inputter;

/**
 *
 * @author Kim Nha
 */
public class DispensedDrink {
    private HashMap<Drink, Integer> dispensedDrinkList = new HashMap();
    
    public DispensedDrink() {}

    public HashMap<Drink, Integer> getDispensedDrinkList() {
        return dispensedDrinkList;
    }
    
    public Drink search(String id) {
        for (Drink d: dispensedDrinkList.keySet())
            if (id.equalsIgnoreCase(d.getId()))
                return d;
        return null;
    }
    
    public void dispenseDrink(IngredientsStore store, Menu menu) {
        String drinkID = Inputter.getPatternString("Enter Drink_ID dispensing: ", "[\\w\\d]+[^_]", "The ID format does not contain special characters!");
        Drink x = menu.search(drinkID);
        if (x == null) {
            System.out.println("\nThe drink does not exist");
            return;
        }
        int quantity = Inputter.getNonNegativeInteger("Enter number of glass(es): ");
        System.out.println();
        if (store.isDrinkAvailable(x, quantity)) {
            System.out.println("Prepare successfully!");
            dispensedDrinkList.put(x, quantity);
        }
        else
            System.out.println("Prepare fail! Not enough ingredients!");
    }
    
    public void updateDispenseDrink(IngredientsStore store, Menu menu) {
        String drinkID = Inputter.getPatternString("Enter Drink_ID dispensing: ", "[\\w\\d]+[^_]", "The ID format does not contain special characters!");
        Drink x = menu.search(drinkID);
        if (x == null) {
            System.out.println("\nThe drink does not exist");
            return;
        }
        int quantity = Inputter.getNonNegativeInteger("Enter number of glass(es): ");
        System.out.println();
        if (search(drinkID) != null) {
            if (store.isDrinkAvailable(x, quantity)) {
                System.out.println("Prepare successfully!");
                dispensedDrinkList.replace(x, quantity);
            }
            else
                System.out.println("Prepare fail! Not enough ingredients!");
            return;
        }
        if (store.isDrinkAvailable(x, quantity)) {
            System.out.println("Prepare successfully!");
            dispensedDrinkList.put(x, quantity);
        }
        else
            System.out.println("Prepare fail! Not enough ingredients!");
    }
    
    public void descreaseIngredients(IngredientsStore store) {
        if (dispensedDrinkList.size() > 0)
            for (Map.Entry<Drink, Integer> e: dispensedDrinkList.entrySet())
                store.descreaseIngredients(e.getKey(), e.getValue());
    }
    
    public boolean saveToFileOrder() {
        if (dispensedDrinkList.isEmpty())
            return false;
        FileManager.saveToFileByAppend(dispensedDrinkList, "Order.dat");
        Tester.orderList.add(this);
        return true;
    }
    
    public void showProfile(int orderNumber) {
        int count = 0;
        for (Map.Entry<Drink, Integer> e: dispensedDrinkList.entrySet()) {
            count++;
            if (count == 1)
                System.out.printf("| %11d | %-35s | %8d |\n", orderNumber, e.getKey().getName(), e.getValue());
            else
                System.out.printf("| %11s | %-35s | %8d |\n", "", e.getKey().getName(), e.getValue());
        }
    }
}
