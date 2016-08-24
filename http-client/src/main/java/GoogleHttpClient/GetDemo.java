package GoogleHttpClient;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetDemo {
    @Test
    public void executable() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory httpRequestFactory = httpTransport.createRequestFactory();

        GenericUrl genericUrl = new GenericUrl("https://jsonplaceholder.typicode.com/posts?id=1");
        HttpRequest httpRequest =httpRequestFactory.buildGetRequest(genericUrl);
        HttpResponse httpResponse = httpRequest.execute();

        System.out.println(httpResponse.getStatusCode());
        System.out.println(httpResponse.parseAsString());
    }

}
