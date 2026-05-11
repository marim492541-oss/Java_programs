package com.jspiders.entity;

import com.jspiders.enums.CertificateType;
import com.jspiders.enums.MovieStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class MovieEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "language",nullable = false)
    private String language;

    @Column(name = "duration",nullable = false)
    private Integer duration;

    @Column(name = "certification",nullable = false)
    @Enumerated(EnumType.STRING)
    private CertificateType certification;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowEntity> showList = new ArrayList<>();

    public List<ShowEntity> getShowList() {
        return showList;
    }

    public void setShowList(List<ShowEntity> showList) {
        this.showList = showList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CertificateType getCertification() {
        return certification;
    }

    public void setCertification(CertificateType certification) {
        this.certification = certification;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration +
                ", certification='" + certification + '\'' +
                ", status=" + status +
                '}';
    }
}