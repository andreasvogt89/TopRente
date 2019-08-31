package pensionCertificate;


import contributionRates.ContributionRates;
import calculate.Calculate;
import contractPerson.ContractPerson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Model {


    private ContractPerson person;
    private ContributionRates contributionRates;
    private Calculate calculate = new Calculate();
    private Double calculatedDetuction;
    private Double coordinatedSalaryBVG;
    private Double coordinatedSalary;
    private Double credit;
    private Calendar pensionDateCalendar;

    public Model(ContractPerson person, ContributionRates contributionRates) {
        this.person = person;
        this.contributionRates = contributionRates;
        this.coordinatedSalaryBVG = calculate.coordinatedSalaryBVG(contributionRates.getCoordinatedDetuctionBVG(), person.getSalary(), contributionRates.getMinCoordinatedSalaryBVG(), contributionRates.getMaxCoordinatedSalaryBVG());
        this.credit = Double.valueOf(person.getCredit());
        this.calculatedDetuction = calculate.coordinationDeduction(contributionRates.getCoordinatedSalaryRate(), contributionRates.getCoordinatedDetuctionBVG(), person.getSalary());
        this.coordinatedSalary = calculate.coordinatedSalary(calculatedDetuction, person.getSalary());
        try {
           this.pensionDateCalendar = calculate.pensionDate(getPensionAge(),person.getBirthday());
        } catch (ParseException e) {
                e.printStackTrace();
            }
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

    private Double entryCredit (Double credit,String entryDate,Double calculatedEntryContribution) throws Exception{
        Date thisEnteryDate =new SimpleDateFormat("yyyy-MM-dd").parse(entryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisEnteryDate);
        double month = calendar.get(Calendar.MONTH);
        double monthRate = month / 12;
        return credit + (calculatedEntryContribution * (1 -monthRate));

    }

    private Double exitCreditWithInterest(Double exitCreditbeforeLastYear, Double interestRate, Double calculatedExitContribution){
        double moth = pensionDateCalendar.get(Calendar.MONTH) + 1;
        double monthRate = moth / 12;
        double untilDone = exitCreditbeforeLastYear + (calculatedExitContribution* monthRate);
       return  untilDone + ((untilDone * (interestRate/100) * monthRate));
    }

    private Double exitCredit(Double exitCreditbeforeLastYear, Double calculatedExitContribution){
        double moth = pensionDateCalendar.get(Calendar.MONTH) + 1;
        double monthRate = moth / 12;
        return exitCreditbeforeLastYear + (calculatedExitContribution* monthRate);
    }

    private Double calculateEntryContributionBVG(){
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculateYearGroup1BVG();
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculateYearGroup2BVG();
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculateYearGroup3BVG();
        } else
            return calculateYearGroup4BVG();
    }

    private Double calculateEntryContribution(){
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculateYearGroup1();
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculateYearGroup2();
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculateYearGroup3();
        } else
            return calculateYearGroup4();
    }

    String getPensionDate() {
        return pensionDateCalendar.getTime().toString();
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
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculate.contribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getSavingContributionAN() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup1(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup2(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getSavingContributionANGroup3(), coordinatedSalary);
        } else
            return calculate.contribution(contributionRates.getSavingContributionANGroup4(), coordinatedSalary);
    }

    Double getSavingContributionAGBVG() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculate.contribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getSavingContributioAG() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup1(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup2(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getSavingContributionAGGroup3(), coordinatedSalary);
        } else
            return calculate.contribution(contributionRates.getSavingContributionAGGroup4(), coordinatedSalary);
    }

    Double getRiskContributionANBVG() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup1BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup2BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculate.contribution(contributionRates.getRiskContributionANGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getRiskContributionAN() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup1(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup2(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getRiskContributionANGroup3(), coordinatedSalary);
        } else
            return calculate.contribution(contributionRates.getRiskContributionANGroup4(), coordinatedSalary);
    }

    Double getRiskContributionAGBVG() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup1BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup2BVG(), coordinatedSalaryBVG);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup3BVG(), coordinatedSalaryBVG);
        } else
            return calculate.contribution(contributionRates.getRiskContributionAGGroup4BVG(), coordinatedSalaryBVG);
    }

    Double getRiskContributioAG() {
        Integer age = person.getAge();
        if (calculate.ageBetween(age, 24, 34)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup1(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 35, 44)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup2(), coordinatedSalary);
        } else if (calculate.ageBetween(age, 44, 54)) {
            return calculate.contribution(contributionRates.getRiskContributionAGGroup3(), coordinatedSalary);
        } else
            return calculate.contribution(contributionRates.getRiskContributionAGGroup4(), coordinatedSalary);
    }

    Double getCalculatedCreditBVG() {
        double newCredit = 0;
        double interest = contributionRates.getInterestRate();
        try {
           newCredit = entryCreditWithInterest(credit,interest,person.getEntrydate(),calculateEntryContributionBVG());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (calculate.ageBetween(age, 24, 34)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit, interest), calculateYearGroup1BVG());
            } else if (calculate.ageBetween(age, 35, 44)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit, interest), calculateYearGroup2BVG());
            } else if (calculate.ageBetween(age, 44, 54)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit, interest), calculateYearGroup3BVG());
            } else {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit, interest), calculateYearGroup4BVG());
            }
        }
        return calculate.round(exitCreditWithInterest(newCredit,interest,calculateYearGroup4BVG()),2);
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
            if (calculate.ageBetween(age, 24, 34)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit,interest),calculateYearGroup1());
            } else if (calculate.ageBetween(age, 35, 44)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit,interest),calculateYearGroup2());
            } else if (calculate.ageBetween(age, 44, 54)) {
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit,interest),calculateYearGroup3());
            } else{
                newCredit = calculate.addUpCredit(calculate.payInterestOn(newCredit,interest),calculateYearGroup4());
            }
            System.out.println(newCredit);
        }
        return calculate.round(exitCreditWithInterest(newCredit,interest,calculateYearGroup4()),2);
    }

    Double getPension (){
        Double conversionByAge = calculate.conversionByAge(person.getSex(),getPensionAge(),contributionRates);
        return calculate.round(calculate.pension(getCalculatedCredit(),conversionByAge),2);
    }

    Double getPensionBVG (){
        return calculate.round(calculate.pension(getCalculatedCreditBVG(),contributionRates.getConversionRateMinBVG()),2);
    }


    private Double calculateYearGroup1BVG(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalaryBVG);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalaryBVG);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup2BVG(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalaryBVG);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalaryBVG);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup3BVG(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalaryBVG);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalaryBVG);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup4BVG(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalaryBVG);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalaryBVG);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup1(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup1(), coordinatedSalary);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup1(), coordinatedSalary);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup2(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup2(), coordinatedSalary);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup2(), coordinatedSalary);

        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup3(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup3(), coordinatedSalary);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup3(), coordinatedSalary);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    private Double calculateYearGroup4(){
        Double group1a = calculate.contribution(contributionRates.getSavingContributionAGGroup4(), coordinatedSalary);
        Double group1b = calculate.contribution(contributionRates.getSavingContributionANGroup4(), coordinatedSalary);
        return calculate.addUpContribution(group1a,group1b) * 12;
    }

    Double getCalculatedInvalidCredit() {
        double newCredit = 0;
        try {
            newCredit = entryCredit(credit,person.getEntrydate(),calculateEntryContribution());
            System.out.println(newCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (calculate.ageBetween(age, 24, 34)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup1());
            } else if (calculate.ageBetween(age, 35, 44)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup2());
            } else if (calculate.ageBetween(age, 44, 54)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup3());
            } else{
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup4());
            }
        }
        return calculate.round(exitCredit(newCredit,calculateYearGroup4()),2);
    }

    Double getCalculatedInvalidCreditBVG() {
        double newCredit = 0;
        try {
            newCredit = entryCredit(credit,person.getEntrydate(),calculateEntryContributionBVG());
            System.out.println(newCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer age = person.getAge() + 1;age  <= getPensionAge() - 1; age++) {
            if (calculate.ageBetween(age, 24, 34)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup1BVG());
            } else if (calculate.ageBetween(age, 35, 44)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup2BVG());
            } else if (calculate.ageBetween(age, 44, 54)) {
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup3BVG());
            } else{
                newCredit = calculate.addUpCredit(newCredit,calculateYearGroup4BVG());
            }
        }
        return calculate.round(exitCredit(newCredit,calculateYearGroup4BVG()),2);
    }

    Double getInvalidPension (){
        Double conversionByAge = calculate.conversionByAge(person.getSex(),getPensionAge(),contributionRates);
        return calculate.round(calculate.pension(getCalculatedInvalidCredit(),conversionByAge),2);
    }

    Double getInvalidPensionBVG (){
        return calculate.round(calculate.pension(getCalculatedInvalidCreditBVG(),contributionRates.getConversionRateMinBVG()),2);
    }

    Double getChildrenPension (){
        return getInvalidPension() * 0.2;
    }

    Double getChildrenPensionBVG (){
        return getInvalidPension() * 0.2;
    }

    Double getPartnerPension (){
        return getInvalidPension() * 0.6;
    }

    Double getPartnerPensionBVG (){
        return getInvalidPension() * 0.6;
    }



}