package com.clone.google.services;

import com.clone.google.entities.WebPage;
import com.clone.google.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository repository;

    public List<WebPage> search(String query) {
        return repository.search(query);
    }

    public void save(WebPage webPage) {
        repository.save(webPage);
    }

    public boolean exist(String link) {
        return repository.exist(link);
    }

    public List getLinksToIndex() {
        return repository.getLinksToIndex();
    }
}
