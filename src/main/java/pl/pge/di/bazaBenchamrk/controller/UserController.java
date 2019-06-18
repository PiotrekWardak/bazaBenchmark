package pl.pge.di.bazaBenchamrk.controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
        import pl.pge.di.bazaBenchamrk.model.utils.CurrentUser;

        import java.io.IOException;


public class UserController {

    public static final String DEPARTAMENT_INWESTYCJI_PGE_S_A = "Departament Inwestycji PGE S.A.";

    @FXML
    private Label lbLogin;


    @FXML
    private Button btnSurvey;

    @FXML
    private Button btnTabelaZbiorcza;

    @FXML
    private Button btnDodajRaport;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mAbout;

    @FXML
    void aboutAction(ActionEvent event) {
        pomoc();
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        Wyloguj();
    }


    @FXML
    void TabelaZbiorczaEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/tabelaZbiorczaView.fxml"));
        primaryStage.setTitle("List of Data");
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



    public void initialize() {

        lbLogin.setText(lbLogin.getText() + " " + CurrentUser.getCurrentUser().getLogin() + " posiadasz rolę " + CurrentUser.getCurrentUser().getRole());
    }

    @FXML
    void DodajRaportEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportView.fxml"));
        primaryStage.setTitle("Raport");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

    public static void pomoc() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Pomoc");
        info.setHeaderText("Informacje");
        info.setContentText("Wszystkie pytania dotyczące pliku proszę kierować na adres piotrekwardak@gmail.com");
        info.show();
    }

    private void Wyloguj() throws java.io.IOException {
        CurrentUser.clean();
        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle(DEPARTAMENT_INWESTYCJI_PGE_S_A);
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }


}



