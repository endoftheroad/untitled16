import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet("http://omdbapi.com/?i=tt0088247");
            CloseableHttpResponse resp = client.execute(get);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
            System.out.println(str);

            JSONObject root = new JSONObject(str);
            JSONArray search = root.getJSONArray("Search");
            for (int i = 0; i < search.length(); i++) {
                JSONObject item = search.getJSONObject(i);
                String title = item.getString("Title");
                String id = item.getString("imdbID");
                System.out.println(title);

                URI uri = new URIBuilder("http://omdbapi.com/").addParameter("i", id).build();

                HttpGet get2= new HttpGet(uri);
                CloseableHttpResponse resp2 = client.execute(get2);
                String str2 = EntityUtils.toString(resp2.getEntity(), "UTF-8");
                System.out.println(str2);
            }
        } finally {

        }


    }

}
