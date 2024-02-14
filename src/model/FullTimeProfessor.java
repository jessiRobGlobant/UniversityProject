package model;

public class FullTimeProfessor extends Professor{

    private byte experienceYears;

    public FullTimeProfessor(String name, byte experienceYears) {
        super(name);
        this.experienceYears = experienceYears;
        calculateSalary();
    }

    public byte getExperienceYears() {
        return experienceYears;
    }

    protected void setExperienceYears(byte experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    protected void calculateSalary() {
        setSalary(getBasesalary()*1.1*getExperienceYears());
    }

    @Override
    public String info(){
        return String.format("%s\nTipo de contrato: tiempo completo\nAños de experiencia: %d",
                            super.info(), getExperienceYears());
    }
    
}
