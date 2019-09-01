package mainInterface;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;


import static java.time.temporal.ChronoUnit.YEARS;

class Model {

    boolean checkAge(String birthday) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = LocalDate.parse(birthday);
        Integer age = Integer.valueOf((int) YEARS.between(birthdayDate, currentDate));
        if (age <= 23) {
            new Alert(Alert.AlertType.ERROR, "Person ist nicht 24").showAndWait();
            return false;}
        return true;
    }

    boolean checkSalary(Integer salary, Double minSalary) {
        if (salary <= minSalary) {
            new Alert(Alert.AlertType.ERROR, "Zu wenig Einkommen").showAndWait();
            return false;}
        return true;
    }

    boolean isInt(TextField input, String message) {
        try {
            int value = Integer.parseInt((input.getText()));
            System.out.println(value + "is a number");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error " + message + " is not a number");
            return false;
        }
    }

}
