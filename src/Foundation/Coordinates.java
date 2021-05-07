package Foundation;

import javax.xml.bind.annotation.*;

@XmlType(name = "coordinates")
@XmlRootElement
public class Coordinates {
    @XmlElement
    private int x; // => -800
    @XmlElement
    private Integer y; // =< 990 & =! null


    // Конструктор для Координат поля
    public Coordinates(int x, Integer Y) {
        this.x = x;
        this.y = y;
    }


    // Конструктор Координат
    public Coordinates() {}

    // Для написания координат в строковом виде
    @Override
    public String toString() {
        return "Coordinates{ x=" + x
                + ", y=" + y + '}';
    }

    public int getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
