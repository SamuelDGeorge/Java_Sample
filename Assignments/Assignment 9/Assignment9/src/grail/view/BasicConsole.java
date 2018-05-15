package grail.view;

import java.beans.PropertyChangeEvent;

import grail.avatar.BasicAvatar;
import grail.draw.objects.BasicBridgeMoat;
import grail.draw.objects.PlatformOvalShape;
import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"ConsoleSceneView"})
public class BasicConsole implements Console{
	BridgeScene scene;
	
	public BasicConsole(BridgeScene listenerScene) {
		setScene(listenerScene);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Source: " + evt.getSource() + " Property To Change: " + evt.getPropertyName() + " Old Value: " + evt.getOldValue() + " New Value: " + evt.getNewValue());
	
	}

	
	public BridgeScene getScene() {
		return this.scene;
	}

	
	public void setScene(BridgeScene toListen) {
		this.scene = toListen;
	
		BasicAvatar.addBodyListener(this.scene.getArthur(),this);
		
		BasicAvatar.addBodyListener(this.scene.getGalahad(),this);
		
		BasicAvatar.addBodyListener(this.scene.getGuard(), this);
		
		BasicAvatar.addBodyListener(this.scene.getLancelot(), this);
		
		BasicAvatar.addBodyListener(this.scene.getRobin(), this);
		
		this.scene.getGorge().addPropertyChangeListener(this);
		BasicBridgeMoat.addListenerToSubShapes(this.scene.getGorge(), this);
		
		
		this.scene.getGuardArea().addPropertyChangeListener(this);
		PlatformOvalShape.addListenersToSubshapes(this.scene.getGuardArea(), this);
		
		this.scene.getKnightArea().addPropertyChangeListener(this);
		PlatformOvalShape.addListenersToSubshapes(this.scene.getKnightArea(), this);
		
	}


}
