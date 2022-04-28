package interview.rectangle.model;

public class Coordinate {
    private final Double x;
    private final Double y;

    public Coordinate(Double x, Double y) throws Exception {
        if (x == null || y == null) {
            throw new Exception("Cannot have null coordinate!");
        }
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public boolean isEqual(Coordinate otherCoord) {
        return ((this.x == otherCoord.getX()) && (this.y == otherCoord.getY()));
    }
}
