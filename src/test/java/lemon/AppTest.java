package lemon;

import lemon.model.FieldVO;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Ricky Fung
 */
public class AppTest {

    private Lemon lemon = new Lemon.Builder()
            .srcPath(new File("D:/code-generator/src"))
            .build();

    @Ignore
    @Test
    public void testJavaBean() throws IOException {

        Request request = new Request.Builder()
                .className("com.mindflow.User")
                .addInterface(Serializable.class.getName())
                .addField(new FieldVO(FieldType.Long, "id"))
                .addField(new FieldVO(FieldType.String, "name"))
                .addField(new FieldVO(FieldType.Integer, "age"))
                .addField(new FieldVO(FieldType.Date, "birthday"))
                .addField(new FieldVO(FieldType.Boolean, "gender"))
                .build();

        Response response = lemon.execute(request);
        System.out.println(response.getCode());
        System.out.println(response.getPath());
    }

}
