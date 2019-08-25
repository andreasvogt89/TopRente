package personView;


import ContributionRates.ContributionRates;
import calculate.CalculateContributions;
import calculate.CalculatePension;
import contractPerson.ContractPerson;


public class ModelPersonView {


    private ContractPerson person;
    private ContributionRates contributionRates;
    private CalculateContributions calculateContributions = new CalculateContributions();
    private CalculatePension calculatePension = new CalculatePension();
    private Double calculatedDetuction;
    private Double coordinatedSalaryBVG;
    private Double coordinatedSalary;
    private Double credit;


    public ModelPersonView(ContractPerson person, ContributionRates contributionRates) {
        this.person = person;
        this.contributionRates = contributionRates;
        calculateDetuction();
        this.coordinatedSalaryBVG = calculateContributions.calculateCoordinatedSalaryBVG(contributionRates.getCoordinatedDetuctionBVG(), person.getSalary(), contributionRates.getMinCoordinatedSalaryBVG(), contributionRates.getMaxCoordinatedSalaryBVG());
        this.coordinatedSalary = calculateContributions.calculateCoordinatedSalary(calculatedDetuction, person.getSalary());
        this.credit = Double.valueOf(person.getCredit());


    }

    private void calculateDetuction() {
        this.calculatedDetuction = calculateContributions.calculateCoordinationDeduction(contributionRates.getCoordinatedSalaryRate(), contributionRates.getCoordinatedDetuctionBVG(), person.getSalary());
    }

    boolean ageBetween(Integer age, Integer minAge, Integer maxAge) {
        if (age >= minAge && age <= maxAge)
            return true;
        else
            return false;
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

    String getCalculatedCreditBVG (){
        Integer age;
        Double newCredit = credit;
        for (age = person.getAge();age  <= 65; age++) {
            if (ageBetween(age, 24, 34)) {
                Double contribution = calculateYearGroup1();
                Double currentCredit = calculatePension.addUpCredit(newCredit,contribution);
                newCredit = calculatePension.calculateInterest(currentCredit,contributionRates.getInterestRate());
            } else if (ageBetween(age, 35, 44)) {
                Double contribution = calculateYearGroup2();
                Double currentCredit = calculatePension.addUpCredit(newCredit,contribution);
                newCredit = calculatePension.calculateInterest(currentCredit,contributionRates.getInterestRate());
            } else if (ageBetween(age, 44, 54)) {
                Double contribution = calculateYearGroup3();
                Double currentCredit = calculatePension.addUpCredit(newCredit,contribution);
                newCredit = calculatePension.calculateInterest(currentCredit,contributionRates.getInterestRate());
            } else{
                Double contribution = calculateYearGroup4();
                Double currentCredit = calculatePension.addUpCredit(newCredit,contribution);
                newCredit = calculatePension.calculateInterest(currentCredit,contributionRates.getInterestRate());
            }
    }
        return String.valueOf(CalculateContributions.round((newCredit),2));
    }

    private Double calculateYearGroup1(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup1BVG(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup1BVG(), coordinatedSalary);
        Double group1c = calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup1BVG(), coordinatedSalary);
        Double group1d = calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup1BVG(), coordinatedSalary);

        return calculatePension.addUpContribution(group1a,group1b,group1c,group1d) * 12;
    }

    private Double calculateYearGroup2(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup2BVG(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup2BVG(), coordinatedSalary);
        Double group1c = calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup2BVG(), coordinatedSalary);
        Double group1d = calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup2BVG(), coordinatedSalary);

        return calculatePension.addUpContribution(group1a,group1b,group1c,group1d) * 12;
    }

    private Double calculateYearGroup3(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup3BVG(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup3BVG(), coordinatedSalary);
        Double group1c = calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup3BVG(), coordinatedSalary);
        Double group1d = calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup3BVG(), coordinatedSalary);

        return calculatePension.addUpContribution(group1a,group1b,group1c,group1d) * 12;
    }

    private Double calculateYearGroup4(){
        Double group1a = calculateContributions.calculateContribution(contributionRates.getSavingContributionAGGroup4BVG(), coordinatedSalary);
        Double group1b = calculateContributions.calculateContribution(contributionRates.getSavingContributionANGroup4BVG(), coordinatedSalary);
        Double group1c = calculateContributions.calculateContribution(contributionRates.getRiskContributionANGroup4BVG(), coordinatedSalary);
        Double group1d = calculateContributions.calculateContribution(contributionRates.getRiskContributionAGGroup4BVG(), coordinatedSalary);

        return calculatePension.addUpContribution(group1a,group1b,group1c,group1d) * 12;
    }

}