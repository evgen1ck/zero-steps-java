package academy.tochkavhoda.colors.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColor {

    @Test
    public void testColorFromString() throws ColorException {
        assertEquals(Color.RED, Color.colorFromString("RED"));
        assertEquals(Color.GREEN, Color.colorFromString("GREEN"));
        assertEquals(Color.BLUE, Color.colorFromString("BLUE"));
    }

    @Test
    public void testColorFromWrongString() {
        try {
            Color.colorFromString("YELLOW");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
    }

    @Test
    public void testColorFromNullString() {
        try {
            Color.colorFromString(null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
    }

    @Test
    public void testErrorCodeString() {
        assertNotNull(ColorErrorCode.WRONG_COLOR_STRING.getErrorString());
        assertNotNull(ColorErrorCode.NULL_COLOR.getErrorString());
        assertFalse(ColorErrorCode.WRONG_COLOR_STRING.getErrorString().isEmpty());
    }

    @Test
    public void testColorExceptionFields() {
        ColorException ex = new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
    }
}
