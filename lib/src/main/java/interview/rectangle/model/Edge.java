package interview.rectangle.model;

public class Edge {
    private Coordinate start;
    private Coordinate end;

    private Double slope;
    private Double xIntercept;
    private Double yIntercept;
    private Double length;

    public Edge(Coordinate start, Coordinate end) throws Exception {
        this.start = start;
        this.end = end;

        refreshValues();
    }

    private void refreshValues() throws Exception {
        if (this.start.isEqual(this.end)) {
            throw new Exception("Edge start and end cannot equal!");
        }
        calculateSlope();
        calculateYIntercept();
        calculateXIntercept();
        calculateLength();
    }

    private void calculateSlope() {
        /**
         * Horizontal line = 0 / n -> 0
         * Vertical line = n / 0 -> Infinity
         */
        this.slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    private void calculateXIntercept() {
        try {
            if (this.yIntercept == null) {
                calculateYIntercept();
            }
            this.xIntercept = this.yIntercept.isInfinite() ? this.start.getX() : ((this.yIntercept * -1) / this.slope);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.xIntercept = Double.NaN;
        }
    }

    private void calculateYIntercept() {
        if (this.slope == null) {
            calculateSlope();
        }
        this.yIntercept = (this.start.getY() - (this.start.getX() * this.slope));
    }

    private void calculateLength() {
        double xSolution = Math.pow((this.end.getX() - this.start.getX()), 2);
        double ySolution = Math.pow((this.end.getY() - this.start.getY()), 2);

        this.length = Math.sqrt(xSolution + ySolution);
    }

    public boolean instersects(Edge otherEdge) throws Exception {
        Coordinate intersection = getIntersectionCoordinate(otherEdge);

        if (intersection.getX().isInfinite() && intersection.getY().isInfinite()) {
            return false;
        }

        return true;
    }

    /**
     * Finds intercept point if this edge and otherEdge intersect
     * @param otherEdge Other edge that potentially intercepts
     * @return new Coordinate of interception point or Infinity if there is no intercept or edge is adjacent.
     */
    public Coordinate getIntersectionCoordinate(Edge otherEdge) throws Exception {
        if (isParallel(otherEdge)) {
            return new Coordinate(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        Double yEdgeIntercept = ((otherEdge.getyIntercept() - this.yIntercept) / (this.slope - otherEdge.getSlope())) + this.yIntercept;

        if (yEdgeIntercept.isInfinite() || yEdgeIntercept.isNaN()) {
            return new Coordinate(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        return new Coordinate(findXCoord(yEdgeIntercept), yEdgeIntercept);
    }

    public AdjacentDefinition findAdjacency(Edge otherEdge) throws Exception {
        if (!isAdjacent(otherEdge)) {
            return AdjacentDefinition.NONE;
        }

        Coordinate startDiff = new Coordinate(
                (double) Integer.signum((this.start.getX().intValue() - otherEdge.getStart().getX().intValue())),
                (double) Integer.signum(this.start.getY().intValue() - otherEdge.getStart().getY().intValue())
        );
        Coordinate endDiff = new Coordinate(
                (double) Integer.signum((this.end.getX().intValue() - otherEdge.getStart().getX().intValue())),
                (double) Integer.signum(this.end.getY().intValue() - otherEdge.getStart().getY().intValue())
        );

        if ((startDiff.getX() == 0 && startDiff.getY() == 0) && (endDiff.getX() == 0 && endDiff.getY() == 0)) {
            return AdjacentDefinition.PROPER;
        } else if ((startDiff.getX() == endDiff.getX() || startDiff.getY() == endDiff.getY())) {
            return AdjacentDefinition.SUB;
        } else {
            return AdjacentDefinition.PARTIAL;
        }
    }

    public boolean isAdjacent(Edge otherEdge) throws Exception {
        if (!isParallel(otherEdge)) {
            return false;
        }

        Coordinate intercept = this.getIntersectionCoordinate(otherEdge);

        if (!intercept.getX().isInfinite() && !intercept.getY().isInfinite()) {
            return false;
        }

        return (otherEdge.findYCoord(this.start.getX()) == this.findYCoord(this.start.getX())) ||
                (otherEdge.findYCoord(this.end.getX()) == this.findYCoord(this.end.getX()));
    }

    public boolean isParallel(Edge otherEdge) {
        return (otherEdge.slope == this.slope);
    }

    public Double findXCoord(Double yValue) {
        // Horizontal line - no X intercept
        if (this.slope == 0) {
            return Double.NaN;
        // Vertical line - x value is constant
        } else if (this.slope.isInfinite()) {
            return this.start.getX();
        }

        return ((yValue - this.yIntercept) / this.slope);
    }

    public Double findYCoord(Double xValue) {
        if (this.slope.isInfinite()) {
            return Double.NaN;
        }
        return ((this.slope * xValue) + this.yIntercept);
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) throws Exception {
        this.start = start;

        refreshValues();
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate end) throws Exception {
        this.end = end;

        refreshValues();
    }

    public Double getSlope() {
        return slope;
    }

    public Double getxIntercept() {
        return xIntercept;
    }

    public Double getyIntercept() {
        return yIntercept;
    }

    public Double getLength() {
        return length;
    }
}
