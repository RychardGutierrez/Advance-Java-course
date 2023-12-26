import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebScraper {
    // Download webs
    static String link = "https://www.bcb.gob.bo/";
    static String link2 = "https://www.bcb.gob.bo/?q=La%20Historia%20del%20BCB";
    static String link3 = "https://www.bcb.gob.bo/?q=constitucion-politica-estado";
    static String link4 = "https://www.bcb.gob.bo/?q=ipm";
    static String link5 = "https://www.bcb.gob.bo/?q=sistema-de-pagos";
    static String link6 = "https://www.bcb.gob.bo/?q=cartillas-educativas";
    static String link7 = "https://www.bcb.gob.bo/?q=comunicados";
    static String link8 = "https://www.bcb.gob.bo/?q=dtlc-reglamento";

    public static void server() {
        List<String> links = new ArrayList<>();
        links.add(link);
        links.add(link2);
        links.add(link3);
        links.add(link4);
        links.add(link5);
        links.add(link6);
        links.add(link7);
        links.add(link8);
        links.add(link);
        links.add(link2);
        links.add(link3);
        links.add(link4);
        links.add(link5);
        links.add(link6);
        links.add(link7);
        links.add(link8);

        Long timeStart = System.nanoTime();
        links.stream().parallel().forEach(WebScraper::getWebContent); //
        Long timeEnd = System.nanoTime();
//        System.out.println(" Diference1: "+(timeEnd - timeStart)); // 1613766600

//        timeStart = System.nanoTime();
//        links.stream().forEach(WebScraper::getWebContent);
//        timeEnd = System.nanoTime();
//        System.out.println(" Diference2: "+(timeEnd - timeStart)); // 560471800

        String result = getWebContent(link);
//        System.out.println(result);
    }

    // synchronized: Start process and if another process want to use this method they need wait to complete the firsts process
    private synchronized static String getWebContent(String link) { // Lock
        System.out.println("init");
        System.out.println(link);
        URL url = null;
        try {
            url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // String encoding = conn.getContentEncoding();

            InputStream inputStream = conn.getInputStream();
            // BufferedReader
            // InputStreamReader
            Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
            String result = lines.collect(Collectors.joining());

            System.out.println("end");
            return result; //Unblock
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
