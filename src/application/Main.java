package application;
	
import java.io.IOException;

import application.model.Collect_item;
import application.view.ControllerAbout;
import application.view.ControllerAll;
import application.view.ControllerCollect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application{
	//public static ObservableList<Item> data=FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loaderAll=new FXMLLoader();
		
		loaderAll.setLocation(Main.class.getResource("view/All.fxml"));
		BorderPane all=(BorderPane)loaderAll.load();

		ControllerAll controllerAll=loaderAll.getController();
		AnchorPane main=controllerAll.init(primaryStage);
		
		all.setCenter(main);
		
		Scene scene=new Scene(all);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
		});
		primaryStage.setTitle("0daybreaker 多功能批量渗透套件");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
