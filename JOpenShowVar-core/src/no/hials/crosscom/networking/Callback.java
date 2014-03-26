/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.networking;

import java.util.Arrays;

/**
 * When querying the KUKA, a Callback with a updated value of the requested variable is sent which this class represents
 * @author Lars Ivar
 */
public final class Callback {

    private final String variable;
    private final int id, option, readTime;
    private final String value;

    public Callback(String variable, byte[] bytes, int readTime) {
        this.variable = variable;
        this.id = CrossComClient.getInt(bytes, 0);
        this.readTime = readTime;
        this.option = (int) bytes[4];
        this.value = new String(Arrays.copyOfRange(bytes, 7, bytes.length)).trim();
    }

    /**
     * Getter for the variable name
     * @return the name of the variable
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Getter for the ID of the callback variable
     * @return the id if the variable
     */
    public int getId() {
        return id;
    }

    /**
     * 0 means that the request was for reading, 1 means that the request was for writing
     * @return the option type, should be 0 or 1
     */
    public int getOption() {
        return option;
    }

    /**
     * The time it took from sending the request to get the Callback
     * @return The time it took from sending the request to get the Callback
     */
    public int getReadTime() {
        return readTime;
    }

    /**
     * Get the value of the Variable
     * @return value of the Variable (This is a String) The Variable class has a static method for parsing this
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Callback{" + "variable=" + variable + ", id=" + id + ", option=" + option + ", readTime=" + readTime + ", value=" + value + '}';
    }
}
