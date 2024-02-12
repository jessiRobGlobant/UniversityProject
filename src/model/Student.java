package model;

public class Student {
    
    // Atributes
    private long id;
    private String name;
    private byte age;
    private static int count = 1;

    // Constructor
    public Student(String name, byte age) {
        this.id = count;
        this.name = name;
        this.age = age;
        count ++;
    }

    // Getters and Setters
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected byte getAge() {
        return age;
    }

    protected void setAge(byte age) {
        this.age = age;
    }

    protected long getId() {
        return id;
    }

    // Display info
    public String info(){
        return String.format("Id: %d\nNombre: %s\nEdad: %d", 
                    getId(), getName(), getAge());
    }
    
}
