package interview.rectangle.model;

public enum IntersectionDefinition {
    NONE ("No Intersection"),
    INTERSECT ("Intersection");

    private final String value;

    IntersectionDefinition(String value) {
        this.value = value;
    }
}
