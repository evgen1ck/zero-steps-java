package academy.tochkavhoda.misc.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFlag {

    @Test
    public void testCreate() {
        Flag flag = new Flag(10, 20, 30, 40, 1);
        assertEquals(10, flag.getX());
        assertEquals(20, flag.getY());
        assertEquals(30, flag.getWidth());
        assertEquals(40, flag.getHeight());
        assertEquals(1, flag.getColor());
    }

    @Test
    public void testMove() {
        Flag flag = new Flag(30, 40, 1);
        flag.moveRel(5, 10);
        assertEquals(5, flag.getX());
        assertEquals(10, flag.getY());
        flag.moveTo(100, 100);
        assertEquals(100, flag.getX());
        assertEquals(100, flag.getY());
    }

    @Test
    public void testResize() {
        Flag flag = new Flag(30, 40, 1);
        flag.resize(2);
        assertEquals(60, flag.getWidth());
        assertEquals(80, flag.getHeight());
    }

    @Test
    public void testStretch() {
        Flag flag = new Flag(30, 40, 1);
        flag.stretch(2, 3);
        assertEquals(60, flag.getWidth());
        assertEquals(120, flag.getHeight());
    }

    @Test
    public void testEquals() {
        Flag f1 = new Flag(0, 0, 10, 20, 1);
        Flag f2 = new Flag(0, 0, 10, 20, 1);
        Flag f3 = new Flag(0, 0, 10, 20, 2);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
    }
}
