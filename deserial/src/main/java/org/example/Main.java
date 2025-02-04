package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

// This is required to ignore someFieldNotPresentInClass key inside the JSON in deserialization.
@JsonIgnoreProperties(ignoreUnknown = true)
class Car {
    String Color;
    String make;

    public Car() {}

    Car(String color, String make) {
        this.Color = color;
        this.make = make;
    }

    public String getColor() {
        return Color;
    }

    public String getMake() {
        return make;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public void setMake(String make) {
        this.make = make;
    }
}

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        // Serialization.
        var om = new ObjectMapper();
        // You can configure the object mapper instance to ignore fields too, instead of decorating each class.
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Car car = new Car("Red", "Tesla");
        try {
            String carJson = om.writeValueAsString(car);
            System.out.println(carJson);
        } catch (Exception e) {
            throw e;
        }

        // Deserialization.
        String jsonData = """
{
    "make":"Tesla",
    "color":"Blue",
    "someFieldNotPresentInClass": "foobar"
}
""";
        try {
            var car2 = om.readValue(jsonData, Car.class);
            System.out.printf("Car2 : (%s, %s)\n", car2.make, car2.Color);
        } catch (Exception e) {
            throw e;
        }
    }
}