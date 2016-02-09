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
public class Abhaengigkeit {

    public String linkeSeite;
    public String rechteSeite;

    public Abhaengigkeit(String lS, String rS) {
        linkeSeite = lS;
        rechteSeite = rS;
    }

    @Override
    public String toString() {
        return linkeSeite + "..." + rechteSeite;
    }

}
