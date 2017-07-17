package lemon;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

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
            throw new IllegalArgumentException("", e);
        }
    }

    public VelocityEngine init(String name) throws IOException {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
        VelocityEngine ve = new VelocityEngine(props);
        ve.init();
        return ve;
    }

    public String genCode(String template, Map<String, Object> params) {

        Template t = ve.getTemplate(template);

        VelocityContext context = new VelocityContext();

        for(Map.Entry<String, Object> me : params.entrySet()) {
            context.put(me.getKey(), me.getValue());
        }

        StringWriter writer = new StringWriter(1024);
        t.merge(context, writer);

        return writer.toString();
    }
}
