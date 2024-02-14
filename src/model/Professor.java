package model;

public abstract class Professor implements UniversityMember{
    
    // Attributes
    private long id;
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

    protected void setName(String name) {
        this.name = name;
    }

    protected double getSalary() {
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
                    this.id, this.name, this.salary);
    }

    protected abstract void calculateSalary();


}
