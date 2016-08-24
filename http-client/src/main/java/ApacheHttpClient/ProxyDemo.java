package ApacheHttpClient;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by leo_zlzhang on 8/24/2016.
 * Demo for apache http client
 */
public class ProxyDemo {

    @Test
    public void un_executable() throws IOException {
        HttpHost proxy = new HttpHost("127.0.0.1", 8899);

        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/posts?id=1");

        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        httpClient.close();
    }

}
