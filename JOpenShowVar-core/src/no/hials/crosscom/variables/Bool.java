/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hials.crosscom.variables;

/**
 * Represents a BOOL value in KRL
 * @author Lars Ivar
 */
public class Bool extends Variable<Boolean>{

    public Bool(int id, String name, Boolean value, int readTime) {
        super(id, name, "Boolean", readTime);
        this.value = value;
    }

}
