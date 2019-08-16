package calculate;

import contractPerson.ContractPerson;

public class CalculateContributions {

    private Integer coordinatedSalaryBVG;
    private Double coordinatedSalary;
    private Integer salary;
    private Integer coordinationDeduction;
    private Integer savingContributionAN;
    private Integer savingContributionAG;
    private Integer riskContributionAN;
    private Integer riskContributionAG;


public Integer calculateCoordinatedSalaryBVG(Integer coordinationDeduction, Integer salary){
    coordinatedSalaryBVG = salary - coordinationDeduction;
    return coordinatedSalaryBVG;
}

public Double calculateCoordinatedSalary(Double coordinationContribution, Integer salary){
       Double calc = coordinationContribution * salary;
       Double calcCo = Double.valueOf(coordinationDeduction);
       if (calc > coordinationDeduction){
           return coordinatedSalary = calcCo;
       }else {
           return coordinatedSalary = calc;
       }


}


public Double calculatesavingContributionAN(Double savingContributionANRate, Integer salary){
   Double savingContributionAN = (salary * (savingContributionANRate / 100)) / 12;

    return savingContributionAN;
}


}