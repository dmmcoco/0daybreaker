package application.view;

import java.io.File;
import java.io.IOException;

import application.About;
import application.AccountManager;
import application.FileUtils;
import application.Main;
import application.Collect_process;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerAll {
	@FXML
	public MenuItem about;
	public MenuItem manageAccount;
	public MenuItem export;
	public AnchorPane init(Stage primaryStage) throws Exception{
		FXMLLoader loaderMain=new FXMLLoader();
		loaderMain.setLocation(Main.class.getResource("view/Main.fxml"));
		AnchorPane main=(AnchorPane)loaderMain.load();
		ControllerCollect controllerMain=loaderMain.getController();
		controllerMain.init();
		
		
		about.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				try {
					new About().init();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		manageAccount.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				try {
					new AccountManager().init();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		/*start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				Collect_process process=new Collect_process(controllerMain);
			}
		});*/
		export.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				File exportFile=new FileChooser().showSaveDialog(primaryStage);
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<Collect_process.data.size();i++){
					sb.append(Collect_process.data.get(i).getUrl()+System.getProperty("line.separator"));
				}
				try {
					FileUtils.createNewFile(exportFile.getPath(),sb.toString());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		//new Process(controllerMain);//////del
		return main;
	}
}
