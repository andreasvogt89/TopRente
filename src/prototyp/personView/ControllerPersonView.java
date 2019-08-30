package personView;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ControllerPersonView implements Initializable {

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
    private  Label CalculatedInvalidCreditBVG;
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

    private ModelPersonView modelPersonView;
    private String chf = " CHF";
    private String procent = " %";


    public void loadView (ControllerPersonView controllerPersonView, ModelPersonView personViewModel) {
        this.modelPersonView = personViewModel;

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/personView/personView.fxml"));
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
        fullNameView.setText(modelPersonView.getPerson().getName() + " " + modelPersonView.getPerson().getLastname() + " " + "Versicherungsnr. " + modelPersonView.getPerson().getInsurance());
        pensionDate.setText(modelPersonView.getPensionDate());
        levelBVG.setText(modelPersonView.getPerson().getLevel() + procent);
        level.setText(modelPersonView.getPerson().getLevel() + procent);
        salaryBVG.setText(modelPersonView.getPerson().getSalary() + chf);
        salary.setText(modelPersonView.getPerson().getSalary() + chf);
        coordinatedSalaryContributionBVG.setText(modelPersonView.getCoordinatedSalaryContributionBVG() + chf);
        coordinatedSalaryContribution.setText(modelPersonView.getCoordinatedSalaryContribution() + chf);
        coordinatedSalaryBVG.setText(modelPersonView.getCoordinatedSalaryBVG() + chf);
        coordinatedSalary.setText(modelPersonView.getCoordinatedSalary() + chf);
        ageBVG.setText(String.valueOf(modelPersonView.getPerson().getAge()));
        age.setText(String.valueOf(modelPersonView.getPerson().getAge()));
        savingContributionANBVG.setText(modelPersonView.getSavingContributionANBVG() + chf);
        savingContributionAN.setText(modelPersonView.getSavingContributionAN() + chf);
        savingContributionAGBVG.setText(modelPersonView.getSavingContributionAGBVG() + chf);
        savingContributionAG.setText(modelPersonView.getSavingContributioAG() + chf);
        riskContributionANBVG.setText(modelPersonView.getRiskContributionANBVG() + chf);
        riskContributionAN.setText(modelPersonView.getRiskContributionAN() + chf);
        riskContributionAGBVG.setText(modelPersonView.getRiskContributionAGBVG() + chf);
        riskContributionAG.setText(modelPersonView.getRiskContributioAG() + chf);
        interestRate.setText(modelPersonView.getContributionRates().getInterestRate() + procent);
        interestRateBVG.setText(modelPersonView.getContributionRates().getInterestRate() + procent);
        credit.setText(modelPersonView.getPerson().getCredit() + chf);
        creditBVG.setText(modelPersonView.getPerson().getCredit() + chf);
        calculatedCreditBVG.setText(modelPersonView.getCalculatedCreditBVG() + chf);
        calculatedCredit.setText(modelPersonView.getCalculatedCredit() + chf);
        pension.setText(modelPersonView.getPension() + chf);
        pensionBVG.setText(modelPersonView.getPensionBVG() + chf);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            loadPensionCertificatePerson();

    }

}
