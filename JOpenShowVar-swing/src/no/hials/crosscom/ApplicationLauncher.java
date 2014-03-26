/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import no.hials.crosscom.JOpenShowVarConstants;
import no.hials.crosscom.swing.AddVarPanel;
import no.hials.crosscom.swing.BasicMenuBar;
import no.hials.crosscom.swing.EditPanel;
import no.hials.crosscom.swing.VarBrowser;
import no.hials.crosscom.swing.VarModel;
import no.hials.crosscom.networking.CrossComClient;
import no.hials.crosscom.variables.TrackException;

/**
 *
 * @author Lars Ivar
 */
public class ApplicationLauncher {
    
    public static final String GUI_TITLE = "JOpenShowVar";

    public static void main(String[] args) throws IOException, TrackException {
        FileInputStream fis = new FileInputStream(JOpenShowVarConstants.FILELOCATION_ROBOT_IP);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String info[] = br.readLine().split(":");
        String IP = info[0];
        int PORT = Integer.parseInt(info[1]);
        
        final CrossComClient client = new CrossComClient(IP, PORT);
        final VarModel model = new VarModel(client);
        model.restore();
        final JFrame frame = new JFrame(GUI_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new BasicMenuBar(model));
        frame.setLayout(new BorderLayout());
        final EditPanel editPanel = new EditPanel(frame, model);
        final VarBrowser browser = new VarBrowser(model, editPanel);
        frame.getContentPane().add(new JScrollPane(browser), BorderLayout.CENTER);
        frame.getContentPane().add(new AddVarPanel(model), BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(editPanel), BorderLayout.EAST);
        frame.pack();
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }
}
