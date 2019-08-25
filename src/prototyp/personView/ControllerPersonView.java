package personView;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
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

    ModelPersonView personViewModel;

    public void loadView (ControllerPersonView controllerPersonView, ModelPersonView personViewModel){
        this.personViewModel = personViewModel;

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

    private void loadPensionCertificatePerson(){
        fullNameView.setText(personViewModel.getPerson().getName() + " " + personViewModel.getPerson().getLastname() + " " + "Versicherungsnr. " + personViewModel.getPerson().getInsurance());
        levelBVG.setText(personViewModel.getPerson().getLevel());
        level.setText(personViewModel.getPerson().getLevel());
        salaryBVG.setText(String.valueOf(personViewModel.getPerson().getSalary()));
        salary.setText(String.valueOf(personViewModel.getPerson().getSalary()));
        coordinatedSalaryContributionBVG.setText(String.valueOf(personViewModel.getCoordinatedSalaryContributionBVG()));
        coordinatedSalaryContribution.setText(String.valueOf(personViewModel.getCoordinatedSalaryContribution()));
        coordinatedSalaryBVG.setText(personViewModel.getCoordinatedSalaryBVG());
        coordinatedSalary.setText(personViewModel.getCoordinatedSalary());
        ageBVG.setText(String.valueOf(personViewModel.getPerson().getAge()));
        age.setText(String.valueOf(personViewModel.getPerson().getAge()));
        savingContributionANBVG.setText(personViewModel.getSavingContributionANBVG());
        savingContributionAN.setText(personViewModel.getSavingContributionAN());
        savingContributionAGBVG.setText(personViewModel.getSavingContributionAGBVG());
        savingContributionAG.setText(personViewModel.getSavingContributioAG());
        riskContributionANBVG.setText(personViewModel.getRiskContributionANBVG());
        riskContributionAN.setText(personViewModel.getRiskContributionAN());
        riskContributionAGBVG.setText(personViewModel.getRiskContributionAGBVG());
        riskContributionAG.setText(personViewModel.getRiskContributioAG());
        interestRate.setText(personViewModel.getInterestRate());
        interestRateBVG.setText(personViewModel.getInterestRate());
        credit.setText(String.valueOf(personViewModel.getPerson().getCredit()));
        creditBVG.setText(String.valueOf(personViewModel.getPerson().getCredit()));
        calculatedCreditBVG.setText(personViewModel.getCalculatedCreditBVG());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPensionCertificatePerson();
    }

}
