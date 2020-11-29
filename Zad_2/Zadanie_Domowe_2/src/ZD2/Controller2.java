package ZD2;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Controller2 {
	
	private Scene s;
	private BorderPane root2;
	

	@FXML Button Butt2;

	@FXML public void ChangeRoot() {
		s.setRoot(root2);
	}

	public BorderPane getRoot2() {
		return root2;
	}

	public Scene getS() {
		return s;
	}

	public void setS(Scene s) {
		this.s = s;
	}
	
	public void setRoot2(BorderPane root2) {
		this.root2 = root2;
	}


}
