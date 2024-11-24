module grafikus.notebook.notebook_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens grafikus.notebook to javafx.fxml;
    opens grafikus.notebook.Models to javafx.base, org.hibernate.orm.core;
    exports grafikus.notebook;
}