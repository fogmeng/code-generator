package lemon;

import lemon.model.ClassVO;
import lemon.model.FieldVO;
import lemon.model.ImportVO;
import lemon.model.PackageVO;
import lemon.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.*;

/**
 *
 * @author Ricky Fung
 */
public class Lemon {
    private final File srcPath;

    private VelocityEngine ve;

    public Lemon() {
        this(new Builder());
    }
    public Lemon(Builder builder) {
        this.srcPath = builder.srcPath;
        init("velocity.properties");
    }

    public Response execute(Request req) throws IOException {

        ClassVO classVO = new ClassVO();
        String packageName = null, simpleClassName = null;
        if(StringUtils.isBlank(packageName)) {
            int index = req.getClassName().lastIndexOf(".");
            if(index<1) {
                throw new IllegalArgumentException("could not parse package name.");
            }
            packageName = req.getClassName().substring(0, index);
            simpleClassName = req.getClassName().substring(index+1);
        }
        PackageVO packageVO = new PackageVO();
        packageVO.setName(packageName);
        classVO.setPackage(packageVO);
        classVO.setClassName(simpleClassName);

        //imports
        Set<String> set = new HashSet<>();
        if(req.getFields()!=null && req.getFields().size()>0) {
            for(FieldVO field : req.getFields()) {
                if(field.getType().isNeedImported()) {
                    set.add(field.getType().getType());
                }
            }
        }
        if(StringUtils.isNotBlank(req.getSuperClass())) {
            set.add(req.getSuperClass());
        }
        if(!CollectionUtils.isEmpty(req.getInterfaces())) {
            set.addAll(req.getInterfaces());
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
        classVO.setSuperClass(req.getSuperClass());
        classVO.setInterfaces(req.getInterfaces());

        classVO.setFields(req.getFields());

        String code = genCode("/templates/javabean.vm", classVO);

        File dir = new File(srcPath, packageName.replace(".", "/"));
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File path = new File(dir, String.format("%s.java", simpleClassName));
        writeJavaFile(path, code);

        Response response = new Response(code, path);
        return response;
    }

    private void init(String name) {
        try {
            this.ve = doInit(name);
        } catch (IOException e) {
            throw new IllegalArgumentException("velocity init failure", e);
        }
    }

    private VelocityEngine doInit(String name) throws IOException {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
        VelocityEngine ve = new VelocityEngine(props);
        ve.init();
        return ve;
    }

    private String genCode(String template, ClassVO classVO) {

        Template t = ve.getTemplate(template);

        VelocityContext context = new VelocityContext();
        context.put("vo", classVO);

        StringWriter writer = new StringWriter(1024);
        t.merge(context, writer);

        return writer.toString();
    }

    private void writeJavaFile(File path, String data) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            bw.write(data);
            bw.flush();
        } finally {
            bw.close();
        }
    }

    public static class Builder {
        private File srcPath;

        public Builder srcPath(File srcPath) {
            this.srcPath = srcPath;
            return this;
        }

        public Lemon build() {
            return new Lemon(this);
        }
    }
}
