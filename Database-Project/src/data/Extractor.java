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

public class Extractor {

    ArrayList<String> relation;
    ArrayList<String> abhaengteile;
    ArrayList<Abhaengigkeit> abhaengigkeiten;
    StringTokenizer st;
    Funktion f;
    int beginning, end;
    String relationS, abhaengigkeitenS, huelle;

    public Extractor(String relation, String abhaengigkeiten, String huelle) {
        this.huelle = huelle;
        this.relationS = relation;
        this.abhaengigkeitenS = abhaengigkeiten;
        this.relation = new ArrayList<>();
        this.abhaengteile = new ArrayList<>();
        this.abhaengigkeiten = new ArrayList<>();
    }

    public void buildRelation() {
        relation = new ArrayList<>();
        abhaengteile = new ArrayList<>();
        beginning = end = 0;
        char[] c;
        char actualc;
        c = new char[relationS.length()];
        relationS.getChars(0, relationS.length(), c, 0);
        for (int i = 0; i < relationS.length(); i++) {
            actualc = c[i];
            if (actualc == '{') {
                beginning = i + 1;
            }
            if (actualc == '}') {
                end = i;
            }
        }
        relationS = relationS.substring(beginning, end);
        System.out.println(relationS);
        st = new StringTokenizer(relationS, ",");
        while (st.hasMoreTokens()) {
            relation.add(st.nextToken());
        }
        for (int i = 0; i < relation.size(); i++) {
            System.out.println(relation.get(i));
            relation.get(i);
        }
    }

    public void buildAbhaengigkeiten() {
        char[] c;
        char actualc;
        c = new char[abhaengigkeitenS.length()];
        abhaengigkeitenS.getChars(0, abhaengigkeitenS.length(), c, 0);
        for (int i = 0; i < abhaengigkeitenS.length(); i++) {
            actualc = c[i];
            if (actualc == '{') {
                beginning = i + 1;
            }
            if (actualc == '}') {
                end = i;
            }
        }
        abhaengigkeitenS = abhaengigkeitenS.substring(beginning, end);
        System.out.println(abhaengigkeitenS);
        st = new StringTokenizer(abhaengigkeitenS, ",");
        while (st.hasMoreTokens()) {
            abhaengteile.add(st.nextToken());
        }
        for (int i = 0; i < abhaengteile.size(); i++) {
            System.out.println(abhaengteile.get(i));
        }
        for (int i = 0; i < abhaengteile.size(); i++) {
            try {
                String s = abhaengteile.get(i);
                st = new StringTokenizer(s, "->");
                String links = st.nextToken();
                String rechts = st.nextToken();
                abhaengigkeiten.add(new Abhaengigkeit(links, rechts));
            } catch (NullPointerException npe) {
                System.out.println("Fehler, keine vollstÃ¤ndige AbhÃ¤ngigkeit vorhanden!");
            }
        }
        for (int i = 0; i < abhaengigkeiten.size(); i++) {
            System.out.println(abhaengigkeiten.get(i));
        }
    }

    public void buildFunction() {
        f = new Funktion(relation, abhaengigkeiten);
    }

    public String doClosure() {
        return Closure.closure(f, huelle);
    }
}
