package prototype;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class ContractPerson {

    private SimpleStringProperty lastname;
    private SimpleStringProperty name;
    private Date birthday;
    private SimpleIntegerProperty salary;
    private SimpleStringProperty level;
    private SimpleIntegerProperty  credit;
    private Integer coordinatedSalary;
    private Integer coordinationDeduction = 24885;



    public ContractPerson(String lastname, String name, Date birthday, Integer salary, String level, Integer credit) {
        this.lastname = new SimpleStringProperty(lastname);
        this.name = new SimpleStringProperty(name);
        this.credit = new SimpleIntegerProperty(credit);
        this.birthday = birthday;
        this.salary = new SimpleIntegerProperty(salary);
        this.level = new SimpleStringProperty(level);
        calculateCoordinatedSalary(salary);
    }


    private Integer calculateCoordinatedSalary(Integer annualSalary){
        coordinatedSalary = coordinationDeduction - annualSalary;
        return coordinatedSalary;
    }

    public String getLastname(){
        return lastname.getName();
    }

    public String getName() {
        return name.getName();
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getSalary() {
        return salary.getValue();
    }

    public String getLevel() {
        return level.getName();
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


