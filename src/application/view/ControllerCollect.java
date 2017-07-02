package application.view;

import java.io.IOException;
import java.io.InputStream;

import com.sun.prism.paint.Paint;

import application.Main;
import application.Collect_process;
import application.model.Collect_accounts;
import application.model.Collect_item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerCollect {
	@FXML
	public TableView<Collect_item> tv;
	@FXML
	public TableColumn<Collect_item, String> title;
    @FXML
    public TableColumn<Collect_item, String> url;
    @FXML
    public TableColumn<Collect_item, String> engine;
    public TableColumn<Collect_item, Integer> id;
    
    public CheckBox baidu,bing,sogou,zoomeye,shodan,zoom_web;
    public TextField common_engine,zoomeye_text,zoomeye_from,zoomeye_to;
    public TextArea statusBar;
    public Button startBtn,endBtn;
    public Collect_accounts accounts;
    //public Label statusBar,statusTotal;
	public void init(){
		//Process.data.add(new Item(1,"phpstudy","zoomeye", "1.1.1.1"));
		accounts=new Collect_accounts();
		title.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("title"));
		engine.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("engine"));
		url.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("url"));
		id.setCellValueFactory(
                new PropertyValueFactory<Collect_item, Integer>("id"));
		tv.setItems(Collect_process.data);
		accounts.readConfig();
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				Collect_process process=new Collect_process(ControllerCollect.this);
			}
		});
		common_engine.setText("phpstudy 探针 2014");
		zoomeye_text.setText("title:\"phpstudy 探针 2014\"");
		
	}
	public void appendStatus(String text){
		statusBar.appendText(text+"\r\n");
	}
	/*public void setStatus(String word){
		statusBar.setText(word);
	}
	public void setTotal(String word){
		statusTotal.setText(word);
	}*/
}
