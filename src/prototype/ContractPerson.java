package prototype;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class ContractPerson {

    private SimpleStringProperty lastName;
    private SimpleStringProperty name;
    private Date birthdate;
    private SimpleIntegerProperty annualSalary;
    private SimpleStringProperty  employmentLevel;
    private SimpleIntegerProperty  credit;
    private Integer coordinatedSalary;
    private Integer coordinationDeduction = 24885;



    public ContractPerson(String lastName, String name, Date birthdate, Integer annualSalary, String employmentLevel, Integer credit) {
        this.lastName = new SimpleStringProperty(lastName);
        this.name = new SimpleStringProperty(name);
        this.credit = new SimpleIntegerProperty(credit);
        this.birthdate = birthdate;
        this.annualSalary = new SimpleIntegerProperty(annualSalary);
        this.employmentLevel = new SimpleStringProperty(employmentLevel);
        calculateCoordinatedSalary(annualSalary);
    }


    private Integer calculateCoordinatedSalary(Integer annualSalary){
        coordinatedSalary = coordinationDeduction - annualSalary;
        return coordinatedSalary;
    }

    public String getLastName(){
        return lastName.getName();
    }

    public String getName() {
        return name.getName();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Integer getAnnualSalary() {
        return annualSalary.getValue();
    }

    public String getEmploymentLevel() {
        return employmentLevel.getName();
    }

    public Integer getCoordinatedSalary() {
        return coordinatedSalary;
    }

    public Integer getCoordinationDeduction() {
        return coordinationDeduction;
    }

    public Integer getCredit(){
        return credit.getValue();
    }
}


