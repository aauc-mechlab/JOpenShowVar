/*
 * Copyright (c) 2014, Aalesund University College
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
        long t0 = System.nanoTime();
        byte[] header = new byte[7];
        bis.read(header);

        byte[] block = new byte[getInt(header, 2)];
        bis.read(block);

        byte[] data = new byte[header.length + block.length];
        System.arraycopy(header, 0, data, 0, header.length);
        System.arraycopy(block, 0, data, header.length, block.length);

        long readTime = (System.nanoTime()- t0);
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
