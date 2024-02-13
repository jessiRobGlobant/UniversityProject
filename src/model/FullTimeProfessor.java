package model;

public class FullTimeProfessor extends Professor{

    private byte experienceYears;

    public FullTimeProfessor(String name, byte experienceYears) {
        super(name);
        this.experienceYears = experienceYears;
    }

    protected byte getExperienceYears() {
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
        return String.format("%s\nHoras de experiencia: %d",
                            super.info(), getExperienceYears());
    }
    
}
