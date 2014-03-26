/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.variables;

/**
 * Represents a INT value in KRL
 * @author Lars Ivar
 */
public class Int extends Variable<Integer> {

    public Int(int id, String name, Integer value, int readTime) {
        super(id, name, "Int", readTime);
        this.value = value;
    }

}
