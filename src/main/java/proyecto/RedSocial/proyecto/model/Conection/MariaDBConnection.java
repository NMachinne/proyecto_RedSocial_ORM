package proyecto.RedSocial.proyecto.model.Conection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class MariaDBConnection {
	protected static Connection conn;
	protected static String file = "connection.xml";
	
	protected MariaDBConnection() {
		MariaBDFileConnection fc = load();
		try {
			conn=DriverManager.getConnection(fc.getUri()+"/"+fc.getDb(),fc.getUser(),fc.getPass());
		} catch (Exception e) {
			e.printStackTrace();
			conn=null;
		}
	}
	
	/**
	 * M�todo static que devuelve la conexi�n a la bd
	 * @return la conexi�n o null en caso de error
	 */
	public static Connection getConnection() {
		if (conn == null) {
			new MariaDBConnection();
		}
		return conn;
	}
	
	public MariaBDFileConnection load() {
		MariaBDFileConnection con = new MariaBDFileConnection();
		JAXBContext jaxb = null;
		try {
			jaxb = JAXBContext.newInstance(MariaBDFileConnection.class);
			Unmarshaller un = jaxb.createUnmarshaller();
			MariaBDFileConnection m = (MariaBDFileConnection)un.unmarshal(new File(file));
			con = m;
		}
		catch (Exception e) {
		}
		return con;
	}
	
	public static void close() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
