package academy.tochkavhoda.misc.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBalloon {

    @Test
    public void testCreate() {
        Balloon balloon = new Balloon(10, 20, 5, 2);
        assertEquals(10, balloon.getX());
        assertEquals(20, balloon.getY());
        assertEquals(5, balloon.getRadius());
        assertEquals(2, balloon.getColor());
    }

    @Test
    public void testMove() {
        Balloon balloon = new Balloon(10, 1);
        balloon.moveRel(3, 4);
        assertEquals(3, balloon.getX());
        assertEquals(4, balloon.getY());
        balloon.moveTo(0, 0);
        assertEquals(0, balloon.getX());
        assertEquals(0, balloon.getY());
    }

    @Test
    public void testResize() {
        Balloon balloon = new Balloon(10, 1);
        balloon.resize(2);
        assertEquals(20, balloon.getRadius());
        balloon.resize(0.5);
        assertEquals(10, balloon.getRadius());
    }

    @Test
    public void testSetColor() {
        Balloon balloon = new Balloon(10, 1);
        balloon.setColor(7);
        assertEquals(7, balloon.getColor());
    }

    @Test
    public void testEquals() {
        Balloon b1 = new Balloon(1, 2, 3, 4);
        Balloon b2 = new Balloon(1, 2, 3, 4);
        Balloon b3 = new Balloon(1, 2, 3, 5);
        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
        assertNotEquals(b1, b3);
    }
}
