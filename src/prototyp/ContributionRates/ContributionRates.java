package ContributionRates;

public class ContributionRates {
    private Double SavingContributionAN;
    private Double SavingContributionAG;
    private Double RiskContributionAN;
    private Double RiskContributionAG;
    private Double conversionRateMin;
    private Double conversionRateMan;
    private Double conversionRateWoman;
    private String AgeRange;

    public ContributionRates(Double savingContributionAN, Double savingContributionAG, Double riskContributionAN, Double riskContributionAG, Double conversionRateMin, String ageRange) {
        SavingContributionAN = savingContributionAN;
        SavingContributionAG = savingContributionAG;
        RiskContributionAN = riskContributionAN;
        RiskContributionAG = riskContributionAG;
        this.conversionRateMin = conversionRateMin;
        AgeRange = ageRange;
    }

    public ContributionRates(Double savingContributionAN, Double savingContributionAG, Double riskContributionAN, Double riskContributionAG, Double conversionRateMan, Double conversionRateWoman, String ageRange) {
        SavingContributionAN = savingContributionAN;
        SavingContributionAG = savingContributionAG;
        RiskContributionAN = riskContributionAN;
        RiskContributionAG = riskContributionAG;
        this.conversionRateMan = conversionRateMan;
        this.conversionRateWoman = conversionRateWoman;
        AgeRange = ageRange;
    }

    public Double getSavingContributionAN() {
        return SavingContributionAN;
    }

    public Double getSavingContributionAG() {
        return SavingContributionAG;
    }

    public Double getRiskContributionAN() {
        return RiskContributionAN;
    }

    public Double getRiskContributionAG() {
        return RiskContributionAG;
    }

    public Double getConversionRateMin() {
        return conversionRateMin;
    }

    public Double getConversionRateMan() {
        return conversionRateMan;
    }

    public Double getConversionRateWoman() {
        return conversionRateWoman;
    }

    public String getAgeRange() {
        return AgeRange;
    }

    public void setSavingContributionAN(Double savingContributionAN) {
        SavingContributionAN = savingContributionAN;
    }

    public void setSavingContributionAG(Double savingContributionAG) {
        SavingContributionAG = savingContributionAG;
    }

    public void setRiskContributionAN(Double riskContributionAN) {
        RiskContributionAN = riskContributionAN;
    }

    public void setRiskContributionAG(Double riskContributionAG) {
        RiskContributionAG = riskContributionAG;
    }

    public void setConversionRateMin(Double conversionRateMin) {
        this.conversionRateMin = conversionRateMin;
    }

    public void setConversionRateMan(Double conversionRateMan) {
        this.conversionRateMan = conversionRateMan;
    }

    public void setConversionRateWoman(Double conversionRateWoman) {
        this.conversionRateWoman = conversionRateWoman;
    }

    public void setAgeRange(String ageRange) {
        AgeRange = ageRange;
    }
}
