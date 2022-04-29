package interview.rectangle.model

import spock.lang.Specification
import spock.lang.Unroll

class EdgeTest extends Specification {

    def "Derived values calculated correctly"(Double x1, Double y1, Double x2, Double y2, Double slope,
                                              Double xIntercept, Double yIntercept, Double length) {
        when:
        Coordinate start = new Coordinate(x1, y1);
        Coordinate end = new Coordinate(x2, y2);

        then:
        Edge edge = new Edge(start, end);
        edge.getSlope() == slope;
        edge.getxIntercept() == xIntercept;
        edge.getyIntercept() == yIntercept;
        edge.getLength() == length;

        where:
        x1 | y1 | x2 | y2 | slope | xIntercept | yIntercept | length
        0  | 0  | 1  | 1  | 1     | 0          | 0          | Math.sqrt(2)
        1  | 1  | 1  | 4  | Double.POSITIVE_INFINITY | 1 | Double.POSITIVE_INFINITY | 3
        1  | 2  | 5  | 2  | 0     | Double.POSITIVE_INFINITY | 2 | 4
    }

    def "NaN not a valid coordinate position"() {
        when:
        Coordinate start = new Coordinate(Double.NaN, y1);
        Coordinate end = new Coordinate(x2, y2);

        new Edge(start, end);

        then:
        thrown Exception;
    }

    def "Changing start or end refreshes derived values"() {
        when:
        Coordinate coord1 = new Coordinate(2, 1);
        Coordinate coord2 = new Coordinate(4, 6);
        Coordinate coord3 = new Coordinate(7, 18);

        Edge edge = new Edge(coord1, coord2);
        double length1 = edge.getLength();
        double slope1 = edge.getSlope();
        double yInt1 = edge.getyIntercept()
        double xInt1 = edge.getxIntercept()

        edge.setEnd(coord3);
        double length2 = edge.getLength();
        double slope2 = edge.getSlope();
        double yInt2 = edge.getyIntercept()
        double xInt2 = edge.getxIntercept()

        edge.setStart(coord2);
        double length3 = edge.getLength();
        double slope3 = edge.getSlope();
        double yInt3 = edge.getyIntercept()
        double xInt3 = edge.getxIntercept()

        then:
        length1 != length2;
        slope1 != slope2;
        yInt1 != yInt2;
        xInt1 != xInt2;

        length1 != length3;
        slope1 != slope3;
        yInt1 != yInt3;
        xInt1 != xInt3;

        length3 != length2;
        slope3 != slope2;
        yInt3 != yInt2;
        xInt3 != xInt2;
    }

    def "Equality works"(Double x1, Double y1, Double x2, Double y2, boolean result) {
        when:
        Coordinate coord1 = new Coordinate(x1, y1);
        Coordinate coord2 = new Coordinate(x2, y2);

        then:
        coord1.isEqual(coord2) == result;

        where:
        x1 | y1 | x2 | y2 | result
        1  | 1  | 1  | 1  | true
        2  | 1  | 1  | 1  | false
        1  | 2  | 1  | 1  | false
        Double.POSITIVE_INFINITY  | 1  | Double.POSITIVE_INFINITY  | 1  | true
        Double.NaN  | 1  | Double.NaN  | 1  | true
    }

    def "Parallel works"() {
        when:
        Coordinate coord1 = new Coordinate(2, 1);
        Coordinate coord2 = new Coordinate(4, 6);
        Coordinate coord3 = new Coordinate(7, 18);

        Edge edge = new Edge(coord1, coord2);
        Edge edge1 = new Edge(coord1, coord2);
        Edge edge2 = new Edge(coord1, coord3);

        then:
        edge.isParallel(edge1);
        !edge.isParallel(edge2);
    }

    def "isAdjacent works"() {
        when:
        Coordinate coord1 = new Coordinate(2, 1);
        Coordinate coord2 = new Coordinate(4, 6);
        Coordinate coord3 = new Coordinate(7, 18);

        Edge edge = new Edge(coord1, coord2);
        Edge edge1 = new Edge(coord1, coord2);
        Edge edge2 = new Edge(coord1, coord3);

        then:
        edge.isAdjacent(edge1);
        !edge.isAdjacent(edge2);
    }
}
