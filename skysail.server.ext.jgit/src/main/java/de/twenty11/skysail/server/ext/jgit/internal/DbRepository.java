package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.List;

import de.twenty11.skysail.server.ext.jgit.LocalRepositoryDescriptor;

public interface DbRepository {

    List<LocalRepositoryDescriptor> getLocalRepositoryDescriptors();

    LocalRepositoryDescriptor getLocalRepositoryDescriptor(String id);

    void addLocalRepositoryDescriptor(LocalRepositoryDescriptor entity);
}
