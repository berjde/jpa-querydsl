package com.cp.jpaquerydsl.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Expression;


/**
 * QHouse is a Querydsl query type for House
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHouse extends EntityPathBase<House> {

    private static final long serialVersionUID = -251527703L;

    public static ConstructorExpression<House> create(Expression<Long> id, Expression<String> address, Expression<Double> latitude, Expression<Double> longitude, Expression<java.time.LocalDateTime> createdAt) {
        return Projections.constructor(House.class, new Class<?>[]{long.class, String.class, double.class, double.class, java.time.LocalDateTime.class}, id, address, latitude, longitude, createdAt);
    }

    public static final QHouse house = new QHouse("house");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public QHouse(String variable) {
        super(House.class, forVariable(variable));
    }

    public QHouse(Path<? extends House> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHouse(PathMetadata metadata) {
        super(House.class, metadata);
    }

}

