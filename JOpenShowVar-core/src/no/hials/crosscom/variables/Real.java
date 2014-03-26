/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.variables;

/**
 * Represents a REAL value in KRL
 * @author Lars Ivar
 */
public class Real extends Variable<Double> {

    public Real(int id, String name, Double value, int readTime) {
        super(id, name, "Real",  readTime);
        this.value = value;
    }

}
