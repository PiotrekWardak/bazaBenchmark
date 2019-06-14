package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.util.List;

public class surveyTableController {

    @FXML
    private Button btnGoBack;

    @FXML
    private TableView<Survey> tbvSurvey;

    @FXML
    private TableColumn<Survey, Long> tcId;

    @FXML
    private TableColumn<Survey, String> tcFirstName;

    @FXML
    private TableColumn<Survey, String> tcLastName;

    @FXML
    private TableColumn<Survey, String> tcMail;

    @FXML
    private TableColumn<Survey, String> tcPhone;

    @FXML
    private TableColumn<Survey, Boolean> tcJava;

    @FXML
    private TableColumn<Survey, Boolean> tcPython;

    @FXML
    private TableColumn<Survey, Boolean> tcOther;

    @FXML
    private TableColumn<Survey, String> tcDesc;

    @FXML
    private TableColumn<Survey, String> tcLanguage;

    @FXML
    private TableColumn<Survey, String> tcCourse;

    private SurveyService surveyService = new SurveyService();

    @FXML
    private Button btnDelete;

    @FXML
    void DeleteEvent(MouseEvent event) {

        if(tbvSurvey.getSelectionModel()!=null && tbvSurvey.getSelectionModel().getSelectedItem()!=null){

            Survey selectedItem = tbvSurvey.getSelectionModel().getSelectedItem();
            surveyService.delete(selectedItem);
            initSurveyToTable();
        }
    }

    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    public void initialize(){


        initSurveyToTable();

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcJava.setCellValueFactory(new PropertyValueFactory<>("java"));
        tcPython.setCellValueFactory(new PropertyValueFactory<>("python"));
        tcOther.setCellValueFactory(new PropertyValueFactory<>("other"));
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("otherDesc"));
        tcLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

    }

    private void initSurveyToTable() {
        List<Survey> surveys = surveyService.getSurveyByStudent(CurrentUser.getCurrentUser().getStudent().getId());

        tbvSurvey.setItems(null);
        ObservableList<Survey> observableSurvey = FXCollections.observableArrayList(surveys);
        tbvSurvey.setItems(observableSurvey);
    }

}
