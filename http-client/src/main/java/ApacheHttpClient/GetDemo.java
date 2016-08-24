package ApacheHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetDemo {

    @Test
    public void executable() throws IOException {
        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/posts?id=1");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null)
            System.out.println(output);
        httpClient.close();
    }
}
