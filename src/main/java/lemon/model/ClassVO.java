package lemon.model;

import java.util.List;

/**
 *
 * @author Ricky Fung
 */
public class ClassVO {
    private PackageVO packageVO;
    private List<ImportVO> imports;
    private List<String> modifiers; //修饰符：public, protected, private, final, static, abstract and interface;
    private String className;
    private List<String> interfaces;   //接口
    private String superClass;  //父类
    private List<FieldVO> fields;

    public PackageVO getPackage() {
        return packageVO;
    }

    public void setPackage(PackageVO packageVO) {
        this.packageVO = packageVO;
    }

    public List<ImportVO> getImports() {
        return imports;
    }

    public void setImports(List<ImportVO> imports) {
        this.imports = imports;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public List<FieldVO> getFields() {
        return fields;
    }

    public void setFields(List<FieldVO> fields) {
        this.fields = fields;
    }
}
