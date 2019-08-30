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
        return (currentCredit * (interestRate/100)) +currentCredit;
    }

    public Double addUpCredit (Double contribution, Double currentCredit){
        return currentCredit + contribution;
    }

    public Double calculatePension(Double calculatedCredit, Double conversionRate){

        return (calculatedCredit/100) * conversionRate;
    }

    public Double calculateConversionAge(String sex, Integer pensionAge, Double conversion60, Double conversion61, Double conversion62, Double conversion63M, Double conversion63W, Double conversion64M, Double conversion64W,Double conversion65){
        if (pensionAge.equals(60)) {
            return conversion60;
        }else if(pensionAge.equals(61)) {
            return conversion61;
        }else if (pensionAge.equals(62)) {
            return conversion62;
        }else if (pensionAge.equals(63) && sex.matches("Männlich")) {
            return conversion63M;
        }else if (pensionAge.equals(63) && sex.matches("Weiblich")) {
            return conversion63W;
        }else if (pensionAge.equals(64) && sex.matches("Männlich")) {
            return conversion63M;
        }else if (pensionAge.equals(64) && sex.matches("Weiblich")) {
            return conversion64W;
        }else return conversion65;

}
}





