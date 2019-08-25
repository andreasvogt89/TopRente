package calculate;


public class CalculatePension {

    public Double addUpContribution(Double saveingAN, Double savingAG, Double riskAN, Double riskAG){
        return saveingAN + savingAG +riskAN + riskAG;
    }

    public Double calculateInterest(Double currentCredit, Double interestRate) {
        return currentCredit * interestRate;
    }

    public Double addUpCredit (Double contribution, Double currentCredit){
        return currentCredit + contribution;
    }
}





