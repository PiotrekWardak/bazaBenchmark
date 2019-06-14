package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class userSurveyController {

    @FXML
    private MenuItem mSaveToFile;

    @FXML
    private MenuItem mSaveToDatabase;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mClear;

    @FXML
    private MenuItem mAbout;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhone;

    @FXML
    private CheckBox cbJava;

    @FXML
    private CheckBox cbPython;

    @FXML
    private CheckBox cbOther;

    @FXML
    private TextField tfOther;

    @FXML
    private RadioButton rbBasic;

    @FXML
    private ToggleGroup g1;

    @FXML
    private RadioButton rbIntermediate;

    @FXML
    private RadioButton rbAdvanced;

    @FXML
    private ComboBox<String> cmbCurses;

    @FXML
    private TextArea taPreview;

    @FXML
    private Button btnGoBack;

    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void ClearEvent(ActionEvent event) {
        tfName.clear();
        tfLastName.clear();
        tfEmail.clear();
        tfPhone.clear();
        cbJava.setSelected(false);
        cbPython.setSelected(false);
        cbOther.setSelected(false);
        tfOther.clear();
        tfOther.setDisable(true);
        rbIntermediate.setSelected(true);
        cmbCurses.setValue(null);
        taPreview.clear();
    }

    @FXML
    void OtherEvent(MouseEvent event) {

        if(cbOther.isSelected()){
            tfOther.setDisable(false);
            tfOther.clear();
        }
        else{
            tfOther.setDisable(true);
            tfOther.clear();
        }
    }

    @FXML
    void PreviewEvent(MouseEvent event) {



        if(isNotCompleted()){
            showAlertNotCompleted();
            return;
        }
        else
        {
            String value = getSurveyText();

            taPreview.setText(value);
        }



    }

    private String getSurveyText() {
        String name = tfName.getText();
        String lastName = tfLastName.getText();
        String phone = tfPhone.getText();
        String mail = tfEmail.getText();
        String java = cbJava.isSelected()?cbJava.getText():"";
        String python = cbPython.isSelected()?cbPython.getText():"";
        String other = cbOther.isSelected()?cbOther.getText():"";
        String language = "";

        if(rbBasic.isSelected()){
            language = rbBasic.getText();
        }
        if(rbAdvanced.isSelected()){
            language = rbAdvanced.getText();
        }
        if(rbIntermediate.isSelected()){
            language = rbIntermediate.getText();
        }

        String course = cmbCurses.getValue();
        return name + "\n" + lastName + "\n" + phone + "\n" + mail
                + "\n" + java + " " + python + " " + other + "\n" + language + "\n" + course;
    }

    private void showAlertNotCompleted() {
        Alert info =  new Alert(Alert.AlertType.WARNING);
        info.setContentText("PLease fill out all fields ...");
        info.setTitle("Warning");
        info.setHeaderText("Warning");
        info.show();
    }

    private boolean isNotCompleted() {
        return "".equals(tfName.getText()) || "".equals(tfLastName.getText()) || "".equals(tfEmail.getText()) || "".equals(tfPhone.getText()) || !(cbJava.isSelected() ||cbPython.isSelected()
        ||cbOther.isSelected())|| cmbCurses.getValue()==null;
    }

    @FXML
    void SaveToDataBaseEvent(ActionEvent event) {

        String name = tfName.getText();
        String lastName = tfLastName.getText();
        String phone = tfPhone.getText();
        String mail = tfEmail.getText();
        Boolean java = cbJava.isSelected();
        Boolean python = cbPython.isSelected();
        Boolean other = cbOther.isSelected();
        String otherDesc = cbOther.isSelected()?tfOther.getText(): "";
        String language = "";

        if(rbBasic.isSelected()){
            language = rbBasic.getText();
        }
        if(rbAdvanced.isSelected()){
            language = rbAdvanced.getText();
        }
        if(rbIntermediate.isSelected()){
            language = rbIntermediate.getText();
        }

        String course = cmbCurses.getValue();

        Survey survey = new Survey(name,lastName,mail,phone,java,python,other,otherDesc,language,course, CurrentUser.getCurrentUser().getStudent().getId());

        SurveyService surveyService = new SurveyService();
        surveyService.save(survey);
    }

    @FXML
    void SaveToFileEvent(ActionEvent event) {

        if(isNotCompleted()){
            showAlertNotCompleted();
        }
        else
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save resource file");
            File file = fileChooser.showSaveDialog(null);
            String filePath = file.getPath();
            try(PrintWriter save = new PrintWriter(filePath)){

                save.println(getSurveyText());
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void aboutAction(ActionEvent event) {

    }

    @FXML
    void closeEvent(ActionEvent event) {

        System.exit(0);
    }

    public void initialize(){

        ObservableList<String> courses = FXCollections.observableArrayList("Back-end","Front-end","Python","Tester");
        cmbCurses.setItems(courses);

    }








}





