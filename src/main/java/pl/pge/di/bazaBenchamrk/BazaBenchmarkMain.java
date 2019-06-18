package pl.pge.di.bazaBenchamrk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.utils.HibernateUtils;

import static pl.pge.di.bazaBenchamrk.controller.UserController.DEPARTAMENT_INWESTYCJI_PGE_S_A;


public class BazaBenchmarkMain extends Application {


    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {


        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/view/glownyView.fxml"));
        primaryStage.setTitle(DEPARTAMENT_INWESTYCJI_PGE_S_A);
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        BazaBenchmarkMain.primaryStage = primaryStage;
    }

    @Override
    public void init() throws Exception{
        super.init();
        System.out.println("Metoda init uruchamiana tylko raz przy starcie projektu");
        HibernateUtils.initSessionFactory();
    }
    @Override
    public void stop() throws Exception{

        super.stop();
        System.out.println("Metoda stop - uruchamia tylko raz przy zamykaniu projketu");
        HibernateUtils.destroySessionFactory();
    }

}
