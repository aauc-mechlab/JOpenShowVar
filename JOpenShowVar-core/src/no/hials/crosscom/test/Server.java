/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.hials.crosscom.networking.Callback;
import no.hials.crosscom.JOpenShowVarConstants;

/**
 * Used for testing when the robot is not available
 * @author LarsIvar
 */
public class Server extends ServerSocket implements Runnable {

    public Server(int port) throws IOException {
        super(port);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = accept();
                new Thread(new ClientHandler(socket)).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7000);
    }

    private class ClientHandler implements Runnable {

        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    BufferedOutputStream bos = new BufferedOutputStream(os)) {
                while (true) {
                    long t0 = System.currentTimeMillis();
                    byte[] header = new byte[7];
                    bis.read(header);

                    byte[] block = new byte[getInt(header, 2)];
                    bis.read(block);

                    byte[] data = new byte[header.length + block.length];
                    System.arraycopy(header, 0, data, 0, header.length);
                    System.arraycopy(block, 0, data, header.length, block.length);

                    int readTime = (int) (System.currentTimeMillis() - t0);
                    Callback callback = new Callback(new String(block), data, readTime);
                    String val = null;
                    switch (callback.getVariable().trim()) {
                        case "$OV_JOG":
                            val = Long.toString(System.currentTimeMillis());
                            break;
                        case "$OV_PRO":
                            val = "50";
                            break;
                        case "MYAXIS":
                            val = "{AXIS: A1 0.0, A2 0.0, A3 0.0, A4 true, A5 0.0}";
                            break;
                        case "$AXIS_ACT":
                            val = "{E6AXIS: A1 0.0, A2 0.0, A3 0.0, A4 0.0, A5 0.0}";
                            break;
                    }
                    byte[] cmd = val.trim().getBytes();
                    List<Byte> header1 = new ArrayList<>();
                    List<Byte> block1 = new ArrayList<>();

                    byte hbyte = (byte) ((cmd.length >> 8) & 0xff00);
                    byte lbyte = (byte) (cmd.length & 0x00ff);

                    int index = 0;
                    block1.add(index++, (byte) JOpenShowVarConstants.READ);
                    block1.add(index++, hbyte);
                    block1.add(index++, lbyte);
                    for (int i = 0; i < cmd.length; i++) {
                        block1.add(index++, cmd[i]);
                    }
                    hbyte = (byte) ((block1.size() >> 8) & 0xff00);
                    lbyte = (byte) (block1.size() & 0x00ff);

                    byte hbytemsg = (byte) ((callback.getId() >> 8) & 0xff00);
                    byte lbytemsg = (byte) (callback.getId() & 0x00ff);

                    index = 0;
                    header1.add(index++, hbytemsg);
                    header1.add(index++, lbytemsg);
                    header1.add(index++, hbyte);
                    header1.add(index++, lbyte);

                    block1.addAll(0, header1);
                    Byte[] toArray = block1.toArray(new Byte[block1.size()]);
                    for (byte b : toArray) {
                        bos.write(b);
                    }
                    bos.flush();

                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Converts a 2 bytes into a integer
     *
     * @param bytes a byte array of at least length 2
     * @param off index of the first byte of the integer that should be
     * converted
     * @return the integer value of the two bytes
     */
    private int getInt(byte[] bytes, int off) {
        return bytes[off] << 8 & 0xFF00 | bytes[off + 1] & 0xFF;
    }

}
