package com.goeuro.test.repository;

import com.goeuro.test.model.Route;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Cacheable("booksForSubscriber")
    @Query(" select distinct b FROM Route b inner join b.stations bStations where bStations in "
            + "( select distinct s FROM Station s where s.id in :stations ) ")
    List<Route> findRoutesForStatios(@Param("stations") List<Integer> stations);

}
