package application;

import java.io.IOException;

import application.view.ControllerAbout;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class About extends Stage{
	public void init() throws Exception{
		FXMLLoader loaderAbout=new FXMLLoader();
		loaderAbout.setLocation(Main.class.getResource("view/About.fxml"));
		BorderPane about=(BorderPane)loaderAbout.load();
		ControllerAbout controllerAbout=loaderAbout.getController();
		controllerAbout.init();
		this.setTitle("关于");
		this.setScene(new Scene(about));
		this.show();
	}
	//public init
}
