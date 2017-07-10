package application;

import application.model.Collect_accounts;
import application.model.Collect_item;
import application.view.ControllerCollect;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.dongliu.requests.Requests;

public class Collect_process{
	public ControllerCollect controllerMain;
	public static int id=0;
	public static ObservableList<Collect_item> data=FXCollections.observableArrayList();
	public static Collect_accounts accounts=new Collect_accounts();
	public static boolean stopFlag=false;
	public Collect_process(ControllerCollect controllerMain) {
		stopFlag=false;
		this.controllerMain=controllerMain;
		
		//data.add(new Collect_item(222, "xx", "ZoomEye", "www.ddhc.cn"));
		//System.out.println(super.common_engine.getText());
		String common_keyword=controllerMain.common_engine.getText();
		String zoomeye_keyword=controllerMain.zoomeye_text.getText();
		
		accounts.readConfig();
		
		/*if(controllerMain.baidu.isSelected()){
			Thread baidu=new Thread(new Baidu(common_keyword));
			baidu.start();
		}*/
		if(controllerMain.bing.isSelected()){
			Thread bing=new Thread(new Collect_bing(common_keyword,Collect_process.this));
			bing.start();
			//controllerMain.setStatus("必应引擎开始工作");
		}
		if(controllerMain.sogou.isSelected()){
			Thread sogou=new Thread(new Collect_sogou(common_keyword));
			sogou.start();
			//controllerMain.setStatus("搜狗引擎开始工作");
		}
		if(controllerMain.zoomeye.isSelected()){
			int from=0;
			int to=10;
			try{
				from=Integer.parseInt(controllerMain.zoomeye_from.getText());
				to=Integer.parseInt(controllerMain.zoomeye_to.getText());
			}catch (Exception e) {
				// TODO: handle exception
				System.err.println(e);
			}
			Collect_zoomeye zoom=new Collect_zoomeye(zoomeye_keyword, from, to,controllerMain.zoom_web.isSelected(),Collect_process.this);
			//zoom.init();
			Thread zoomeye=new Thread(zoom);
			
			zoomeye.start();
			//zoom.start();
			
		}
	}
	/*public void setStatus(String word){
		controllerMain.statusBar.setText(word);
	}*/
	public void stopAll(){
		stopFlag=true;
	}
	public synchronized static int nextId(){
		return ++id;
	}
	public void refreshTotal() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				//controllerMain.setTotal("采集总数："+id);
			}
		});
		
	}

}
