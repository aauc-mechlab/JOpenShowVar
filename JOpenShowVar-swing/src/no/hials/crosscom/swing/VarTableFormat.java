/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.swing;

import ca.odell.glazedlists.gui.TableFormat;
import no.hials.crosscom.variables.Variable;

/**
 *
 * @author LarsIvar
 */
public class VarTableFormat implements TableFormat<Variable> {

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "ID";
        } else if (column == 1) {
            return "Variable";
        } else if (column == 2) {
            return "Type";
        } else if (column == 3) {
            return "Value";
        } else if (column == 4) {
            return "Read time";
        }

        throw new IllegalStateException();
    }

    @Override
    public Object getColumnValue(Variable var, int column) {
        if (column == 0) {
            return var.getId();
        }else if (column == 1) {
            return var.getName();
        } else if (column == 2) {
            return var.getDataType();
        } else if (column == 3) {
            return var.getValue().toString();
        } else if (column == 4) {
            return var.getReadTime() + " ms";
        }

        throw new IllegalStateException();
    }
}

