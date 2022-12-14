module proyecto.RedSocial.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.xml.bind;
	requires java.desktop;
	requires java.xml;
	requires jdk.compiler;
	requires java.persistence;
	requires jdk.javadoc;
	requires org.mariadb.jdbc;
	requires java.sql.rowset;
	requires org.hibernate.orm.core;
	

    opens proyecto.RedSocial.proyecto to javafx.fxml;
    opens proyecto.RedSocial.proyecto.model.Entity;
    opens proyecto.RedSocial.proyecto.model.Conection to java.xml.bind;
    exports proyecto.RedSocial.proyecto.model.Conection;
    exports proyecto.RedSocial.proyecto;
}
