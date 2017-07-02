package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.view.ControllerCollect;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;

public class Collect_baidu implements Runnable{
	String keyword;
	public Collect_baidu(String keyword) {
		// TODO 自动生成的构造函数存根
		this.keyword=keyword;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		//Process.setStatus("百度引擎开始工作");
		//while(true){
			String source=Requests.get("http://www.baidu.com/s?wd=phpstudy&pn=0&rn=50").send().readToText();
			Pattern p=Pattern.compile("www\\.baidu\\.com/link\\?url=(.*?)\\\"");
			Matcher m=p.matcher(source);
			while(m.find()){
				System.out.println(source.substring(m.start()+23, m.end()-1));
				//String url="https://www.baidu.com/link?url="+source.substring(m.start()+23, m.end()-1);
				String url="http://"+source.substring(m.start(), m.end()-1);
				System.out.println(url);
				try{
					RawResponse rr=Requests.get("http://www.baidu.com/link?"
							+ "url=QizAKn6n08uJWlumpPPpgsGethctSgErQ6UyJbi_OCEBsIrQuiFbwwbB0UR0zoRbjgN23d2-Oi1fFYNDBxm2zLe4LHr4Uy6erna4zsWZmJ3"
							).send();
					System.out.println(rr.getStatusCode());
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		//}
	}

}
