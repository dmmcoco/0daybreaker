package application;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.Collect_item;
import application.view.ControllerCollect;
import javafx.application.Platform;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;

public class Collect_bing implements Runnable{
	String keyword;
	String page;
	int first;
	Collect_process process;
	public Collect_bing(String keyword,Collect_process process) {
		// TODO 自动生成的构造函数存根
		this.keyword=URLEncoder.encode(keyword);
		this.process=process;
	}
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				process.controllerMain.appendStatus("[*]必应引擎开始工作");
			}
		});
		// TODO 自动生成的方法存根
		page="http://www.bing.com/search?q="+keyword;
		while(true){
			String source=Requests.get(page).send().readToText();
			Pattern p=Pattern.compile("href=\\\"http([s]*)://(.*?)\\\"");
			Matcher m=p.matcher(source);
			while(m.find()){
				String url=source.substring(m.start()+6, m.end()-1);
				Collect_process.data.add(new Collect_item(1,"","必应",url));
			}
			p=Pattern.compile("页\\\" href=\\\"(.*?)\\\"");
			m=p.matcher(source);
			while(true){
				m.find();
				String nextPage="";
				try{
					nextPage=source.substring(m.start(),m.end());
				}catch(Exception e){
					source=Requests.get(page).send().readToText();
					p=Pattern.compile("页\\\" href=\\\"(.*?)\\\"");
					m=p.matcher(source);
					continue;
				}
				Pattern p2=Pattern.compile(";first=(.*?)&");
				
				Matcher m2=p2.matcher(nextPage);
				m2.find();
				int newFirst=Integer.parseInt(nextPage.substring(m2.start()+7,m2.end()-1));
				if(first < newFirst){
					first=newFirst;
					page="http://www.bing.com"+nextPage.substring(9,nextPage.length()-1).replaceAll("&amp;","&");
					System.out.println(page);
					break;
				}
			}
		}
	}

}
