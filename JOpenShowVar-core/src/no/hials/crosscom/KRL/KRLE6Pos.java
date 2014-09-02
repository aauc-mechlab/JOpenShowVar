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
 * Represents a E6Pos struct variable from the KRL language
 *
 * @author Lars Ivar Hatledal
 */
public final class KRLE6Pos extends KRLStruct {

    public KRLE6Pos(String name) {
        super(name, new String[]{"X", "Y", "Z", "A", "B", "C", "E1", "E2", "E3", "E4", "E5", "E6", "S", "T"});
        inititialize();
    }
    
     /**
     * Sets X, Y and Z 
     * @param values the new values
     */
    public void setXToZ(double ... values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The number of values should be exatly 3!");
        }
        setX(values[0]);
        setY(values[1]);
        setZ(values[2]);
    }
    
     /**
     * Sets A, B and C 
     * @param values the new values
     */
    public void setAToC(double ... values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The number of values should be exatly 3!");
        }
        setA(values[0]);
        setB(values[1]);
        setC(values[2]);
    }
    
     /**
     * Sets E1 to E6
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
     * Sets the value of 'X'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setX(double d) {
        struct.put(nodes[0], d);
        return this;
    }

    /**
     * Sets the value of 'Y'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setY(double d) {
        struct.put(nodes[1], d);
        return this;
    }

    /**
     * Sets the value of 'Z'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setZ(double d) {
        struct.put(nodes[2], d);
        return this;
    }

    /**
     * Sets the value of 'A'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setA(double d) {
        struct.put(nodes[3], d);
        return this;
    }

    /**
     * Sets the value of 'B'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setB(double d) {
        struct.put(nodes[4], d);
        return this;
    }

    /**
     * Sets the value of 'C'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setC(double d) {
        struct.put(nodes[5], d);
        return this;
    }

    /**
     * Sets the value of 'E1'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE1(double d) {
        struct.put(nodes[6], d);
        return this;
    }

    /**
     * Sets the value of 'E2'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE2(double d) {
        struct.put(nodes[7], d);
        return this;
    }

    /**
     * Sets the value of 'E3'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE3(double d) {
        struct.put(nodes[8], d);
        return this;
    }

    /**
     * Sets the value of 'E4'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE4(double d) {
        struct.put(nodes[9], d);
        return this;
    }

    /**
     * Sets the value of 'E5'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE5(double d) {
        struct.put(nodes[10], d);
        return this;
    }

    /**
     * Sets the value of 'E6'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setE6(double d) {
        struct.put(nodes[11], d);
        return this;
    }

    /**
     * Sets the value of 'S'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setS(double d) {
        struct.put(nodes[12], d);
        return this;
    }

    /**
     * Sets the value of 'T'
     *
     * @param d the value to set
     * @return this - so that method call chaining is possible
     */
    public KRLE6Pos setT(double d) {
        struct.put(nodes[13], d);
        return this;
    }

    /**
     * Initializes the values to zero
     */
    private void inititialize() {
        setX(0);
        setY(0);
        setZ(0);
        setA(0);
        setB(0);
        setC(0);
        setE1(0);
        setE2(0);
        setE3(0);
        setE4(0);
        setE5(0);
        setE6(0);
        setS(0);
        setT(0);

    }

    @Override
    public HashMap<String, Double> getValue() {
        HashMap<String, Double> map = new HashMap<>();
        for (String key : struct.keySet()) {
            map.put(key, Double.parseDouble((String) struct.get(key)));
        }
        return map;
    }

}
