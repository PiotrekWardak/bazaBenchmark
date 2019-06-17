package pl.pge.di.bazaBenchamrk.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
import pl.pge.di.bazaBenchamrk.model.Survey;
import pl.pge.di.bazaBenchamrk.model.User;
import pl.pge.di.bazaBenchamrk.model.dto.StudentDTO;
import pl.pge.di.bazaBenchamrk.model.utils.CurrentUser;
import pl.pge.di.bazaBenchamrk.service.SurveyService;
import pl.pge.di.bazaBenchamrk.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import static pl.pge.di.bazaBenchamrk.controller.SignUpController.DEPARTAMENT_INWESTYCJI_PGE_S_A;

public class TabelaZbiorczaController {

    @FXML
    private TableView<Survey> tvSurvey;

    @FXML
    private TableColumn<Survey, String> colidS;

    @FXML
    private TableColumn<Survey, String> colOvernightS;

    @FXML
    private TableColumn<Survey, String> colCapexNaRokS;

    @FXML
    private TableColumn<Survey, String> colCapexRaportNaMWS;

    @FXML
    private TableColumn<Survey, String> colRokRaportuS;

    @FXML
    private TableColumn<Survey, String> colWalutaS;

    @FXML
    private TableColumn<Survey, String> colCapexWybranyRokkEurNaMWS;

    @FXML
    private TableColumn<Survey, String> colFixedOmNaMWS;

    @FXML
    private TableColumn<Survey, String> colFixedOmWybranyRokKeurNaMWyrS;

    @FXML
    private TableColumn<Survey, String> colVariableOmInMWhS;

    @FXML
    private TableColumn<Survey, String> colVariableOmWybranyRokEurNaMWhS;

    @FXML
    private TableColumn<Survey, String> colLcoeS;

    @FXML
    private TableColumn<Survey, String> colLcoeWybranyRokEurNaMWhS;

    @FXML
    private TableColumn<Survey, String> colPlanowanieBudowyLataS;

    @FXML
    private TableColumn<Survey, String> colBudowaLataS;

    @FXML
    private TableColumn<Survey, String> colOkresEksploatacjiLataS;

    @FXML
    private TableColumn<Survey, String> colRaportIDS;

    @FXML
    private TableColumn<Survey, String> colRealEscalatorsPercentS;

    @FXML
    private TableColumn<Survey, String> colCzasPracyPercentS;

    @FXML
    private TableColumn<Survey, String> colStopaDyskontaPercentS;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mAbout;

    @FXML
    private Button btnGoBack;

    SurveyService surveyService = new SurveyService();

    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }


    @FXML
    void aboutAction(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About");
        info.setHeaderText("Instruction");
        info.setContentText("Instruction for using the form ...");
        info.show();
    }

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logoutAction(ActionEvent event) throws Exception {
        CurrentUser.clean();
        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle(DEPARTAMENT_INWESTYCJI_PGE_S_A);
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

    public void initialize(){

        initDataTable();


    }

    private void initDataTable() {

        List<Survey> surveys = surveyService.get20lastSurveys();

        ObservableList<Survey> surveyDataRows = FXCollections.observableArrayList(surveys);

        tvSurvey.setItems(null);
        tvSurvey.setItems(surveyDataRows);

        //ustawienie kolumn które pola z User mają być widoczne i w jakiej kolumnie z widoku
        colidS.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOvernightS.setCellValueFactory(new PropertyValueFactory<>("overnight"));
        colCapexNaRokS.setCellValueFactory(new PropertyValueFactory<>("capexNaRok"));
        colCapexRaportNaMWS.setCellValueFactory(new PropertyValueFactory<>("capexRaportNaMW"));
        colRokRaportuS.setCellValueFactory(new PropertyValueFactory<>("rokRaportu"));
        colWalutaS.setCellValueFactory(new PropertyValueFactory<>("waluta"));
        colCapexWybranyRokkEurNaMWS.setCellValueFactory(new PropertyValueFactory<>("capexWybranyRokkEurNaMW"));
        colFixedOmNaMWS.setCellValueFactory(new PropertyValueFactory<>("fixedOmNaMW"));
        colFixedOmWybranyRokKeurNaMWyrS.setCellValueFactory(new PropertyValueFactory<>("fixedOmWybranyRokKeurNaMWyr"));
        colVariableOmInMWhS.setCellValueFactory(new PropertyValueFactory<>("variableOmInMWh"));
        colVariableOmWybranyRokEurNaMWhS.setCellValueFactory(new PropertyValueFactory<>("variableOmWybranyRokEurNaMWh"));
        colLcoeS.setCellValueFactory(new PropertyValueFactory<>("lcoe"));
        colLcoeWybranyRokEurNaMWhS.setCellValueFactory(new PropertyValueFactory<>("lcoeWybranyRokEurNaMWh"));
        colPlanowanieBudowyLataS.setCellValueFactory(new PropertyValueFactory<>("planowanieBudowyLata"));
        colBudowaLataS.setCellValueFactory(new PropertyValueFactory<>("budowaLata"));
        colOkresEksploatacjiLataS.setCellValueFactory(new PropertyValueFactory<>("okresEksploatacjiLata"));
        colRaportIDS.setCellValueFactory(new PropertyValueFactory<>("raportID"));
        colRealEscalatorsPercentS.setCellValueFactory(new PropertyValueFactory<>("realEscalatorsPercent"));
        colCzasPracyPercentS.setCellValueFactory(new PropertyValueFactory<>("czasPracyPercent"));
        colStopaDyskontaPercentS.setCellValueFactory(new PropertyValueFactory<>("stopaDyskontaPercent"));
    }

}
