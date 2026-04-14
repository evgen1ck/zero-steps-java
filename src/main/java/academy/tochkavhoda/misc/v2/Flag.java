package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import academy.tochkavhoda.iface.v2.Stretchable;

import java.util.Objects;

public class Flag implements Movable, Stretchable, Colored {
    private int x;
    private int y;
    private int width;
    private int height;
    private int color;

    public Flag(int x, int y, int width, int height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Flag(int width, int height, int color) {
        this(0, 0, width, height, color);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        this.width = (int) (this.width * ratio);
        this.height = (int) (this.height * ratio);
    }

    @Override
    public void stretch(double xRatio, double yRatio) {
        this.width = (int) (this.width * xRatio);
        this.height = (int) (this.height * yRatio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag flag = (Flag) o;
        return x == flag.x && y == flag.y && width == flag.width && height == flag.height && color == flag.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height, color);
    }
}
