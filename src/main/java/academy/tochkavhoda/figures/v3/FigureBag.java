package academy.tochkavhoda.figures.v3;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class FigureBag {

    private final Bag<Figure> bag = new HashBag<>();

    public void addFigure(Figure figure) {
        bag.add(figure);
    }

    public void addFigure(Figure figure, int copies) {
        bag.add(figure, copies);
    }

    public boolean removeFigure(Figure figure) {
        return bag.remove(figure);
    }

    public boolean removeFigure(Figure figure, int copies) {
        return bag.remove(figure, copies);
    }

    public int getFigureCopies(Figure figure) {
        return bag.getCount(figure);
    }
}
