package calculate;
/**
 * Class for calculate all Pension
 * is Part of the model to the mcv construct interface person view
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class CalculatePension {

    public Double addUpContribution(Double saveingAN, Double savingAG){
        return saveingAN + savingAG;
    }

    public Double calculateInterest(Double currentCredit, Double interestRate) {
        return currentCredit * interestRate;
    }

    public Double addUpCredit (Double contribution, Double currentCredit){
        return currentCredit + contribution;
    }
}





