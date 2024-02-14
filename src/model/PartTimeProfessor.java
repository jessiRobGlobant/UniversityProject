package model;

public class PartTimeProfessor extends Professor{

    private byte activeHoursPerWeek;
    
    public PartTimeProfessor(String name, byte activeHoursPerWeek) {
        super(name);
        this.activeHoursPerWeek = activeHoursPerWeek;
        calculateSalary();
    }

    // Getters and Setters
    public byte getActiveHoursPerWeek() {
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
        return String.format("%s\nTipo de contrato: medio tiempo\nHoras por semana: %d",super.info(), getActiveHoursPerWeek());
    }

}
