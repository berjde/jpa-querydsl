package com.cp.jpaquerydsl.repository;

import com.cp.jpaquerydsl.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>, HouseRepositoryEx, QuerydslPredicateExecutor<House> {
}
