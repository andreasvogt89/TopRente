package personView;


import ContributionRates.ContributionRates;
import calculate.CalculateContributions;
import contractPerson.ContractPerson;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class PersonViewModel {


    private ContractPerson person;
    private List contributionRates;
    private LinkedList contributionRatesBVG;
    private CalculateContributions calculateContributions = new CalculateContributions();


    public PersonViewModel(ContractPerson person, LinkedList contributionRatesBVG, List contributionRates){
        this.person = person;
        this.contributionRates = contributionRates;
        this.contributionRatesBVG = contributionRatesBVG;

    }

    public ContractPerson getPerson() {
        return person;
    }

    public String setCoordinatedSalaryBVG (){
       return String.valueOf(calculateContributions.calculateCoordinatedSalaryBVG(24844,person.getSalary()));

    }

    /*public String setSavingContributionAN (){

        return  String.valueOf(calculateContributions.calculatesavingContributionAN(,person.getSalary()));
    }*/
}
