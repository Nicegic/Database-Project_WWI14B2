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
    
    //stellt die Funktion dar
    
    //das Alphabet / die Relation
    ConcurrentSkipListSet<String> attlist = new ConcurrentSkipListSet<>();
    
    //die Liste der Abhängigkeiten, die die Funktion enthält
    HashSet<Abhaengigkeit> abhlist = new HashSet();

    //wird in den Algorithmen manchmal benutzt, daher notwendig
    public Funktion() {
    }
    
    //erstellt eine Funktion mit Relation und Liste von Abhängigkeiten
    public Funktion(ConcurrentSkipListSet<String> attlist, HashSet<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        this.attlist = new ConcurrentSkipListSet();
        this.abhlist = new HashSet();
        setAlphabet(this.attlist);
        addAbhaengigkeiten(abhaengigkeiten);
    }

    //setzt 
    public void setAlphabet(ConcurrentSkipListSet<String> attlist) {
        this.attlist.clear();
        this.attlist.addAll(attlist);
    }
    
    //um eine Liste mit Abhängigkeiten zur Funktion hinzuzufügen
    public void addAbhaengigkeiten(HashSet<Abhaengigkeit> abhaengigkeiten) throws NotInAlphabetException {
        this.abhlist.clear();
        Iterator it = abhaengigkeiten.iterator();
        while(it.hasNext()){
            addAbhaengigkeit((Abhaengigkeit)it.next());
        }
    }
    
    //ersetzt die aktuelle Liste durch eine neue
    public void setAbhaengigkeiten(HashSet<Abhaengigkeit> abhaengigkeiten) {
        abhlist.clear();
        abhlist.addAll(abhaengigkeiten);
    }
    
    //fügt eine einzelne Abhängigkeit zur Liste hinzu
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
    
    //löscht die letzte Abhängigkeit wieder aus der Liste (muss nicht die letzte eingegebene sein,
    //da die Abhängigkeiten in einem HashSet gespeichert werden!!!
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
    
    //kopiert eine Funktion, sodass die Relation und alle Abhängigkeiten tiefenkopiert werden
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
