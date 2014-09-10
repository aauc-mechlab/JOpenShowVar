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
 * Represents a Axis Struct variable from the KRL language
 *
 * @author Lars Ivar Hatledal
 */
public final class KRLAxis extends KRLStruct {

    private final HashMap<String, Double> struct = new HashMap<>();

    public KRLAxis(String name) {
        super(name, new String[]{"A1", "A2", "A3", "A4", "A5", "A6"});
        initialize();
    }

    /**
     * Initializes the values to zero
     */
    private void initialize() {
        setA1(0);
        setA2(90);
        setA3(-90);
        setA4(0);
        setA5(0);
        setA6(0);
    }

    /**
     * Sets the value of 'A1'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA1(double d) {
        struct.put(nodes[0], d);
        return this;
    }

    /**
     * Sets the value of 'A2'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA2(double d) {
        struct.put(nodes[1], d);
        return this;
    }

    /**
     * Sets the value of 'A3'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA3(double d) {
        struct.put(nodes[2], d);
        return this;
    }

    /**
     * Sets the value of 'A4'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA4(double d) {
        struct.put(nodes[3], d);
        return this;
    }

    /**
     * Sets the value of 'A5'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA5(double d) {
        struct.put(nodes[4], d);
        return this;
    }

    /**
     * Sets the value of 'A6'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLAxis setA6(double d) {
        struct.put(nodes[5], d);
        return this;
    }

    @Override
    public void setValue(String str, String obj) {
        struct.put(str, Double.parseDouble(obj));
    }

    @Override
    public HashMap<String, Double> getValue() {
        return struct;
    }

}
