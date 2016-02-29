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
    StringBuffer alphabetsb = new StringBuffer();
    String alphabet;

    public Funktion() {
    }

    public Funktion(ArrayList<String> attlist, ArrayList<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        this.attlist = attlist;
        setAlphabet(this.attlist);
        addAbhaengigkeiten(abhaengigkeiten);
    }

    public void setAlphabet(ArrayList<String> attlist) {
        alphabetsb = new StringBuffer();
        for (int i = 0; i < attlist.size(); i++) {
            alphabetsb.append(attlist.get(i));
        }
        alphabet = new String(alphabetsb);
    }

    public void addAbhaengigkeiten(ArrayList<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        for (int i = 0; i < abhaengigkeiten.size(); i++) {
            addAbhaengigkeit(abhaengigkeiten.get(i));
        }
    }

    public void addAbhaengigkeit(Abhaengigkeit abh) throws NotInAlphabetException {
        boolean left;
        boolean right;
        right = left = true;
        for (int j = 0; j < abh.linkeSeite.length(); j++) {
            if (!alphabet.contains("" + abh.linkeSeite.charAt(j))) {
                left = false;
            }
        }
        for (int j = 0; j < abh.rechteSeite.length(); j++) {
            if (!alphabet.contains("" + abh.rechteSeite.charAt(j))) {
                right = false;
            }
        }
        if (!left) {
            throw new NotInAlphabetException("Linke Seite der AbhÃ¤ngigkeit nicht in der Relation vorhanden!");
        } else if (!right) {
            throw new NotInAlphabetException("Rechte Seite der AbhÃ¤ngigkeit nicht in der Relation vorhanden!");
        } else {
            abhlist.add(abh);
        }
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

    public void removeAbhaengigkeit() {
        if (abhlist.size() > 0) {
            abhlist.remove(abhlist.size() - 1);
        }
    }

    public boolean keineAbhaengigkeiten() {
        return abhlist.isEmpty();
    }

    public Funktion copy() {
        ArrayList<Abhaengigkeit> vabhlist = new ArrayList();
        ArrayList<String> vattlist = new ArrayList();
        Funktion f = null;
        for (int i = 0; i < attlist.size(); i++) {
            vattlist.add(attlist.get(i));
        }
        for (int i = 0; i < abhlist.size(); i++) {
            vabhlist.add(abhlist.get(i));
        }
        try {
            f = new Funktion(vattlist, vabhlist);
            return f;
        } catch (NotInAlphabetException niae) {
            return null;
        }
    }

    public ArrayList<Abhaengigkeit> getAbhaengigkeiten() {
        return abhlist;
    }
}
