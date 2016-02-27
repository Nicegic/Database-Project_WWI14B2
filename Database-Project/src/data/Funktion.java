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

    ArrayList<String> attlist = new ArrayList<String>();
    ArrayList<Abhaengigkeit> abhlist = new ArrayList<Abhaengigkeit>();
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
                abhlist.add(ab);
            }
        }
        for (int i = 0; i < abhlist.size(); i++) {
            System.out.println(abhlist.get(i));
        }
    }

    public void addAbhaengigkeit(Abhaengigkeit abh) {
        abhlist.add(abh);
    }

    public ArrayList<Abhaengigkeit> getList() {
        return abhlist;
    }

    public int getAttSize() {
        return attlist.size();
    }

    public int getAbhSize() {
        return abhlist.size();
    }

    public Abhaengigkeit getAbhaengigkeit(int i) {
        return abhlist.get(i);
    }

    public Abhaengigkeit removeAbh(Abhaengigkeit abh, Funktion f) {
        Abhaengigkeit aah = null;
        for (int i = 0; i < f.getAbhSize(); i++) {
            if (abh.equals(f.getAbhaengigkeit(i))) {
                aah = f.getAbhaengigkeit(i);
                abhlist.remove(abh);
            }
        }
        return aah;
    }

    public Funktion copy() {
        ArrayList<Abhaengigkeit> vabhlist=new ArrayList();
        ArrayList<String> vattlist = new ArrayList();
        for(int i=0;i<attlist.size();i++){
            vattlist.add(attlist.get(i));
        }
        for(int i=0;i<abhlist.size();i++){
            vabhlist.add(abhlist.get(i));
        }
        Funktion f=new Funktion(vattlist,vabhlist);
        return f;
    }
}
