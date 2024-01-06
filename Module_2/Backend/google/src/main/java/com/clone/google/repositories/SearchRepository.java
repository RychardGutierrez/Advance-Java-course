package com.clone.google.repositories;

import com.clone.google.entities.WebPage;

import java.util.List;

public interface SearchRepository {
    public List<WebPage> search(String query);

    void save(WebPage webPage);

    boolean exist(String link);

    public WebPage getByUrl(String url);

    public List<WebPage> getLinksToIndex();
}
