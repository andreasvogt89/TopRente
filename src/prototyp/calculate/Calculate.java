package calculate;

import contributionRates.ContributionRates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to calculate the pension certificate
 *
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class Calculate {

public Double coordinationDeduction(Double coordinationDeductionRate, Double coordinationDeductionBVG, Integer salary){
        Double coordinationDeductioncalc = (coordinationDeductionRate/100) * Double.valueOf(salary);
        if (coordinationDeductioncalc >= coordinationDeductionBVG){
            return coordinationDeductionBVG;
        }else {
            return coordinationDeductioncalc;
        }

    }

public Double coordinatedSalary(Double coordinationDeduction, Integer salary){
    return salary - coordinationDeduction;
    }

public Double coordinatedSalaryBVG(Double coordinationDeduction, Integer salary, Double minCoordinatedSalary, Double maxCoordinatedSalary){
        Double coordinatedSalary = salary - coordinationDeduction;
        if (coordinatedSalary <= minCoordinatedSalary){
            return minCoordinatedSalary;
        }else if (coordinatedSalary >= maxCoordinatedSalary) {
            return maxCoordinatedSalary;
        } else return coordinatedSalary;

    }

public Double contribution(Double ContributionRate, Double coordinatedSalary){
    return round((coordinatedSalary * (ContributionRate / 100))/12,2);

}

public Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

public boolean ageBetween(Integer age, Integer minAge, Integer maxAge) {
        return (age >= minAge && age <= maxAge);

    }

public Double addUpContribution(Double saveingAN, Double savingAG){
        return saveingAN + savingAG;
    }

public Double payInterestOn(Double currentCredit, Double interestRate) {
        return (currentCredit * (interestRate/100)) +currentCredit;
    }

public Double addUpCredit (Double contribution, Double currentCredit){
        return currentCredit + contribution;
    }

public Double pension(Double calculatedCredit, Double conversionRate){

        return (calculatedCredit/100) * conversionRate;
    }

public Double conversionByAge(String sex, Integer pensionAge, ContributionRates contributionRates){
        if (pensionAge.equals(60)) {
            return contributionRates.getConversionRate60();
        }else if(pensionAge.equals(61)) {
            return contributionRates.getConversionRate62();
        }else if (pensionAge.equals(62)) {
            return contributionRates.getConversionRate62();
        }else if (pensionAge.equals(63) && sex.matches("Männlich")) {
            return contributionRates.getConversionRate63M();
        }else if (pensionAge.equals(63) && sex.matches("Weiblich")) {
            return contributionRates.getConversionRate63W();
        }else if (pensionAge.equals(64) && sex.matches("Männlich")) {
            return contributionRates.getConversionRate64M();
        }else if (pensionAge.equals(64) && sex.matches("Weiblich")) {
            return contributionRates.getConversionRate64W();
        }else return contributionRates.getConversionRate65();

    }

public Calendar pensionDate(Integer pensionAge, String birthday) throws ParseException {
        Date thisBirthday =new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisBirthday);
        calendar.add(Calendar.YEAR,pensionAge);
        int lastDayOfMoth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastDayOfMoth);
        return calendar;
    }



}