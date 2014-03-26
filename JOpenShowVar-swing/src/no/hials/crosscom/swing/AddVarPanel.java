/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import no.hials.crosscom.swing.VarModel;
import no.hials.crosscom.variables.TrackException;

/**
 *
 * @author LarsIvar
 */
public class AddVarPanel extends JPanel {

    private final VarModel model;
    private final JTextField textField = new JTextField();
    private final JButton addButton = new JButton("Add");

    public AddVarPanel(VarModel model) {
        this.model = model;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel("Add variable: "));
        this.add(textField);
        this.add(addButton);
        initListeners();
    }

    private void initListeners() {
        ActionListener addVariableActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String var = textField.getText().trim();
                try {
                    model.addVariable(var);
                } catch (TrackException ex) {
                    Logger.getLogger(AddVarPanel.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
                }  finally {
                    textField.setText(null);
                }
            }
        };
        textField.addActionListener(addVariableActionListener);
        addButton.addActionListener(addVariableActionListener);
    }

}
