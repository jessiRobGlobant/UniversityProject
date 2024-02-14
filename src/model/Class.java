package model;

import java.util.HashSet;
import java.util.Set;

public class Class {
    
    // Attributes
    private String name;
    private String classroom;
    private long teacher;
    private Set<Long> students = new HashSet<>();

    // Constructor
    public Class(String name, String classroom, long teacher, Set<Long> students) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = students;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
    public String getClassroom() {
        return classroom;
    }
    protected void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public long getTeacher() {
        return teacher;
    }
    protected void setTeacher(long teacher) {
        this.teacher = teacher;
    }
    public Set<Long> getStudents() {
        return students;
    }
    
    public void addStudents(Set<Long> newStudents){
        getStudents().addAll(newStudents);
    }

    public boolean searchStudent(Long stuentId){
        return getStudents().contains(stuentId);
    }

    // Display info
    public String info(){
        return String.format("Nombre: %s\nSalon: %s", 
                    getName(), getClassroom());
    }
    

}
