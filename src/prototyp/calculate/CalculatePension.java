package calculate;


public class CalculatePension {

    public Double addUpContribution(Double contributionAN, Double contributionAG){
        return contributionAN + contributionAG;
    }

    public Double calculateInterest(Double currentCredit, Double interestRate) {
        return currentCredit * interestRate;
    }

    public Double addUpCredit (Double contribution, Double currentCredit){
        return currentCredit + contribution;
    }
}





