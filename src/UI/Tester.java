/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import data.DispensedDrink;
import data.IngredientsStore;
import data.Menu;
import java.util.ArrayList;

/**
 *
 * @author Kim Nha
 */
public class Tester {
    public static IngredientsStore store = new IngredientsStore();
    public static ArrayList<DispensedDrink> orderList = new ArrayList();
    public static void main(String[] args) {
        Menu menu = new Menu();
        FStore f = new FStore();
        f.mainProcess(store, menu);
    }
}
