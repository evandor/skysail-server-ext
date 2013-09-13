package de.twenty11.skysail.server.ext.bookmarks.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFolder is a Querydsl query type for Folder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFolder extends EntityPathBase<Folder> {

    private static final long serialVersionUID = -535949829;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFolder folder = new QFolder("folder");

    public final StringPath name = createString("name");

    public final QFolder parent;

    public final NumberPath<Integer> pid = createNumber("pid", Integer.class);

    public QFolder(String variable) {
        this(Folder.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QFolder(Path<? extends Folder> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFolder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFolder(PathMetadata<?> metadata, PathInits inits) {
        this(Folder.class, metadata, inits);
    }

    public QFolder(Class<? extends Folder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QFolder(forProperty("parent"), inits.get("parent")) : null;
    }

}

