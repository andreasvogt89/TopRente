package pensionCertificate;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label fullNameView;
    @FXML
    private Label levelBVG;
    @FXML
    private  Label salaryBVG;
    @FXML
    private  Label coordinatedSalaryContributionBVG;
    @FXML
    private  Label coordinatedSalaryBVG;
    @FXML
    private Label ageBVG;
    @FXML
    private  Label savingContributionANBVG;
    @FXML
    private  Label savingContributionAGBVG;
    @FXML
    private  Label riskContributionANBVG;
    @FXML
    private  Label riskContributionAGBVG;
    @FXML
    private  Label interestRateBVG;
    @FXML
    private  Label creditBVG;
    @FXML
    private  Label calculatedCreditBVG;
    @FXML
    private  Label calculatedInvalidCreditBVG;
    @FXML
    private  Label pensionBVG;
    @FXML
    private  Label pensionInvalidBVG;
    @FXML
    private  Label pensionPartnerBVG;
    @FXML
    private  Label pensionChildrenBVG;
    @FXML
    private Label level;
    @FXML
    private  Label salary;
    @FXML
    private  Label coordinatedSalaryContribution;
    @FXML
    private  Label coordinatedSalary;
    @FXML
    private Label age;
    @FXML
    private  Label savingContributionAN;
    @FXML
    private  Label savingContributionAG;
    @FXML
    private  Label riskContributionAN;
    @FXML
    private  Label riskContributionAG;
    @FXML
    private  Label interestRate;
    @FXML
    private  Label credit;
    @FXML
    private  Label calculatedCredit;
    @FXML
    private  Label calculatedInvalidCredit;
    @FXML
    private  Label pension;
    @FXML
    private  Label pensionInvalid;
    @FXML
    private  Label pensionPartner;
    @FXML
    private  Label pensionChildren;
    @FXML
    private Label pensionDate;


    private Model model;
    private String chf = " CHF";
    private String percent = " %";


    public void loadView (Controller controllerPersonView, Model personViewModel) {
        this.model = personViewModel;

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pensionCertificate/pensionCertificate.fxml"));
            fxmlLoader.setController(controllerPersonView);
            Pane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(personViewModel.getPerson().getName() + " " + personViewModel.getPerson().getLastname());
            stage.show();
        } catch (Exception e) {

        }



    }

    private void loadPensionCertificatePerson() {
        fullNameView.setText(model.getPerson().getName() + " " + model.getPerson().getLastname() + " " + "Versicherungsnr. " + model.getPerson().getInsurance());
        pensionDate.setText(model.getPensionDate());
        levelBVG.setText(model.getPerson().getLevel() + percent);
        level.setText(model.getPerson().getLevel() + percent);
        salaryBVG.setText(model.getPerson().getSalary() + chf);
        salary.setText(model.getPerson().getSalary() + chf);
        coordinatedSalaryContributionBVG.setText(model.getCoordinatedSalaryContributionBVG() + chf);
        coordinatedSalaryContribution.setText(model.getCoordinatedSalaryContribution() + chf);
        coordinatedSalaryBVG.setText(model.getCoordinatedSalaryBVG() + chf);
        coordinatedSalary.setText(model.getCoordinatedSalary() + chf);
        ageBVG.setText(String.valueOf(model.getPerson().getAge()));
        age.setText(String.valueOf(model.getPerson().getAge()));
        savingContributionANBVG.setText(model.getSavingContributionANBVG() + chf);
        savingContributionAN.setText(model.getSavingContributionAN() + chf);
        savingContributionAGBVG.setText(model.getSavingContributionAGBVG() + chf);
        savingContributionAG.setText(model.getSavingContributioAG() + chf);
        riskContributionANBVG.setText(model.getRiskContributionANBVG() + chf);
        riskContributionAN.setText(model.getRiskContributionAN() + chf);
        riskContributionAGBVG.setText(model.getRiskContributionAGBVG() + chf);
        riskContributionAG.setText(model.getRiskContributioAG() + chf);
        interestRate.setText(model.getContributionRates().getInterestRate() + percent);
        interestRateBVG.setText(model.getContributionRates().getInterestRate() + percent);
        credit.setText(model.getPerson().getCredit() + chf);
        creditBVG.setText(model.getPerson().getCredit() + chf);
        calculatedCreditBVG.setText(model.getCalculatedCreditBVG() + chf);
        calculatedCredit.setText(model.getCalculatedCredit() + chf);
        pension.setText(model.getPension() + chf);
        pensionBVG.setText(model.getPensionBVG() + chf);
        calculatedInvalidCredit.setText(model.getCalculatedInvalidCredit() + chf);
        calculatedInvalidCreditBVG.setText(model.getCalculatedInvalidCreditBVG() + chf);
        pensionInvalid.setText(model.getInvalidPension()+ chf);
        pensionInvalidBVG.setText(model.getInvalidPensionBVG()+ chf);
        pensionPartner.setText(model.getPartnerPension() + chf);
        pensionPartnerBVG.setText(model.getPartnerPensionBVG() + chf);
        pensionChildren.setText(model.getChildrenPension() + chf);
        pensionChildrenBVG.setText(model.getChildrenPensionBVG() + chf);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            loadPensionCertificatePerson();

    }

}
