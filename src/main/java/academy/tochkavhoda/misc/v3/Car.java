package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;

import java.util.Objects;

public class Car implements Movable, Colored {
    private int x;
    private int y;
    private Color color;
    private String model;

    public Car(int x, int y, Color color, String model) throws ColorException {
        this.x = x;
        this.y = y;
        this.model = model;
        setColor(color);
    }

    public Car(int x, int y, String colorString, String model) throws ColorException {
        this(x, y, Color.colorFromString(colorString), model);
    }

    public Car(String model) throws ColorException {
        this(0, 0, Color.RED, model);
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
