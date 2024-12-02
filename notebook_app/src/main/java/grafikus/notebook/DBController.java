package grafikus.notebook;


import grafikus.notebook.Models.Notebook;


import grafikus.notebook.Models.OS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DBController implements Initializable {

    static SessionFactory factory;
    static Session session;

    @FXML public Label lb1,lb2,lb3,lb4,lb5, radioLabel, updateLabel, createLabel, deleteLabel;
    @FXML public VBox Read2, updateOS, createOS, deleteNotebook;
    @FXML public TextField searchField, modifiedOS, newOS;
    @FXML public ComboBox comboGyartok;
    @FXML public RadioButton radioButtonINTEL, radioButtonATi, radioButtonNVIDIA, radioButtonVIA;
    @FXML public CheckBox checkBox;
    @FXML public Button searchButton;
    @FXML public ComboBox comboOS, comboNotebook;
    @FXML public AnchorPane readNotebook;

    @FXML private TableView<Notebook> tv1, tv2, tv5;
    @FXML private TableColumn<Notebook, Integer> IDCol;
    @FXML private TableColumn<Notebook, String> gyartoCol;
    @FXML private TableColumn<Notebook, String> tipusCol;
    @FXML private TableColumn<Notebook, Float> kijelzoCol;
    @FXML private TableColumn<Notebook, Integer> memoriaCol;
    @FXML private TableColumn<Notebook, Integer> merevlemezCol;
    @FXML private TableColumn<Notebook, String> videovezerloCol;
    @FXML private TableColumn<Notebook, Integer> arCol;
    @FXML private TableColumn<Notebook, Integer> processzorCol;
    @FXML private TableColumn<Notebook, Integer> oprendszerCol;
    @FXML private TableColumn<Notebook, Integer> dbCol;

    @FXML private TableView<OS> tv3,tv4;
    @FXML private TableColumn<OS, Integer> idCol;
    @FXML private TableColumn<OS, String> nevCol;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (session == null || !session.isOpen()) {
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
                factory = cfg.buildSessionFactory();
                session = factory.openSession();
            }
            setNotebookTable(tv1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hiba a Hibernate inicializáció során: " + e.getMessage());
        }
    }


    @FXML
    public void setNotebookTable(TableView<Notebook> tv) {
        tv.getColumns().clear();
        tv.getItems().clear();// Előző oszlopok törlése
        // Oszlopok létrehozása a Notebook osztály mezői alapján
        IDCol = new TableColumn<>("Id");
        gyartoCol = new TableColumn<>("Gyártó");
        tipusCol = new TableColumn<>("Típus");
        kijelzoCol = new TableColumn<>("Képernyő");
        memoriaCol = new TableColumn<>("Memória");
        merevlemezCol = new TableColumn<>("Merevlemez");
        videovezerloCol = new TableColumn<>("Videóvezérlő");
        arCol = new TableColumn<>("Ár");
        processzorCol = new TableColumn<>("Processzor");
        oprendszerCol = new TableColumn<>("Operációs rendszer");
        dbCol = new TableColumn<>("DB");
        // Oszlopok hozzáadása a táblázathoz
        tv.getColumns().addAll(IDCol, gyartoCol, tipusCol, kijelzoCol, memoriaCol, merevlemezCol, videovezerloCol, arCol, processzorCol, oprendszerCol, dbCol);
        processzorCol.setCellValueFactory(new PropertyValueFactory<>("processzorNev"));
        oprendszerCol.setCellValueFactory(new PropertyValueFactory<>("operaciosRendszerNev"));
        // Beállítjuk, hogy az oszlopokhoz melyik Notebook mezőt társítjuk
        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        gyartoCol.setCellValueFactory(new PropertyValueFactory<>("Gyarto"));
        tipusCol.setCellValueFactory(new PropertyValueFactory<>("Tipus"));
        kijelzoCol.setCellValueFactory(new PropertyValueFactory<>("Kijelzo"));
        memoriaCol.setCellValueFactory(new PropertyValueFactory<>("Memoria"));
        merevlemezCol.setCellValueFactory(new PropertyValueFactory<>("Merevlemez"));
        videovezerloCol.setCellValueFactory(new PropertyValueFactory<>("Videovezerlo"));
        arCol.setCellValueFactory(new PropertyValueFactory<>("Ar"));
        dbCol.setCellValueFactory(new PropertyValueFactory<>("Db"));
        Transaction transaction = session.beginTransaction();
        List<Notebook> notebookLista = session.createQuery("FROM Notebook", Notebook.class).list();
        for (Notebook notebook : notebookLista) {
            tv.getItems().add(notebook);
        }
        transaction.commit();
    }
    public void setNotebook2Table(Query query) {
        tv2.getColumns().clear();
        tv2.getItems().clear();// Előző oszlopok törlése


        // Oszlopok létrehozása a Notebook osztály mezői alapján
        IDCol = new TableColumn<>("Id");
        gyartoCol = new TableColumn<>("Gyártó");
        tipusCol = new TableColumn<>("Típus");
        kijelzoCol = new TableColumn<>("Képernyő");
        memoriaCol = new TableColumn<>("Memória");
        merevlemezCol = new TableColumn<>("Merevlemez");
        videovezerloCol = new TableColumn<>("Videóvezérlő");
        arCol = new TableColumn<>("Ár");
        processzorCol = new TableColumn<>("Processzor");
        oprendszerCol = new TableColumn<>("Operációs rendszer");
        dbCol = new TableColumn<>("DB");

        // Oszlopok hozzáadása a táblázathoz
        tv2.getColumns().addAll(IDCol, gyartoCol, tipusCol, kijelzoCol, memoriaCol, merevlemezCol, videovezerloCol, arCol, processzorCol, oprendszerCol, dbCol);

        processzorCol.setCellValueFactory(new PropertyValueFactory<>("processzorNev"));
        oprendszerCol.setCellValueFactory(new PropertyValueFactory<>("operaciosRendszerNev"));

        // Beállítjuk, hogy az oszlopokhoz melyik Notebook mezőt társítjuk
        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        gyartoCol.setCellValueFactory(new PropertyValueFactory<>("Gyarto"));
        tipusCol.setCellValueFactory(new PropertyValueFactory<>("Tipus"));
        kijelzoCol.setCellValueFactory(new PropertyValueFactory<>("Kijelzo"));
        memoriaCol.setCellValueFactory(new PropertyValueFactory<>("Memoria"));
        merevlemezCol.setCellValueFactory(new PropertyValueFactory<>("Merevlemez"));
        videovezerloCol.setCellValueFactory(new PropertyValueFactory<>("Videovezerlo"));
        arCol.setCellValueFactory(new PropertyValueFactory<>("Ar"));
        dbCol.setCellValueFactory(new PropertyValueFactory<>("Db"));


        Transaction transaction = session.beginTransaction();
        if(query==null) {
            query= session.createQuery("FROM Notebook", Notebook.class);
        }
        List<Notebook> notebookLista = query.getResultList();
        for (Notebook notebook : notebookLista) {
            tv2.getItems().add(notebook);
        }
        transaction.commit();

        lb2.setText("Összesen: "+(long) tv2.getItems().size()+" db.");
    }
    public void setOsTable(TableView<OS> tv) {
        tv.getColumns().clear();
        tv.getItems().clear();// Előző oszlopok törlése
        idCol = new TableColumn<>("Id");
        nevCol = new TableColumn<>("Név");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("Nev"));
        tv.getColumns().addAll(idCol,nevCol);
        List<OS>getOsLista = session.createQuery("FROM OS", OS.class).list();
        for(OS os : getOsLista) {
            tv.getItems().add(os);
        }
    }
    @FXML
    public void getComboOsList(){
        List<String> osLista=new ArrayList<>();
        List<OS>getOsLista = session.createQuery("FROM OS", OS.class).list();
        for(OS os : getOsLista) {
            osLista.add(os.getId()+". "+os.getNev());
        }
        comboOS.setItems(FXCollections.observableArrayList(osLista));
    }
    @FXML
    public void getComboNotebookList(){

        List<String> notebookLista=new ArrayList<>();
        List<Notebook>getNotebookLista = session.createQuery("FROM Notebook", Notebook.class).list();
        for(Notebook nb : getNotebookLista) {
            notebookLista.add(nb.getId()+". "+nb.getGyarto()+" "+nb.getTipus());
        }
        comboNotebook.setItems(FXCollections.observableArrayList(notebookLista));
        lb5.setText("Az adatbázisban található összes notebook. Összesen: "+(long) tv5.getItems().size()+" db.");
    }
    @FXML
    public void menuReadClick(Event event) {
        if(session!=null){
            setNotebookTable(tv1);
        }

    }

    public void menuSearcReadClick(Event event) {
        Read2.setVisible(true);
        Read2.setManaged(true);

        radioButtonINTEL.setSelected(false);
        radioButtonATi.setSelected(false);
        radioButtonNVIDIA.setSelected(false);
        radioButtonVIA.setSelected(false);

        radioButtonINTEL.setOnAction(e -> {
            if (radioButtonINTEL.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha az INTEL be van jelölve, az ATi,NVIDIA,VIA ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonATi.setOnAction(e -> {
            if (radioButtonATi.isSelected()) {
                radioButtonINTEL.setSelected(false);  // Ha az ATi be van jelölve, az Intel,NVIDIA,VIA ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonNVIDIA.setOnAction(e -> {
            if (radioButtonNVIDIA.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha az NVIDIA be van jelölve, az ATi,Intel,VIA ki legyen jelölve
                radioButtonINTEL.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonVIA.setOnAction(e -> {
            if (radioButtonVIA.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha a VIA be van jelölve, az ATi,NVIDIA,Intel ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonINTEL.setSelected(false);
            }
        });


        List<String> gyartoLista=new ArrayList<>();
        gyartoLista.add("Válasszon egy gyártót");
        List<String>getGyartoLista = session.createQuery
                ("SELECT DISTINCT n.Gyarto FROM Notebook n", String.class).list();
        for(var item : getGyartoLista) {
            gyartoLista.add(item.toString());
        }
        comboGyartok.setItems(FXCollections.observableArrayList(gyartoLista));

        comboGyartok.getSelectionModel().selectFirst();


        setNotebook2Table(null);
    }

    public void btSearch(ActionEvent actionEvent) {

        String searchFieldText = searchField.getText();
        String searchText="";
        if(!searchField.getText().isEmpty()) {
            searchText = "%"+searchFieldText+"%";
        }

        boolean inStock = checkBox.isSelected();
        String getGyarto= comboGyartok.getSelectionModel().getSelectedItem().toString();
        int getGyartoIndex=comboGyartok.getSelectionModel().getSelectedIndex();
        String hql = "FROM Notebook WHERE 1=1";


        //Megnézzük melyik van kiválaszta, az true értékkel tér vissza
        List<Boolean>radios=new ArrayList<>();
        radios.add(radioButtonINTEL.isSelected());
        radios.add(radioButtonATi.isSelected());
        radios.add(radioButtonNVIDIA.isSelected());
        radios.add(radioButtonVIA.isSelected());
        if(radios.get(0)){
            hql += "AND videovezerlo LIKE 'Intel%'";
        }
        if(radios.get(1)){
            hql += "AND videovezerlo LIKE 'ATi%'";
        }
        if(radios.get(2)){
            hql += "AND videovezerlo LIKE 'NVIDIA%'";
        }
        if(radios.get(3)){
            hql += "AND videovezerlo LIKE 'VIA%'";
        }
        if(!searchText.isEmpty()) {
            hql += " AND (gyarto LIKE :searchText OR tipus LIKE :searchText  OR videovezerlo LIKE :searchText)";
        }
        if(comboGyartok.getSelectionModel().getSelectedIndex()!=0){
            hql += " AND gyarto = :getGyarto";
        }
        if(checkBox.isSelected()){
            hql += " AND db>0";
        }
        // Adatok lekérése a Hibernate segítségével
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql,Notebook.class);
        if (searchText!="") {
            query.setParameter("searchText", searchText); // searchText paraméter
        }
        if (getGyartoIndex!=0) {
            query.setParameter("getGyarto", getGyarto);
        }
        transaction.commit();
        setNotebook2Table(query);



    }

    public void menuCreateClick(Event event) {
        setOsTable(tv3);
        lb3.setText("Összesen: "+(long) tv3.getItems().size()+" db.");
    }
    public void Create(ActionEvent actionEvent) {

        Session sessionForNewOs = factory.openSession();

        System.out.println(newOS.getText());
        OS osCreate;
        if(!newOS.getText().trim().isEmpty()) {
            osCreate=new OS(newOS.getText());
            sessionForNewOs.save(osCreate);
            sessionForNewOs.close();
        }
        setOsTable(tv3);

        // Adatok lekérése a Hibernate segítségével
        tv3.getItems().clear();
        setOsTable(tv3);


    }
    public void menuUpdateClick(Event event) {
        setOsTable(tv4);
        getComboOsList();
        lb4.setText("Összesen: "+(long) tv4.getItems().size()+" db.");
    }

    public void updateTextField(ActionEvent actionEvent) {
        modifiedOS.setText("");
        if(!comboOS.getSelectionModel().isEmpty()){
            String selectedOS= comboOS.getSelectionModel()
                    .getSelectedItem()
                    .toString();
            // Levágjuk az első pont előtti részt
            // (beleértve a pontot és az utána lévő szóközt is)
            modifiedOS.setText(selectedOS.substring(selectedOS.indexOf(".")+2));
        }
    }


    public void Update(ActionEvent actionEvent) {
        if(!modifiedOS.getText().trim().isEmpty() || !modifiedOS.getText()
                .trim()
                .equals(comboOS.getSelectionModel()
                        .getSelectedItem()
                        .toString())){
            Transaction transaction = session.beginTransaction();
            String selectedOS=comboOS.getSelectionModel().getSelectedItem().toString();
            int dotIndex = selectedOS.indexOf('.');
            int selectedOsIndex = Integer.parseInt(selectedOS.substring(0, dotIndex).trim());

            OS os=session.get(OS.class, selectedOsIndex);
            os.setNev(modifiedOS.getText().trim());
            session.update(os);
            tv4.refresh();
            comboOS.getSelectionModel().clearSelection();
            getComboOsList();

            modifiedOS.setText("");

            transaction.commit();
        }
    }


    public void menuDeleteClick(Event event) {
        setNotebookTable(tv5);
        getComboNotebookList();

    }

    public void Delete(ActionEvent actionEvent) {
        if(!comboNotebook.getSelectionModel().isEmpty()){
            Transaction transaction = session.beginTransaction();
            String selectedNotebook=comboNotebook.getSelectionModel().getSelectedItem().toString();
            int dotIndex = selectedNotebook.indexOf('.');
            int selectedNotebookIndex = Integer.parseInt(selectedNotebook.substring(0, dotIndex).trim());

            Notebook nb=session.get(Notebook.class, selectedNotebookIndex);
            session.delete(nb);

            comboNotebook.getSelectionModel().clearSelection();
            transaction.commit();

            menuDeleteClick(actionEvent);
        }
    }
}