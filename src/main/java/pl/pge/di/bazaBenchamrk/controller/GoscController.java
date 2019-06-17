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

public class GoscController {

    @FXML
    private Button btnGoBack;

    @FXML
    void GoBackEvent(MouseEvent event) throws IOException {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle("Departament Inwestycji PGE S.A.");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

}
