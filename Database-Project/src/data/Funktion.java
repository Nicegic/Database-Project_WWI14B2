/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Nicolas
 */
import java.util.*;

public class Funktion {

    ArrayList<String> Attributliste = new ArrayList<String>();
    ArrayList<Abhaengigkeit> Abhaengigkeitenliste = new ArrayList<Abhaengigkeit>();
    String attribut;
    StringBuffer alphabet = new StringBuffer();

    public Funktion(ArrayList<String> alphabet, ArrayList<Abhaengigkeit> abhaengigkeiten) {
        for (int i = 0; i < alphabet.size(); i++) {
            this.alphabet.append(alphabet.get(i));
        }
        addAbhaengigkeiten(abhaengigkeiten);
    }

    public void addAbhaengigkeiten(ArrayList<Abhaengigkeit> abhaengigkeiten) {
        boolean left;
        boolean right;
        String alphabetS = this.alphabet.toString();
        for (int i = 0; i < abhaengigkeiten.size(); i++) {
            right = left = true;
            Abhaengigkeit ab = abhaengigkeiten.get(i);
            for (int j = 0; j < ab.linkeSeite.length(); j++) {
                if (!alphabetS.contains("" + ab.linkeSeite.charAt(j))) {
                    left = false;
                }
            }
            for (int j = 0; j < ab.rechteSeite.length(); j++) {
                if (!alphabetS.contains("" + ab.rechteSeite.charAt(j))) {
                    right = false;
                }
            }
            if (!left) {
                System.out.println("Linke Seite der AbhÃ¤ngigkeit nicht in der Relation vorhanden!");
            } else if (!right) {
                System.out.println("Rechte Seite der AbhÃ¤ngigkeit nicht in der Relation vorhanden!");
            } else {
                Abhaengigkeitenliste.add(ab);
            }
        }
        for (int i = 0; i < Abhaengigkeitenliste.size(); i++) {
            System.out.println(Abhaengigkeitenliste.get(i));
        }
    }

    public ArrayList<Abhaengigkeit> getList() {
        return Abhaengigkeitenliste;
    }

    public int getAttSize() {
        return Attributliste.size();
    }

    public int getAbhSize() {
        return Abhaengigkeitenliste.size();
    }

    public Abhaengigkeit getAbhaengigkeit(int i) {
        return Abhaengigkeitenliste.get(i);
    }
}
