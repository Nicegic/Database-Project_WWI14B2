/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.*;

/**
 *
 * @author Nicolas
 */
public class Closure {

    public static String closure(Funktion F, String p) {
        ArrayList<Abhaengigkeit> abhlist = F.getList();
        String X = p;
        String Xx;
        String regex;
        do {
            Xx = X;
            for (int i = 0; i < abhlist.size(); i++) {
                regex = ".*";
                for (int g = 0; g < abhlist.get(i).linkeSeite.length(); g++) {
                    regex = regex + abhlist.get(i).linkeSeite.charAt(g) + ".*";
                }
                if (X.matches(regex)) {
                    for (int j = 0; j < abhlist.get(i).rechteSeite.length(); j++) {
                        regex = ".*" + abhlist.get(i).rechteSeite.charAt(j) + ".*";
                        if (X.matches(regex)) {
                            continue;
                        } else {
                            X = X + abhlist.get(i).rechteSeite.charAt(j);
                        }
                    }
                }
            }
        } while (X.equals(Xx) == false);
        return Xx;
    }
}
