package ApacheHttpClient;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PostDemo {

    @Test
    public void executable() throws IOException {
        HttpEntityEnclosingRequestBase request = new HttpPost("https://jsonplaceholder.typicode.com/posts");
        addStringBody(request);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null)
            System.out.println(output);
        httpClient.close();
    }

    private void addStringBody(HttpEntityEnclosingRequestBase request) {
        Map<String, Object> bodyObject = new HashMap<String, Object>();
        bodyObject.put("title", "foo");
        bodyObject.put("body", "bar");
        bodyObject.put("userId", 10);
        String bodyString = (new Gson()).toJson(bodyObject);
        StringEntity entity = new StringEntity(bodyString, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
    }

}
