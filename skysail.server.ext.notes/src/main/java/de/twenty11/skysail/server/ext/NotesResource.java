package de.twenty11.skysail.server.ext;

import java.util.List;

import javax.persistence.Query;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.server.GridDataServerResource;
import de.twenty11.skysail.server.ext.notes.Note;

/**
 * A grid-based resource for bundles.
 * 
 * @author carsten
 * 
 */
public class NotesResource extends GridDataServerResource {

    public NotesResource() {
        super(new ColumnsBuilder() {
            @Override
            public void configure() {
                addColumn("id").setWidth(50).sortDesc(1);
                addColumn("title").setWidth(350).setWidth(100);
                addColumn("changed").setWidth(100);
            }
        });
        setTemplate("skysail.server.ext.notes:notes.ftl");
    }

    @Override
    public void buildGrid() {
        Query notesQuery = ServiceProvider.entityManager().createQuery("SELECT n FROM NoteImpl n");
        List<Note> resultList = notesQuery.getResultList();
        for (Note note : resultList) {
            RowData rowData = new RowData(getSkysailData().getColumns());
            rowData.add(note.getId());
            rowData.add(note.getTitle());
            rowData.add(note.getChanged());
            getSkysailData().addRowData(rowData);

        }
    }
}
