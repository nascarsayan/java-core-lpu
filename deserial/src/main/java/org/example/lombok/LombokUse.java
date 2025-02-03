package org.example.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
@Data // Lombok generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Required for Jackson deserialization
@AllArgsConstructor // Generates constructor with all fields
class Car {
    private String color;
    private String make;
}

public class LombokUse {
    public static void main(String[] args) throws JsonProcessingException {
        // Initialize ObjectMapper
        var om = new ObjectMapper();

        // Serialization
        Car car = new Car("Red", "Tesla");
        String carJson = om.writeValueAsString(car);
        System.out.println("Serialized JSON: " + carJson);

        // Deserialization with extra fields
        String jsonData = """
        {
            "make":"Tesla",
            "color":"Blue",
            "someFieldNotPresentInClass": "foobar"
        }
        """;

        Car car2 = om.readValue(jsonData, Car.class);
        System.out.printf("Deserialized Car: (%s, %s)\n", car2.getMake(), car2.getColor());
    }
}
