package model;

public abstract class Professor implements UniversityMember{
    
    // Attributes
    private final long id;
    private String name;
    private double salary;
    private static final double baseSalary = 1000;
    private static int count = 1;

    // Constructor
    public Professor(String name) {
        this.id = count;
        this.name = name;
        count ++;
    }

    // Getters and Setters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

    /* All classes can see the info, but only in the same package 
    the apps can be changed, only members of the package 
    could do it (likely University class)*/ 
    protected void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double salary){
        this.salary = salary;
    }

    public static double getBasesalary() {
        return baseSalary;
    }

    // Display info
    public String info(){
        return String.format("Id: %d\nNombre: %s\nSalario: $%.2f", 
                    getId(), getName(), getSalary());
    }

    protected abstract void calculateSalary();


}
