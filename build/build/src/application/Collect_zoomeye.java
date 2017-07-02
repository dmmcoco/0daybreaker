package application;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import application.model.Collect_item;
import application.view.ControllerCollect;
import javafx.application.Platform;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;

public class Collect_zoomeye implements Runnable{
	String keyword;
	int from,to;
	Collect_process process;
	boolean if_web;
	public Collect_zoomeye(String keyword,int from,int to,boolean if_web, Collect_process process) {
		// TODO 自动生成的构造函数存根
		this.keyword=URLEncoder.encode(keyword);
		this.from=from;
		this.to=to;
		this.process=process;
		this.if_web=if_web;
	}
	public void noticeExit(String apiMessage){
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				process.controllerMain.appendStatus("[*]ZoomEye引擎停止工作，信息："+apiMessage);
			}
		});
	}
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				process.controllerMain.appendStatus("[*]ZoomEye引擎开始工作");
			}
		});
		String exitMessage="已完成指定页数";
		String source=Requests.post("https://api.zoomeye.org/user/login").body("{\"username\": \"daybreaker@yopmail.com\",\"password\": \"daybreaker2017\"}").send().readToText();
		JSONObject json=new JSONObject(source);
		String access_token=json.getString("access_token");
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", "JWT "+access_token);
		String type="host";
		if(if_web){
			type="web";
		}
		for(int page=from;page<=to;page++){
			source=Requests.get("https://api.zoomeye.org/"+type+"/search?query="+keyword+"&page="+page).headers(headers).send().readToText();
			System.out.println(source);
			JSONArray base=new JSONArray();
			try{
				base=new JSONObject(source).getJSONArray("matches");
			}catch(Exception e){
				//noticeExit(new JSONObject(source).getString("message"));
				exitMessage=new JSONObject(source).getString("message");
				break;
			}
			for(int i=0;i<base.length();i++){
				String site="http://"+base.getJSONObject(i).getString("site");
				//String site=base.getJSONObject(i).getJSONArray("webapp").getJSONObject(0).getString("url");
				String title=base.getJSONObject(i).getString("title");
				Collect_process.data.add(new Collect_item(Collect_process.nextId(), title, "ZoomEye", site));
			}
			process.refreshTotal();
			process.controllerMain.appendStatus("[*]ZoomEye引擎已采集页码 "+page);
		}
		noticeExit(exitMessage);
	}
	

}
