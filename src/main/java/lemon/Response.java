package lemon;

import java.io.File;

/**
 * @author Ricky Fung
 */
public class Response {
    private String code;
    private File path;

    public Response(String code, File path) {
        this.code = code;
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public File getPath() {
        return path;
    }
}
