package jpabook.jpashop.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1091322645L;

    public static final QItem item = new QItem("item");

    public final jpabook.jpashop.domain.QBaseEntity _super = new jpabook.jpashop.domain.QBaseEntity(this);

    public final ListPath<jpabook.jpashop.domain.Category, jpabook.jpashop.domain.QCategory> category = this.<jpabook.jpashop.domain.Category, jpabook.jpashop.domain.QCategory>createList("category", jpabook.jpashop.domain.Category.class, jpabook.jpashop.domain.QCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

