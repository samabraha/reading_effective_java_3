package chapter_11;

public class LazyInit {
    public static void main(String[] args) {
        FieldType field =
    }
}


/** Double-check idion */
class FieldType {
    private volatile FieldType field;

    private FieldType getField() {
        FieldType result = field;

        if (result == null) {
            synchronized (this) {
                if (field == null) {
                    result = computeFieldValue();
                }
            }
        }
        return result;
    }

    private FieldType computeFieldValue() {
        return null;
    }


}