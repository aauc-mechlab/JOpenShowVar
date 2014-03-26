/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The Client used to communicate with KukaVarProxy
 * @author Lars Ivar
 */
public final class CrossComClient extends Socket  {

    BufferedInputStream bis = new BufferedInputStream( getInputStream());
    BufferedOutputStream bos = new BufferedOutputStream(getOutputStream());

    public CrossComClient(String host, int port) throws UnknownHostException, IOException {
        super(host, port);
    }


    /**
     * Sends a request to the KUKA robot
     * @param request the request to send
     * @return a Callback from the robot with an updated variable value
     * @throws IOException 
     */
    public Callback sendRequest(Request request) throws IOException {
        for (byte b : request.getCmd()) {
            bos.write(b);
        }
        bos.flush();
        return getCallback(request.getVariable());
    }

    private Callback getCallback(String variable) throws IOException {
        long t0 = System.currentTimeMillis();
        byte[] header = new byte[7];
        bis.read(header);

        byte[] block = new byte[getInt(header, 2)];
        bis.read(block);

        byte[] data = new byte[header.length + block.length];
        System.arraycopy(header, 0, data, 0, header.length);
        System.arraycopy(block, 0, data, header.length, block.length);

        int readTime = (int) (System.currentTimeMillis() - t0);
        return new Callback(variable, data, readTime);
    }
    
    
    /**
     * Converts a 2 bytes into a integer
     *
     * @param bytes a byte array of at least length 2
     * @param off index of the first byte of the integer that should be
     * converted
     * @return the integer value of the two bytes
     */
    public static int getInt(byte[] bytes, int off) {
        return bytes[off] << 8 & 0xFF00 | bytes[off + 1] & 0xFF;
    }
}
