package pl.pge.di.bazaBenchamrk.controller;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
        import pl.pge.di.bazaBenchamrk.service.UserService;


public class SignUpController {

    public static final String DEPARTAMENT_INWESTYCJI_PGE_S_A = "Departament Inwestycji PGE S.A.";
    @FXML
    private TextField tfNewLogin;

    @FXML
    private TextField tfNewPassword;

    @FXML
    private Button btnAddToDB;

    @FXML
    private Button btnGoBack;

    @FXML
    void AddToDBEvent(MouseEvent event) {

        String login = tfNewLogin.getText();
        String pass =tfNewPassword.getText();

        int id = UserService.addToDB(login,pass);
        System.out.println("Dodano użytkownika o id: "+ id);



    }

    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle(DEPARTAMENT_INWESTYCJI_PGE_S_A);
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

}