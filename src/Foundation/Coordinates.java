package Foundation;


public class Coordinates {
    private int x; // => -800
    private Integer y; // =< 990 & =! null


    // Конструктор для Координат поля
    public Coordinates(Integer x, Integer Y) {
        this.x = x;
        this.y = y;
    }



    // Для написания координат в строковом виде
    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
