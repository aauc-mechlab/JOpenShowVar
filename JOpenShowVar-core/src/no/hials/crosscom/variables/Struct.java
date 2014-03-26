/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.crosscom.variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a STRUCT value in KRL
 * @author Lars Ivar
 */
public class Struct extends Variable<List<StructNode>> {
    
    public Struct(int id, String name, List<StructNode> value, int readTime) {
        super(id, name, "Struct", readTime);
        this.value = value;
    }

    /**
     * Parses a String encoded KRL Struct to a JOpenShowVar Struct
     * @param value the KRL String encoded Struct
     * @return a JOpenShowVar Struct 
     */
    public static List<StructNode> parseString(String value) {
        if (value.contains(":")) {
            value = value.substring(value.indexOf(":") + 1, value.indexOf("}") + 1);
        } else {
            value = value.substring(value.indexOf("{") + 1, value.indexOf("}") + 1);
            value = " " + value;
        }
        value = value.replaceAll("\\{", "").replaceAll("\\}", "");
        List<StructNode> nodes = new ArrayList<>();
        String[] split1 = value.split(",");
        for (String str1 : split1) {
            String[] split2 = str1.split(" ");
            String name = split2[1];
            Object val;
            Scanner sc = new Scanner(split2[2]);
            if (sc.hasNextInt()) {
                val = sc.nextInt();
            } else if (sc.hasNextDouble()) {
                val = sc.nextDouble();
            } else if (sc.hasNextBoolean()) {
                val = sc.nextBoolean();
            } else {
                val = sc.next();
            }
            StructNode node = new StructNode(name, val);
            nodes.add(node);
        }
        return nodes;
    }
}
