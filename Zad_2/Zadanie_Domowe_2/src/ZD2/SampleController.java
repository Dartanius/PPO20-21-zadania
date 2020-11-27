package ZD2;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SampleController {

	@FXML Button ButtonRo;
	private Scene s;
	private BorderPane root1;
	

	@FXML Button Butt2;

	@FXML public void ChangeRoot() {
		getS().setRoot(getRoot1());
	}

	public BorderPane getRoot1() {
		return root1;
	}

	public void setRoot1(BorderPane root1) {
		this.root1 = root1;
	}

	public Scene getS() {
		return s;
	}

	public void setS(Scene s) {
		this.s = s;
	}


	
}
