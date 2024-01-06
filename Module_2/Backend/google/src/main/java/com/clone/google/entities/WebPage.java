package com.clone.google.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "webpage")
@ToString // lombok
@EqualsAndHashCode
@Getter
@Setter
public class WebPage {

    public WebPage() {
    }

    public WebPage(String url) {

        this.url = url;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;


}
