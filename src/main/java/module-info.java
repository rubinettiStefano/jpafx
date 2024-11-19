module com.generation.jpafx {
	requires javafx.controls;
	requires javafx.fxml;
	requires jakarta.persistence;
	requires org.hibernate.orm.core;

	opens com.generation.jpafx to javafx.fxml, org.hibernate.orm.core;
	exports com.generation.jpafx;
	exports com.generation.jpafx.model;
	opens com.generation.jpafx.model to javafx.fxml, org.hibernate.orm.core;
	opens com.generation.jpafx.controller to javafx.fxml, org.hibernate.orm.core;
}
