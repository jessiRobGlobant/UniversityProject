package model;

public class PartTimeProfessor extends Professor{

    private byte activeHoursPerWeek;
    
    public PartTimeProfessor(String name, byte activeHoursPerWeek) {
        super(name);
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    // Getters and Setters
    protected byte getActiveHoursPerWeek() {
        return activeHoursPerWeek;
    }

    protected void setActiveHoursPerWeek(byte activeHoursPerWeek) {
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    @Override
    protected void calculateSalary() {
        setSalary(getBasesalary()*activeHoursPerWeek);
    }

    @Override
    public String info(){
        return String.format("%s\nHoras de experiencia: %d",super.info(), getActiveHoursPerWeek());
    }

}
