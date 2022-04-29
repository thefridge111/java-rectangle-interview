package interview.rectangle.model

import spock.lang.Specification
import spock.lang.Unroll

class CoordinateTest extends Specification {

    @Unroll
    def "Throws exception on null coordinate"(Double x, Double y) {
        when:
        new Coordinate(x, y);

        then:
        thrown Exception;

        where:
        x    | y
        1    | null
        null | 1
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
}
