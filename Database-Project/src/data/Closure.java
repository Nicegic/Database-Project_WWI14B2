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
        ArrayList<Abhaengigkeit> AbhaengigkeitenlisteC = F.getList();
        String X = p;
        String Xx;
        String regex;
        do {
            Xx = X;
            for (int i = 0; i < AbhaengigkeitenlisteC.size(); i++) {
                regex = ".*";
                for (int g = 0; g < AbhaengigkeitenlisteC.get(i).linkeSeite.length(); g++) {
                    regex = regex + AbhaengigkeitenlisteC.get(i).linkeSeite.charAt(g) + ".*";
                }
                if (X.matches(regex)) {
                    for (int j = 0; j < AbhaengigkeitenlisteC.get(i).rechteSeite.length(); j++) {
                        regex = ".*" + AbhaengigkeitenlisteC.get(i).rechteSeite.charAt(j) + ".*";
                        if (X.matches(regex)) {
                            continue;
                        } else {
                            X = X + AbhaengigkeitenlisteC.get(i).rechteSeite.charAt(j);
                        }
                    }
                }
            }
        } while (X.equals(Xx) == false);
        return Xx;
    }
}
