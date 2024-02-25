/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.HashMap;
import data.*;

/**
 *
 * @author Kim Nha
 */
public class DataValidation {
    public static void inputIngredientCode(Ingredient i, IngredientsStore store) {
        String code;
        do {
            // code = Inputter.getPatternString("Enter Ingredient_Code: ", "I\\d{2}", "The code format must be Ixx (x stands for digit)");
            code = Inputter.getPatternString("Enter Ingredient_Code: ", "[\\w\\d]+[^_]", "The code does not contain special characters!");
            if (store.search(code) != null)
                System.out.println("The code is duplicated! Try with another!");
        } while (store.search(code) != null);
        i.setCode(code);
    }

    public static void inputIngredientName(Ingredient i) {
        String name = Inputter.getNonBlankString("Enter Ingredient_Name: ");
        i.setName(name);
    }

    public static void inputIngredientUnit(Ingredient i) {
        String unit = Inputter.getNonBlankString("Enter unit: ");
        i.setUnit(unit);
    }

    public static void updateIngredientName(Ingredient i) {
        String name = Inputter.getString("Enter Name_Update (Leave blank if not updating): ");
        if (!name.isEmpty())
            i.setName(name);
    } 

    public static void updateIngredientQuantity(HashMap<Ingredient, Integer> map, Ingredient i) {
        String s = "a";
        int quantity = -1;
        while (!s.isEmpty() && quantity < 0) {
            s = Inputter.getString("Enter Quantity_Update (Leave blank if not updating): ");
            try {
                quantity = Integer.parseInt(s);
                if (quantity < 0) {
                    System.out.println("The input must be a non-negative integer!");
                }
            } catch (Exception e) {
                if (s.isEmpty())
                    return ;
                System.out.println("Invalid input!");
            }
        }
        map.replace(i, quantity);
    }

    public static void updateIngredientUnit(Ingredient i) {
        String unit = Inputter.getString("Enter Unit_Update (Leave blank if not updating): ");
        if (!unit.isEmpty())
            i.setUnit(unit);
    }

    public static void inputDrinkId(Drink d, Menu menu) {
        String id;
        do {
            id = Inputter.getPatternString("Enter Drink_ID: ", "[\\w\\d]+[^_]", "The ID format does not contain special characters!");
            if (menu.search(id) != null)
                System.out.println("ID is duplicated! Try with another!");
        } while (menu.search(id) != null);
        d.setId(id);
    }

    public static void inputDrinkName(Drink d) {
        String name = Inputter.getNonBlankString("Enter Drink_Name: ");
        d.setName(name);
    }

    public static void updateDrinkName(Drink d) {
        String name = Inputter.getString("Enter Drink_Name_Update (Leave blank if not updating): ");
        if (!name.isEmpty())
            d.setName(name);
    }
}
