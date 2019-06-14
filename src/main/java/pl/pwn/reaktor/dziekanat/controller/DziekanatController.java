package pl.pwn.reaktor.dziekanat.controller;

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
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.LoginService;

import java.io.IOException;

public class DziekanatController {

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


        if("SHOW".equalsIgnoreCase(btnShow.getText())){
            tfPassword.setText(pfPassword.getText());
            tfPassword.setVisible(true);
            pfPassword.setVisible(false);
            btnShow.setText("hide");
        }
        else if ("hide".equalsIgnoreCase(btnShow.getText())){
            pfPassword.setText(tfPassword.getText());
            pfPassword.setVisible(true);
            tfPassword.setVisible(false);
            btnShow.setText("show");
        }
    }

    @FXML
    void SignUpEvent(MouseEvent event) throws Exception {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/signUpView.fxml"));
        primaryStage.setTitle("Sign up");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

    @FXML
    void guestEvent(MouseEvent event) throws Exception {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/guestView.fxml"));
        primaryStage.setTitle("Guest view");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void loginEvent(InputEvent event) throws IOException {


        String login = tfLogin.getText();
        String pass =pfPassword.isVisible()?pfPassword.getText(): tfPassword.getText();

        LoginService loginService = new LoginService();
        User user = loginService.login(login,pass);
        if(user!=null){

            RoleEnum role = user.getRole();
            System.out.println("Zalogowano użytkownika: "+ login + " o roli: " + role);

            CurrentUser.setCurrentUser(user);

            if(RoleEnum.ROLE_STUDENT.equals(role))
            {
                Stage primaryStage = DziekanatMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
                primaryStage.setTitle("User view");
                primaryStage.setScene((new Scene(root)));
                primaryStage.show();
            }
            else if(RoleEnum.ROLE_ADMIN.equals(role))
            {
                Stage primaryStage = DziekanatMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/adminView.fxml"));
                primaryStage.setTitle("Admin view");
                primaryStage.setScene((new Scene(root)));
                primaryStage.show();
            }
        }

    }

    @FXML
    void loginKeyAction(KeyEvent event) throws IOException {

        if(KeyCode.ENTER.equals(event.getCode())){

            loginEvent(event);


        }

    }

}
