import java.util.List;
import java.util.Scanner;

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
            System.out.println("6. Listar clases de un estudiante");
            System.out.println("0. Salir de la aplicacion");

            System.out.println("\nSeleccione la accion que desea realizar:");

            option = scan.nextLine();

            if (isAnOption(option, "6")){
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
                        break;
                    case 5: 
                        break;
                    case 6: 
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
            System.out.println(i);
            System.out.println(university.getProfessorInfo(id));
            System.out.println();
            i ++;
        }
    }

    public static void showStudents(){
        System.out.println("\nLISTADO DE ESTUDIANTES\n");
        List<Long> studentsIds = university.getStudentsIds();
        short i = 0;
        for (long id: studentsIds){
            System.out.println(i);
            System.out.println(university.getStudentInfo(id));
            System.out.println();
            i ++;
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

            if(isAnOption(option, String.valueOf(String.valueOf(i-1)))){
                String className = university.getClassByIndex(Integer.valueOf(i-1));
                System.out.println("\nInformacion sobre la clase seleccionada:\n");
                System.out.println(university.getClassInfo(className));
            }
        }
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
            System.out.println("\nNo se ingresó una cantidad valida");
            return false;
        }

    }

    public static boolean isAlphabetical(String text){
        boolean isAlpha = text.matches("\\w*");
        if(isAlpha){
            System.out.println("\n La entrada ingresada no es un nombre valido.");
        }
        return isAlpha;
    }

    // Main
    public static void main(String[] args) {
        
        System.out.println("Bienvenido a tu programa universitario :) \n");
        // Excecute main menu
        mainMenu();
    }
}