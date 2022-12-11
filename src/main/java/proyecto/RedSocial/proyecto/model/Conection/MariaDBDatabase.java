package proyecto.RedSocial.proyecto.model.Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariaDBDatabase extends MariaDBConnection {
	private final static String uri = "jdbc:mariadb://localhost:3306";
	private final static String sql = "CREATE DATABASE IF NOT EXISTS postt;"
									+ "USE postt;"
									+ "CREATE TABLE comment (\n"
									+ "  id_usuario int(11) NOT NULL,\n"
									+ "  id_publicacion int(11) NOT NULL,\n"
									+ "  fecha datetime NOT NULL,\n"
									+ "  texto varchar(255) NOT NULL\n"
									+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
									+ "CREATE TABLE follow (\n"
									+ "  id int(11) NOT NULL,\n"
									+ "  id_usuario int(11) NOT NULL\n"
									+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
									+ "CREATE TABLE likes (\n"
									+ "  id_usuario int(11) NOT NULL,\n"
									+ "  id_publicacion int(11) NOT NULL,\n"
									+ "  fecha datetime NOT NULL\n"
									+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
									+ "CREATE TABLE post (\n"
									+ "  id int(11) NOT NULL,\n"
									+ "  fecha datetime NOT NULL,\n"
									+ "  texto varchar(255) NOT NULL,\n"
									+ "  multimedia blob DEFAULT NULL,\n"
									+ "  id_usuario int(11) NOT NULL\n"
									+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
									+ "CREATE TABLE user (\n"
									+ "  id int(11) NOT NULL,\n"
									+ "  nombre varchar(256) NOT NULL,\n"
									+ "  password varchar(512) NOT NULL,\n"
									+ "  avatar blob DEFAULT NULL\n"
									+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
									+ "ALTER TABLE comment\n"
									+ "  ADD KEY id_comentario (id_usuario),\n"
									+ "  ADD KEY id_publicacion2 (id_publicacion);\n"
									+ "ALTER TABLE follow\n"
									+ "  ADD PRIMARY KEY (id,id_usuario),\n"
									+ "  ADD KEY id_seguidor (id_usuario);"
									+ "ALTER TABLE likes\n"
									+ "  ADD KEY id_likes (id_usuario),\n"
									+ "  ADD KEY id_publicacion (id_publicacion);"
									+ "ALTER TABLE post\n"
									+ "  ADD PRIMARY KEY (id),\n"
									+ "  ADD KEY id_usuario (id_usuario);"
									+ "ALTER TABLE user\n"
									+ "  ADD PRIMARY KEY (id),\n"
									+ "  ADD UNIQUE KEY nombre (nombre);"
									+ "ALTER TABLE post\n"
									+ "  MODIFY id int(11) NOT NULL AUTO_INCREMENT;"
									+ "ALTER TABLE user\n"
									+ "  MODIFY id int(11) NOT NULL AUTO_INCREMENT;"
									+ "ALTER TABLE comment\n"
									+ "  ADD CONSTRAINT id_comentario FOREIGN KEY (id_usuario) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
									+ "  ADD CONSTRAINT id_publicacion2 FOREIGN KEY (id_publicacion) REFERENCES post (id);"
									+ "ALTER TABLE follow\n"
									+ "  ADD CONSTRAINT id_seguidor FOREIGN KEY (id_usuario) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE;"
									+ "ALTER TABLE likes\n"
									+ "  ADD CONSTRAINT id_likes FOREIGN KEY (id_usuario) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
									+ "  ADD CONSTRAINT id_publicacion FOREIGN KEY (id_publicacion) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE;"
									+ "ALTER TABLE post\n"
									+ "  ADD CONSTRAINT id_usuario FOREIGN KEY (id_usuario) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE;";
	
	private MariaDBDatabase() {}

	/**
	 * M�todo que crea la bd
	 */
	public static void create() {
		Connection conn;
		int i = 0;
		String[] s = sql.split(";");
		try {
			MariaBDFileConnection fc = new MariaDBConnection().load();
			conn = DriverManager.getConnection(uri+"/",fc.getUser(),fc.getPass());
			PreparedStatement ps = null;
			while (i < s.length) {
				ps = conn.prepareStatement(s[i]);
				ps.executeUpdate();
				i++;
			}
			ps.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
