package de.twenty11.skysail.server.ext.notes.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.stringtemplate.v4.ST;

import de.twenty11.skysail.common.Presentable2;

/**
 * 
 */
public class Note extends Component implements Presentable2 {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    @Override
    @JsonIgnore
    public String getHtml() {
        ST html = new ST("<title> <content>");
        html.add("title", "title");
        html.add("content", "content");
        return html.render();
    }
}
