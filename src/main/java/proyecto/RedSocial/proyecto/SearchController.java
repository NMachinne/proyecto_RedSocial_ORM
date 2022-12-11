package proyecto.RedSocial.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchController extends AController  implements Initializable{

	@FXML
	private TextField texto;

	@FXML
	private TableView<Object> tabla;

	@FXML
	private TableColumn<Object, String> c1;

	/**
	 * Busca por nombre de usuario
	 * 
	 * @param event Objeto ActionEvent
	 */
	@FXML
	void bbuscar(ActionEvent event) {
		ObservableList<Object> list = FXCollections.observableArrayList();
		Collection<User> users = new UserDAO().getAll();
		List<User> users2 = new ArrayList<User>();
		for (User u : users) {
			Pattern pattern = Pattern.compile(texto.getText(), Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(u.getNombre());
			if (matcher.find()) {
				users2.add(u);
			}
		}
		list.addAll(users2);
		c1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tabla.setItems(list);
	}

	/**
	 * Obtiene el nombre de usuario
	 * 
	 * @param event Objeto MouseEvent
	 */
	@FXML
	void tclick(MouseEvent event) {
		String r = null;
		try {
			r = event.getPickResult().getIntersectedNode().toString().split("TableColumn")[1];
			r = event.getPickResult().getIntersectedNode().toString().split("'")[0];
			r = event.getPickResult().getIntersectedNode().toString().split("'")[1];
		} catch (Exception e) {
			r = null;
		}

		if (r != null && !r.toString().equals("null")) {
			AController.user = (User) new UserDAO().getByName(new User(-1, r, "", "")).toArray()[0];
			texto.getScene().getWindow().hide();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bbuscar(null);
	}
}
