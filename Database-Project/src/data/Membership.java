/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Nicolas
 */
public class Membership {

    public static boolean member(Funktion F, Abhaengigkeit a) {
        return closure(F, a.links).containsAll(a.rechts);
    }

    public static HashSet<String> closure(Funktion F, Set<String> left) {
        HashSet abhlist = F.getAbhaengigkeiten();
        HashSet listx = new HashSet();
        listx.addAll(left);
        HashSet listxx = new HashSet();
        /*ArrayList alistx = new ArrayList();
        ArrayList alistxx = new ArrayList();*/
        do {
            listxx.addAll(listx);
            Iterator it = abhlist.iterator();
            while (it.hasNext()) {
                Abhaengigkeit abh = (Abhaengigkeit) it.next();
                if (listx.containsAll(abh.links)) {
                    listx.addAll(abh.rechts);
                }
            }
        } while (!listx.equals(listxx));
        return listx;
    }

    public static Funktion reducedCover(Funktion F) {
        Funktion G = F.copy();
        HashSet abhlist = new HashSet();
        abhlist.addAll(G.getAbhaengigkeiten());
        HashSet sidelist = new HashSet();
        HashSet worklist = new HashSet();
        Abhaengigkeit help;
        Iterator it = abhlist.iterator();
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            System.out.println("while 1:"+abh);
            sidelist.clear();
            sidelist.addAll(abh.links);
            worklist.clear();
            worklist.addAll(sidelist);
            Iterator iter = sidelist.iterator();
            while (iter.hasNext()) {
                String s = (String) iter.next();
                worklist.remove(s);
                if (closure(G, worklist).containsAll(abh.rechts)) {
                    abh.setLeft(worklist);
                } else {
                    worklist.add(s);
                }
            }
        }
        System.out.println("Nach Linksüberdeckung " +abhlist);
        it = abhlist.iterator();
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            sidelist.clear();
            sidelist.addAll(abh.rechts);
            worklist.clear();
            worklist.addAll(sidelist);
            Iterator iter = sidelist.iterator();
            while (iter.hasNext()) {
                help = new Abhaengigkeit(abh.links, abh.rechts);
                System.out.println("help vor"+help);
                help.setLeft(abh.links);
                help.setRight(abh.rechts);
                System.out.println("help nach"+help);
                G.removeAbh(abh, G);
                String s = (String) iter.next();
                worklist.remove(s);
                abh.setRight(worklist);
                G.addAbhaengigkeit(abh);
                System.out.println("Liste der Abhängigkeiten aus Funktion"+G.getAbhaengigkeiten());
                System.out.println(closure(G, abh.links).contains(s));
                if (!closure(G, abh.links).contains(s)) {
                    G.removeAbh(abh, G);
                    abh.setRight(help.rechts);
                    G.addAbhaengigkeit(abh);
                }
            }
        }
        System.out.println(abhlist);
        abhlist.clear();
        abhlist.addAll(G.getAbhaengigkeiten());
        it = abhlist.iterator();
        worklist.clear();
        worklist.addAll(abhlist);
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            if (abh.rechts == null || abh.rechts.isEmpty()) {
                worklist.remove(abh);
            }
        }
        G.setAbhaengigkeiten(worklist);
        abhlist.clear();
        abhlist.addAll( G.getAbhaengigkeiten());
        worklist.clear();
        worklist.addAll(abhlist);
        it = abhlist.iterator();
        Iterator iter;
        Abhaengigkeit abh1, abh2;
        int anzahl = 1;
        while (it.hasNext()) {
            abh1 = (Abhaengigkeit) it.next();
            iter = abhlist.iterator();
            for (int i = 0; i < anzahl; i++) {
                if (iter.hasNext()) {
                    iter.next();
                } else {
                    break;
                }
            }
            while (iter.hasNext()) {
                abh2 = (Abhaengigkeit) iter.next();
                if (abh2.links.equals(abh1.links)) {
                    abh1.rechts.addAll(abh2.rechts);
                    worklist.remove(abh2);
                }
            }
            anzahl++;
        }
        G.setAbhaengigkeiten(worklist);
        return G;
    }
}
