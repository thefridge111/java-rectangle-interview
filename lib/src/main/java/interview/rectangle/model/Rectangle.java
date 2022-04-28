package interview.rectangle.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle implements Shape {
    Coordinate topLeft;
    Coordinate topRight;
    Coordinate bottomLeft;
    Coordinate bottomRight;

    Edge leftEdge;
    Edge topEdge;
    Edge rightEdge;
    Edge bottomEdge;

    Double area;

    public Rectangle(Coordinate topLeft, Coordinate topRight, Coordinate bottomLeft, Coordinate bottomRight) throws Exception {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;

        this.leftEdge = checkEdge(bottomLeft, topLeft);
        this.topEdge = checkEdge(topLeft, topRight);
        this.rightEdge = checkEdge(topRight, bottomRight);
        this.bottomEdge = checkEdge(bottomLeft, bottomRight);

        verifyCornerAngles();

        this.area = this.leftEdge.getLength() * this.topEdge.getLength();
    }

    private Edge checkEdge(Coordinate start, Coordinate end) throws Exception {
        Edge newEdge = new Edge(start, end);

        if (!newEdge.getSlope().isInfinite() && newEdge.getSlope() != 0) {
            throw new Exception("Rotated rectangles not supported!");
        }

        return newEdge;
    }

    private void verifyCornerAngles() throws Exception {
        if (!this.leftEdge.isParallel(this.rightEdge) && !this.topEdge.isParallel(this.bottomEdge)) {
            throw new Exception("Only rectangles are supported! No parallelograms!");
        }
    }

    public AdjacentDefinition isAdjacent(Rectangle otherRectangle) throws Exception {
        ArrayList<AdjacentDefinition> definitionValues = new ArrayList<>(Arrays.asList(
                AdjacentDefinition.NONE,
                AdjacentDefinition.PARTIAL,
                AdjacentDefinition.SUB,
                AdjacentDefinition.PROPER)
        );

        ArrayList<AdjacentDefinition> adjacencies = new ArrayList<>();
        adjacencies.add(this.leftEdge.findAdjacency(otherRectangle.getRightEdge()));
        adjacencies.add(this.topEdge.findAdjacency(otherRectangle.getBottomEdge()));
        adjacencies.add(this.rightEdge.findAdjacency(otherRectangle.getLeftEdge()));
        adjacencies.add(this.bottomEdge.findAdjacency(otherRectangle.getTopEdge()));

        int currAdjValue = 0;
        int indexValue = 0;

        // Find 'highest' adjacent value
        for(int i = 0; i < adjacencies.size(); i++) {
            indexValue = definitionValues.indexOf(adjacencies.get(i));

            if (indexValue > currAdjValue) {
                currAdjValue = indexValue;
            }
        }

        return definitionValues.get(indexValue);
    }

    public IntersectionDefinition intersects(Rectangle otherRectangle) throws Exception {
        boolean leftIntersect = this.leftEdge.instersects(otherRectangle.getTopEdge()) || this.leftEdge.instersects(otherRectangle.getBottomEdge());
        boolean topIntersect = this.topEdge.instersects(otherRectangle.getLeftEdge()) || this.topEdge.instersects(otherRectangle.getRightEdge());
        boolean rightIntersect = this.rightEdge.instersects(otherRectangle.getTopEdge()) || this.rightEdge.instersects(otherRectangle.getBottomEdge());
        boolean bottomIntersect = this.bottomEdge.instersects(otherRectangle.getLeftEdge()) || this.bottomEdge.instersects(otherRectangle.getRightEdge());

        if (leftIntersect || topIntersect || rightIntersect || bottomIntersect) {
            return IntersectionDefinition.INTERSECT;
        }

        return IntersectionDefinition.NONE;
    }

    public ContainmentDefinition contains(Rectangle otherRectangle) {
        if (otherRectangle.getArea() > this.area) {
            return ContainmentDefinition.NONE;
        }

        if ((otherRectangle.topLeft.getX() < this.topLeft.getX()) ||
                (otherRectangle.bottomLeft.getX() < this.bottomLeft.getX()) ||
                (otherRectangle.topRight.getX() > this.topRight.getX()) ||
                (otherRectangle.bottomRight.getX() > this.bottomRight.getX())) {
            return ContainmentDefinition.NONE;
        }

        if ((otherRectangle.topLeft.getY() > this.topLeft.getY()) ||
                (otherRectangle.bottomLeft.getY() < this.bottomLeft.getY()) ||
                (otherRectangle.topRight.getY() > this.topRight.getY()) ||
                (otherRectangle.bottomRight.getY() < this.bottomRight.getY())) {
            return ContainmentDefinition.NONE;
        }

        return ContainmentDefinition.COMPLETE;
    }

    public Coordinate getTopLeft() {
        return topLeft;
    }

    public Coordinate getTopRight() {
        return topRight;
    }

    public Coordinate getBottomLeft() {
        return bottomLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }

    public Edge getLeftEdge() {
        return leftEdge;
    }

    public Edge getTopEdge() {
        return topEdge;
    }

    public Edge getRightEdge() {
        return rightEdge;
    }

    public Edge getBottomEdge() {
        return bottomEdge;
    }

    public Double getArea() {
        return area;
    }
}
