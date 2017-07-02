package application.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerAbout {
	public ImageView image;
	public void init(){
		image.setImage(new Image("application/view/about.png"));
	}
}
