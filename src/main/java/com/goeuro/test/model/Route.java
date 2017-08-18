package com.goeuro.test.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROUTE")
public class Route {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROUTE_STATION", joinColumns = { @JoinColumn(name = "ROUTE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "STATION_ID") })
    private List<Station> stations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
