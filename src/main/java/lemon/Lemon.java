package lemon;

import lemon.model.ClassVO;
import lemon.model.FieldVO;
import lemon.model.ImportVO;
import lemon.model.PackageVO;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Ricky Fung
 */
public class Lemon {

    private VelocityEngine ve;

    public Lemon() {
        this("velocity.properties");
    }

    public Lemon(String name) {
        try {
            this.ve = init(name);
        } catch (IOException e) {
            throw new IllegalArgumentException("velocity init failure", e);
        }
    }

    public VelocityEngine init(String name) throws IOException {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
        VelocityEngine ve = new VelocityEngine(props);
        ve.init();
        return ve;
    }

    public String execute(Request request) {

        ClassVO classVO = new ClassVO();
        PackageVO packageVO = new PackageVO();
        packageVO.setName(request.getPackageName());
        classVO.setPackage(packageVO);

        //imports
        if(request.getFields()!=null && request.getFields().size()>0) {

            Set<String> set = new HashSet<>();
            for(FieldVO field : request.getFields()) {
                if(field.getType().isNeedImported()) {
                    set.add(field.getType().getType());
                }
            }

            if(set.size()>0) {
                List<ImportVO> imports = new ArrayList<>(8);
                for (String cls : set) {
                    ImportVO importVO = new ImportVO();
                    importVO.setName(cls);
                    imports.add(importVO);
                }
                classVO.setImports(imports);
            }
        }

        classVO.setClassName(request.getClassName());
        classVO.setSuperClass(request.getSuperClass());
        classVO.setInterfaces(request.getInterfaces());

        classVO.setFields(request.getFields());

        return genCode("/templates/javabean.vm", classVO);
    }

    protected String genCode(String template, ClassVO classVO) {

        Template t = ve.getTemplate(template);

        VelocityContext context = new VelocityContext();
        context.put("vo", classVO);

        StringWriter writer = new StringWriter(1024);
        t.merge(context, writer);

        return writer.toString();
    }
}
