package application.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.sun.prism.paint.Paint;

import application.Main;
import application.Collect_process;
import application.FileUtils;
import application.model.Collect_accounts;
import application.model.Collect_item;
import javafx.application.Platform;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
    public Button startBtn,endBtn,export,removeRepeated;
    Collect_process process;
    //public Label statusBar,statusTotal;
	public void init(){
		//Process.data.add(new Item(1,"phpstudy","zoomeye", "1.1.1.1"));
		title.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("title"));
		engine.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("engine"));
		url.setCellValueFactory(
                new PropertyValueFactory<Collect_item, String>("url"));
		id.setCellValueFactory(
                new PropertyValueFactory<Collect_item, Integer>("id"));
		tv.setItems(Collect_process.data);
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				process=new Collect_process(ControllerCollect.this);
			}
		});
		endBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				if(process!=null){
					process.stopAll();
				}
			}
		});
		export.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				FileChooser fileChooser=new FileChooser();
				fileChooser.setTitle("选择导出的位置");
				fileChooser.getExtensionFilters().addAll(
					      new ExtensionFilter("文本文档", "*.txt"));
				File exportFile=fileChooser.showSaveDialog(null);
				if(exportFile==null){
					appendStatus("[!]导出被取消");
					return;
				}
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<Collect_process.data.size();i++){
					sb.append(Collect_process.data.get(i).getUrl()+System.getProperty("line.separator"));
				}
				try {
					FileUtils.createNewFile(exportFile.getPath(),sb.toString());
					appendStatus("[*]导出完毕");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		removeRepeated.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				for(int i=0;i<Collect_process.data.size()-1;i++){
					for(int j=Collect_process.data.size()-1;j>i;j--){
						if(Collect_process.data.get(j).url.getValue().equals(Collect_process.data.get(i).url.getValue())){
							Collect_process.data.remove(j);
						}
					}
				}
				appendStatus("[*]已移除重复项");
			}
		});
		common_engine.setText("phpstudy 探针 2014");
		zoomeye_text.setText("title:\"phpstudy 探针 2014\"");
		
	}
	public void appendStatus(String text){
		statusBar.appendText(text+"\r\n");
	}
	
}
