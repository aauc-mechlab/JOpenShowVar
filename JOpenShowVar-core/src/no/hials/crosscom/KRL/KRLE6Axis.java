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
package no.hials.crosscom.KRL;

import java.util.HashMap;

/**
 * Represents a E6Axis struct variable from the KRL language
 *
 * @author Lars Ivar Hatledal
 */
public class KRLE6Axis extends KRLStruct {

    private final HashMap<String, Double> struct = new HashMap<>();

    public KRLE6Axis(String name) {
        super(name, new String[]{"A1", "A2", "A3", "A4", "A5", "A6", "E1", "E2", "E3", "E4", "E5", "E6"});
        initialize();
    }

    /**
     * Initializes the values 
     */
    private void initialize() {
        setA1(0);
        setA2(90);
        setA3(-90);
        setA4(0);
        setA5(0);
        setA6(0);
        setE1(0);
        setE2(0);
        setE3(0);
        setE4(0);
        setE5(0);
        setE6(0);
    }

    /**
     * Sets A1 to A6
     *
     * @param values the new values
     */
    public void setA1ToA6(double ... values) {
        if (values.length != 6) {
            throw new IllegalArgumentException("The number of values should be exatly 6!");
        }
        setA1(values[0]);
        setA2(values[1]);
        setA3(values[2]);
        setA4(values[3]);
        setA5(values[4]);
        setA6(values[5]);
    }

    /**
     * Sets E1 to E6
     *
     * @param values the new values
     */
    public void setE1ToE6(double ... values) {
        if (values.length != 6) {
            throw new IllegalArgumentException("The number of values should be exatly 6!");
        }
        setE1(values[0]);
        setE2(values[1]);
        setE3(values[2]);
        setE4(values[3]);
        setE5(values[4]);
        setE6(values[5]);
    }

    /**
     * Sets the value of 'A1'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA1(double d) {
        struct.put(nodes[0], d);
        return this;
    }

    /**
     * Sets the value of 'A2'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA2(double d) {
        struct.put(nodes[1], d);
        return this;
    }

    /**
     * Sets the value of 'A3'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA3(double d) {
        struct.put(nodes[2], d);
        return this;
    }

    /**
     * Sets the value of 'A4'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA4(double d) {
        struct.put(nodes[3], d);
        return this;
    }

    /**
     * Sets the value of 'A5'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA5(double d) {
        struct.put(nodes[4], d);
        return this;
    }

    /**
     * Sets the value of 'A6'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setA6(double d) {
        struct.put(nodes[5], d);
        return this;
    }

    /**
     * Sets the value of 'E1'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE1(double d) {
        struct.put(nodes[6], d);
        return this;
    }

    /**
     * Sets the value of 'E2'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE2(double d) {
        struct.put(nodes[7], d);
        return this;
    }

    /**
     * Sets the value of 'E3'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE3(double d) {
        struct.put(nodes[8], d);
        return this;
    }

    /**
     * Sets the value of 'E4'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE4(double d) {
        struct.put(nodes[9], d);
        return this;
    }

    /**
     * Sets the value of 'E5'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE5(double d) {
        struct.put(nodes[10], d);
        return this;
    }

    /**
     * Sets the value of 'E6'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Axis setE6(double d) {
        struct.put(nodes[11], d);
        return this;
    }

     /**
     * Get a double array representation of this object
     *
     * @return a new double array with the values contained in this struct
     */
    public double[] asArray() {

        double[] arr = new double[nodes.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = struct.get(nodes[i]);
        }
        return arr;
    }

    @Override
    public HashMap<String, Double> getValue() {
        return struct;
    }

    @Override
    public void setValue(String str, String obj) {
        struct.put(str, Double.parseDouble(obj));
    }
}
