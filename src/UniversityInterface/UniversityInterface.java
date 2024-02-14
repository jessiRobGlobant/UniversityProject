/* Class used to control the inputs and outputs of the user via console */ 
package UniversityInterface;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Student;
import model.University;
import model.Class;

public class UniversityInterface {

    // Scanner used to read user input
    private static final Scanner scan = new Scanner(System.in);
    private static final University university = new University();

    // Default constructor
    
    // Main menu
    public static void mainMenu(){
        System.out.println("Bienvenido a tu programa universitario :) \n");
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

            if (isAnOption(option,"0", "7")){
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
                        listStudClasses();
                        break;
                }
            }
        }
    }
    
    // Show info
    public static void showProfessors(){
        System.out.println("\nLISTADO DE PROFESORES\n");
        final List<Long> professorsIds = university.getTeachersIds();
        short i = 0;
        for (final long id: professorsIds){
            i ++;
            System.out.println(i);
            System.out.println(university.getProfessorInfo(id));
            System.out.println();
        }
    }

    public static void showStudents(){
        System.out.println("\nLISTADO DE ESTUDIANTES\n");
        final List<Long> studentsIds = university.getStudentsIds();
        short i = 0;
        for (final long id: studentsIds){
            i ++;
            System.out.println(i);
            System.out.println(university.getStudentInfo(id));
            System.out.println();
        }
    }

    public static void chooseClass(){
        System.out.println("\nLISTADO DE CLASES\n");

        int i  = 0;
        for (final String name: university.getClassesNames()){
            i ++;
            System.out.println(String.format("%d. %s", i, name));
        }
        
        if (!university.getClassesNames().isEmpty()){
            System.out.println("Ingrese la clase de la que desea ver más informacion:");
            final String option = scan.nextLine();

            if(isAnOption(option,"1", String.valueOf(i))){
                final String className = university.getClassByIndex(Integer.valueOf(option)-1);
                System.out.println("\nInformacion sobre la clase seleccionada:\n");
                System.out.println(university.getClassInfo(className));
            }
        }
    }

    // Create objects
    public static void createStudent(){
        System.out.println("\nCREAR ESTUDIANTE\n");

        System.out.println("Ingrese el nombre:");
        final String name = scan.nextLine();
        System.out.println("Ingrese la edad:");
        final String age = scan.nextLine();

        if ((isName(name)) && (isInt(age))){
            final Student student = university.createStudent(name, Byte.valueOf(age));
            System.out.println("\nNuevo estudiante creado con exito\n");
            System.out.println(student.info());
        }
    }

    public static void createClass(){
        System.out.println("\nCREAR CLASE\n");

        System.out.println("Ingrese el nombre:");
        final String name = scan.nextLine();
        System.out.println("Ingrese el salon:");
        final String classroom = scan.nextLine();
        System.out.println("Ingrese el id del profesor:");
        final String professorId = scan.nextLine();
        System.out.println("Ingrese los ids de los estudiantes separados por ',' y sin espacios:");
        final String[] studentsIds = scan.nextLine().split(",");

        if ((isInt(professorId))){
            final Set<Long> studentsIdList = validateStudents(studentsIds);
            // If the ids are valid, create the class
            if (!studentsIdList.isEmpty()){
                final Class class1 = university.createClass(name, classroom, 
                                    Long.parseLong(professorId), studentsIdList);
                if (class1 != null){
                    System.out.println("\nClase creada con exito\n\n");
                    System.out.println(university.getClassInfo(class1.getName())); 
                }
                else{
                    System.out.println("\nNo se ha podido crear la clase. Uno o más ids no se encuentran registrados o puede que la clase con ese nombre ya exista");
                }
            }
        }
    }

    // Add student to class
    public static void addStudentsToClass(){
        System.out.println("\nAÑADIR ESTUDIANTES A UNA CLASE\n");

        System.out.println("Ingrese los ids de los estudiantes separados por ',' y sin espacios:");
        final String[] studentsIds = scan.nextLine().split(",");
        System.out.println("Ingrese la clase:");
        final String className = scan.nextLine();

        final Set<Long> studentsIdList = validateStudents(studentsIds);
        // If the ids are valid, add them to the class
        if (!studentsIdList.isEmpty()){
            final Class class1 = university.getClass(className);
            if (class1 != null){
                if (university.addStudentsToClass(studentsIdList, class1)){
                    System.out.println("\nEstudiantes añadidos con exito\n\n");
                    System.out.println(university.getClassInfo(class1.getName())); 
                }
                else{
                    System.out.println("\nUno o más Ids no están registrados");
                }
            }
            else{
                System.out.println("\nLa clase no se encuentra registrada");
            }
        }
    }

    public static Set<Long> validateStudents(final String[] studentsIds){
        /*Function used to validate if the students in list are
         * ids(number)
         */
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

    // List student classes
    public static void listStudClasses() {
        System.out.println("\nCONOCER CLASES DE UN ESTUDIANTE\n");

        System.out.println("Ingrese el id del estudiante:");
        final String studentId = scan.nextLine();

        if (isInt(studentId)){
            final List<String> classes = university.getStudentClasses(Long.parseLong(studentId));
            if(!classes.isEmpty()){
                System.out.println("\nEl estudiante se encuentra en las siguientes clases:");
                int i = 0;
                for (final String class1: classes){
                    i ++;
                    System.out.println(String.format("%d. %s", i, class1));
                }
            }
            else{
                System.out.println("\nEl id del estudiante no se encuentra registrado en ninguna clase");
            }
        }
        else{
            System.out.println("El id "+studentId+"no es valido");
        }
    }

    // Input verification
    public static boolean isAnOption(final String option, final String minOption, final String maxOption){

        final String regexString = String.format("([%s-%s])", minOption, maxOption);

        final boolean inOptions = option.matches(regexString);
        if (inOptions){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó una opcion valida");
            return false;
        }

    }

    public static boolean isPrice(final String price){

        final boolean isPrice = price.matches("(\\d)*\\.?\\d+");
        if (isPrice){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó un precio valido");
            return false;
        }

    }
    
    public static boolean isInt(final String integer){

        final boolean isInteger = integer.matches("(\\d)+");
        if (isInteger){
            return true;
        }
        else{
            System.out.println("\nNo se ingresó una entero valido");
            return false;
        }

    }

    public static boolean isName(final String text){
        // [A-Za-z]+ -> Begining with at least a letter
        // (\\s[A-Za-z]+)* -> Zero or more occurences of a space followed by one or more lettters
        final boolean isName = text.matches("[A-Za-z]+(\\s[A-Za-z]+)*");
        if(!isName){
            System.out.println("\nLa entrada ingresada no es un nombre valido.");
        }
        return isName;
    }
}