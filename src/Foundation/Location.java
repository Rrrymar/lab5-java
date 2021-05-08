package Foundation;

public class Location {
    private int x;
    private int y;
    private Integer z;

    public Location(Long x, Long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Integer getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}


