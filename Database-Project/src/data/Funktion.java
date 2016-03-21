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
import java.util.concurrent.ConcurrentSkipListSet;

public class Funktion {

    ConcurrentSkipListSet<String> attlist = new ConcurrentSkipListSet<>();
    HashSet<Abhaengigkeit> abhlist = new HashSet();
    String attribut;

    public Funktion() {
    }

    public Funktion(ConcurrentSkipListSet<String> attlist, HashSet<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        this.attlist = attlist;
        setAlphabet(this.attlist);
        addAbhaengigkeiten(abhaengigkeiten);
    }

    public void setAlphabet(ConcurrentSkipListSet<String> attlist) {
        this.attlist = attlist;
    }

    public void addAbhaengigkeiten(HashSet<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        Iterator it = abhaengigkeiten.iterator();
        while(it.hasNext()){
            addAbhaengigkeit((Abhaengigkeit)it.next());
        }
    }

    public void setAbhaengigkeiten(HashSet<Abhaengigkeit> abhaengigkeiten) {
        abhlist.clear();
        abhlist.addAll(abhaengigkeiten);
    }

    public void addAbhaengigkeit(Abhaengigkeit abh) {
        abhlist.add(abh);
    }
    
    public ConcurrentSkipListSet<String> getAlphabet(){
        return attlist;
    }

    public int getAttSize() {
        return attlist.size();
    }

    public int getAbhSize() {
        return abhlist.size();
    }

    public Abhaengigkeit getAbhaengigkeit(int i) {
        int k = 0;
        Iterator it = abhlist.iterator();
        while (k < i) {
            it.next();
            k++;
        }
        return (Abhaengigkeit) it.next();
    }

    public void removeAbh(Abhaengigkeit abh, Funktion f) {
        abhlist.remove(abh);
    }

    public void removeAbhaengigkeit() {
        Abhaengigkeit abh = null;
        Iterator it = abhlist.iterator();
        while (it.hasNext()) {
            abh = (Abhaengigkeit) it.next();
        }
        if (abh != null) {
            abhlist.remove(abh);
        }
    }

    public boolean keineAbhaengigkeiten() {
        return abhlist.isEmpty();
    }

    public Funktion copy() {
        HashSet<Abhaengigkeit> vabhlist = new HashSet();
        ConcurrentSkipListSet<String> vattlist = new ConcurrentSkipListSet();
        Funktion f = null;
        vabhlist.addAll(abhlist);
        vattlist.addAll(attlist);
        try {
            f = new Funktion(vattlist, vabhlist);
            return f;
        } catch (NotInAlphabetException niae) {
            return null;
        }
    }

    public HashSet<Abhaengigkeit> getAbhaengigkeiten() {
        return abhlist;
    }
}
