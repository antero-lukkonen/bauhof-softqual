package utils;

import java.net.URI;
import java.net.URISyntaxException;

public class Func {
    public static URI toUri(String x) {
        try {
            return new URI(x);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}