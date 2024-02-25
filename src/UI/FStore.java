/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import data.*;
import java.util.ArrayList;
import java.util.Map;
import utilities.FileManager;

/**
 *
 * @author Kim Nha
 */
public class FStore {
    public void mainProcess(IngredientsStore store, Menu menu) {
        FileManager.loadFromFile(store, "Ingredients.dat");
        FileManager.loadFromFile(menu, "Menu.dat");
        String[] opts = {"Manage ingredients",
                        "Manage beverage recipes",
                        "Dispensing beverages",
                        "Report"};
        int choice;
        do {
            choice = MenuManager.getChoice(opts, "MAIN MENU");
            System.out.println();
            switch(choice) {
                case 1:
                    processChoice1(store);
                    break;
                case 2:
                    processChoice2(menu);
                    break;
                case 3:
                    processChoice3(menu, store);
                    break;
                case 4:
                    processChoice4(menu, store);
                    break;
                default:
                    System.out.println("Quit! See you next time!");
            }
            // store.saveToFile("Ingredients.dat");
            FileManager.saveToFile(store, "Ingredients.dat");
            FileManager.saveToFile(menu, "Menu.dat");
            System.out.println();
        } while (choice >= 1 && choice <= 4);
    }

    public void processChoice1(IngredientsStore store) {
        String[] opts = {"Add an ingredient",
                        "Update ingredient information",
                        "Delete ingredient",
                        "Show all ingredients (Ascending by name)"};
        int choice;
        do {
            choice = MenuManager.getChoice(opts, "Manage Ingredients");
            System.out.println();
            switch(choice) {
                case 1:
                    store.addIngredient();
                    break;
                case 2:
                    store.update();
                    break;
                case 3:
                    store.delete();
                    break;
                case 4:
                    store.showAllIngredients();
                    break;
                default:
                    System.out.println("Quit to Main Menu!");
            }
            FileManager.saveToFile(store, "Ingredients.dat");
            System.out.println();
        } while (choice >=1 && choice <= 4);
    }

    public void processChoice2(Menu menu) {
        String[] opts = {"Add the drink to menu",
                        "Update drink information",
                        "Delete drink from menu",
                        "Show menu"};
        int choice;
        do {
            choice = MenuManager.getChoice(opts, "Manage Recipes");
            System.out.println();
            switch(choice) {
                case 1:
                    menu.addDrink();
                    break;
                case 2:
                    menu.updateDrink();
                    break;
                case 3:
                    menu.deleteDrink();
                    break;
                case 4:
                    menu.showMenu();
                    break;
                default:
                    System.out.println("Quit to Main Menu!");
            }
            System.out.println();
            FileManager.saveToFile(menu, "Menu.dat");
        } while (choice >= 1 && choice <= 4);
    }

    public void processChoice3(Menu menu, IngredientsStore store) {
        String[] opts = {"Dispensing the drink", 
                        "Update the dispensing drink"};
        int choice;
        DispensedDrink dispensedDrinkList = new DispensedDrink();
        do {
            choice = MenuManager.getChoice(opts, "Dispensing beverages");
            System.out.println();
            switch(choice) {
                case 1:
                    dispensedDrinkList.dispenseDrink(store, menu);
                    break;
                case 2:
                    dispensedDrinkList.updateDispenseDrink(store, menu);
                    break;
                default:
                    System.out.println("Quit to Main Menu!");
            }
            System.out.println();
        } while (choice >= 1 && choice <= 2);
        dispensedDrinkList.descreaseIngredients(store);
        dispensedDrinkList.saveToFileOrder(); 
    }
    
    public void processChoice4(Menu menu, IngredientsStore store) {
        String[] opts = {"The ingredients are available",
                        "The drinks which are out of ingredients",
                        "Show all the dispensing drink"};
        int choice;
        do {
            choice = MenuManager.getChoice(opts, "End-Day Report");
            System.out.println();
            switch(choice) {
                case 1:
                    ArrayList<Ingredient> ingredientList = new ArrayList();
                    for (Map.Entry<Ingredient, Integer> e: store.entrySet())
                        if (e.getValue() > 0)
                            ingredientList.add(e.getKey());
                    store.printList(ingredientList, "Here are available ingredients:", "No ingredient is available!");
                    break;
                case 2:
                    ArrayList<Drink> drinkList = new ArrayList();
                    for (Drink x: menu)
                        if (!store.isDrinkAvailable(x, 1))
                            drinkList.add(x);
                    menu.printList(drinkList, "Here are drinks which are out of ingredients:", "No drinks is out of ingredients!");
                    break;
                case 3:
                    if (Tester.orderList.isEmpty())
                        System.out.println("No drink is dispensed yet!");
                    else {
                        String heading = String.format("| %11s | %-35s | %-8s |", "OrderNumber", "DrinkName", "Quantity");
                        String line = "";
                        for (int i = 1; i <= heading.length(); i++)
                            line += "-";
                        System.out.println(heading);
                        System.out.println(line);
                        for (DispensedDrink x: Tester.orderList) {
                            x.showProfile(Tester.orderList.indexOf(x) + 1);
                            System.out.println(line);
                        }
                        System.out.println("Total: " + Tester.orderList.size() + " order(s)");
                    }
                    break;
                default:
                    System.out.println("Quit to Main Menu!");
            }
            System.out.println();
        } while (choice >= 1 && choice <= 3);
    }
}

