package model;

import java.util.ArrayList;
import java.util.List;

public class Class {
    
    // Attributes
    private String name;
    private String classroom;
    private long teacher;
    private List<Long> students = new ArrayList<>();

    // Constructor
    public Class(String name, String classroom, long teacher, List<Long> students) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = students;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public long getTeacher() {
        return teacher;
    }
    public void setTeacher(long teacher) {
        this.teacher = teacher;
    }
    public List<Long> getStudents() {
        return students;
    }
    public void setStudents(List<Long> students) {
        this.students = students;
    }

    // Display info
    public String info(){
        return String.format("Nombre: %s\nSalon: %s", 
                    getName(), getClassroom());
    }
    

}
