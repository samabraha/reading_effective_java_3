package chapter_11;



public class LazyInit {
}

class FieldHolder {
    public static final FieldType field = computeFieldValue();


}

class FieldType {
    private static FieldType getField() { return FieldHolder.field; }
}