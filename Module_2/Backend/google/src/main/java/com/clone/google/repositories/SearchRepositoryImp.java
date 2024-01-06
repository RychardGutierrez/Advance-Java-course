package com.clone.google.repositories;

import com.clone.google.entities.WebPage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SearchRepositoryImp implements SearchRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List search(String textQuery) {
        String query = "FROM WebPage WHERE description like :query";
        return entityManager
                .createQuery(query).setParameter("query", "%" + textQuery + "%")
                .getResultList();
    }

    @Transactional
    @Override
    public void save(WebPage webPage) {
        entityManager.merge(webPage);
    }

    @Override
    public WebPage getByUrl(String url) {
        String query = "FROM WebPage WHERE url = :url";
        List<WebPage> list = entityManager.createQuery(query)
                .setParameter("url", url)
                .getResultList();
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public List<WebPage> getLinksToIndex() {
        String query = "FROM WebPage WHERE title is null AND description is null";
        return entityManager
                .createQuery(query).setMaxResults(100)
                .getResultList();
    }


    @Override
    public boolean exist(String link) {
        return getByUrl(link) != null;
    }
}
