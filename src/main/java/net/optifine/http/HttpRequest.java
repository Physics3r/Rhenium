package net.optifine.http;

import lombok.Getter;
import lombok.Setter;

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class HttpRequest {
    private String host = null;
    private int port = 0;
    private Proxy proxy = Proxy.NO_PROXY;
    private String method = null;
    private String file = null;
    private String http = null;
    private Map<String, String> headers = new LinkedHashMap();
    private byte[] body = null;
    @Setter
    private int redirects = 0;
    public static final String METHOD_GET = "GET";
    public static final String METHOD_HEAD = "HEAD";
    public static final String METHOD_POST = "POST";
    public static final String HTTP_1_0 = "HTTP/1.0";
    public static final String HTTP_1_1 = "HTTP/1.1";

    public HttpRequest(String host, int port, Proxy proxy, String method, String file, String http, Map<String, String> headers, byte[] body) {
        this.host = host;
        this.port = port;
        this.proxy = proxy;
        this.method = method;
        this.file = file;
        this.http = http;
        this.headers = headers;
        this.body = body;
    }

}
