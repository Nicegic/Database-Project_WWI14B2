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

    int beginning, end;
    String huelle;
    StringTokenizer st;

    public void setHuelle(String huelle) {
        this.huelle = huelle;
    }

    public ArrayList<String> buildRelation(String relationS) throws IllegalEntryException {
        ArrayList<String> relation;
        boolean alreadybegun = false;
        relation = new ArrayList<>();
        beginning = end = 0;
        char[] cc;
        char actualc;
        cc = new char[relationS.length()];
        relationS.getChars(0, relationS.length(), cc, 0);
        ArrayList<Character> c = new ArrayList();
        for(int i=0;i<cc.length;i++){
            c.add(cc[i]);
        }
        for (int i = 0; i < c.size(); i++) {
            actualc = c.get(i);
            if (actualc == ' '){
                c.remove(i);
                i--;
            }
            if(i==c.size()-1){
                end = i+1;
            }
        }
        relationS = relationS.substring(beginning, end);
        if(relationS.length()<2){
            throw new IllegalEntryException("Die Relation muss mindestens 2 Elemente beinhalten!");
        }
        st = new StringTokenizer(relationS, ",");
        while (st.hasMoreTokens()) {
            relation.add(st.nextToken());
        }
            return relation;
    }
}
