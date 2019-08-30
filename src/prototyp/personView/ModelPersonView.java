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

    private Double entryCreditWithInterest (Double credit, Double interestRate, String entryDate,Double calculatedEntryContribution) throws Exception{
        Date thisEnteryDate =new SimpleDateFormat("yyyy-MM-dd").parse(entryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisEnteryDate);
        double month = calendar.get(Calendar.MONTH);
        double monthRate = month / 12;
        return credit + ((credit*(interestRate/100))* monthRate) + ((calculatedEntryContribution * (1 -monthRate)));

    }

    private Double exitCredit (Double exitCreditbeforeLastYear, Double interestRate, Double calculatedExitContribution){
        double moth = pensionDateCalendar.get(Calendar.MONTH) + 1;
        double monthRate = moth / 12;
        double untilDone = exitCreditbeforeLastYear + (calculatedExitContribution* monthRate);
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

    private Double calculateEntryContribution(){
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateYearGroup1();
        } else if (ageBetween(age, 35, 44)) {
            return calculateYearGroup2();
        } else if (ageBetween(age, 44, 54)) {
            return calculateYearGroup3();
        } else
            return calculateYearGroup4();
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

    ContributionRates getContributionRates (){
        return this.contributionRates;
    }

    Double getCoordinatedSalaryContributionBVG() {
        return contributionRates.getCoordinatedDetuctionBVG();
    }

    Double getCoordinatedSalaryContribution() {
        return calculatedDetuction;
    }

    Double getCoordinatedSalaryBVG() {
        return coordinatedSalaryBVG;
    }

    Double getCoordinatedSalary() {
        return coordinatedSalary;
    }

    Double getSavingContributionANBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getSavingContributionAN() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1(), coordinatedSalary);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2(), coordinatedSalary);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3(), coordinatedSalary);
        } else
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4(), coordinatedSalary);
    }

    Double getSavingContributionAGBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getSavingContributioAG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1(), coordinatedSalary);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2(), coordinatedSalary);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3(), coordinatedSalary);
        } else
            return calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4(), coordinatedSalary);
    }

    Double getRiskContributionANBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup1BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup2BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getRiskContributionAN() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup1(), coordinatedSalary);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup2(), coordinatedSalary);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup3(), coordinatedSalary);
        } else
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup4(), coordinatedSalary);
    }

    Double getRiskContributionAGBVG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup1BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup2BVG(), coordinatedSalaryBVG);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getRiskContributioAG() {
        Integer age = person.getAge();
        if (ageBetween(age, 24, 34)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup1(), coordinatedSalary);
        } else if (ageBetween(age, 35, 44)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup2(), coordinatedSalary);
        } else if (ageBetween(age, 44, 54)) {
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup3(), coordinatedSalary);
        } else
            return calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup4(), coordinatedSalary);
    }

    Double getCalculatedCreditBVG() {
        double newCredit = 0;
        double interest = contributionRates.getInterestRate();
        try {
           newCredit = entryCreditWithInterest(credit,interest,person.getEntrydate(),calculateEntryContributionBVG());
           System.out.println(newCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (ageBetween(age, 24, 34)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit, interest), calculateYearGroup1BVG());
            } else if (ageBetween(age, 35, 44)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit, interest), calculateYearGroup2BVG());
            } else if (ageBetween(age, 44, 54)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit, interest), calculateYearGroup3BVG());
            } else {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit, interest), calculateYearGroup4BVG());
            }
        }
        System.out.println(newCredit);
        return CalculateContributions.round(exitCredit(newCredit,interest,calculateYearGroup4BVG()),2);
    }

    Double getCalculatedCredit() {
        double newCredit = 0;
        double interest = contributionRates.getInterestRate();
        try {
            newCredit = entryCreditWithInterest(credit,interest,person.getEntrydate(),calculateEntryContribution());
            System.out.println(newCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (ageBetween(age, 24, 34)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),calculateYearGroup1());
            } else if (ageBetween(age, 35, 44)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),calculateYearGroup2());
            } else if (ageBetween(age, 44, 54)) {
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),calculateYearGroup3());
            } else{
                newCredit = calculatePension.addUpCredit(calculatePension.calculateInterest(newCredit,interest),calculateYearGroup4());
            }
            System.out.println(newCredit);
        }
        return CalculateContributions.round(exitCredit(newCredit,interest,calculateYearGroup4()),2);
    }

    Double getPension (){
        Double conversionByAge = calculatePension.calculateConversionAge(person.getSex(),getPensionAge(),contributionRates.getConversionRate60(),contributionRates.getConversionRate61(),contributionRates.getConversionRate62(),contributionRates.getConversionRate63M(),contributionRates.getConversionRate63W(),contributionRates.getConversionRate64M(),contributionRates.getConversionRate64W(),contributionRates.getConversionRate65());
        return CalculateContributions.round(calculatePension.calculatePension(getCalculatedCredit(),conversionByAge),2);
    }

    Double getPensionBVG (){

        return CalculateContributions.round(calculatePension.calculatePension(getCalculatedCreditBVG(),contributionRates.getConversionRateMinBVG()),2);
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

    private Double calculateYearGroup1(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1(), coordinatedSalary);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup2(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2(), coordinatedSalary);

        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup3(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3(), coordinatedSalary);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup4(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4(), coordinatedSalary);
        return calculatePension.addUpContribution(group1a,group1b) * 12;
    }


}