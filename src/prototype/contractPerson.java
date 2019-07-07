package prototype;

public class contractPerson extends Observable{
    private String name;



    private Integer age;
    private Integer annualSalary;
    private String  employmentLevel;
    private Integer coordinatedSalary;
    private Integer coordinationDeduction = 24885;


    public contractPerson(String name, Integer age, Integer annualSalary,String employmentLevel) {
        this.name = name;
        this.age = age;
        this.annualSalary = annualSalary;
        this.employmentLevel = employmentLevel;
        calculateCoordinatedSalary(annualSalary);
    }

    private Integer calculateCoordinatedSalary(Integer annualSalary){
        coordinatedSalary = annualSalary - coordinationDeduction;
        return coordinatedSalary;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public String getEmploymentLevel() {
        return employmentLevel;
    }

    public Integer getCoordinatedSalary() {
        return coordinatedSalary;
    }

    public Integer getCoordinationDeduction() {
        return coordinationDeduction;
    }
}
