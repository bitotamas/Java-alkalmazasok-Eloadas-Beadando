package grafikus.notebook;


import grafikus.notebook.Models.OS;
import grafikus.notebook.Models.Notebook;
import grafikus.notebook.Models.CPU;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class NotebookController {

    @FXML private Label lb1;
    @FXML private GridPane gp1;
    @FXML private TextField tfId, tfNev;
    @FXML private TableView<Notebook> tv1;
    @FXML private TableColumn<Notebook, Integer> IDCol;
    @FXML private TableColumn<Notebook, String> gyartoCol;
    @FXML private TableColumn<Notebook, String> tipusCol;
    @FXML private TableColumn<Notebook, Float> kijelzoCol;
    @FXML private TableColumn<Notebook, Integer> memoriaCol;
    @FXML private TableColumn<Notebook, Integer> merevlemezCol;
    @FXML private TableColumn<Notebook, String> videovezerloCol;
    @FXML private TableColumn<Notebook, Integer> arCol;
    @FXML private TableColumn<Notebook, Integer> processzoridCol;
    @FXML private TableColumn<Notebook, Integer> oprendszeridCol;
    @FXML private TableColumn<Notebook, Integer> dbCol;

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
        lb1.setVisible(false);
        lb1.setManaged(false);
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
    }

    @FXML
    protected void menuReadClick() {
        ElemekElrejtése();
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
        processzoridCol = new TableColumn<>("Processzor ID");
        oprendszeridCol = new TableColumn<>("Operációs rendszer ID");
        dbCol = new TableColumn<>("DB");

        // Oszlopok hozzáadása a táblázathoz
        tv1.getColumns().addAll(IDCol, gyartoCol, tipusCol, kijelzoCol, memoriaCol, merevlemezCol, videovezerloCol, arCol, processzoridCol, oprendszeridCol, dbCol);

        // Beállítjuk, hogy az oszlopokhoz melyik Notebook mezőt társítjuk
        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        gyartoCol.setCellValueFactory(new PropertyValueFactory<>("Gyarto"));
        tipusCol.setCellValueFactory(new PropertyValueFactory<>("Tipus"));
        kijelzoCol.setCellValueFactory(new PropertyValueFactory<>("Kijelzo"));
        memoriaCol.setCellValueFactory(new PropertyValueFactory<>("Memoria"));
        merevlemezCol.setCellValueFactory(new PropertyValueFactory<>("Merevlemez"));
        videovezerloCol.setCellValueFactory(new PropertyValueFactory<>("Videovezerlo"));
        arCol.setCellValueFactory(new PropertyValueFactory<>("Ar"));
        processzoridCol.setCellValueFactory(new PropertyValueFactory<>("ProcesszorId"));
        oprendszeridCol.setCellValueFactory(new PropertyValueFactory<>("OprendszerId"));
        dbCol.setCellValueFactory(new PropertyValueFactory<>("Db"));

        tv1.getItems().clear();

        // Adatok lekérése a Hibernate segítségével
        Transaction transaction = session.beginTransaction();
        List<Notebook> lista = session.createQuery("FROM Notebook", Notebook.class).list();
        for (Notebook notebook : lista) {
            tv1.getItems().add(notebook);
        }
        transaction.commit();
    }

    public void menuCreateClick(ActionEvent actionEvent) {
        // Új notebook hozzáadása (később implementálandó)
    }

    public void menuUpdateClick(ActionEvent actionEvent) {
        // Notebook módosítása (később implementálandó)
    }

    public void menuDeleteClick(ActionEvent actionEvent) {
        // Notebook törlése (később implementálandó)
    }
}