package com.cp.jpaquerydsl.repository;

import com.cp.jpaquerydsl.model.House;
import com.cp.jpaquerydsl.model.QHouse;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
class HouseRepositoryTest {

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void 일반_JPA_레파지토리(){
        houseRepository.findAll();
        //houseRepository.findById(1L);
        houseRepository.save(
                House.builder()
                        .address("우리집")
                        .latitude(23.22222)
                        .longitude(11.0302)
                        .createdAt(LocalDateTime.now().minusDays(1))
                        .build()
        );
    }

    @Test
    void Querydsl_확장(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<House> houses = houseRepository.getList("우", pageRequest);

        System.out.println("total Count: " + houses.getTotalElements());
        System.out.println("total page Count: " + houses.getTotalPages());
        System.out.println("pageNumber: " +houses.getNumber()); // jpa 페이징은 0부터 시작한다.

        System.out.println(houses.get()
                .map(h -> h.toString())
                .collect(Collectors.joining(","))
        );
    }

    @Test
    void Querydsl_predicated_실행(){
        QHouse qHouse = QHouse.house;

        BooleanExpression predicate = qHouse.address.eq("우리집")
                .and(qHouse.createdAt.before(LocalDateTime.now()));

        Iterable<House> houses = houseRepository.findAll(predicate);

        Optional<House> house = houseRepository.findOne(predicate.and(qHouse.id.eq(1L)));

        StreamSupport.stream(houses.spliterator(), false)
                .forEach(t-> System.out.println(t));

        System.out.println();

        System.out.println(house.orElseThrow(()-> new RuntimeException("NULL")));

    }
}