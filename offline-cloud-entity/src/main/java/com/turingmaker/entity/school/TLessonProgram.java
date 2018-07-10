package com.turingmaker.entity.school;

import java.io.Serializable;

public class TLessonProgram implements Serializable {
    private Long id;

    private Long programId;

    private Long lessonId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString() {
         return  "TLessonProgram{"+
				"id="+this.getId()+
				"programId="+this.getProgramId()+
				"lessonId="+this.getLessonId()+"}";
    }
}