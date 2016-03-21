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

    ConcurrentSkipListSet<String> links;
    ConcurrentSkipListSet<String> rechts;

    public Abhaengigkeit(String links, ConcurrentSkipListSet<String> alphabet, Object o) throws NotInAlphabetException {
        this.links = new ConcurrentSkipListSet();
        this.rechts = new ConcurrentSkipListSet();
        StringBuffer sb = new StringBuffer(links);
        validate(sb, alphabet, this.links);
    }

    public Abhaengigkeit(ConcurrentSkipListSet<String> links, ConcurrentSkipListSet<String> rechts) {
        this.links = new ConcurrentSkipListSet();
        this.links.addAll(links);
        this.rechts = new ConcurrentSkipListSet();
        this.rechts.addAll(rechts);
    }

    public Abhaengigkeit(String links, String rechts, ConcurrentSkipListSet<String> alphabet) throws NotInAlphabetException {
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
        StringBuffer seitenwert = new StringBuffer();
        Iterator it = links.iterator();
        while (it.hasNext()) {
            seitenwert.append(it.next());
        }
        return seitenwert.toString();
    }

    public String getRightString() {
        StringBuffer seitenwert = new StringBuffer();
        Iterator it = rechts.iterator();
        while (it.hasNext()) {
            seitenwert.append(it.next());
        }
        return seitenwert.toString();
    }
}
