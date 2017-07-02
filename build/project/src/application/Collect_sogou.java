package application;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.Collect_item;
import net.dongliu.requests.Cookie;
import net.dongliu.requests.Requests;

public class Collect_sogou implements Runnable{
	String keyword;
	String page;
	int first;
	public Collect_sogou(String keyword) {
		// TODO 自动生成的构造函数存根
		this.keyword=URLEncoder.encode(keyword);
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		page="http://www.sogou.com/web?query=php";//+keyword;
		Map<String, Object> headers = new HashMap<>();
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:53.0) Gecko/20100101 Firefox/53.0");
		headers.put("Referer","http://www.sogou.com/");
		
		Map<String, Object> cookies = new HashMap<>();
		cookies.put("SNUID", "D48CAFC3B6B0E54CB00EEAAEB64C784B");
		while(true){
			Collection<Cookie> cook = Requests.head("http://www.sogou.com/web?query=").send().getCookies();
			String source=Requests.get(page).headers(headers).send().readToText();
			Pattern p=Pattern.compile("href=\\\"http([s]*)://(.*?)\\\"");
			Matcher m=p.matcher(source);
			System.out.println(source);
			while(m.find()){
				String url=source.substring(m.start(), m.end());
				System.out.println(url);
				//Process.data.add(new Item(1,"","搜狗",url));
			}
			/*
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
			}*/
			break;
		}
	}

}
