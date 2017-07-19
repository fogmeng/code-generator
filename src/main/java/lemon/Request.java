package lemon;

import lemon.model.FieldVO;
import lemon.util.StringUtils;
import java.util.*;

/**
 *
 * @author Ricky Fung
 */
public class Request {
    private final String packageName;
    private final String className;
    private final List<String> interfaces;
    private final String superClass;
    private final List<FieldVO> fields;

    Request(Builder builder) {
        this.packageName = builder.packageName;
        this.className = builder.className;
        this.interfaces = builder.interfaces!=null ? new ArrayList<>(builder.interfaces) : null;
        this.superClass = builder.superClass;
        this.fields = builder.fields!=null ? new ArrayList<>(builder.fields.values()) : null;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public String getSuperClass() {
        return superClass;
    }

    public List<FieldVO> getFields() {
        return fields;
    }

    public static class Builder {
        private String packageName;
        private String className;
        private Set<String> interfaces;
        private String superClass;
        private Map<String, FieldVO> fields;

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder addInterface(String interfaceName) {
            if(StringUtils.isBlank(interfaceName)) {
                throw new IllegalArgumentException("interface can not be null");
            }
            if(interfaces==null) {
                interfaces = new LinkedHashSet<>();
            }
            if(interfaces.contains(interfaceName)) {
                throw new IllegalArgumentException("duplicated interface:"+interfaceName);
            }
            interfaces.add(interfaceName);
            return this;
        }

        public Builder superClass(String superClass) {
            this.superClass = superClass;
            return this;
        }

        public Builder addField(FieldVO field) {
            if(field==null) {
                throw new NullPointerException("field is null");
            }
            if(StringUtils.isBlank(field.getName())) {
                throw new IllegalArgumentException("field name can not be null");
            }
            if(fields==null) {
                fields = new HashMap<>();
            }
            if(fields.containsKey(field.getName())) {
                throw new IllegalArgumentException("duplicated field:"+field.getName());
            }
            fields.put(field.getName(), field);
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
