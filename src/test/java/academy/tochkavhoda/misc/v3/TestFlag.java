package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFlag {

    @Test
    public void testCreateByColor() throws ColorException {
        Flag flag = new Flag(10, 20, 30, 40, Color.GREEN);
        assertEquals(10, flag.getX());
        assertEquals(20, flag.getY());
        assertEquals(30, flag.getWidth());
        assertEquals(40, flag.getHeight());
        assertEquals(Color.GREEN, flag.getColor());
    }

    @Test
    public void testCreateByString() throws ColorException {
        Flag flag = new Flag(10, 20, 30, 40, "BLUE");
        assertEquals(Color.BLUE, flag.getColor());
    }

    @Test
    public void testCreateShortByColor() throws ColorException {
        Flag flag = new Flag(30, 40, Color.RED);
        assertEquals(0, flag.getX());
        assertEquals(0, flag.getY());
        assertEquals(30, flag.getWidth());
        assertEquals(40, flag.getHeight());
    }

    @Test
    public void testCreateShortByString() throws ColorException {
        Flag flag = new Flag(30, 40, "BLUE");
        assertEquals(Color.BLUE, flag.getColor());
    }

    @Test
    public void testMove() throws ColorException {
        Flag flag = new Flag(30, 40, Color.RED);
        flag.moveRel(5, 10);
        assertEquals(5, flag.getX());
        assertEquals(10, flag.getY());
        flag.moveTo(100, 100);
        assertEquals(100, flag.getX());
        assertEquals(100, flag.getY());
    }

    @Test
    public void testResize() throws ColorException {
        Flag flag = new Flag(30, 40, Color.RED);
        flag.resize(2);
        assertEquals(60, flag.getWidth());
        assertEquals(80, flag.getHeight());
    }

    @Test
    public void testStretch() throws ColorException {
        Flag flag = new Flag(30, 40, Color.RED);
        flag.stretch(2, 3);
        assertEquals(60, flag.getWidth());
        assertEquals(120, flag.getHeight());
    }

    @Test
    public void testSetColor() throws ColorException {
        Flag flag = new Flag(30, 40, Color.RED);
        flag.setColor(Color.BLUE);
        assertEquals(Color.BLUE, flag.getColor());
        flag.setColor("GREEN");
        assertEquals(Color.GREEN, flag.getColor());
    }

    @Test
    public void testWrongColor() {
        try {
            new Flag(10, 20, "YELLOW");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
        try {
            new Flag(10, 20, (Color) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
        try {
            new Flag(10, 20, (String) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
        try {
            new Flag(0, 0, 10, 20, (String) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
    }

    @Test
    public void testEquals() throws ColorException {
        Flag f1 = new Flag(0, 0, 10, 20, Color.RED);
        Flag f2 = new Flag(0, 0, 10, 20, Color.RED);
        Flag f3 = new Flag(0, 0, 10, 20, Color.BLUE);
        Flag f4 = new Flag(0, 0, 10, 30, Color.RED);
        assertEquals(f1, f2);
        assertNotEquals(f1, f3);
        assertNotEquals(f1, f4);
        assertNotEquals(f1, null);
    }
}
