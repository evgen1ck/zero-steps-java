package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBalloon {

    @Test
    public void testCreateByColor() throws ColorException {
        Balloon balloon = new Balloon(10, 20, 5, Color.BLUE);
        assertEquals(10, balloon.getX());
        assertEquals(20, balloon.getY());
        assertEquals(5, balloon.getRadius());
        assertEquals(Color.BLUE, balloon.getColor());
    }

    @Test
    public void testCreateByString() throws ColorException {
        Balloon balloon = new Balloon(10, 20, 5, "GREEN");
        assertEquals(Color.GREEN, balloon.getColor());
    }

    @Test
    public void testCreateShortByColor() throws ColorException {
        Balloon balloon = new Balloon(10, Color.RED);
        assertEquals(0, balloon.getX());
        assertEquals(0, balloon.getY());
        assertEquals(10, balloon.getRadius());
        assertEquals(Color.RED, balloon.getColor());
    }

    @Test
    public void testCreateShortByString() throws ColorException {
        Balloon balloon = new Balloon(10, "BLUE");
        assertEquals(Color.BLUE, balloon.getColor());
    }

    @Test
    public void testMove() throws ColorException {
        Balloon balloon = new Balloon(10, Color.RED);
        balloon.moveRel(3, 4);
        assertEquals(3, balloon.getX());
        assertEquals(4, balloon.getY());
        balloon.moveTo(0, 0);
        assertEquals(0, balloon.getX());
        assertEquals(0, balloon.getY());
    }

    @Test
    public void testResize() throws ColorException {
        Balloon balloon = new Balloon(10, Color.RED);
        balloon.resize(2);
        assertEquals(20, balloon.getRadius());
        balloon.resize(0.5);
        assertEquals(10, balloon.getRadius());
        balloon.setRadius(7);
        assertEquals(7, balloon.getRadius());
    }

    @Test
    public void testSetColor() throws ColorException {
        Balloon balloon = new Balloon(10, Color.RED);
        balloon.setColor(Color.BLUE);
        assertEquals(Color.BLUE, balloon.getColor());
        balloon.setColor("GREEN");
        assertEquals(Color.GREEN, balloon.getColor());
    }

    @Test
    public void testWrongColor() {
        try {
            new Balloon(10, "YELLOW");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
        try {
            new Balloon(10, (Color) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
        try {
            new Balloon(10, (String) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
        try {
            new Balloon(0, 0, 10, (String) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
    }

    @Test
    public void testEquals() throws ColorException {
        Balloon b1 = new Balloon(1, 2, 3, Color.RED);
        Balloon b2 = new Balloon(1, 2, 3, Color.RED);
        Balloon b3 = new Balloon(1, 2, 3, Color.BLUE);
        Balloon b4 = new Balloon(1, 2, 4, Color.RED);
        assertEquals(b1, b2);
        assertNotEquals(b1, b3);
        assertNotEquals(b1, b4);
        assertNotEquals(b1, null);
    }
}
