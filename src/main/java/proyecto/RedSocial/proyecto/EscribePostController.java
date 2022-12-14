package proyecto.RedSocial.proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class EscribePostController extends AController {
	@FXML
	private Button SubeImagenes;

	@FXML
	private Button SubePost;

	@FXML
	private TextArea texto;

	@FXML
	private Button Cancelar;
	@FXML
	private Label ruta;

	static Post p1 = new Post();
	static PostDAO ps = new PostDAO();

	@FXML
	void multiFileChooser(ActionEvent event) {
		// LoginController lg=new LoginController();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Se permite png, jpg y bmp", "*.png", "*.jpg", "*.bmp"));
		File f = fc.showOpenDialog(null);

		String nam = "";
		String imagen = "";
		File fl = f.getAbsoluteFile();
		nam = f.getAbsolutePath();
		imagen = encodeFileToBase64(fl);
		byte[] byteData = imagen.getBytes();

		Blob docInBlob = null;
		try {
			docInBlob = new SerialBlob(byteData);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ruta.setText(nam);
		p1.setLikes(new User(login_user.getId(), "", "", null));
		LocalDateTime locdate = LocalDateTime.now().withNano(0);
		p1.setFecha(Timestamp.valueOf(locdate));
		p1.setMultimedia(docInBlob);

		// ps.save(p1);
	}

	@FXML
	void sube(ActionEvent event) {
		LocalDateTime locdate = LocalDateTime.now().withNano(0);
		String text = texto.getText();
		p1.setTxt(text);
		p1.setLikes(login_user);
		String imagen = "1";
		byte[] byteData = imagen.getBytes();
		Blob docInBlob = null;
		p1.setFecha(Timestamp.valueOf(locdate));
		try {
			docInBlob = new SerialBlob(byteData);
		} catch (SerialException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
		p1.setMultimedia(docInBlob);
		ps.save(p1);
		}
		catch (Exception e) {}
		texto.setText("");

	}

	@FXML
	void cancelar(ActionEvent event) {
		try {
			App.setRoot("post");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String encodeFileToBase64(File file) {
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			throw new IllegalStateException("could not read file " + file, e);
		}
	}
}