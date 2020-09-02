package com.cp.jpaquerydsl.repository;

import com.cp.jpaquerydsl.model.House;
import com.cp.jpaquerydsl.model.QHouse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class HouseRepositoryImpl implements HouseRepositoryEx {

    final private JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<House> getList(String address, Pageable pageable) {
        QHouse qHouse = QHouse.house;

        long totalCount = this.jpaQueryFactory.selectFrom(qHouse).where(qHouse.address.containsIgnoreCase(address)).fetchCount();

        JPAQuery<House> query = this.jpaQueryFactory
                .select(Projections.fields(House.class,
                        qHouse.id,
                        qHouse.address,
                        qHouse.latitude,
                        qHouse.longitude,
                        qHouse.createdAt))

                .from(qHouse)

                .where(qHouse.address.containsIgnoreCase(address))

                .orderBy(qHouse.createdAt.desc())

                .offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<House> houses =  query.fetch();

        PageImpl page = new PageImpl(houses, pageable, totalCount);

        return page;
    }
}
