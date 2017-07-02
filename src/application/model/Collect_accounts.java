package application.model;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import application.FileUtils;

public class Collect_accounts {
	public String zoomeye_email="",zoomeye_password="";
	public void readConfig(){
		try{
			JSONObject accounts=new JSONObject(FileUtils.readFile("accounts.conf"));
			JSONObject zoomeye=accounts.getJSONObject("zoomeye");
			this.zoomeye_email=zoomeye.getString("email");
			this.zoomeye_password=zoomeye.getString("password");
		}catch(Exception e){
			e.printStackTrace();
			writeConfig();
		}
		
	}
	public void writeConfig(){
		JSONObject accounts=new JSONObject();
		JSONObject zoomeye=new JSONObject();
		zoomeye.put("email",this.zoomeye_email);
		zoomeye.put("password",this.zoomeye_password);
		accounts.put("zoomeye", zoomeye);
		try {
			FileUtils.createNewFile("accounts.conf",accounts.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
