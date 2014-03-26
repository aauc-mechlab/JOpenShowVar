/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hials.crosscom.swing;

import java.util.Comparator;
import no.hials.crosscom.variables.Variable;

/**
 * Compares two variables
 * @author LarsIvar
 */
public class VarComparator implements Comparator<Variable>{

    @Override
    public int compare(Variable v1, Variable v2) {
        return v1.toString().length() - v2.toString().length();
    }
    
}
