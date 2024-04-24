import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class pr03 {
    public static void main(String[] args) {
        try {
            // Create a URL object
            URL url = new URL("http://127.0.0.1:5500/index.html");

            // Open a connection to the URL
            URLConnection connection = url.openConnection();

            // Set additional connection properties (optional)
            connection.setConnectTimeout(5000); // Set connection timeout to 5 seconds
            connection.setReadTimeout(10000);   // Set read timeout to 10 seconds

            // Establish the connection
            connection.connect();

            // Read data from the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Display the retrieved content
            System.out.println("Content retrieved from URL:");
            System.out.println(content.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
