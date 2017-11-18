package utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class Func {
    public static URI toUri(String x) {
        try {
            return new URI(x);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toUtf8UriPart(String searchedItem) {
        try {
            return URLEncoder.encode(searchedItem, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}