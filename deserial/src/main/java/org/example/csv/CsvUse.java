package org.example.csv;

import com.opencsv.bean.CsvBindByName;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvUse {
    @Data // Lombok generates getters, setters, toString, equals, and hashCode
    @NoArgsConstructor // Explicitly add NoArgsConstructor
    @AllArgsConstructor // Optional, but useful for the parameterized constructor
    public static class Car {
        @CsvBindByName
        private String color;
        @CsvBindByName
        private String make;
    }
    public static void main(String[] args) throws IOException {
        String[] header = {"color", "make"};
        String[] carData = {"Red", "Tesla"};

        try (CSVWriter writer = new CSVWriter(new FileWriter("cars.csv"))) {
            writer.writeNext(header);
            writer.writeNext(carData);
        }

        Reader reader = new FileReader("cars.csv");
        CsvToBean<Car> csvToBean = new CsvToBeanBuilder<Car>(reader)
                .withType(Car.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<Car> cars = csvToBean.parse();
        for (Car car : cars) {
            System.out.printf("Car: (%s, %s)\n", car.getColor(), car.getMake());
        }
    }
}
