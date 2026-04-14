package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;

import java.util.Objects;

public class Car implements Movable, Colored {
    private int x;
    private int y;
    private int color;
    private String model;

    public Car(int x, int y, int color, String model) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.model = model;
    }

    public Car(String model) {
        this(0, 0, 1, model);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return x == car.x && y == car.y && color == car.color && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color, model);
    }
}
