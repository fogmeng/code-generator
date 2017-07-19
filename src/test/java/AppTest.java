import lemon.FieldType;
import lemon.Lemon;
import lemon.Request;
import lemon.model.FieldVO;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author Ricky Fung
 */
public class AppTest {

    Lemon lemon = new Lemon();

    @Test
    public void testJavaBean() {

        Request request = new Request.Builder()
                .packageName("com.mindflow")
                .className("User")
                .addInterface(Serializable.class.getName())
                .addField(new FieldVO(FieldType.Long, "id"))
                .addField(new FieldVO(FieldType.String, "name"))
                .addField(new FieldVO(FieldType.Integer, "age"))
                .addField(new FieldVO(FieldType.Date, "birthday"))
                .addField(new FieldVO(FieldType.Boolean, "gender"))
                .build();

        String code = lemon.execute(request);
        System.out.println(code);

    }

}
