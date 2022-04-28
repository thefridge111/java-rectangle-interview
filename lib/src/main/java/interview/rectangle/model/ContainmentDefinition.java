package interview.rectangle.model;

public enum ContainmentDefinition {
    NONE ("No containment"),
    COMPLETE ("Containment");

    private final String value;

    ContainmentDefinition(String value) {
        this.value = value;
    }
}
