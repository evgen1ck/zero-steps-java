package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCar {

    @Test
    public void testCreate() {
        Car car = new Car(10, 20, 3, "Lada");
        assertAll(
                () -> assertEquals(10, car.getX()),
                () -> assertEquals(20, car.getY()),
                () -> assertEquals(3, car.getColor()),
                () -> assertEquals("Lada", car.getModel())
        );
    }

    @Test
    public void testMove() {
        Car car = new Car(10, 20, 1, "BMW");
        car.moveRel(5, 5);
        assertEquals(15, car.getX());
        assertEquals(25, car.getY());
        car.moveTo(100, 200);
        assertEquals(100, car.getX());
        assertEquals(200, car.getY());
        car.moveTo(new Point(1, 2));
        assertEquals(1, car.getX());
        assertEquals(2, car.getY());
    }

    @Test
    public void testSetColor() {
        Car car = new Car("Audi");
        assertEquals(1, car.getColor());
        car.setColor(5);
        assertEquals(5, car.getColor());
    }

    @Test
    public void testEquals() {
        Car c1 = new Car(0, 0, 1, "Opel");
        Car c2 = new Car(0, 0, 1, "Opel");
        Car c3 = new Car(0, 0, 2, "Opel");
        Car c4 = new Car(0, 0, 1, "Ford");
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1, c3);
        assertNotEquals(c1, c4);
    }
}
