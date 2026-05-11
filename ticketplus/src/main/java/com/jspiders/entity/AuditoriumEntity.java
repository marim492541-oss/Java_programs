package com.jspiders.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auditorium")
public class AuditoriumEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seat_rows")
    private Long seatRows;

    @Column(name = "seat_columns")
    private Long seatColumns;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(Long seatRows) {
        this.seatRows = seatRows;
    }

    public Long getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(Long seatColumns) {
        this.seatColumns = seatColumns;
    }
}
