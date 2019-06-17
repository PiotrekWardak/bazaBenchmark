
package pl.pge.di.bazaBenchamrk.controller;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.event.ActionEvent;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;
        import pl.pge.di.bazaBenchamrk.BazaBenchmarkMain;
        import pl.pge.di.bazaBenchamrk.model.Currency;
        import pl.pge.di.bazaBenchamrk.model.Report_1;
        import pl.pge.di.bazaBenchamrk.model.Survey;
        import pl.pge.di.bazaBenchamrk.service.ReportService;
        import pl.pge.di.bazaBenchamrk.service.SurveyService;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.PrintWriter;
        import java.util.ArrayList;
        import java.util.List;

public class SurveyController {

    @FXML
    private MenuItem mSaveToFile;

    @FXML
    private MenuItem mSaveToDatabase;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mClear;

    @FXML
    private MenuItem mAbout;

    @FXML
    private TextField tfCapexNaRok;

    @FXML
    private TextField tfCapexRaportNaMW;

    @FXML
    private TextField tfRokRaportu;

    @FXML
    private TextField tfCapexWybranyRokKEurNaMW;

    @FXML
    private RadioButton rbTAK;

    @FXML
    private ToggleGroup g1;

    @FXML
    private RadioButton rbNIE;

    @FXML
    private Button btnPrzelicz;

    @FXML
    private ComboBox<String> cmbRaportu;

    @FXML
    private TextArea taPreview;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<String> cmbTechnologia;

    @FXML
    private ComboBox<String> cmbWaluta;

    @FXML
    private TextField tfFixedOmNaMW;

    @FXML
    private TextField tfVariableOmInMWh;

    @FXML
    private TextField tfFixedOmWybranyRokKeurNaMWyr;

    @FXML
    private TextField tfVariableOmWybranyRokEurNaMWh;

    @FXML
    private TextField tfLCOE;

    @FXML
    private TextField tfPlanowanieBudowyLata;

    @FXML
    private TextField tfBudowaLata;

    @FXML
    private TextField tfOkresEksploatacjiLata;

    @FXML
    private TextField tfRealEscalatorsPercent;

    @FXML
    private TextField tfCzasPracyPercent;

    @FXML
    private TextField tfStopaDyskontaPercent;

    @FXML
    private Button btnDodajDoBazy;

    @FXML
    private TextField tfLcoeWybranyRokEurNaMWh;


    @FXML
    void ClearEvent(ActionEvent event) {

        taPreview.clear();
        cmbTechnologia.setValue(null);
        rbNIE.setSelected(true);
        tfCapexNaRok.clear();
        tfCapexRaportNaMW.clear();
        tfRokRaportu.clear();
        cmbWaluta.setValue(null);
        tfCapexWybranyRokKEurNaMW.clear();
        tfFixedOmNaMW.clear();
        tfFixedOmWybranyRokKeurNaMWyr.clear();
        tfVariableOmInMWh.clear();
        tfVariableOmWybranyRokEurNaMWh.clear();
        tfLCOE.clear();
        tfLcoeWybranyRokEurNaMWh.clear();
        tfPlanowanieBudowyLata.clear();
        tfBudowaLata.clear();
        tfOkresEksploatacjiLata.clear();
        cmbRaportu.setValue(null);
        tfRealEscalatorsPercent.clear();
        tfCzasPracyPercent.clear();
        tfStopaDyskontaPercent.clear();


    }


    @FXML
    void DodajDoBazyEvent(MouseEvent event) {

        ZapiszDoDB();
    }


    @FXML
    void GoBackEvent(MouseEvent event) throws Exception {

        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    @FXML
    void PrzeliczEvent(MouseEvent event) {


        System.out.println("Tutaj będą przeliczenia");
    }


    @FXML
    void SaveToDataBaseEvent(ActionEvent event) {

        ZapiszDoDB();
    }

    private void ZapiszDoDB() {
        if(isNotCompleted()){
            showAlertNotCompleted();
        }
        else {

            String technology = (String) cmbTechnologia.getValue();
            String overnight = "";

            if (rbTAK.isSelected()) {
                overnight = rbTAK.getText();
            }
            if (rbNIE.isSelected()) {
                overnight = rbNIE.getText();
            }
            String capexNaRok = tfCapexNaRok.getText();
            String capexRaportNaMW = tfCapexRaportNaMW.getText();
            String rokRaportu = tfRokRaportu.getText();
            String waluta = cmbWaluta.getValue();
            String capexWybranyRokkEurNaMW = tfCapexWybranyRokKEurNaMW.getText();
            String fixedOmNaMW = tfFixedOmNaMW.getText();
            String fixedOmWybranyRokKeurNaMWyr = tfFixedOmWybranyRokKeurNaMWyr.getText();
            String variableOmInMWh = tfVariableOmInMWh.getText();
            String variableOmWybranyRokEurNaMWh = tfVariableOmWybranyRokEurNaMWh.getText();
            String lcoe = tfLCOE.getText();
            String lcoeWybranyRokEurNaMWh = tfLcoeWybranyRokEurNaMWh.getText();
            String planowanieBudowyLata = tfPlanowanieBudowyLata.getText();
            String budowaLata = tfBudowaLata.getText();
            String okresEksploatacjiLata = tfOkresEksploatacjiLata.getText();
            //String raportID = cmbRaportu.getValue().toString();
            String realEscalatorsPercent = tfRealEscalatorsPercent.getText();
            String czasPracyPercent = tfCzasPracyPercent.getText();
            String stopaDyskontaPercent = tfStopaDyskontaPercent.getText();


            Survey survey = new Survey(technology, overnight, capexNaRok, capexRaportNaMW, rokRaportu, waluta, capexWybranyRokkEurNaMW, fixedOmNaMW, fixedOmWybranyRokKeurNaMWyr, variableOmInMWh,
                    variableOmWybranyRokEurNaMWh, lcoe, lcoeWybranyRokEurNaMWh, planowanieBudowyLata, budowaLata, okresEksploatacjiLata, "1000", realEscalatorsPercent, czasPracyPercent, stopaDyskontaPercent);

            SurveyService surveyService = new SurveyService();
            surveyService.save(survey);
        }
    }


    @FXML
    void SaveToFileEvent(ActionEvent event) {

        if(isNotCompleted()){
            showAlertNotCompleted();
        }
        else
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save resource file");
            File file = fileChooser.showSaveDialog(null);
            String filePath = file.getPath(); // tutaj dodac jeszcze dobra sciezke do pliku i jego format
            try(PrintWriter save = new PrintWriter(filePath)){

                save.println(getSurveyText());
                System.out.println("dodawanie pliku");
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("exception jest");
            }
        }
    }


    @FXML
    void aboutAction(ActionEvent event) {

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About");
        info.setHeaderText("Instruction");
        info.setContentText("Instruction for using the form ...");
        info.show();

    }

    @FXML
    void closeEvent(ActionEvent event) {

        System.exit(0);
    }


    private boolean isNotCompleted() {
        return cmbTechnologia.getValue()==null || "".equals(tfRokRaportu.getText());
    }

    private String getSurveyText() {

        String technology = (String) cmbTechnologia.getValue();
        String overnight = "";

        if(rbTAK.isSelected()){
            overnight = rbTAK.getText();
        }
        if(rbNIE.isSelected()){
            overnight = rbNIE.getText();
        }
        String capexNaRok = tfCapexNaRok.getText();
        String capexRaportNaMW = tfCapexRaportNaMW.getText();
        String rokRaportu = tfRokRaportu.getText();
        String waluta = cmbWaluta.getValue();
        String capexWybranyRokkEurNaMW = tfCapexWybranyRokKEurNaMW.getText();
        String fixedOmNaMW = tfFixedOmNaMW.getText();
        String fixedOmWybranyRokKeurNaMWyr = tfFixedOmWybranyRokKeurNaMWyr.getText();
        String variableOmInMWh = tfVariableOmInMWh.getText();
        String variableOmWybranyRokEurNaMWh = tfVariableOmWybranyRokEurNaMWh.getText();
        String lcoe = tfLCOE.getText();
        String lcoeWybranyRokEurNaMWh = tfLcoeWybranyRokEurNaMWh.getText();
        String planowanieBudowyLata = tfPlanowanieBudowyLata.getText();
        String budowaLata = tfBudowaLata.getText();
        String okresEksploatacjiLata = tfOkresEksploatacjiLata.getText();
        //String raportID = cmbRaportu.getValue().toString();
        String realEscalatorsPercent = tfRealEscalatorsPercent.getText();
        String czasPracyPercent = tfCzasPracyPercent.getText();
        String stopaDyskontaPercent = tfStopaDyskontaPercent.getText();

        return technology + "\t" + overnight + "\n" + capexNaRok + "\n" + capexRaportNaMW
                + "\t" + rokRaportu + " " + waluta + " " + capexWybranyRokkEurNaMW + "\n" + fixedOmNaMW + "\n" + fixedOmWybranyRokKeurNaMWyr
                + "\t" + variableOmInMWh + " " + variableOmWybranyRokEurNaMWh + "\n" + lcoe + "\n" + lcoeWybranyRokEurNaMWh + "\n" + planowanieBudowyLata + "\n" + budowaLata
                + "\n" + okresEksploatacjiLata + "\n" + "raport"+ "\n" + realEscalatorsPercent + "\n" + czasPracyPercent
                + "\n" + stopaDyskontaPercent;
    }

    private void showAlertNotCompleted() {
        Alert info =  new Alert(Alert.AlertType.WARNING);
        info.setContentText("Musisz podać co najmniej Technologię oraz rok raportu");
        info.setTitle("Błąd");
        info.setHeaderText("Błąd");
        info.show();
    }

    private ReportService reportService = new ReportService();

    public void initialize(){

        ObservableList<String> wartosci = FXCollections.observableArrayList("Atom","OCGT","CCGT","Wegiel kamienny","Wegiel brunatny","Wiatr ladowy","Wiatr morski",
                "Biomasa","Biomasa CHP do 20 MW","Biomasa CHP ponad 20 MW","Szczytowo-pompowa","Fotowoltaika do 1 MW","Fotowoltaika powyżej 1 MW","Fotowoltaika powyżej 5 MW","Wodna do 1 MW"," Wodna do 5 MW",
                "IGCC wegiel brunatnt","IGCC wegiel kamienny","IGCC CCS WB","IGCC CCS WK");
        cmbTechnologia.setItems(wartosci);

        ArrayList<String> listaWalut = new ArrayList<>();
        for(int i =0; i<Currency.values().length;i++) {

           listaWalut.add(i,Currency.values()[i].toString());

        }
        List<Report_1> reports = reportService.getAllReports();
        ArrayList<String> arrayraporty = new ArrayList<>();

        for(Report_1 rep : reports) {

            arrayraporty.add(String.valueOf(rep.getId()));

        }


        ObservableList<String> waluty = FXCollections.observableArrayList(listaWalut);

        ObservableList<String> raporty = FXCollections.observableArrayList(arrayraporty);

        cmbWaluta.setItems(waluty);

        cmbRaportu.setItems(raporty);

    }





}





