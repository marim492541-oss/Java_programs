package com.jspiders.entity;

import com.jspiders.enums.ShowStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "shows")
public class ShowEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_time")
    private LocalDate showTime;

    @Column(name = "show_end_time")
    private LocalDate EndTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "audi_id")
    private AuditoriumEntity auditorium;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDate showTime) {
        this.showTime = showTime;
    }

    public LocalDate getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDate endTime) {
        EndTime = endTime;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public AuditoriumEntity getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumEntity auditorium) {
        this.auditorium = auditorium;
    }
}
