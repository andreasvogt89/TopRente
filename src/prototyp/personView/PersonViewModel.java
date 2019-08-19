package personView;


import ContributionRates.ContributionRates;
import calculate.CalculateContributions;
import contractPerson.ContractPerson;


public class PersonViewModel {


    private ContractPerson person;
    private ContributionRates contributionRates;
    private CalculateContributions calculateContributions = new CalculateContributions();
    private Double calculatedDetuction;
    private Double coordinatedSalaryBVG;
    private Double coordinatedSalary;


    public PersonViewModel(ContractPerson person, ContributionRates contributionRates){
        this.person = person;
        this.contributionRates = contributionRates;
        calculateDetuction();
        this.coordinatedSalaryBVG = calculateContributions.calculateCoordinatedSalaryBVG(contributionRates.getCoordinatedDetuctionBVG(),person.getSalary(),contributionRates.getMinCoordinatedSalaryBVG(),contributionRates.getMaxCoordinatedSalaryBVG());
        this.coordinatedSalary = calculateContributions.calculateCoordinatedSalary(calculatedDetuction, person.getSalary());


    }

    private void calculateDetuction(){
        this.calculatedDetuction = calculateContributions.calculateCoordinationDeduction(contributionRates.getCoordinatedSalaryRate(),contributionRates.getCoordinatedDetuctionBVG(), person.getSalary());
    }

    ContractPerson getPerson() {
        return person;
    }

    String getCoordinatedSalaryContributionBVG(){
        return String.valueOf(contributionRates.getCoordinatedDetuctionBVG());
    }

    String getCoordinatedSalaryContribution(){
        return String.valueOf(calculateContributions.calculateCoordinationDeduction(contributionRates.getCoordinatedSalaryRate(),contributionRates.getCoordinatedDetuctionBVG(), person.getSalary()));
    }

    String getCoordinatedSalaryBVG(){
       return String.valueOf(coordinatedSalaryBVG);
    }

    String getCoordinatedSalary(){
        return String.valueOf(coordinatedSalary);
    }


}
