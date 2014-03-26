/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.swing;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import no.hials.crosscom.variables.Variable;

/**
 *
 * @author Lars Ivar
 */
public class Variables  {
    private final EventList<Variable> variables = new BasicEventList<>();
    
    
   public EventList<Variable> getVariables;
    
    public Variable getByID(int id) {
        for (Variable var : variables) {
            if (var.getId() == id) {
                return var;
            }
        }
        
        return null;
    }

    public Variable getByName(String name) {
        for (Variable var : variables) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
        return null;
    }

    public String getIDs() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Variable var : variables) {
            sb.append(var.getId());
            if (i != variables.size() - 1) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }

    public String getDataTypes() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Variable var : variables) {
            sb.append(var.getDataType());
            if (i != variables.size() - 1) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }

    public String getValues() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Variable var : variables) {
            sb.append(var.getValue());
            if (i != variables.size() - 1) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }

    public String getReadTimes() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Variable var : variables) {
            sb.append(var.getReadTime());
            if (i != variables.size() - 1) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }
}
