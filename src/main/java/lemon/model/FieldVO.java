package lemon.model;

import lemon.FieldType;
import lemon.util.StringUtils;

/**
 * 属性
 * @author Ricky Fung
 */
public class FieldVO {
    private FieldType type;
    private String name;
    private String initialUpper;
    private String boolType;    // 0 or 1

    private static final String TRUE = "1";
    private static final String FALSE = "0";

    public FieldVO(FieldType type, String name) {
        this.type = type;
        this.name = name;
        this.initialUpper = StringUtils.capitalize(name);
        this.boolType = type==FieldType.Boolean ? TRUE:FALSE;
    }

    public FieldType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getInitialUpper() {
        return initialUpper;
    }

    public String getBoolType() {
        return boolType;
    }

}
