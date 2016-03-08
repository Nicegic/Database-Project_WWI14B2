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
 
    public static boolean member(Funktion F, Abhaengigkeit a) {
        String regex=".*"+a.rechteSeite+".*";
        return closure(F, a.linkeSeite).matches(regex);
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
   
    public static Funktion reducedCover(Funktion F){
        Funktion G = F;
        Abhaengigkeit aah=null;
        Abhaengigkeit aah2=null;
        boolean removed=false;
        System.out.println("reducedCover");
        for(int i=0;i<G.getAbhSize();i++){//all funktionale Abhängigkeiten X → Y ∈ F /* Linksreduktion */
            System.out.println("for 1: i="+i+" Ende bei G.getAbhSize()="+G.getAbhSize());
            for(int j=0;j<G.getAbhaengigkeit(i).linkeSeite.length();j++){//all a ∈ X /* a unwesentlich? */
                System.out.println("for 1: i="+i+" for 1.1: j="+j+" Ende bei G.getAbhaengigkeit("+i+").linkeSeite.length()="+G.getAbhaengigkeit(i).linkeSeite.length()+"\nArbeit mit: "+G.getAbhaengigkeit(i));
                String links = "";
                for(int h=j+1;h<G.getAbhaengigkeit(i).linkeSeite.length();h++){
                    System.out.println("for 1: i="+i+" for 1.1: j="+j+" for 1.2: h="+h+" Ende bei G.getAbhaengigkeit("+i+").linkeSeite.length()="+G.getAbhaengigkeit(i).linkeSeite.length());
                    links=links+G.getAbhaengigkeit(i).linkeSeite.charAt(h);
                    System.out.println("Links="+links);
                }
                if (closure(G,links).matches((".*"+G.getAbhaengigkeit(i).rechteSeite+".*"))){//Y ⊆ CLOSURE F,X − a
                    System.out.println("for 1: i="+i+" for 1.1: j="+j+" if 1");
                    G.getAbhaengigkeit(i).linkeSeite=links; //then ersetze X → Y durch X − a → Y in F
                }
            }//end for
        }//end for
         for(int i=0;i<G.getAbhSize();i++){//all verbleibende FAs X → Y ∈ F /* Rechtsreduktion */
            System.out.println("for 2: i="+i+" Ende bei G.getAbhSize()="+G.getAbhSize());
            for(int j=0;j<G.getAbhaengigkeit(i).rechteSeite.length();j++){//all b ∈ Y /* b unwesentlich? */
                System.out.println("for 2: i="+i+" for 2.1: j="+j+" Ende bei G.getAbhaengigkeit("+i+").rechteSeite.length()="+G.getAbhaengigkeit(i).rechteSeite.length());
                String rechts="";
                for(int h=j+1;h<G.getAbhaengigkeit(i).rechteSeite.length();h++){
                    System.out.println("for 2: i="+i+" for 2.1: j="+j+" for 2.2: h="+h+" Ende bei G.getAbhaengigkeit("+i+").rechteSeite.length()="+G.getAbhaengigkeit(i).rechteSeite.length());
                    aah = G.removeAbh(F.getAbhaengigkeit(i), G);
                    removed=true;
                    rechts=rechts+G.getAbhaengigkeit(i).rechteSeite.charAt(h);
                    System.out.println("Rechts="+rechts);
                }
                if(removed){
                    aah2 = new Abhaengigkeit(G.getAbhaengigkeit(i).linkeSeite, G.getAbhaengigkeit(i).rechteSeite);
                    System.out.println("for 2: i="+i+" for 2.1: j="+j+" aah2 gesetzt vor try");
                    try{
                        G.addAbhaengigkeit(aah2);
                        System.out.println("for 2: i="+i+" for 2.1: j="+j+" try 1");
                    }
                    catch(NotInAlphabetException niae){
                        System.out.println(niae);
                        System.out.println("for 2: i="+i+" for 2.1: j="+j+" catch 1");
                    }
                    catch(NullPointerException e){
                        System.out.println(e);
                        System.out.println("for 2: i="+i+" for 2.1: j="+j+" catch 1.1");
                    }
                    if(closure(G, G.getAbhaengigkeit(i).linkeSeite).matches((".*"+G.getAbhaengigkeit(i).rechteSeite+".*"))){ //b ⊆ CLOSURE(F − {X → Y} ∪ X → Y − b ,X)
                        //G.getAbhaengigkeit(i).rechteSeite;
                        //then ersetze X → Y durch X → (Y − b)
                        System.out.println("for 2: i="+i+" for 2.1: j="+j+" if 2");
                    }
                    else{
                        try{
                            G.addAbhaengigkeit(aah);
                            System.out.println("for 2: i="+i+" for 2.1: j="+j+" else 2 try 2");
                        }
                        catch(NotInAlphabetException niae){
                            System.out.println(niae);
                            System.out.println("for 2: i="+i+" for 2.1: j="+j+" else 2 catch 2.1");
                        }
                        catch(NullPointerException npe){
                            System.out.println(npe);
                            System.out.println("for 2: i="+i+" for 2.1: j="+j+" else 2 catch 2.2");
                        }
                        G.removeAbh(aah2, G);
                        System.out.println("for 2: i="+i+" for 2.1: j="+j+" else 2");
                    }
                    removed=false;
                }
            }//end for
        }//end for
        for(int i=0;i<G.getAbhSize();i++){
            System.out.println("for 3: i="+i+" Ende bei G.getAbhSize()="+G.getAbhSize());
            if(G.getAbhaengigkeit(i).rechteSeite.equals("")||G.getAbhaengigkeit(i)==null){
                System.out.println("for 3: i="+i+" if 3");
                G.removeAbh(G.getAbhaengigkeit(i), G);
            }
        }//Eliminiere FAs der Form X → ∅
        for(int i=0; i< G.getAbhSize();i++){
            System.out.println("for 4.1: i="+i+" Ende bei G.getAbhSize()="+G.getAbhSize());
            for(int j=0; j<G.getAbhSize();j++){
                try{
                    System.out.println("for 4.2: i="+i+" try 3");
                    if(G.getAbhaengigkeit(i+1)==null){
                        System.out.println("for 4.2: i="+i+" try 3 if 4");
                        break;
                    }
                    else if(G.getAbhaengigkeit(i).linkeSeite.equals(G.getAbhaengigkeit(i+1).linkeSeite)){
                        System.out.println("for 4.2: i="+i+" try 3 else 4");
                        G.getAbhaengigkeit(i).rechteSeite=G.getAbhaengigkeit(i).rechteSeite+G.getAbhaengigkeit(i+1).rechteSeite;
                    }
                }
                catch(IndexOutOfBoundsException e){
                    System.out.println("for 4.2: i="+i+" catch 3");
                    System.out.println(e);
                }
            }
        }//Vereinige FAs der Form X → Y1,X → Y2,… zu X → Y1Y2
        System.out.println("return G="+G);
        String abh="";
        for(int i=0; i<G.getAbhSize();i++){
            System.out.println("G.getAbhaengigkeit("+i+")="+G.getAbhaengigkeit(i));
        }
        return G;
    }
}