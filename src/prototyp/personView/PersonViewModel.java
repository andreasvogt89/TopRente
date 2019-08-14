package personView;


import ContributionRates.ContributionRates;
import calculate.CalculateContributions;
import contractPerson.ContractPerson;

public class PersonViewModel {


    private ContractPerson person;
    private ContributionRates contributionRates;
    private ContributionRates contributionRatesBVG;



    public PersonViewModel(ContractPerson person, ContributionRates contributionRates, ContributionRates contributionRatesBVG){
        this.person = person;
        this.contributionRates = contributionRates;
        this.contributionRatesBVG = contributionRatesBVG;
    }

    public ContractPerson getPerson() {
        return person;
    }



}
