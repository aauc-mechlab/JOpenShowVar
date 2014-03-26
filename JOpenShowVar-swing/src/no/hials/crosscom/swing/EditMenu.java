/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hials.crosscom.swing;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Lars Ivar
 */
public class EditMenu extends JMenu {

    private final JMenuItem editVariableMenuItem = new JMenuItem("Edit variable");
    private final JMenuItem removeVariableMenuItem = new JMenuItem("Remove variable");
    
    public EditMenu() {
        super("Edit");
        this.add(editVariableMenuItem);
        this.add(removeVariableMenuItem);
    }

    public JMenuItem getEditVariableMenuItem() {
        return editVariableMenuItem;
    }

    public JMenuItem getRemoveVariableMenuItem() {
        return removeVariableMenuItem;
    }
    
    
    
}
