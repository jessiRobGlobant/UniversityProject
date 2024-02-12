package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class University {

    // Attributes
    private final Map<Long, Professor> teachers = new HashMap<>();
    private final Map<Long, Student> students = new HashMap<>();
    private final Map<String, Class> classes = new HashMap<>();

    private final List<Long> teachersIds = new ArrayList<>();
    private final List<Long> studentsIds = new ArrayList<>();
    private final List<String> classesNames = new ArrayList<>();

    // Constructor
    public University() {
        // TODO - Create teachers
        // TODO - Create Default objects
    }

    // Getters
    public List<Long> getTeachersIds() {
        return teachersIds;
    }

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public List<String> getClassesNames() {
        return classesNames;
    }

    // Get info
    public String getProfessorInfo(long id){
        return teachers.get(id).info();
    }

    public String getStudentInfo(long id){
        return students.get(id).info();
    }

    public String getClassInfo(String name){
        Class class1 = classes.get(name);
        String ans = class1.info();

        // Get teacher info
        ans += String.format("\nProfesor: \n\t%s", getProfessorInfo(class1.getTeacher()));

        ans += "Estudiantes:";
        short i = 1;
        for (long id: class1.getStudents()){
            ans += String.format("\n%d\n\t%s", i, getStudentInfo(id));
        }
        
        return ans;
    }

    
    
    
}
