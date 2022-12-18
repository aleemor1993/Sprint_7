package org.example.courier;

import java.util.Random;

public class CourierGenerator {

    public Courier generic(){
        return new Courier("John JR", "P@ssword12", "Smith");
    }

    public Courier random(){
        return new Courier("John JRR" + new Random().nextInt(200), "P@ssword12", "Smith");
    }
}
