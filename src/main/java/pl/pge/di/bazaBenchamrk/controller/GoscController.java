package pl.pge.di.bazaBenchamrk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;

import java.io.IOException;

import static pl.pge.di.bazaBenchamrk.controller.SignUpController.DEPARTAMENT_INWESTYCJI_PGE_S_A;

public class GoscController {

    @FXML
    private Button btnGoBack;

    @FXML
    void GoBackEvent(MouseEvent event) throws IOException {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle(DEPARTAMENT_INWESTYCJI_PGE_S_A);
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

}
