package calculate;

import contractPerson.ContractPerson;

public class CalculateContributions {

    private Integer coordinatedSalary;
    private Integer salary;
    private Integer coordinationDeduction;
    private Integer savingContributionAN;
    private Integer savingContributionAG;
    private Integer riskContributionAN;
    private Integer riskContributionAG;

    public CalculateContributions (ContractPerson contractPerson){
        this.salary = contractPerson.getSalary();
    }

public Integer calculateCoordinatedSalary(Integer coordinationDeduction){
    coordinatedSalary = salary - coordinationDeduction;
    return coordinatedSalary;
}

}