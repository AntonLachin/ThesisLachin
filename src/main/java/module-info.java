module com.work.diploma {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.work.diploma to javafx.fxml, jakarta.persistence;
    exports com.work.diploma;
    opens com.work.diploma.images;
}