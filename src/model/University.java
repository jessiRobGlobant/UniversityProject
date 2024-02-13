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

        // Create professors
        Professor prof1 = createProfesor("Esteban", (byte)20, false);
        Professor prof2 = createProfesor("Anamaria", (byte)25, false);
        Professor prof3 = createProfesor("Numpaque", (byte)5, true);

        // Create Students
        Student stud1 = createStudent("Jessica", (byte)21);
        Student stud2 = createStudent("Andres", (byte)20);
        Student stud3 = createStudent("Luisa", (byte)21);
        Student stud4 = createStudent("Maria Paula", (byte)20);
        Student stud5 = createStudent("Sebastian", (byte)17);
        Student stud6 = createStudent("Martina", (byte)16);

        // Create classes
        List<Long> students = new LinkedList<>();
        students.add(stud1.getId());
        students.add(stud3.getId());
        students.add(stud4.getId());
        createClass("Java Introduction", "virtual", prof1.getId(), students);

        students = new LinkedList<>();
        students.add(stud1.getId());
        students.add(stud5.getId());
        students.add(stud4.getId());
        createClass("IP", "AU403", prof2.getId(), students);

        students = new LinkedList<>();
        students.add(stud2.getId());
        createClass("Typescript", "virtual", prof1.getId(), students);

        students = new LinkedList<>();
        students.add(stud6.getId());
        students.add(stud5.getId());
        createClass("Precalculo", "R202", prof3.getId(), students);

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
        if((getProfessor(professorId) != null) && (!classes.containsKey(name))){

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
                classes.put(name, class1);
                classesNames.add(name);
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
        ans += String.format("\n\nProfesor: \n%s", getProfessorInfo(class1.getTeacher()));

        ans += "\n\nEstudiantes:";
        short i = 1;
        for (long id: class1.getStudents()){
            ans += String.format("\n\n%d\n%s", i, getStudentInfo(id));
            i ++;
        }
        
        return ans;
    }

    public String getClassByIndex(int index){
        System.out.println(getClassesNames().get(index));
        return getClassesNames().get(index);
    }    

    
    
    
}
