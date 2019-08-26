package calculate;

/**
 * Class for calculate Contribution and Coordination Salary
 * is Part of the model to the mcv construct interface person view
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class CalculateContributions {


public Double calculateCoordinationDeduction(Double coordinationDeductionRate, Double coordinationDeductionBVG, Integer salary){
        Double coordinationDeductioncalc = (coordinationDeductionRate/100) * Double.valueOf(salary);
        if (coordinationDeductioncalc >= coordinationDeductionBVG){
            return coordinationDeductionBVG;
        }else {
            return coordinationDeductioncalc;
        }

    }

public Double calculateCoordinatedSalary(Double coordinationDeduction, Integer salary){
    return salary - coordinationDeduction;
    }

public Double calculateCoordinatedSalaryBVG(Double coordinationDeduction, Integer salary, Double minCoordinatedSalary, Double maxCoordinatedSalary){
        Double coordinatedSalary = salary - coordinationDeduction;
        if (coordinatedSalary <= minCoordinatedSalary){
            return minCoordinatedSalary;
        }else if (coordinatedSalary >= maxCoordinatedSalary) {
            return maxCoordinatedSalary;
        } else return coordinatedSalary;

    }

public Double calculateContribution(Double ContributionRate, Double coordinatedSalary){
    return round((coordinatedSalary * (ContributionRate / 100))/12,2);

}

public static Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}