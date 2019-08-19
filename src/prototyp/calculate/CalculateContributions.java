package calculate;

/**
 * Class for calculate Contribution and Coordination Salary
 *
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class CalculateContributions {
    private Double calculatetDetuction;


public Double calculateCoordinationDeduction(Double coordinationDeductionRate, Double coordinationDeductionBVG, Integer salary){
        Double coordinationDeductioncalc = (coordinationDeductionRate/100) * Double.valueOf(salary);
        if (coordinationDeductioncalc > coordinationDeductionBVG){
            return coordinationDeductionBVG;
        }else {
            return coordinationDeductioncalc;
        }

    }

public Double calculateCoordinatedSalary(Double coordinationDeduction, Integer salary){
        Double coordinatedSalary = salary - coordinationDeduction;
        return coordinatedSalary;
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
   Double contribution = (coordinatedSalary * (ContributionRate / 100)) / 12;
    return contribution;
}

}