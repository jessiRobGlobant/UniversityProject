import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Student;
import model.Class;
import model.University;

public class UniversityInterface {

    // Scanner used to read user input
    private static final Scanner scan = new Scanner(System.in);
    private static final University university = new University();

    // Main menu
    public static void mainMenu(){
        String option = "";
        while (!option.equals("0")){
            System.out.println("\nMENU PRINCIPAL\n");

            System.out.println("1. Ver profesores");
            System.out.println("2. Ver clases");
            System.out.println("3. Ver estudiantes");
            System.out.println("4. Crear nuevo estudiante");
            System.out.println("5. Crear nueva clase");
            System.out.println("6. Añadir estudiantes a clase");
            System.out.println("7. Listar clases de un estudiante");
            System.out.println("0. Salir de la aplicacion");

            System.out.println("\nSeleccione la accion que desea realizar:");

            option = scan.nextLine();

            if (isAnOption(option, "7")){
                switch (Byte.parseByte(option)) {
                    case 1: 
                        showProfessors();
                        break;
                    case 2:
                        chooseClass();
                        break;
                    case 3: 
                        showStudents();
                        break;
                    case 4: 
                        createStudent();
                        break;
                    case 5: 
                        createClass();
                        break;
                    case 6: 
                        addStudentsToClass();
                        break;
                    case 7: 
                        break;
                }
            }
        }
    }
    
    // Show info
    public static void showProfessors(){
        System.out.println("\nLISTADO DE PROFESORES\n");
        List<Long> professorsIds = university.getTeachersIds();
        short i = 0;
        for (long id: professorsIds){
            i ++;
            System.out.println(i);
            System.out.println(university.getProfessorInfo(id));
            System.out.println();
        }
    }

    public static void showStudents(){
        System.out.println("\nLISTADO DE ESTUDIANTES\n");
        List<Long> studentsIds = university.getStudentsIds();
        short i = 0;
        for (long id: studentsIds){
            i ++;
            System.out.println(i);
            System.out.println(university.getStudentInfo(id));
            System.out.println();
        }
    }

    public static void chooseClass(){
        System.out.println("\nLISTADO DE CLASES\n");

        int i  = 0;
        for (String name: university.getClassesNames()){
            i ++;
            System.out.println(String.format("%d. %s", i, name));
        }
        
        if (!university.getClassesNames().isEmpty()){
            System.out.println("Ingrese la clase de la que desea ver más informacion:");
            String option = scan.nextLine();

            if(isAnOption(option, String.valueOf(i))){
                String className = university.getClassByIndex(Integer.valueOf(option)-1);
                System.out.println("\nInformacion sobre la clase seleccionada:\n");
                System.out.println(university.getClassInfo(className));
            }
        }
    }

    // Create objects
    public static void createStudent(){
        System.out.println("\nCREAR ESTUDIANTE\n");

        System.out.println("Ingrese el nombre:");
        String name = scan.nextLine();
        System.out.println("Ingrese la edad:");
        String age = scan.nextLine();

        if ((isName(name)) && (isInt(age))){
            Student student = university.createStudent(name, Byte.valueOf(age));
            System.out.println("\nNuevo estudiante creado con exito\n");
            System.out.println(student.info());
        }
    }

    public static void createClass(){
        System.out.println("\nCREAR CLASE\n");

        System.out.println("Ingrese el nombre:");
        String name = scan.nextLine();
        System.out.println("Ingrese el salon:");
        String classroom = scan.nextLine();
        System.out.println("Ingrese el id del profesor:");
        String professorId = scan.nextLine();
        System.out.println("Ingrese los ids de los estudiantes separados por ',' y sin espacios:");
        String[] studentsIds = scan.nextLine().split(",");

        if ((isInt(professorId))){
            Set<Long> studentsIdList = validateStudents(studentsIds);
            // If the ids are valid, create the class
            if (!studentsIdList.isEmpty()){
                Class class1 = university.createClass(name, classroom, 
                                    Long.parseLong(professorId), studentsIdList);
                if (class1 != null){
                    System.out.println("\nClase creada con exito\n\n");
                    System.out.println(university.getClassInfo(class1.getName())); 
                }
                else{
                    System.out.println("\nUno o más ids no se encuentran registrados");
                }
            }
        }
    }

    // Add student to class
    public static void addStudentsToClass(){
        System.out.println("\nAÑADIR ESTUDIANTES A UNA CLASE\n");

        System.out.println("Ingrese los ids de los estudiantes separados por ',' y sin espacios:");
        String[] studentsIds = scan.nextLine().split(",");
        System.out.println("Ingrese la clase:");
        String className = scan.nextLine();

        Set<Long> studentsIdList = validateStudents(studentsIds);
        // If the ids are valid, add them to the class
        if (!studentsIdList.isEmpty()){
            Class class1 = university.getClass(className);
            if (class1 != null){
                if (university.addStudentsToClass(studentsIdList, class1)){
                    System.out.println("\nEstudiantes añadidos con exito\n\n");
                    System.out.println(university.getClassInfo(class1.getName())); 
                }
                else{
                    System.out.println("\nuno o más Ids no están registrados");
                }
            }
            else{
                System.out.println("\nLa clase no se encuentra registrada");
            }
        }
    }

    public static Set<Long> validateStudents(String[] studentsIds){
        short i = 0;
        boolean validIds = true;
        Set<Long> studentsIdList = new HashSet<>();
        String studentId = "";

        while ((i<studentsIds.length) && (validIds)){
            studentId = studentsIds[i];
            if (isInt(studentId)){
                studentsIdList.add(Long.valueOf(studentId));
            }
            else{
                validIds = false;
            }
            i++;
        }
        if (!validIds){
            // Return empty list if not valid ids
            studentsIdList = new HashSet<>();
            System.out.println("El id "+studentId+"no es valido");
        }
        return studentsIdList;
    }

    // Input verification
    public static boolean isAnOption(String option, String maxOption){

        String regexString = String.format("([0-%s])", maxOption);

        boolean inOptions = option.matches(regexString);
        if (inOptions){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó una opcion valida");
            return false;
        }

    }

    public static boolean isPrice(String price){

        boolean isPrice = price.matches("(\\d)*\\.?\\d+");
        if (isPrice){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó un precio valido");
            return false;
        }

    }
    
    public static boolean isInt(String integer){

        boolean isInteger = integer.matches("(\\d)+");
        if (isInteger){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó una entero valido");
            return false;
        }

    }

    public static boolean isName(String text){
        // [A-Za-z]+ -> Begining with at least a letter
        // (\\s[A-Za-z]+)* -> Zero or more occurences of a space followed by one or more lettters
        boolean isName = text.matches("[A-Za-z]+(\\s[A-Za-z]+)*");
        if(!isName){
            System.out.println("\nLa entrada ingresada no es un nombre valido.");
        }
        return isName;
    }

    // Main
    public static void main(String[] args) {
        
        System.out.println("Bienvenido a tu programa universitario :) \n");
        // Excecute main menu
        mainMenu();
    }
}