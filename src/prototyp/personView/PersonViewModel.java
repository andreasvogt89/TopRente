package personView;


import contractPerson.ContractPerson;

public class PersonViewModel {
    private ContractPerson person;


    public PersonViewModel(ContractPerson person){
        this.person = person;
    }

    public ContractPerson getPerson() {
        return person;
    }

}
