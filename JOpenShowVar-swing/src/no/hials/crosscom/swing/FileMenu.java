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
public class FileMenu extends JMenu {

    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    private final JMenuItem saveMenuItem = new JMenuItem("Save");
    
    public FileMenu() {
        super("File");
        this.add(saveMenuItem);
        this.add(exitMenuItem);
        
    }
    
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }
    
    
    
}
