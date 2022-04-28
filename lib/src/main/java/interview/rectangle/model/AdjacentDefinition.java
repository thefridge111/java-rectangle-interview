package interview.rectangle.model;

public enum AdjacentDefinition {
    PROPER ("Adjacent (Proper)"),
    SUB ("Adjacent (Sub-line)"),
    PARTIAL ("Adjacent (Partial)"),
    NONE ("Not adjacent");

    private final String value;

    AdjacentDefinition(String value) {
        this.value = value;
    }
}
