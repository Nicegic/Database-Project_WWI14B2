/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author Nicolas
 */
public class Abhaengigkeit {
    //Die Klasse zur Darstellung einer Abhängigkeit

    ConcurrentSkipListSet<String> links;    //linke Seite (kann aus mehreren Alphabetteilen bestehen)
    ConcurrentSkipListSet<String> rechts;   //rechte Seite (kann auch aus mehreren Alphabetteilen bestehen)

    public Abhaengigkeit(String links, ConcurrentSkipListSet<String> alphabet, Object o) throws NotInAlphabetException {
        //dient zur Erzeugung einer Abhängigkeit für den Closure-Algorithmus
        this.links = new ConcurrentSkipListSet();
        this.rechts = new ConcurrentSkipListSet();
        StringBuffer sb = new StringBuffer(links);
        validate(sb, alphabet, this.links);
    }

    public Abhaengigkeit(ConcurrentSkipListSet<String> links, ConcurrentSkipListSet<String> rechts) {
        //dient zur Erzeugung von Abhängigkeiten während der Algorithmen, wo keine Validierung mehr
        //stattfinden muss
        this.links = new ConcurrentSkipListSet();
        this.links.addAll(links);
        this.rechts = new ConcurrentSkipListSet();
        this.rechts.addAll(rechts);
    }

    public Abhaengigkeit(String links, String rechts, ConcurrentSkipListSet<String> alphabet) throws NotInAlphabetException {
        //dient zur Erzeugung von eingegebenen Abhängigkeiten und zur Neuvalidierung.
        //hierbei wird überprüft, ob die Seite nur aus Teilen des Alphabets besteht
        //wenn dies nicht der Fall ist, wird eine NotInAlphabetException geworfen
        this.links = new ConcurrentSkipListSet();
        this.rechts = new ConcurrentSkipListSet();
        StringBuffer sb = new StringBuffer(links);
        validate(sb, alphabet, this.links);
        sb = new StringBuffer(rechts);
        validate(sb, alphabet, this.rechts);
    }

    public ConcurrentSkipListSet<String> getLeft() {
        return links;
    }

    public ConcurrentSkipListSet<String> getRight() {
        return rechts;
    }

    public void setLeft(HashSet<String> leftnew) {
        links.clear();
        links.addAll(leftnew);
    }

    public void setLeft(Set<String> leftnew) {
        links.clear();
        links.addAll(leftnew);
    }

    public void setRight(Set<String> rightnew) {
        rechts.clear();
        rechts.addAll(rightnew);
    }

    @Override
    public String toString() {
        //manuelle Ausgabe der Abhängigkeit in der Form "a --> b"
        StringBuffer wert = new StringBuffer();
        Iterator i = links.iterator();
        while (i.hasNext()) {
            wert.append(i.next());
        }
        wert.append(" --> ");
        i = rechts.iterator();
        while (i.hasNext()) {
            wert.append(i.next());
        }
        return new String(wert);
    }

    private void validate(StringBuffer sb, ConcurrentSkipListSet<String> attlist, ConcurrentSkipListSet<String> side) throws NotInAlphabetException {
        //Methode zum Validieren der Seite der Abhängigkeit, ob diese valide bezüglich des Alphabets ist
        int i = sb.length();
        int j = 0;
        while (j < i) {
            if (i < j) {
                throw new NotInAlphabetException("Die eingetragene Abhängigkeit ist ungültig!");
            }
            String part;
            part = sb.substring(j, i);
            if (!attlist.contains(part)) {
                i--;
            } else {
                side.add(part);
                j = i;
                i = sb.length();
            }
        }
    }

    public String getLeftString() {
        //Ausgabe der linken Seite
        StringBuffer seitenwert = new StringBuffer();
        Iterator it = links.iterator();
        while (it.hasNext()) {
            seitenwert.append(it.next());
        }
        return seitenwert.toString();
    }

    public String getRightString() {
        //Ausgabe der rechten Seite
        StringBuffer seitenwert = new StringBuffer();
        Iterator it = rechts.iterator();
        while (it.hasNext()) {
            seitenwert.append(it.next());
        }
        return seitenwert.toString();
    }
}
