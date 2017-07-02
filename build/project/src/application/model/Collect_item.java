package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collect_item {
	public StringProperty engine;
	public StringProperty title;
	public StringProperty url;
	public IntegerProperty id;
    public Collect_item(int id,String title,String engine,String url){
    	this.engine=new SimpleStringProperty(engine);
    	this.title=new SimpleStringProperty(title);
    	this.url=new SimpleStringProperty(url);
    	this.id=new SimpleIntegerProperty(id);
    }
    public String getEngine() {
        return engine.get();
    }
    public String getTitle() {
        return title.get();
    }
    public String getUrl() {
        return url.get();
    }
    public Integer getId() {
        return id.get();
    }

}
