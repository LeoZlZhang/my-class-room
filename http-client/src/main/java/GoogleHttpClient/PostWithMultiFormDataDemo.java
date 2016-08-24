package GoogleHttpClient;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.jackson.JacksonFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostWithMultiFormDataDemo {
    @Test
    public void executable() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory httpRequestFactory = httpTransport.createRequestFactory();

        GenericUrl genericUrl = new GenericUrl("https://jsonplaceholder.typicode.com/posts");
        HttpRequest httpRequest = httpRequestFactory.buildPostRequest(genericUrl, null);
        addMultiFormDataBody(httpRequest);

        HttpResponse httpResponse = httpRequest.execute();

        System.out.println(httpResponse.getStatusCode());
        System.out.println(httpResponse.parseAsString());
    }


    private void addMultiFormDataBody(HttpRequest httpRequest) {
        MultipartContent multipartContent = new MultipartContent();
        multipartContent.setMediaType(new HttpMediaType("multipart/form-data").setParameter("boundary", "__END_OF_PART__"));

        //Text form data
        MultipartContent.Part textPart = new MultipartContent.Part();
        textPart.setEncoding(null);
        textPart.setHeaders(new HttpHeaders().set("Content-Disposition", "form-data; name=\"name\""));
        textPart.setContent(ByteArrayContent.fromString(null, "text"));
        multipartContent.addPart(textPart);


        //File form data
        MultipartContent.Part filePart = new MultipartContent.Part();
        filePart.setEncoding(null);
        filePart.setHeaders(new HttpHeaders().set("Content-Disposition", "form-data; name=\"fileName\"; filename=\"fileNameToServer\""));
        filePart.setContent(new FileContent("multipart/form-data", new File(System.getProperty("user.dir") + File.separator + "pom.xml")));
        multipartContent.addPart(filePart);

        httpRequest.setContent(multipartContent);
    }
}
