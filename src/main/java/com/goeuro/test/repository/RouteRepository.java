package com.goeuro.test.repository;

import com.goeuro.test.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(" select distinct route FROM Route route where " + //
            " ( select count(s.id) from Route r inner join r.stations s " + //
            "    where s.id in (:dep, :arr) and r.id = route.id ) = 2 ")
    List<Route> findRoutesForStations(@Param("dep") Integer departure, @Param("arr") Integer arrival);

}
