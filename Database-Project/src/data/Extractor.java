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
import data.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Extractor {

    String huelle;
    StringTokenizer st;

    public void setHuelle(String huelle) {
        this.huelle = huelle;
    }

    public ConcurrentSkipListSet<String> buildRelation(String relationS) throws IllegalEntryException {
        ConcurrentSkipListSet<String> relation;
        relation = new ConcurrentSkipListSet();
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
