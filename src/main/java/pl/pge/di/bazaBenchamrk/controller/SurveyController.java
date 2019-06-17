
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
        import java.util.Objects;
        import java.util.Optional;

public class SurveyController {

    public static int numerWybranegoRekordu = 0;

    public static boolean czyUpdate=false;

    public static final String InformacjaDodawanieDanychGdyBlad = "Musisz podać co najmniej Technologię, rok raportu oraz id raportu do ktorego chcesz przypisac rekord";
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

//    @FXML
//    private TextArea taPreview;

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
    private Button btnUpdateRekordu;

    @FXML
    void UpdateRekorduEvent(MouseEvent event) throws Exception {

        List <Survey> survey = surveyService.findselectedSurvey();
        int index =0;
        surveyService.update(changeValues(survey.get(index)));

        czyUpdate=false;
        Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/tabelaZbiorczaView.fxml"));
        primaryStage.setTitle("user");
        primaryStage.setScene((new Scene(root)));
        primaryStage.show();
    }

    private Survey changeValues(Survey s) {



        s.setCapexNaRok(tfCapexNaRok.getText());
        s.setCapexRaportNaMW(tfCapexRaportNaMW.getText());
        s.setRokRaportu(tfRokRaportu.getText());
        s.setWaluta(cmbWaluta.getValue());
        s.setCapexWybranyRokkEurNaMW(tfCapexWybranyRokKEurNaMW.getText());
        s.setFixedOmNaMW(tfFixedOmNaMW.getText());
        s.setFixedOmWybranyRokKeurNaMWyr(tfFixedOmWybranyRokKeurNaMWyr.getText());
        s.setVariableOmInMWh(tfVariableOmInMWh.getText());
        s.setVariableOmWybranyRokEurNaMWh( tfVariableOmWybranyRokEurNaMWh.getText());
        s.setLcoe(tfLCOE.getText());
        s.setLcoeWybranyRokEurNaMWh(tfLcoeWybranyRokEurNaMWh.getText());
        s.setPlanowanieBudowyLata(tfPlanowanieBudowyLata.getText());
        s.setBudowaLata(tfBudowaLata.getText());
        s.setOkresEksploatacjiLata(tfOkresEksploatacjiLata.getText());
        s.setRaportID( cmbRaportu.getValue().toString());
        s.setRealEscalatorsPercent(tfRealEscalatorsPercent.getText());
        s.setCzasPracyPercent(tfCzasPracyPercent.getText());
        s.setStopaDyskontaPercent(tfStopaDyskontaPercent.getText());

        return s;


    }

    @FXML
    void ClearEvent(ActionEvent event) {

        czyszczenie();


    }

    private void czyszczenie() {
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

        if(czyUpdate==false) {
            Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
            primaryStage.setTitle("user");
            primaryStage.setScene((new Scene(root)));
            primaryStage.show();
        }
        else{
            czyUpdate=false;
            Stage primaryStage = BazaBenchmarkMain.getPrimaryStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/tabelaZbiorczaView.fxml"));
            primaryStage.setTitle("user");
            primaryStage.setScene((new Scene(root)));
            primaryStage.show();
        }


    }

    @FXML
    void PrzeliczEvent(MouseEvent event) {


        System.out.println("Tutaj będą przeliczenia");
    }


    @FXML
    void SaveToDataBaseEvent(ActionEvent event) {

        ZapiszDoDB();
    }
    SurveyService surveyService = new SurveyService();

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
            String raportID = cmbRaportu.getValue().toString();
            String realEscalatorsPercent = tfRealEscalatorsPercent.getText();
            String czasPracyPercent = tfCzasPracyPercent.getText();
            String stopaDyskontaPercent = tfStopaDyskontaPercent.getText();


            Survey survey = new Survey(technology, overnight, capexNaRok, capexRaportNaMW, rokRaportu, waluta, capexWybranyRokkEurNaMW, fixedOmNaMW, fixedOmWybranyRokKeurNaMWyr, variableOmInMWh,
                    variableOmWybranyRokEurNaMWh, lcoe, lcoeWybranyRokEurNaMWh, planowanieBudowyLata, budowaLata, okresEksploatacjiLata, raportID, realEscalatorsPercent, czasPracyPercent, stopaDyskontaPercent);


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
        return cmbTechnologia.getValue()==null || "".equals(tfRokRaportu.getText()) || cmbRaportu.getValue()==null;
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
        String raportID = cmbRaportu.getValue().toString();
        String realEscalatorsPercent = tfRealEscalatorsPercent.getText();
        String czasPracyPercent = tfCzasPracyPercent.getText();
        String stopaDyskontaPercent = tfStopaDyskontaPercent.getText();

        return technology + "\t" + overnight + "\n" + capexNaRok + "\n" + capexRaportNaMW
                + "\t" + rokRaportu + " " + waluta + " " + capexWybranyRokkEurNaMW + "\n" + fixedOmNaMW + "\n" + fixedOmWybranyRokKeurNaMWyr
                + "\t" + variableOmInMWh + " " + variableOmWybranyRokEurNaMWh + "\n" + lcoe + "\n" + lcoeWybranyRokEurNaMWh + "\n" + planowanieBudowyLata + "\n" + budowaLata
                + "\n" + okresEksploatacjiLata + "\n" + raportID + "\n" + realEscalatorsPercent + "\n" + czasPracyPercent
                + "\n" + stopaDyskontaPercent;
    }

    private void showAlertNotCompleted() {
        Alert info =  new Alert(Alert.AlertType.WARNING);
        info.setContentText(InformacjaDodawanieDanychGdyBlad);
        info.setTitle("Błąd");
        info.setHeaderText("Błąd");
        info.show();
    }

    private ReportService reportService = new ReportService();

    public void initialize(){

        if(czyUpdate==false) {
            DodawanieWartosciDoRozwijanychList();
            btnDodajDoBazy.setVisible(true);
            btnUpdateRekordu.setVisible(false);
        }

        else{
            DodawanieWartosciDoRozwijanychList();
            List <Survey> survey = surveyService.findselectedSurvey();
            int index = 0;
            mapToForm(survey.get(index));

            btnDodajDoBazy.setVisible(false);
            btnUpdateRekordu.setVisible(true);

        }

    }
    private void mapToForm(Survey s) {
        cmbTechnologia.setValue(s.getTechnology());
        if(s.getOvernight() == "TAK"){
            rbTAK.setSelected(true);
        }
        else{
            rbNIE.setSelected(true);
        }
        tfCapexNaRok.setText(s.getCapexNaRok());
        tfCapexRaportNaMW.setText(s.getCapexRaportNaMW());
        tfRokRaportu.setText(s.getRokRaportu());
        cmbWaluta.setValue(s.getWaluta());
        tfCapexWybranyRokKEurNaMW.setText(s.getCapexWybranyRokkEurNaMW());
        tfFixedOmNaMW.setText(s.getFixedOmNaMW());
        tfFixedOmWybranyRokKeurNaMWyr.setText(s.getFixedOmWybranyRokKeurNaMWyr());
        tfVariableOmInMWh.setText(s.getVariableOmInMWh());
        tfVariableOmWybranyRokEurNaMWh.setText(s.getVariableOmWybranyRokEurNaMWh());
        tfLCOE.setText(s.getLcoe());
        tfLcoeWybranyRokEurNaMWh.setText(s.getLcoeWybranyRokEurNaMWh());
        tfPlanowanieBudowyLata.setText(s.getPlanowanieBudowyLata());
        tfBudowaLata.setText(s.getBudowaLata());
        tfOkresEksploatacjiLata.setText(s.getOkresEksploatacjiLata());
        cmbRaportu.setValue(s.getRaportID());
        tfRealEscalatorsPercent.setText(s.getRealEscalatorsPercent());
        tfCzasPracyPercent.setText(s.getCzasPracyPercent());
        tfStopaDyskontaPercent.setText(s.getStopaDyskontaPercent());





    }

    private void DodawanieWartosciDoRozwijanychList() {
        ObservableList<String> wartosci = FXCollections.observableArrayList("Atom", "OCGT", "CCGT", "Wegiel kamienny", "Wegiel brunatny", "Wiatr ladowy", "Wiatr morski",
                "Biomasa", "Biomasa CHP do 20 MW", "Biomasa CHP ponad 20 MW", "Szczytowo-pompowa", "Fotowoltaika do 1 MW", "Fotowoltaika powyżej 1 MW", "Fotowoltaika powyżej 5 MW", "Wodna do 1 MW", " Wodna do 5 MW",
                "IGCC wegiel brunatnt", "IGCC wegiel kamienny", "IGCC CCS WB", "IGCC CCS WK");
        cmbTechnologia.setItems(wartosci);

        ArrayList<String> listaWalut = new ArrayList<>();
        for (int i = 0; i < Currency.values().length; i++) {
            listaWalut.add(i, Currency.values()[i].toString());
        }
        ObservableList<String> waluty = FXCollections.observableArrayList(listaWalut);


        List<Report_1> reports = reportService.getAllReports();
        ArrayList<String> arrayraporty = new ArrayList<>();
        for (Report_1 rep : reports) {
            arrayraporty.add(String.valueOf(rep.getId()));
        }
        ObservableList<String> raporty = FXCollections.observableArrayList(arrayraporty);

        cmbWaluta.setItems(waluty);
        cmbRaportu.setItems(raporty);
    }


}





