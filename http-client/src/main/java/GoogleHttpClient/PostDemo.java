package GoogleHttpClient;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.jackson.JacksonFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostDemo {
    @Test
    public void executable() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory httpRequestFactory = httpTransport.createRequestFactory();

        GenericUrl genericUrl = new GenericUrl("https://jsonplaceholder.typicode.com/posts");
        HttpRequest httpRequest =httpRequestFactory.buildPostRequest(genericUrl, null);
        addJsonBody(httpRequest);

        HttpResponse httpResponse = httpRequest.execute();

        System.out.println(httpResponse.getStatusCode());
        System.out.println(httpResponse.parseAsString());
    }


    private void addJsonBody(HttpRequest httpRequest) {
        Map<String, Object> bodyObject = new HashMap<String, Object>();
        bodyObject.put("title", "foo");
        bodyObject.put("body", "bar");
        bodyObject.put("userId", 10);
        HttpContent content = new JsonHttpContent(new JacksonFactory(), bodyObject);
        httpRequest.setContent(content);
    }
}
