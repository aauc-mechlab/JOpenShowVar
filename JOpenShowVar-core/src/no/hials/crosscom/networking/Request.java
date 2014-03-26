/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.networking;

import no.hials.crosscom.JOpenShowVarConstants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lars Ivar
 */
public class Request {

    private final String var, val;
    private final int id;
    private final Byte[] cmd;

    public Request(int id, String var, String val) {
        this.id = id;
        this.var = var;
        this.val = val;
        if (val == null) {
            cmd = getReadCommand();
        } else {
            cmd = getWriteCommand();
        } 
    }

    /**
     * Get the name of the variable
     * @return the name of the variable
     */
    public String getVariable() {
        return var;
    }

    /**
     * Get the command to send (byte array)
     * @return the command to send to the KUKA 
     */
    public Byte[] getCmd() {
        return cmd.clone();
    }

    /**
     * The read command
     * @return The read command
     */
    private Byte[] getReadCommand() {
        byte[] cmd = var.getBytes();
        List<Byte> header = new ArrayList<>();
        List<Byte> block = new ArrayList<>();

        byte hbyte = (byte) ((cmd.length >> 8) & 0xff00);
        byte lbyte = (byte) (cmd.length & 0x00ff);

        int index = 0;
        block.add(index++, (byte) JOpenShowVarConstants.READ);
        block.add(index++, hbyte);
        block.add(index++, lbyte);
        for (int i = 0; i < cmd.length; i++) {
            block.add(index++, cmd[i]);
        }
        hbyte = (byte) ((block.size() >> 8) & 0xff00);
        lbyte = (byte) (block.size() & 0x00ff);

        byte hbytemsg = (byte) ((id >> 8) & 0xff00);
        byte lbytemsg = (byte) (id & 0x00ff);

        index = 0;
        header.add(index++, hbytemsg);
        header.add(index++, lbytemsg);
        header.add(index++, hbyte);
        header.add(index++, lbyte);

        block.addAll(0, header);

        return block.toArray(new Byte[block.size()]);
    }

    /**
     * The write command
     * @return the write command
     */
    private Byte[] getWriteCommand() {
        byte[] cmd = var.getBytes();
        byte[] value = val.getBytes();
        List<Byte> header = new ArrayList<>();
        List<Byte> block = new ArrayList<>();

        byte hbyte = (byte) ((cmd.length >> 8) & 0xff00);
        byte lbyte = (byte) (cmd.length & 0x00ff);

        int index = 0;
        block.add(index++, (byte) JOpenShowVarConstants.WRITE);
        block.add(index++, hbyte);
        block.add(index++, lbyte);
        for (int i = 0; i < cmd.length; i++) {
            block.add(index++, cmd[i]);
        }

        hbyte = (byte) ((value.length >> 8) & 0xff00);
        lbyte = (byte) (value.length & 0x00ff);

        block.add(index++, hbyte);
        block.add(index++, lbyte);
        for (int i = 0; i < value.length; i++) {
            block.add(index++, value[i]);
        }

        hbyte = (byte) ((block.size() >> 8) & 0xff00);
        lbyte = (byte) (block.size() & 0x00ff);

        byte hbytemsg = (byte) ((id >> 8) & 0xff00);
        byte lbytemsg = (byte) (id & 0x00ff);

        index = 0;
        header.add(index++, hbytemsg);
        header.add(index++, lbytemsg);
        header.add(index++, hbyte);
        header.add(index++, lbyte);

        block.addAll(0, header);

        return block.toArray(new Byte[block.size()]);
    }
}
