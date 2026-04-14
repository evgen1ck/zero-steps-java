package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;
import academy.tochkavhoda.iface.v3.Resizable;

import java.util.Objects;

public class Balloon implements Movable, Resizable, Colored {
    private int x;
    private int y;
    private int radius;
    private Color color;

    public Balloon(int x, int y, int radius, Color color) throws ColorException {
        this.x = x;
        this.y = y;
        this.radius = radius;
        setColor(color);
    }

    public Balloon(int x, int y, int radius, String colorString) throws ColorException {
        this(x, y, radius, Color.colorFromString(colorString));
    }

    public Balloon(int radius, Color color) throws ColorException {
        this(0, 0, radius, color);
    }

    public Balloon(int radius, String colorString) throws ColorException {
        this(0, 0, radius, Color.colorFromString(colorString));
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
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
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
