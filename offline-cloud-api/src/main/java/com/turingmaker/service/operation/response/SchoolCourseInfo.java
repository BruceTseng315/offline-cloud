package com.turingmaker.service.operation.response;

import com.turingmaker.entity.operation.TCourse;

public class SchoolCourseInfo extends TCourse{

    private Byte state;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SchoolCourseInfo{" +
                "state=" + state +
                '}'+super.toString();
    }
}
