package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
        defaultObjects();
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

    // Create objects
    private void defaultObjects(){

    }

    public Student createStudent(String name, byte age){
        Student student  = new Student(name, age);
        studentsIds.add(student.getId());
        students.put(student.getId(), student);
        return student;
    }

    public Professor createProfesor(String name, byte hoursExpYears, boolean partTime){ 
        /** HoursExpYears can be experienceYears or hoursPerWeek, depending
         * on the professor type
        **/
        Professor professor;
        if (partTime){
            professor = new PartTimeProfessor(name, hoursExpYears);
        }
        else{
            professor = new FullTimeProfessor(name, hoursExpYears);
        }
        teachersIds.add(professor.getId());
        teachers.put(professor.getId(), professor);
        return professor;
    }

    public Class createClass(String name, String classroom, long professorId, List<Long> students){
        Class class1 = null;
        if(getProfessor(professorId) != null){

            boolean missingStudent = false;
            Long studentIt;
            Iterator<Long> iterator = students.iterator();

            while ((iterator.hasNext()) && (!missingStudent)){
                studentIt = iterator.next();
                if (getStudent(studentIt) == null){
                    missingStudent = true;
                }
            }

            if (!missingStudent){
                class1 = new Class(name, classroom, professorId, students);
            }
        }
        return class1;
    }

    // Get info
    private Professor getProfessor(long id){
        return teachers.get(id);
    }

    public String getProfessorInfo(long id){
        return getProfessor(id).info();
    }

    private Student getStudent(long id){
        return students.get(id);
    }

    public String getStudentInfo(long id){
        return getStudent(id).info();
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
            i ++;
        }
        
        return ans;
    }

    public String getClassByIndex(int index){
        return getClassesNames().get(index);
    }    

    
    
    
}
