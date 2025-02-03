package org.example.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;

@XmlRootElement
class Car {
    @XmlElement
    String color;

    @XmlElement
    String make;

    public Car() {}

    public Car(String color, String make) {
        this.color = color;
        this.make = make;
    }
}

public class XmlUse {
    public static void main(String[] args) throws JAXBException {
        Car car = new Car("Red", "Tesla");
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(car, System.out);

        String xmlData = """
          <car>
              <color>Blue</color>
              <make>Tesla</make>
          </car>
          """;
        Unmarshaller unmarshaller = context.createUnmarshaller();
        car = (Car) unmarshaller.unmarshal(new StringReader(xmlData));
        System.out.printf("Car: (%s, %s)\n", car.color, car.make);
    }
}