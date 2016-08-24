package GoogleHttpClient;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyDemo {


    @Test
    public void un_executable() throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8899));
        HttpTransport httpTransport = new NetHttpTransport.Builder().setProxy(proxy).build();
        HttpRequestFactory httpRequestFactory = httpTransport.createRequestFactory();

        GenericUrl genericUrl = new GenericUrl("https://jsonplaceholder.typicode.com/posts?id=1");
        HttpRequest httpRequest = httpRequestFactory.buildGetRequest(genericUrl);
        HttpResponse httpResponse = httpRequest.execute();

        System.out.println(httpResponse.getStatusCode());
        System.out.println(httpResponse.parseAsString());
    }
}
