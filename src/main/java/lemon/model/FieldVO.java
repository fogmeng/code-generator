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
    private int boolType;    // 0 or 1

    public FieldVO(FieldType type, String name) {
        this.type = type;
        this.name = name;
        this.initialUpper = StringUtils.capitalize(name);
        this.boolType = type==FieldType.Boolean ? 1:0;
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

    public int getBoolType() {
        return boolType;
    }

}
