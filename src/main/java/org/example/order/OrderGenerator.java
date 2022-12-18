package org.example.order;

import java.util.ArrayList;

public class OrderGenerator {

    public Order generic(){
            return new Order("John JR", "Smith", "StPetersburg", "Sadovaya",
                    "+79999999999", 10, "2023-06-06", "ohohoh", new ArrayList<>());
        }

    }
