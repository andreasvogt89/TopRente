package contractPerson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.FormatterClosedException;

import static java.time.temporal.ChronoUnit.YEARS;

public class ContractPerson {

    private SimpleStringProperty lastname;
    private SimpleStringProperty name;
    private SimpleStringProperty birthday;
    private SimpleIntegerProperty salary;
    private SimpleStringProperty level;
    private SimpleIntegerProperty  credit;
    private Integer coordinatedSalary;
    private Integer coordinationDeduction = 24885;

    public ContractPerson(String lastname, String name, String birthday, Integer salary, String level, Integer credit) {
        this.lastname = new SimpleStringProperty(lastname);
        this.name = new SimpleStringProperty(name);
        this.credit = new SimpleIntegerProperty(credit);
        this.birthday = new SimpleStringProperty(birthday);
        this.salary = new SimpleIntegerProperty(salary);
        this.level = new SimpleStringProperty(level);
        calculateCoordinatedSalary(salary);
    }


    private Integer calculateCoordinatedSalary(Integer annualSalary){
        coordinatedSalary = coordinationDeduction - annualSalary;
        return coordinatedSalary;
    }

    public String getLastname(){
        return lastname.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getBirthday() {
        return birthday.getValue();
    }

    public Integer getSalary() {
        return salary.getValue();
    }

    public String getLevel() {
        return level.getValue();
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

    public Integer calculateAge (String birthday) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = LocalDate.parse(birthday);
        Integer age = Integer.valueOf((int) YEARS.between(birthdayDate, currentDate));
        try{
        if (age >= 24) ;}catch (Exception e){
        new Alert(Alert.AlertType.ERROR, "Person ist nicht 24").showAndWait();}
        return age;
    }
}


