package contractPerson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.YEARS;

/**
 * Object for contract persons
 * is Part of the model to the mcv construct interface
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class ContractPerson {

    private SimpleStringProperty lastname;
    private SimpleStringProperty name;
    private SimpleStringProperty birthday;
    private SimpleIntegerProperty salary;
    private SimpleStringProperty level;
    private SimpleIntegerProperty  credit;
    private SimpleStringProperty  sex;
    private SimpleIntegerProperty  insurance;
    private SimpleStringProperty entrydate;



    public ContractPerson(String lastname, String name, String birthday, Integer salary, String level, Integer credit,Integer insurance,String sex, String entrydate) {
        this.lastname = new SimpleStringProperty(lastname);
        this.name = new SimpleStringProperty(name);
        this.credit = new SimpleIntegerProperty(credit);
        this.birthday = new SimpleStringProperty(birthday);
        this.salary = new SimpleIntegerProperty(salary);
        this.level = new SimpleStringProperty(level);
        this.insurance = new SimpleIntegerProperty(insurance);
        this.sex = new SimpleStringProperty(sex);
        this.entrydate = new SimpleStringProperty(entrydate);
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

    public Integer getCredit(){
        return credit.getValue();
    }

    public String getSex() {
        return sex.getValue();
    }

    public Integer getInsurance() {
        return insurance.getValue();
    }

    public String getEntrydate(){
        return entrydate.getValue();
    }

    public Integer getAge (){
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = LocalDate.parse(getBirthday());
        Integer age = Integer.valueOf((int) YEARS.between(birthdayDate, currentDate));
        return age;
    }

}


