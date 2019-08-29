package personView;


import ContributionRates.ContributionRates;
import calculate.CalculateContributions;
import calculate.CalculatePension;
import contractPerson.ContractPerson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ModelPersonView {


    private ContractPerson person;
    private ContributionRates contributionRates;
    private CalculateContributions calculateContributions = new CalculateContributions();
    private CalculatePension calculatePension = new CalculatePension();
    private Double calculatedDetuction;
    private Double coordinatedSalaryBVG;
    private Double coordinatedSalary;
    private Double credit;
    private Calendar pensionDateCalendar;

    public ModelPersonView(ContractPerson person, ContributionRates contributionRates) {
        this.person = person;
        this.contributionRates = contributionRates;
        calculateDetuction();
        this.coordinatedSalaryBVG = calculateContributions.calculateCoordinatedSalaryBVG(contributionRates.getCoordinatedDetuctionBVG(), person.getSalary(), contributionRates.getMinCoordinatedSalaryBVG(), contributionRates.getMaxCoordinatedSalaryBVG());
        this.coordinatedSalary = calculateContributions.calculateCoordinatedSalary(calculatedDetuction, person.getSalary());
        this.credit = Double.valueOf(person.getCredit());

        try {
           this.pensionDateCalendar = calculatePensionDate(getPensionAge(),person.getBirthday());
        } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    private void calculateDetuction() {
        this.calculatedDetuction = calculateContributions.calculateCoordinationDeduction(contributionRates.getCoordinatedSalaryRate(), contributionRates.getCoordinatedDetuctionBVG(), person.getSalary());
    }

    private boolean ageBetween(Integer age, Integer minAge, Integer maxAge) {
        if (age >= minAge && age <= maxAge)
            return true;
        else
            return false;
    }

    private Integer getPensionAge(){
        if(person.getSex().matches("Männlich")){
            return 65;}
        return 64;

    }

    private Double entryCreditWithInterest (Double credit, Double interestRate, String entryDate) throws Exception{
        Date thisEnteryDate =new SimpleDateFormat("yyyy-MM-dd").parse(entryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisEnteryDate);
        double month = calendar.get(Calendar.MONTH);
        double monthRate = month / 12;
        return credit + ((credit*(interestRate/100))* monthRate) + ((calculateEntryContributionBVG() * (1 -monthRate)));

    }

    private Double exitCredit (Double exitCreditbeforeLastYear, Double interestRate){
        double moth = pensionDateCalendar.get(Calendar.MONTH) + 1;
        double monthRate = moth / 12;
        double untilDone = exitCreditbeforeLastYear + (calculateYearGroup4BVG()* monthRate);
       return  untilDone + ((untilDone * (interestRate/100) * monthRate));
    }

    private Double calculateEntryContributionBVG(){
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateYearGroup1BVG();
        } else if (ageBetween(age, 35, 44)) {
            return calculateYearGroup2BVG();
        } else if (ageBetween(age, 44, 54)) {
            return calculateYearGroup3BVG();
        } else
            return calculateYearGroup4BVG();
    }

    String getPensionDate() {
        return pensionDateCalendar.getTime().toString();
    }

    private Calendar calculatePensionDate(Integer pensionAge, String birthday) throws ParseException {
        Date thisBirthday =new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisBirthday);
        calendar.add(Calendar.YEAR,pensionAge);
        int lastDayOfMoth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastDayOfMoth);
        return calendar;
    }

    ContractPerson getPerson() {
        return person;
    }

    String getCoordinatedSalaryContributionBVG() {
        return String.valueOf(contributionRates.getCoordinatedDetuctionBVG());
    }

    String getCoordinatedSalaryContribution() {
        return String.valueOf(calculatedDetuction);
    }

    String getCoordinatedSalaryBVG() {
        return String.valueOf(coordinatedSalaryBVG);
    }

    String getCoordinatedSalary() {
        return String.valueOf(coordinatedSalary);
    }

    String getSavingContributionANBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalaryBVG));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalaryBVG));
    }

    String getSavingContributionAN() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1(), coordinatedSalary));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2(), coordinatedSalary));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3(), coordinatedSalary));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4(), coordinatedSalary));
    }

    String getSavingContributionAGBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalaryBVG));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalaryBVG));
    }

    String getSavingContributioAG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1(), coordinatedSalary));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2(), coordinatedSalary));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3(), coordinatedSalary));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4(), coordinatedSalary));
    }

    String getRiskContributionANBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup1BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup2BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup3BVG(), coordinatedSalaryBVG));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup4BVG(), coordinatedSalaryBVG));
    }

    String getRiskContributionAN() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup1(), coordinatedSalary));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup2(), coordinatedSalary));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup3(), coordinatedSalary));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup4(), coordinatedSalary));
    }

    String getRiskContributionAGBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup1BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup2BVG(), coordinatedSalaryBVG));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup3BVG(), coordinatedSalaryBVG));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup4BVG(), coordinatedSalaryBVG));
    }

    String getRiskContributioAG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup1(), coordinatedSalary));
        } else if (ageBetween(age, 35, 44)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup2(), coordinatedSalary));
        } else if (ageBetween(age, 44, 54)) {
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup3(), coordinatedSalary));
        } else
            return String.valueOf(calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup4(), coordinatedSalary));
    }

    String getInterestRate(){
        return String.valueOf(contributionRates.getInterestRate());
    }

    String getCalculatedCreditBVG() {
        double newCredit = 0;
        double interest = contributionRates.getInterestRate();
        try {
           newCredit = entryCreditWithInterest(credit,interest,person.getEntrydate());
           System.out.println(newCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (ageBetween(age, 24, 34)) {
                Double contribution = calculateYearGroup1BVG();
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),contribution);
            } else if (ageBetween(age, 35, 44)) {
                Double contribution = calculateYearGroup2BVG();
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),contribution);
            } else if (ageBetween(age, 44, 54)) {
                Double contribution = calculateYearGroup3BVG();
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),contribution);
            } else{
                Double contribution = calculateYearGroup4BVG();
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),contribution);
            }
            System.out.println(newCredit);

    }
        System.out.println(newCredit);
        return String.valueOf(CalculateContributions.round((exitCredit(newCredit,interest)),2));
    }

    private Double calculateYearGroup1BVG(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalaryBVG);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalaryBVG);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup2BVG(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalaryBVG);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalaryBVG);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup3BVG(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalaryBVG);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalaryBVG);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup4BVG(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalaryBVG);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalaryBVG);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

}