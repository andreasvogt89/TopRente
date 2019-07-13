package prototype;

import java.util.Date;

public class ContractPerson {

    private String name;
    private Date birthdate;
    private Integer annualSalary;
    private String  employmentLevel;
    private Integer credit;
    private Integer coordinatedSalary;
    private Integer coordinationDeduction = 24885;


    public ContractPerson(String name, Date birthdate, Integer annualSalary, String employmentLevel, Integer credit) {
        this.name = name;
        this.credit = credit;
        this.birthdate = birthdate;
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

    public Date getBirthdate() {
        return birthdate;
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

    public Integer getCredit(){
        return credit;
    }
}
