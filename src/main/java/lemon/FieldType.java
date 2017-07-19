package lemon;

/**
 * @author Ricky Fung
 */
public enum FieldType {
    Byte(java.lang.Byte.class.getName(), "byte", false),
    Short(java.lang.Short.class.getName(), "short", false),
    Integer(java.lang.Integer.class.getName(), "int", false),
    Float(java.lang.Float.class.getName(), "float", false),
    Double(java.lang.Double.class.getName(), "double", false),
    Long(java.lang.Long.class.getName(), "long", false),
    Character(java.lang.Character.class.getName(), "char", false),
    Boolean(java.lang.Boolean.class.getName(), "boolean", false),

    String(java.lang.String.class.getName(), "NA", false),
    BigDecimal(java.math.BigDecimal.class.getName(), "NA", true),
    Date(java.util.Date.class.getName(), "NA", true);

    private String type;
    private String primitiveType;
    private boolean needImported;

    FieldType(String type, String primitiveType, boolean needImported) {
        this.type = type;
        this.primitiveType = primitiveType;
        this.needImported= needImported;
    }

    public java.lang.String getType() {
        return type;
    }

    public java.lang.String getPrimitiveType() {
        return primitiveType;
    }

    public boolean isNeedImported() {
        return needImported;
    }
}
