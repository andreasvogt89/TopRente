package interfaceView;

import javafx.scene.control.Alert;
import java.time.LocalDate;


import static java.time.temporal.ChronoUnit.YEARS;

class InterfaceModel {

    boolean checkAge(String birthday) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = LocalDate.parse(birthday);
        Integer age = Integer.valueOf((int) YEARS.between(birthdayDate, currentDate));
        if (age <= 23) {
            new Alert(Alert.AlertType.ERROR, "Person ist nicht 24").showAndWait();
            return false;}
        return true;
    }

    boolean checkSalary(Integer salary) {
        if (salary <= 21330) {
            new Alert(Alert.AlertType.ERROR, "Zu wenig Einkommen").showAndWait();
            return false;}
        return true;
    }

}
