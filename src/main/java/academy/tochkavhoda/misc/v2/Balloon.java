package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import academy.tochkavhoda.iface.v2.Resizable;

import java.util.Objects;

public class Balloon implements Movable, Resizable, Colored {
    private int x;
    private int y;
    private int radius;
    private int color;

    public Balloon(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public Balloon(int radius, int color) {
        this(0, 0, radius, color);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double ratio) {
        this.radius = (int) (this.radius * ratio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balloon balloon = (Balloon) o;
        return x == balloon.x && y == balloon.y && radius == balloon.radius && color == balloon.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, radius, color);
    }
}
