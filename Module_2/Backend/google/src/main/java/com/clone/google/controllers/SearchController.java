package com.clone.google.controllers;

import com.clone.google.entities.WebPage;
import com.clone.google.services.SearchService;
import com.clone.google.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    SpiderService spiderService;

    @RequestMapping(value = "api/search", method = RequestMethod.GET)
    public List<WebPage> search(@RequestParam Map<String, String> params) {
        String query = params.get("query");
        return searchService.search(query);
    }

    @RequestMapping(value = "api/spider", method = RequestMethod.GET)
    public void spider() {
        spiderService.indexWebPages();
    }

}
