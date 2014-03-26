/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.variables;

/**
 * A node in a Struct
 * @author Lars Ivar
 * @param <E> type variable
 */
public class StructNode<E> {

    private final String name;
    private final E value;

    public StructNode(String name, E value) {
        this.name = name;
        this.value = value;
    }

    /**
     * The name of the node
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * The value of the node
     * @return the value of the node
     */
    public E getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " " + value;
    }
}
