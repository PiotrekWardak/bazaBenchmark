package pl.pge.di.bazaBenchamrk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
import pl.pge.di.bazaBenchamrk.model.Report_1;
import pl.pge.di.bazaBenchamrk.service.ReportService;
import pl.pge.di.bazaBenchamrk.service.UserService;
import pl.pge.di.bazaBenchamrk.model.Address;
import pl.pge.di.bazaBenchamrk.model.User;
import pl.pge.di.bazaBenchamrk.model.utils.CurrentUser;

import java.util.Objects;
import java.util.Optional;

public class UpdateUserDataController {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfCity;

    @FXML
    private Button btnSave;
    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void saveEvent(MouseEvent event) {

        User user = CurrentUser.getCurrentUser();
//
//        Report_1 student = Objects.isNull(user.getStudent())?new Report_1(): user.getStudent();
//        user.setStudent(student);

        Report_1 student= user.getStudent();
        ReportService reportService = new ReportService();
        if(Objects.isNull(user.getStudent())){

            student = new Report_1();
            // zapis studenta do bazy danych

            reportService.save(student);
            user.setStudent(student);
            UserService userService = new UserService();
            userService.update(user);




        }


        //student.setFirstName(tfFirstName.getText());
       // student.setLastName(tfLastName.getText());

        Address address = new Address(tfStreet.getText(), tfCity.getText());

        //student.setAddress(address);

       try {
           reportService.update(student);
           Alert info =  new Alert(Alert.AlertType.INFORMATION);
           info.setContentText("Success update student");
           info.setTitle("Update student");
           info.show();

       }
       catch (Exception e){
           Alert error =  new Alert(Alert.AlertType.ERROR);
           error.setContentText("Update student error\n"+e);
           error.setTitle("Error update student");
           error.show();
       }

    }



    public void initialize(){

        User currentUser = CurrentUser.getCurrentUser();
        //zaminaa lambdy na referencje alt+enter na tym s-> mapToForm(s)
        Optional.ofNullable(currentUser.getStudent()).ifPresent(this::mapToForm);
    }

    private void mapToForm(Report_1 s) {

        //Objects pochodzi z java util, zawiera metody do sprawdzania czy cos jest nullem, do weryfikacji typow
        tfId.setText(String.valueOf(s.getId()));
//        tfFirstName.setText(Objects.nonNull(s.getFirstName())?s.getFirstName():"");
//        tfLastName.setText(Optional.ofNullable(s.getLastName()).orElse(""));
//        if(Objects.nonNull(s.getAddress())) {
//            tfStreet.setText(Objects.isNull(s.getAddress().getStreet()) ? "" : s.getAddress().getStreet());
//            //.orElseGet przyjmuje lambde. nie jest zachłanne(tworza sie tylko obiekty tam gdzie program sie wykonuje)
//            // zachlanne tworzy wszelkie mozliwe obiekty i zabiera nam zasoby
//            tfCity.setText(Optional.ofNullable(s.getAddress().getCity()).orElse(""));
//        }
    }

}
