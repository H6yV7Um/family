package org.yxyqcy.family.home.story.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lcy on 17/3/16.
 */
@Entity
@Table(name = "l_story")
public class Story  extends IdEntity<Story> {

    private static final long serialVersionUID = 5143129080645119848L;

    public Story() {
    }
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
