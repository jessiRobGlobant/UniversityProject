package model;

public class Student implements UniversityMember{
    
    // Atributes
    private final long id;
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

    public byte getAge() {
        return age;
    }

    protected void setAge(byte age) {
        this.age = age;
    }


    // Display info
    public String info(){
        return String.format("Id: %d\nNombre: %s\nEdad: %d", 
                    getId(), getName(), getAge());
    }
    
}
