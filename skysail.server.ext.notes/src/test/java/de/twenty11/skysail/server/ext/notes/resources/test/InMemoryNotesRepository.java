package de.twenty11.skysail.server.ext.notes.resources.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.twenty11.skysail.server.ext.notes.domain.Note;
import de.twenty11.skysail.server.ext.notes.repos.ComponentRepository;

public class InMemoryNotesRepository implements ComponentRepository<Note> {

    Map<Long, Note> db = new HashMap<Long, Note>();
    int id = 0;

    @Override
    public Note getById(Long id) {
        return db.get(id);
    }

    @Override
    public void add(Note entity) {
        Long pid = new Long(id++);
        entity.setPid(id);
        db.put(pid, entity);
    }

    @Override
    public void update(Note entity) {
        db.put(Long.valueOf(entity.getPid()), entity);
    }

    @Override
    public List<Note> getComponents() {
        return Arrays.asList(db.values().toArray(new Note[db.values().size()]));
    }

    @Override
    public void delete(Long folderId) {
        // TODO Auto-generated method stub

    }
}
