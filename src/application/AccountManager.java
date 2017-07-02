package application;

import application.view.ControllerAbout;
import application.view.ControllerAccountManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AccountManager extends Stage{
	public void init() throws Exception{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AccountManager.fxml"));
		AnchorPane am=(AnchorPane)loader.load();
		ControllerAccountManager controller=loader.getController();
		//controller.init();
		this.setTitle("API账号管理");
		this.setScene(new Scene(am));
		this.show();
	}
}
