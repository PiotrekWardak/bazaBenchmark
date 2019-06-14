package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.UserService;

import java.util.List;

public class AdminController {

    @FXML
    private TableView<StudentDTO> tvStudent;

    @FXML
    private TableColumn<StudentDTO, Long> colidS;

    @FXML
    private TableColumn<StudentDTO, String> colLoginS;

    @FXML
    private TableColumn<StudentDTO, Boolean> colActiveS;

    @FXML
    private TableColumn<StudentDTO, String> colFirstNameS;

    @FXML
    private TableColumn<StudentDTO, String> colLastNameS;

    @FXML
    private TableColumn<StudentDTO, String> colStreetS;

    @FXML
    private TableColumn<StudentDTO, String> colCityS;

    @FXML
    private TableView<User> tvAdmin;

    @FXML// drugi ? to typ kolumny a pierwszy to klasa wyswietlana w tabeli
    private TableColumn<User, Long> colIDA;

    @FXML
    private TableColumn<User, String> colLoginA;

    @FXML
    private TableColumn<User, Enum> colRoleA;

    @FXML
    private TableColumn<User, Boolean> colActiveA;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mAbout;

    private UserService userService = new UserService();

    @FXML
    void aboutAction(ActionEvent event) {
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    info.setTitle("About");
    info.setHeaderText("Instruction");
    info.setContentText("Instruction for using the form ...");
    info.show();
    }

    @FXML
    void closeAction(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void logoutAction(ActionEvent event) throws Exception {
        CurrentUser.clean();
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/dziekanatView.fxml"));
        primaryStage.setTitle("Logowanie");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();

    }

    public void initialize(){
        initAdminTable();

        //pobranie danych studenta z bazy danych

        List<StudentDTO> Students = userService.getAllStudent();

        //konwertowanie na listę zrozumiałą przez TableView z JavaFx
        ObservableList<StudentDTO> observableStudents = FXCollections.observableArrayList(Students);
        tvStudent.setItems(null);
        tvStudent.setItems(observableStudents);
        //ustawienie kolumn ktore pola z user maja byc widoczne i w jakiej kolumnie z widoku
        colidS.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginS.setCellValueFactory(new PropertyValueFactory<>("login"));
        colActiveS.setCellValueFactory(new PropertyValueFactory<>("active"));
        colFirstNameS.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastNameS.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStreetS.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCityS.setCellValueFactory(new PropertyValueFactory<>("city"));

    }

    private void initAdminTable() {

        List<User> admins = userService.getAllAdmin();

        ObservableList<User> observableAdmins = FXCollections.observableArrayList(admins);

        tvAdmin.setItems(null);
        tvAdmin.setItems(observableAdmins);

        //ustawienie kolumn które pola z User mają być widoczne i w jakiej kolumnie z widoku
        colIDA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginA.setCellValueFactory(new PropertyValueFactory<>("login"));
        colRoleA.setCellValueFactory(new PropertyValueFactory<>("role"));
        colActiveA.setCellValueFactory(new PropertyValueFactory<>("active"));
    }

}
