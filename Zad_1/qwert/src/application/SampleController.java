package application;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.PasswordField;

public class SampleController {

	@FXML ToggleButton ToggleButton1;
	@FXML TextArea tExt1;
	@FXML ColorPicker ColorChooser1;
	@FXML ProgressBar Cdbar;
	@FXML Slider slide;
	@FXML PasswordField password;
	@FXML public void changeC() {
		tExt1.setStyle("-fx-control-inner-background:"+ColorChooser1.getValue().toString().substring(2));
	}
	@FXML public void changeWhite() {
		tExt1.setStyle("-fx-control-inner-background: White");
	}
	@FXML public void OpenWindow() {
	
        StackPane secondaryLayout = new StackPane();
        
		
		Scene S = new Scene(secondaryLayout,230,100);
		Stage newWindow = new Stage();
		newWindow.setTitle("sekretne okienko");
		newWindow.setScene(S);
		
		newWindow.setX(200);
        newWindow.setY(100);

        newWindow.show();
		
	}
	@FXML public void Write() {
		tExt1.setText("Jest okienko ukyte, trzba wpisac cokolwiek do pola po prawej  Do tego, prosze zmienić kolor i najechać myszką na okienko");
	}
	
	@FXML public void slider() {
		Cdbar.setProgress(slide.getValue()/100);
            
	}
	
	

}
