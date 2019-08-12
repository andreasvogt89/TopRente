package calculate;

import contractPerson.ContractPerson;
import personView.PersonViewModel;

public class CalculatePension {



    public Integer CalculatePension(PersonViewModel personViewModel, CalculateContributions calculateContributions){
        Integer credit = personViewModel.getPerson().getCredit();
        Integer calculatedCredit = credit;
        return calculatedCredit;

}

}
