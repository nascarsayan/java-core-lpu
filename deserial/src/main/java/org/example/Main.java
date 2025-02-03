package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Car {
    String color;
    String make;

    Car(String color, String make) {
        this.color = color;
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public String getMake() {
        return make;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMake(String make) {
        this.make = make;
    }
}

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        var om = new ObjectMapper();
        Car car = new Car("Red", "Tesla");
        try {
            var carJson = om.writeValueAsString(car);
            System.out.println(carJson);
        } catch (Exception e) {
            throw e;
        }
    }
}