package Foundation;

public class Location {
    private long x;
    private long y;
    private Integer z;

    public Location(long x, long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getY() {
        return y;
    }

    public long getX() {
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


