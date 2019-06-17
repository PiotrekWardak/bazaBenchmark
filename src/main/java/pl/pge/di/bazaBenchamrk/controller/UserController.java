package pl.pge.di.bazaBenchamrk.controller;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
        import pl.pge.di.bazaBenchamrk.model.utils.CurrentUser;


public class UserController {


    @FXML
    private Label lbLogin;

    @FXML
    private Button btnUpdateData;

    @FXML
    private Button btnSurvey;

    @FXML
    private Button btnSurveyTable;

    @FXML
    private Button btnDodajRaport;


    @FXML
    void SurveyTableEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportView.fxml"));
        primaryStage.setTitle("List of surveys");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void SurveyEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/surveyView.fxml"));
        primaryStage.setTitle("Survey");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void UpdateDataEvent(MouseEvent event) throws Exception {
        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateDataView.fxml"));
        primaryStage.setTitle("Update");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }


    public void initialize() {

        lbLogin.setText(lbLogin.getText()+ CurrentUser.getCurrentUser().getLogin());
    }

    @FXML
    void DodajRaportEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportView.fxml"));
        primaryStage.setTitle("Raport");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }


}



