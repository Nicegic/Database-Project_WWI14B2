/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
 
import java.util.*;
 
/**
 *
 * @author Nicolas
 */
public class Membership {
 
    public boolean member(Funktion F, Abhaengigkeit a) {
        return closure(F, a.rechteSeite).matches(a.linkeSeite);
    }
 
    public static String closure(Funktion F, String p) {
        ArrayList<Abhaengigkeit> AbhaengigkeitenlisteC = F.getList();
        String X = p;
        String Xx;
        String regex;
        do {
            Xx = X;
            for (int i = 0; i < AbhaengigkeitenlisteC.size(); i++) {
                regex = ".*";
                for (int g = 0; g < AbhaengigkeitenlisteC.get(i).linkeSeite.length(); g++) {
                    regex = regex + AbhaengigkeitenlisteC.get(i).linkeSeite.charAt(g) + ".*";
                }
                if (X.matches(regex)) {
                    for (int j = 0; j < AbhaengigkeitenlisteC.get(i).rechteSeite.length(); j++) {
                        regex = ".*" + AbhaengigkeitenlisteC.get(i).rechteSeite.charAt(j) + ".*";
                        if (X.matches(regex)) {
                            continue;
                        } else {
                            X = X + AbhaengigkeitenlisteC.get(i).rechteSeite.charAt(j);
                        }
                    }
                }
            }
        } while (X.equals(Xx) == false);
        return Xx;
    }
 
    public Funktion nonRedundantCover(Funktion F) {
        Funktion G = F.copy();
        Abhaengigkeit aah;
        for (int i = 0; i < F.getAbhSize(); i++) {
            aah = G.removeAbh(F.getAbhaengigkeit(i), G);//die i-te Abhängigkeit wird entfernt
            if (member(G, F.getAbhaengigkeit(i))) { // Überprüfung ob die eben entfernte Abhängigkeit
                //noch einmal vorhanden ist, wenn ja bleibt die gelöschte Abhängigkeit gelöscht
            } else {
                G.addAbhaengigkeit(aah);//ist sie eindeutig, wird die Abhängigkeit wieder eingefügt
            }
        }
        return G;
    }
   
    public Funktion reducedCover(Funktion F){
        Funktion G = F;
        Abhaengigkeit aah;
        Abhaengigkeit aah2;
        for(int i=0;i<G.getAbhSize();i++){//all funktionale Abhängigkeiten X → Y ∈ F /* Linksreduktion */
            for(int j=0;j<G.getAbhaengigkeit(i).linkeSeite.length();j++){//all a ∈ X /* a unwesentlich? */
                String links = "";
                for(int h=j+1;h-1<G.getAbhaengigkeit(i).linkeSeite.length();h++){
                    links=links+G.getAbhaengigkeit(i).linkeSeite.charAt(h);
                }
                if (closure(G,links).matches(G.getAbhaengigkeit(i).rechteSeite)){//Y ⊆ CLOSURE F,X − a
                    G.getAbhaengigkeit(i).linkeSeite=links; //then ersetze X → Y durch X − a → Y in F
                }
            }//end for
        }//end for
         for(int i=0;i<G.getAbhSize();i++){//all verbleibende FAs X → Y ∈ F /* Rechtsreduktion */
            for(int j=0;j<G.getAbhaengigkeit(i).rechteSeite.length();j++){//all b ∈ Y /* b unwesentlich? */
                aah = G.removeAbh(F.getAbhaengigkeit(i), G);
                String rechts="";
                for(int h=j+1;h-1<G.getAbhaengigkeit(i).linkeSeite.length();h++){
                    rechts=rechts+G.getAbhaengigkeit(i).rechteSeite.charAt(h);
                }
                aah2 = new Abhaengigkeit(G.getAbhaengigkeit(i).linkeSeite, G.getAbhaengigkeit(i).rechteSeite);
                G.addAbhaengigkeit(aah2);
                if(closure(G, G.getAbhaengigkeit(i).linkeSeite).matches(G.getAbhaengigkeit(i).rechteSeite)){ //b ⊆ CLOSURE(F − {X → Y} ∪ X → Y − b ,X)
                    //then ersetze X → Y durch X → (Y − b)
                }
                else{
                    G.addAbhaengigkeit(aah);
                    G.removeAbh(aah2, G);
                }
            }//end for
        }//end for
        for(int i=0;i<G.getAbhSize();i++){
            if(G.getAbhaengigkeit(i).rechteSeite.equals("")||G.getAbhaengigkeit(i)==null){
                G.removeAbh(G.getAbhaengigkeit(i), G);
            }
        }//Eliminiere FAs der Form X → ∅
        for(int i=0; i< G.getAbhSize();i++){
            if(G.getAbhaengigkeit(i+1)==null){
                break;
            }
            else if(G.getAbhaengigkeit(i).linkeSeite.equals(G.getAbhaengigkeit(i+1).linkeSeite)){
                G.getAbhaengigkeit(i).rechteSeite=G.getAbhaengigkeit(i).rechteSeite+G.getAbhaengigkeit(i+1).rechteSeite;
            }
        }//Vereinige FAs der Form X → Y1,X → Y2,… zu X → Y1Y2
        return G;
    }
}