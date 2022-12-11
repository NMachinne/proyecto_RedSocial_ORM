package proyecto.RedSocial.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.User;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class LoginController extends AController {

	@FXML
	private Button createAcoount;
	@FXML
	private Button changepassword;

	@FXML
	private PasswordField getpassword;

	@FXML
	private TextField getusername;

	@FXML
	private ImageView photo;

	@FXML
	private Text pswrd;

	@FXML
	private Text recpasswrd;

	@FXML
	private Button singin;

	@FXML
	private Text username;

	private static final String SECRET_KEY = "my_super_secret_key";
	private static final String SALT = "ssshhhhhhhhhhh!!!!";

	/**
	 * Permite crear una cuenta
	 * 
	 */
	@FXML
	void createaccount(ActionEvent event) {
		User userAdd = new User();
		String name = getusername.getText();
		String password = getpassword.getText();
		password = encrypt(password);
		userAdd.setNombre(name);
		userAdd.setPassword(password);
		UserDAO us = new UserDAO();
		us.save(userAdd);

	}

	@FXML
	void recoverpasswd(MouseEvent event) {

		try {
			App.setRoot("recoverpasswd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Vuelve a cargar login
	 */
	@FXML
    void backToLogin(MouseEvent event) throws IOException{
			App.setRoot("login");
    }

	/**
	 * 
	 * @param event
	 */
	@FXML
	void recoverpasswd2(ActionEvent event) {

		User userAdd = new User();
		boolean flag = false;
		String name = getusername.getText();
		String password = getpassword.getText();
		userAdd.setNombre(name);
		userAdd.setPassword(password);
		UserDAO us = new UserDAO();
		String pass2 = encrypt(userAdd.getPassword());

		for (User user : us.getAll()) { // String pass2=decrypt(user.getPassword());
			int id = user.getId();
			if (user.getNombre().equals(userAdd.getNombre())) {
				userAdd.setPassword(pass2);
				userAdd.setId(id);
				us.update(userAdd);
				flag = true;
			}
		}
		getusername.setText("");
		getpassword.setText("");
		if (flag == false) {
			alertAcount();
		}

		try {
			App.setRoot("login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void singinAplication(ActionEvent event) {
		User userAdd = new User();
		String name = getusername.getText();
		String password = getpassword.getText();
		userAdd.setNombre(name);
		userAdd.setPassword(password);
		UserDAO us = new UserDAO();
		for (User user : us.getAll()) {
			String pass2 = decrypt(user.getPassword());
			if (user.getNombre().equals(userAdd.getNombre()) && pass2.equals(userAdd.getPassword())) {
				userAdd = (User) new UserDAO().getByName(userAdd).toArray()[0];
				login_user = userAdd;
				try {
					App.setRoot("post");
				} catch (IOException e) {

					alertAcount2();
				}
			}
		}
	}

	@FXML
	void alertAcount() {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Usuario no encontrado");
		al.setTitle("CREAR NUEVO USUARIO?");
		al.showAndWait();
	}

	@FXML
	void alertAcount2() {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("El usuario introducido no existe");
		al.setTitle("Usuario no encontrado");
		al.showAndWait();
	}

	public static String encrypt(String strToEncrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			
		}
		return null;
	}
}