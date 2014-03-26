/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.variables;

import java.util.Scanner;
import no.hials.crosscom.networking.Callback;

/**
 * Represents a KRL variable
 * @author Lars Ivar
 * @param <E> Variable type
 */
public abstract class Variable<E> implements Comparable<Variable> {

    private final int id;
    private final String name;
    private final String dataType;
    private int readTime;
    protected E value;

    public Variable(int id, String name, String dataType, int readTime) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.readTime = readTime;
    }

    /**
     * Get the name of the variable
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * Get the datatype: 'INT', 'REAL', 'BOOL', etc.
     * @return 
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Get the time it took to read the variable from the robot
     * @return the time it took to read the variable from the robot
     */
    public int getReadTime() {
        return readTime;
    }

    /**
     * Get the assigned ID of the variable
     * @return the assigned ID of the variable
     */
    public int getId() {
        return id;
    }

    /**
     * Get the value of the variable
     * @return the value of the variable
     */
    public E getValue() {
        return value;
    }

    /**
     * Sets the value of the variable
     * @param value 
     */
    protected  void setValue(E value) {
        this.value = value;
    }

    /**
     * Updates the value
     * @param value the new value
     * @param readTime the new read time
     */
    public void update(E value, int readTime) {
        setValue(value);
        this.readTime = readTime;
    }

    @Override
    public String toString() {
        return "ID = " + id + "\t" + name + " = " + getValue() + "\t#ReadTime: " + readTime;
    }

    /**
     * Parses a string encoded KRL variable to a JOpenShowVar Variable
     * @param callback the Callback from the robot
     * @return the JOpenShowVar Variable
     * @throws NumberFormatException 
     */
    public static Variable parseVariable(Callback callback) throws NumberFormatException{
        String variable = callback.getVariable();
        String value = callback.getValue();
        int id = callback.getId();
        int readTime = callback.getReadTime();
//        int option = callback.getOption();

        Scanner sc = new Scanner(value);
        Variable var;
        if (sc.hasNextInt()) {
            var = new Int(id, variable, sc.nextInt(), readTime);
            sc.close();
        }else if (sc.hasNextFloat()) {
            var = new Real(id, variable, (double)sc.nextFloat(), readTime);
            sc.close();
        } else if (sc.hasNextDouble()) {
            var = new Real(id, variable, sc.nextDouble(), readTime);
            sc.close();
        } else if (sc.hasNextBoolean()) {
            var = new Bool(id, variable, sc.nextBoolean(), readTime);
            sc.close();
        } else if (value.contains("{")){
            sc.close();
            var = new Struct(id, variable, Struct.parseString(value), readTime);
        } else {
            var = new Real(id, variable, Double.parseDouble(value), readTime);
            sc.close();
        }
        return var;
    }

    @Override
    public int compareTo(Variable o) {
        if (toString().length() == o.toString().length()) {
            return 0;
        } else if (toString().length() < o.toString().length()) {
            return -1;
        } else {
            return 1;
        }
    }
}
