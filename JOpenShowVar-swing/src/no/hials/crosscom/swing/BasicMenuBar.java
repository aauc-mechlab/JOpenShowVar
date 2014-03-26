/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.swing;

import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Lars Ivar
 */
public class BasicMenuBar extends JMenuBar {

    private final FileMenu fileMenu = new FileMenu();
    private final VarModel model;
    
    public BasicMenuBar(VarModel model) {
        this.model = model;
        this.add(fileMenu);
        init();
    }
    
    private void init() {
        final JMenuItem exitMenuItem = fileMenu.getExitMenuItem();
        exitMenuItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        JMenuItem saveMenuItem = fileMenu.getSaveMenuItem();
        saveMenuItem.addActionListener((ActionEvent e) -> {
            model.save();
        });
    }
}
