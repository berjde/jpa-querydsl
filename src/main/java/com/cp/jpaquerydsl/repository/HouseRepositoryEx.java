package com.cp.jpaquerydsl.repository;


import com.cp.jpaquerydsl.model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseRepositoryEx {
    Page<House> getList(String address, Pageable pageable);
}
