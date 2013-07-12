package de.twenty11.skysail.server.ext.bookmarks.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBookmark is a Querydsl query type for Bookmark
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBookmark extends EntityPathBase<Bookmark> {

    private static final long serialVersionUID = 2059560835;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmark bookmark = new QBookmark("bookmark");

    public final QFolder folder;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> pid = createNumber("pid", Integer.class);

    public final StringPath url = createString("url");

    public QBookmark(String variable) {
        this(Bookmark.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QBookmark(Path<? extends Bookmark> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookmark(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookmark(PathMetadata<?> metadata, PathInits inits) {
        this(Bookmark.class, metadata, inits);
    }

    public QBookmark(Class<? extends Bookmark> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.folder = inits.isInitialized("folder") ? new QFolder(forProperty("folder"), inits.get("folder")) : null;
    }

}

