package pl.pge.di.bazaBenchamrk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
import pl.pge.di.bazaBenchamrk.model.RoleEnum;
import pl.pge.di.bazaBenchamrk.model.User;
import pl.pge.di.bazaBenchamrk.model.utils.CurrentUser;
import pl.pge.di.bazaBenchamrk.service.LoginService;

import java.io.IOException;

public class GlownyController {

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnLogin;

    @FXML
    void ShowEvent(MouseEvent event) {


        if("Pokaż hasło".equalsIgnoreCase(btnShow.getText())){
            tfPassword.setText(pfPassword.getText());
            tfPassword.setVisible(true);
            pfPassword.setVisible(false);
            btnShow.setText("Ukryj hasło");
        }
        else if ("Ukryj hasło".equalsIgnoreCase(btnShow.getText())){
            pfPassword.setText(tfPassword.getText());
            pfPassword.setVisible(true);
            tfPassword.setVisible(false);
            btnShow.setText("Pokaż hasło");
        }
    }

    @FXML
    void SignUpEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/signUpView.fxml"));
        primaryStage.setTitle("Formularz Rejestracji Użytkownika");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

    @FXML
    void guestEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/goscView.fxml"));
        primaryStage.setTitle("Widok Niezalogowanego Użytkownika");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void loginEvent(InputEvent event) throws IOException {


        String login = tfLogin.getText();
        String pass = pfPassword.isVisible()?pfPassword.getText(): tfPassword.getText();

        LoginService loginService = new LoginService();
        User user = loginService.login(login,pass);
        if(user!=null){

//            RoleEnum role = user.getRole();
            RoleEnum role = user.getRole();
            System.out.println("Zalogowano użytkownika: "+ login + " o roli: " + role);

            CurrentUser.setCurrentUser(user);

            if(RoleEnum.ROLE_ADMIN.equals(role))
            {
                Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
                primaryStage.setTitle("Departament Inwestycji PGE S.A. - widok Admin");
                primaryStage.setScene((new Scene(root)));
                primaryStage.show();
            }
//            else if(RoleEnum.ROLE_ADMIN.equals(role))
//            {
//                Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
//                Parent root = FXMLLoader.load(getClass().getResource("/view/tabelaZbiorczaView.fxml"));
//                primaryStage.setTitle("Admin view");
//                primaryStage.setScene((new Scene(root)));
//                primaryStage.show();
//            }
        }

    }

    @FXML
    void loginKeyAction(KeyEvent event) throws IOException {

        if(KeyCode.ENTER.equals(event.getCode())){

            loginEvent(event);


        }

    }

}
