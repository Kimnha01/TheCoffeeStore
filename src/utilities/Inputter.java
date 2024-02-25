package utilities;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim Nha
 */
public class Inputter {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static int getNonNegativeInteger(String inputMsg) {
        int n = -1;
        boolean cont = true;
        do {
            try {
                n = Integer.parseInt(getNonBlankString(inputMsg));
                if (n >= 0)
                    cont = false;
                else
                    System.out.println("The input must be a positive integer!");
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (cont);
        return n;
    }
    
    public static int getIntegerInRange(String inputMsg, int min, int max) {
        int n = -1;
        boolean cont = true;
        do {
            try {
                n = Integer.parseInt(getNonBlankString(inputMsg));
                if (n >= min && n <= max)
                    cont = false;
                else
                    System.out.println("The input must be from " + min + " to " + max + "!");
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (cont);
        return n;
    }
    
    public static String getPatternString(String inputMsg, String pattern, String errorMsg) {
        String s;
        do {
            s = getNonBlankString(inputMsg);
            if (!s.matches(pattern))
                System.out.println(errorMsg);
        } while (!s.matches(pattern));
        return s;
    }

    public static String getPatternStringWithBlankPossible(String inputMsg, String pattern, String errorMsg) {
        String s;
        do {
            s = getString(inputMsg);
            if (!s.matches(pattern))
                System.out.println(errorMsg);
        } while (!s.matches(pattern) && !s.isEmpty());
        return s;
    }
    
    public static String getNonBlankString(String inputMsg) {
        String s;
        do {
            System.out.print(inputMsg);
            s = sc.nextLine().trim();
            if (s.isEmpty())
                System.out.println("This can't not be empty!");
        } while (s.isEmpty());
        return s;
    }
    
    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        String s = sc.nextLine().trim();
        return s;
    }
    
    public static boolean getYesOrNo(String inputMsg) {
        String s;
        do {
            s = getNonBlankString(inputMsg).toUpperCase();
            if (!s.matches("Y|N"))
                System.out.println("Please enter 'Y' or 'N'");
        } while (!s.matches("Y|N"));
        return s.equals("Y");
    }
}
