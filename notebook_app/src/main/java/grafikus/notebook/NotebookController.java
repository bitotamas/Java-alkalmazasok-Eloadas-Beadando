package grafikus.notebook;


import grafikus.notebook.Models.Notebook;


import grafikus.notebook.Models.OS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class NotebookController {

    @FXML private VBox read_two;
    @FXML private VBox createOS;
    @FXML private VBox updateOS;
    @FXML private VBox deleteNotebook;
    @FXML private TextField searchField;
    @FXML private TextField newOS;
    @FXML private TextField modifiedOS;
    @FXML private ComboBox comboGyartok;
    @FXML private ComboBox comboOS;
    @FXML private ComboBox comboNotebook;
    @FXML private RadioButton radioButtonINTEL;
    @FXML private RadioButton radioButtonATi;
    @FXML private RadioButton radioButtonNVIDIA;
    @FXML private RadioButton radioButtonVIA;
    @FXML private CheckBox checkBox;
    @FXML private Button searchButton;




    @FXML private Label lb1;
    @FXML private GridPane gp1;
    @FXML private TableView<Notebook> tv1;
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


    @FXML private Label lb2;
    @FXML private GridPane gp2;
    @FXML private TableView<OS> tv2;
    @FXML private TableColumn<OS, Integer> idCol;
    @FXML private TableColumn<OS, String> nevCol;



    SessionFactory factory;
    private static Session session;



    @FXML
    void initialize() {
        if (session == null || !session.isOpen()) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).buildMetadata();
            factory = metadata.getSessionFactoryBuilder().build();
            session = factory.openSession();
        }
        ElemekElrejtése();
    }

    void ElemekElrejtése() {

        //Táblázat
        lb1.setVisible(false);
        lb1.setManaged(false);
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);


        tv2.setVisible(false);
        tv2.setManaged(false);
        gp2.setVisible(false);
        gp2.setManaged(false);
        lb2.setVisible(false);
        lb2.setManaged(false);


        //Keresési mező
        read_two.setVisible(false);
        read_two.setManaged(false);
        searchField.setVisible(false);
        searchField.setManaged(false);
        comboGyartok.setVisible(false);
        comboGyartok.setManaged(false);
        radioButtonINTEL.setVisible(false);
        radioButtonINTEL.setManaged(false);
        radioButtonATi.setVisible(false);
        radioButtonATi.setManaged(false);
        radioButtonNVIDIA.setVisible(false);
        radioButtonNVIDIA.setManaged(false);
        radioButtonVIA.setVisible(false);
        radioButtonVIA.setManaged(false);
        checkBox.setVisible(false);
        checkBox.setManaged(false);

        //Create beviteli mezők
        createOS.setVisible(false);
        createOS.setManaged(false);
        newOS.setVisible(false);
        newOS.setManaged(false);


        //Update

        updateOS.setVisible(false);
        updateOS.setManaged(false);
        comboOS.setVisible(false);
        comboOS.setManaged(false);


        //Delete

        deleteNotebook.setVisible(false);
        deleteNotebook.setManaged(false);
        comboNotebook.setVisible(false);
        comboNotebook.setManaged(false);
    }

    @FXML
    public void menuCreateClick(ActionEvent actionEvent) {
        ElemekElrejtése();

        tv2.setVisible(true);
        tv2.setManaged(true);
        tv2.getColumns().clear();
        tv2.getItems().clear();

        idCol = new TableColumn<>("Id");
        nevCol = new TableColumn<>("Név");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("Nev"));
        tv2.getColumns().addAll(idCol,nevCol);


        createOS.setVisible(true);
        createOS.setManaged(true);
        newOS.setVisible(true);
        newOS.setManaged(true);
        List<OS> osLista = session.createQuery("FROM OS", OS.class).list();
        tv2.getItems().addAll(osLista);

    }
    public void Create(ActionEvent actionEvent) {

        Session session = factory.openSession();


        System.out.println(newOS.getText());
        OS osCreate=new OS(newOS.getText());
        session.save(osCreate);


        // Adatok lekérése a Hibernate segítségével
        tv2.getItems().clear();
        List<OS> osLista = session.createQuery("FROM OS", OS.class).list();
        for (OS os : osLista) {
            tv2.getItems().addAll(os);
        }

    }

    @FXML
    protected void menuReadClick() {
        ElemekElrejtése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Az adatbázisban található összes notebook. Összesen: "+session.createQuery("FROM Notebook", Notebook.class).stream().count()+" db.");

        setNotebookTable();

        // Adatok lekérése a Hibernate segítségével
        Transaction transaction = session.beginTransaction();
        List<Notebook> notebookLista = session.createQuery("FROM Notebook", Notebook.class).list();
        for (Notebook notebook : notebookLista) {
            tv1.getItems().add(notebook);

        }
        transaction.commit();
    }
    public void setNotebookTable(){
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().clear();  // Előző oszlopok törlése

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
        tv1.getColumns().addAll(IDCol, gyartoCol, tipusCol, kijelzoCol, memoriaCol, merevlemezCol, videovezerloCol, arCol, processzorCol, oprendszerCol, dbCol);

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

        tv1.getItems().clear();
    }
    @FXML
    public void menuSearchReadClick(ActionEvent actionEvent) {

        ElemekElrejtése();


        read_two.setVisible(true);
        read_two.setManaged(true);
        searchField.setVisible(true);
        searchField.setManaged(true);
        comboGyartok.setVisible(true);
        comboGyartok.setManaged(true);
        radioButtonINTEL.setVisible(true);
        radioButtonINTEL.setManaged(true);
        radioButtonATi.setVisible(true);
        radioButtonATi.setManaged(true);
        radioButtonNVIDIA.setVisible(true);
        radioButtonNVIDIA.setManaged(true);
        radioButtonVIA.setVisible(true);
        radioButtonVIA.setManaged(true);
        checkBox.setVisible(true);
        checkBox.setManaged(true);
        tv1.getItems().clear();



        radioButtonINTEL.setSelected(false);
        radioButtonATi.setSelected(false);
        radioButtonNVIDIA.setSelected(false);
        radioButtonVIA.setSelected(false);

        radioButtonINTEL.setOnAction(event -> {
            if (radioButtonINTEL.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha az INTEL be van jelölve, az ATi,NVIDIA,VIA ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonATi.setOnAction(event -> {
            if (radioButtonATi.isSelected()) {
                radioButtonINTEL.setSelected(false);  // Ha az ATi be van jelölve, az Intel,NVIDIA,VIA ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonNVIDIA.setOnAction(event -> {
            if (radioButtonNVIDIA.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha az NVIDIA be van jelölve, az ATi,Intel,VIA ki legyen jelölve
                radioButtonINTEL.setSelected(false);
                radioButtonVIA.setSelected(false);
            }
        });
        radioButtonVIA.setOnAction(event -> {
            if (radioButtonVIA.isSelected()) {
                radioButtonATi.setSelected(false);  // Ha a VIA be van jelölve, az ATi,NVIDIA,Intel ki legyen jelölve
                radioButtonNVIDIA.setSelected(false);
                radioButtonINTEL.setSelected(false);
            }
        });



        List<String> gyartoLista=new ArrayList<>();
        gyartoLista.add("Válasszon egy gyártót");
        List<String>getGyartoLista = session.createQuery("SELECT DISTINCT n.Gyarto FROM Notebook n", String.class).list();
        for(var item : getGyartoLista) {
            gyartoLista.add(item.toString());
        }
        comboGyartok.setItems(FXCollections.observableArrayList(gyartoLista));

        comboGyartok.getSelectionModel().selectFirst();
        btSearch(actionEvent);
    }
    @FXML
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

        setNotebookTable();
        lb1.setVisible(true);
        lb1.setManaged(true);
        // Adatok lekérése a Hibernate segítségével
        Transaction transaction = session.beginTransaction();
        var query = session.createQuery(hql,Notebook.class);
        if (searchText!="") {
            query.setParameter("searchText", searchText); // searchText paraméter
        }
        if (getGyartoIndex!=0) {
            query.setParameter("getGyarto", getGyarto);
        }

        List<Notebook> notebookLista = query.list();
        for (Notebook notebook : notebookLista) {
            tv1.getItems().add(notebook);

        }
        tv1.refresh();
        lb1.setText("Az adatbázisban található szűrt notebookok. Összesen: "+ (long) tv1.getItems().size() +" db.");

        transaction.commit();


    }

    @FXML
    public void menuUpdateClick(ActionEvent actionEvent) {
       //Operációs rendszer nevének módosítása
        ElemekElrejtése();

        tv2.setVisible(true);
        tv2.setManaged(true);
        tv2.getItems().clear();
        tv2.getColumns().clear();

        lb2.setVisible(true);
        lb2.setManaged(true);



        updateOS.setVisible(true);
        updateOS.setManaged(true);
        comboOS.setVisible(true);
        comboOS.setManaged(true);

        idCol = new TableColumn<>("Id");
        nevCol = new TableColumn<>("Név");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("Nev"));
        tv2.getColumns().addAll(idCol,nevCol);

        List<String> osLista=new ArrayList<>();
        List<OS>getOsLista = session.createQuery("FROM OS", OS.class).list();
        for(OS os : getOsLista) {
            osLista.add(os.getId()+". "+os.getNev());
            tv2.getItems().add(os);
        }
        comboOS.setItems(FXCollections.observableArrayList(osLista));


        lb2.setText("Az adatbázisban található operációs rendszerek. Összesen: "+(long) tv2.getItems().size()+" db.");

        //comboOS.getSelectionModel().selectFirst();

    }
    public void updateTextField(ActionEvent actionEvent) {

        modifiedOS.setText("");
        if(!comboOS.getSelectionModel().isEmpty()){
            String selectedOS= comboOS.getSelectionModel().getSelectedItem().toString();
            // Levágjuk az első pont előtti részt (beleértve a pontot és az utána lévő szóközt is)
            modifiedOS.setText(selectedOS.substring(selectedOS.indexOf(".")+2));
        }

    }

    public void Update(ActionEvent actionEvent) {

        if(!modifiedOS.getText().trim().isEmpty() || !modifiedOS.getText().trim().equals(comboOS.getSelectionModel().getSelectedItem().toString())){
            Transaction transaction = session.beginTransaction();
            String selectedOS=comboOS.getSelectionModel().getSelectedItem().toString();
            int dotIndex = selectedOS.indexOf('.');
            int selectedOsIndex = Integer.parseInt(selectedOS.substring(0, dotIndex).trim());

            OS os=session.get(OS.class, selectedOsIndex);
            os.setNev(modifiedOS.getText().trim());
            session.update(os);
            tv2.refresh();
            comboOS.getSelectionModel().clearSelection();
            modifiedOS.setText("");

            transaction.commit();
        }
    }

    public void menuDeleteClick(ActionEvent actionEvent) {
        // Notebook törlése
        ElemekElrejtése();
        tv1.getItems().clear();
        tv1.getColumns().clear();
        lb1.setText("");

        lb1.setVisible(true);
        lb1.setManaged(true);
         deleteNotebook.setVisible(true);
        deleteNotebook.setManaged(true);
        comboNotebook.setVisible(true);
        comboNotebook.setManaged(true);

        setNotebookTable();

        List<String> notebookLista=new ArrayList<>();
        List<Notebook>getOsLista = session.createQuery("FROM Notebook", Notebook.class).list();
        for(Notebook nb : getOsLista) {
            notebookLista.add(nb.getId()+". "+nb.getGyarto()+" "+nb.getTipus());
            tv1.getItems().add(nb);
        }
        comboNotebook.setItems(FXCollections.observableArrayList(notebookLista));
        lb1.setText("Az adatbázisban található összes notebook. Összesen: "+(long) tv1.getItems().size()+" db.");

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