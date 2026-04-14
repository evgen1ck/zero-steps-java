package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCar {

    @Test
    public void testCreateByColor() throws ColorException {
        Car car = new Car(10, 20, Color.BLUE, "Lada");
        assertAll(
                () -> assertEquals(10, car.getX()),
                () -> assertEquals(20, car.getY()),
                () -> assertEquals(Color.BLUE, car.getColor()),
                () -> assertEquals("Lada", car.getModel())
        );
    }

    @Test
    public void testCreateByString() throws ColorException {
        Car car = new Car(1, 2, "GREEN", "BMW");
        assertEquals(Color.GREEN, car.getColor());
        assertEquals("BMW", car.getModel());
    }

    @Test
    public void testCreateDefault() throws ColorException {
        Car car = new Car("Audi");
        assertEquals(0, car.getX());
        assertEquals(0, car.getY());
        assertEquals(Color.RED, car.getColor());
    }

    @Test
    public void testMove() throws ColorException {
        Car car = new Car(10, 20, Color.RED, "BMW");
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
    public void testSetColorByColor() throws ColorException {
        Car car = new Car("Audi");
        car.setColor(Color.BLUE);
        assertEquals(Color.BLUE, car.getColor());
    }

    @Test
    public void testSetColorByString() throws ColorException {
        Car car = new Car("Audi");
        car.setColor("GREEN");
        assertEquals(Color.GREEN, car.getColor());
    }

    @Test
    public void testSetModel() throws ColorException {
        Car car = new Car("Audi");
        car.setModel("Lada");
        assertEquals("Lada", car.getModel());
    }

    @Test
    public void testWrongColor() {
        try {
            new Car(0, 0, "YELLOW", "Ford");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
        try {
            new Car(0, 0, (Color) null, "Ford");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
        try {
            new Car(0, 0, (String) null, "Ford");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
    }

    @Test
    public void testEquals() throws ColorException {
        Car c1 = new Car(0, 0, Color.RED, "Opel");
        Car c2 = new Car(0, 0, Color.RED, "Opel");
        Car c3 = new Car(0, 0, Color.GREEN, "Opel");
        Car c4 = new Car(0, 0, Color.RED, "Ford");
        Car c5 = new Car(1, 0, Color.RED, "Opel");
        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertNotEquals(c1, c4);
        assertNotEquals(c1, c5);
        assertNotEquals(c1, null);
        assertNotEquals(c1, "string");
    }
}
