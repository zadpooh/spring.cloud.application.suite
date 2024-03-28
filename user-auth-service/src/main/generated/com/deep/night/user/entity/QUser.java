package com.deep.night.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -732732194L;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final StringPath birthday = createString("birthday");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> dnUserId = createNumber("dnUserId", Integer.class);

    public final StringPath dnUserPassword = createString("dnUserPassword");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath nation = createString("nation");

    public final DateTimePath<java.time.LocalDateTime> passwordChageDate = createDateTime("passwordChageDate", java.time.LocalDateTime.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath province = createString("province");

    public final DateTimePath<java.time.LocalDateTime> signInDate = createDateTime("signInDate", java.time.LocalDateTime.class);

    public final StringPath zipcode = createString("zipcode");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

