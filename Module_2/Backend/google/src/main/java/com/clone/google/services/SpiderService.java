package com.clone.google.services;

import com.clone.google.entities.WebPage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Service
public class SpiderService {

    @Autowired
    private SearchService searchService;

    public void indexWebPages() {
        List<WebPage> linksToIndex = searchService.getLinksToIndex();
        linksToIndex.stream().parallel().forEach(webPage -> {
            try {
                indexWebPage(webPage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void indexWebPage(WebPage webPage) throws Exception {
        String url = webPage.getUrl();
        String content = getWebContent(url);

        if (isBlank(content)) {
            return;
        }

        processAndSaveWebPage(content, webPage);
        String domain = getDomain(url);
        saveLinks(domain, content);
    }

    private String getDomain(String url) {
        String[] aux = url.split("/");
        return aux[0] + "//" + aux[2];
    }

    private void saveLinks(String domain, String content) {
        List<String> links = getLinks(domain, content);

        links.stream().filter(link -> !searchService.exist(link)).map(link -> new WebPage(link)).forEach(webPage -> {
            searchService.save(webPage);
        });

    }

    private List<String> getLinks(String domain, String content) {
        List<String> links = new ArrayList<>();

        String[] linkStart = content.split("href=\"");
        List<String> listRef = Arrays.asList(linkStart);
//        listRef.remove(0);

        listRef.forEach(ref -> {
            String[] refAux = ref.split("\"");
            links.add(refAux[0]);
        });
        return cleanLinks(domain, links);
    }

    private List<String> cleanLinks(String domain, List<String> links) {
        String[] excludedExtensions = new String[]{"css", "js", "json", "jpg", "png", "woff2"};
        List<String> resultLinks = links.stream()
                .filter(link -> Arrays.stream(excludedExtensions)
                        .noneMatch(extension -> link.endsWith(extension)))
                .map(link -> link.startsWith("/") ? domain + link : link)
                .collect(Collectors.toList());

        List<String> uniqueLinks = new ArrayList<>(new HashSet<>(resultLinks));

        return uniqueLinks;
    }

    private void processAndSaveWebPage(String content, WebPage webPage) {
        String title = getTitle(content);
        String description = getDescription(content);

        webPage.setTitle(title);
        webPage.setDescription(description);
        searchService.save(webPage);
    }

    private String getTitle(String content) {
        String[] titleStart = content.split("<title>");
        String[] titleEnd = titleStart[1].split("</title>");

        return titleEnd[0];
    }

    private String getDescription(String content) {
        String[] desStart = content.split("<meta name=\"description\" content=\"");
        String[] desEnd = desStart[1].split("\">");

        return desEnd[0];
    }


    private String getWebContent(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String encoding = conn.getContentEncoding();

            InputStream input = conn.getInputStream();

            Stream<String> lines = new BufferedReader(new InputStreamReader(input))
                    .lines();

            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return "";
    }
}
