package ApacheHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostWithMultiFormDataDemo {

    @Test
    public void executable() throws IOException {
        HttpEntityEnclosingRequestBase request = new HttpPost("https://jsonplaceholder.typicode.com/posts");

        addMultipartBody(request);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null)
            System.out.println(output);
        httpClient.close();
    }


    private void addMultipartBody(HttpEntityEnclosingRequestBase request) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("name", "text");
        builder.addBinaryBody("name", new File(System.getProperty("user.dir") + File.separator + "pom.xml"), ContentType.create("multipart/form-data"), "fileNameToServer");
        request.setEntity(builder.build());
    }

}
