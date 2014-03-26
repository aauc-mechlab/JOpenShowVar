/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hials.crosscom.swing;

import ca.odell.glazedlists.TextFilterator;
import java.util.List;
import no.hials.crosscom.variables.Variable;

/**
 * Filters variables
 * @author LarsIvar
 */
public class VarTextFilterator implements TextFilterator<Variable>{

    @Override
    public void getFilterStrings(List<String> baseList, Variable element) {
        baseList.add("" + element.getId());
        baseList.add(element.getDataType());
        baseList.add(element.getName());
        baseList.add(element.getReadTime() + "");
        baseList.add(element.getValue().toString());
    }
    
}
