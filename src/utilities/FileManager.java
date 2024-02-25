/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kim Nha
 */
public class FileManager {
    public static <T> boolean loadFromFile(HashMap<T, Integer> hashmap, String fileName) {
        hashmap.clear();
        File f = new File(fileName);
        if (!f.exists())
            return false;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    T key = (T)ois.readObject();
                    Integer value = (Integer)ois.readObject();
                    hashmap.put(key, value);
                } catch (Exception e) {
                    break;
                }
            }
            ois.close();
            fis.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static <T> boolean saveToFile(HashMap<T, Integer> hashmap, String fileName) {
        File f = new File(fileName);
        if (!f.exists())
            return false;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Map.Entry<T, Integer> e: hashmap.entrySet()) {
                oos.writeObject(e.getKey());
                oos.writeObject(e.getValue());
            }
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static <T> boolean saveToFileByAppend(HashMap<T, Integer> hashmap, String fileName) {
        File f = new File(fileName);
        if (!f.exists())
            return false;
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Map.Entry<T, Integer> e: hashmap.entrySet()) {
                oos.writeObject(e.getKey());
                oos.writeObject(e.getValue());
            }
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static <T> boolean loadFromFile(List<T> list, String fileName) {
        list.clear();
        File f = new File(fileName);
        if (!f.exists())
            return false;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    T obj = (T)ois.readObject();
                    list.add(obj);
                } catch (Exception e) {
                    break;
                }
            }
            ois.close();
            fis.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static <T> boolean saveToFile(List<T> list, String fileName) {
        File f = new File(fileName);
        if (!f.exists())
            return false;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (T obj: list)
                oos.writeObject(obj);
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
