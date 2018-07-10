package com.turingmaker.entity.school;

import java.io.Serializable;

public class TResourceLesson implements Serializable {
    private Long id;

    private Long resourceId;

    private Long lessonId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString() {
         return  "TResourceLesson{"+
				"id="+id+
				"resourceId="+resourceId+
				"lessonId="+lessonId+"}";
    }
}