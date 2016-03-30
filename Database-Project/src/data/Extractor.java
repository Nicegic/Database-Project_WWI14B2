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

public class Extractor {    //dient zur Umwandlung der Eingabe in die Relation/das Alphabet

    StringTokenizer st;

    public ConcurrentSkipListSet<String> buildRelation(String relationS) throws IllegalEntryException {
        ConcurrentSkipListSet<String> relation;
        relation = new ConcurrentSkipListSet();
        //Die Relation muss mindestens 2 Elemente beinhalten
        if (relationS.length() < 2) {
            throw new IllegalEntryException("Die Relation muss mindestens 2 Elemente enthalten!");
        }
        st = new StringTokenizer(relationS, ", ");
        while(st.hasMoreTokens()){
            StringBuffer s = new StringBuffer(st.nextToken());
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)==' ')
                    s.deleteCharAt(i);
            }
            relation.add(new String(s));
        }
        return relation;
    }
}
