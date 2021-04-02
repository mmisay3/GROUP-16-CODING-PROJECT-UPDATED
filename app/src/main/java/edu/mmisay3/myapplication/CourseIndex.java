package edu.mmisay3.myapplication;

public enum CourseIndex {
    CPR(0), AED(1), WFS(2), SSA(3), CC(4);
    public Integer index;
    private CourseIndex(Integer _index){
        this.index = _index;
    }
}
