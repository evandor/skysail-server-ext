package de.twenty11.skysail.server.ext.notes.repos;

import de.twenty11.skysail.server.ext.notes.domain.Component;

public interface ComponentRepository<T extends Component> {

    public abstract T getById(Long id);

    public abstract void add(T entity);

}
