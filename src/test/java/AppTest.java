import lemon.Lemon;
import lemon.model.Field;
import org.junit.Test;

import java.util.*;

/**
 * @author Ricky Fung
 */
public class AppTest {

    @Test
    public void testJavaBean() {

        Lemon lemon = new Lemon();

        Map<String, Object> params = new HashMap<>();
        params.put("package", "com.mindflow");
        params.put("className", "User");

        params.put("interfaces", Arrays.asList("java.io.Serializable"));

        List<Field> fields = new ArrayList<>();
        fields.add(new Field("long", "id"));
        fields.add(new Field("String", "name"));
        fields.add(new Field("int", "age"));
        params.put("fields", fields);

        String code = lemon.genCode("/templates/javabean.vm", params);
        System.out.println(code);
    }

}
