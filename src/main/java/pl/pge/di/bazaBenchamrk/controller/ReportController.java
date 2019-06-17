package pl.pge.di.bazaBenchamrk.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
import pl.pge.di.bazaBenchamrk.model.Report_1;
import pl.pge.di.bazaBenchamrk.model.Survey;
import pl.pge.di.bazaBenchamrk.service.ReportService;

import java.util.List;

public class ReportController {

    public static final String InformacjaDodawanieRaportuGdyBlad = "Musisz podać co najmniej Tytuł Raportu oraz rok publikacji";
    @FXML
    private Button btnGoBack;

    @FXML
    private TableView<Report_1> tbvSurvey;

    @FXML
    private TableColumn<Survey, Long> tcId;

    @FXML
    private TableColumn<Survey, String> tcTytulRaportu;

    @FXML
    private TableColumn<Survey, String> tcAutorRaportu;

    @FXML
    private TableColumn<Survey, String> tcDataRaportu;

    private ReportService reportService = new ReportService();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDodajRaport;

    @FXML
    private TextField tfTytulRaportu;

    @FXML
    private TextField tfAutorRaportu;

    @FXML
    private TextField tfDataRaportu;

    @FXML
    void DeleteEvent(MouseEvent event) {

//        if(tbvSurvey.getSelectionModel()!=null && tbvSurvey.getSelectionModel().getSelectedItem()!=null){
//
//            Survey selectedItem = tbvSurvey.getSelectionModel().getSelectedItem();
//            surveyService.delete(selectedItem);
//            initSurveyToTable();
//        }
    }

    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {
        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    public void initialize(){


        initSurveyToTable();
// tutaj dodajemy pola z entity
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTytulRaportu.setCellValueFactory(new PropertyValueFactory<>("tytulRaportu"));
        tcAutorRaportu.setCellValueFactory(new PropertyValueFactory<>("autorRaportu"));
        tcDataRaportu.setCellValueFactory(new PropertyValueFactory<>("dataRaportu"));


    }

    private void initSurveyToTable() {
        List<Report_1> reports = reportService.getAllReports();

        tbvSurvey.setItems(null);
        ObservableList<Report_1> reportList = FXCollections.observableArrayList(reports);
        tbvSurvey.setItems(reportList);

    }

    @FXML
    void DodajRaportEvent(MouseEvent event) {

        if(isNotCompleted()){
            showAlertNotCompleted();
        }
        else {


            String tytulRaportu = tfTytulRaportu.getText();
            String autorRaportu = tfAutorRaportu.getText();
            String dataRaportu = tfDataRaportu.getText();

            Report_1 report = new Report_1(tytulRaportu,autorRaportu,dataRaportu);

        ReportService reportService = new ReportService();
        reportService.save(report);
        initialize();
    }

    }

    private boolean isNotCompleted() {
        return "".equals(tfTytulRaportu.getText()) || "".equals(tfDataRaportu.getText());
    }

    private void showAlertNotCompleted() {
        Alert info =  new Alert(Alert.AlertType.WARNING);
        info.setContentText(InformacjaDodawanieRaportuGdyBlad);
        info.setTitle("Błąd");
        info.setHeaderText("Błąd");
        info.show();
    }





}

