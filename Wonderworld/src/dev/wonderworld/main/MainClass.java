/**
 * 
 */
package dev.wonderworld.main;

import dev.wonderland.entity.Entity;
import dev.wonderland.entity.components.PositionComponent;
import dev.wonderland.gfx.DisplayManager;
import dev.wonderworld.event.EVENT_BUS;
import dev.wonderworld.event.Event;
import dev.wonderworld.event.events.luaEvents.onInRangeEvent;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 10.10.2015
 * @vesion 0.1
 */
public class MainClass {
	
	public static void main(String args[]){
		
		DisplayManager.createDisplay();
		
		Entity p1 = new Entity().addComponent(new PositionComponent(50, 50));
		Entity p2 = new Entity().addComponent(new PositionComponent(50, 50));
		
		EVENT_BUS.registerEventHandler(onInRangeEvent.ID, (Event e) -> (test(e)));

		EVENT_BUS.callEvent(new onInRangeEvent("res/luaTest.lua", p1, p2));
		
		while(!DisplayManager.isCloseRequested()){
			
			DisplayManager.updateDisplay();
			System.exit(-1);
		}
		DisplayManager.closeDisplay();
		
	}
	
	public static boolean test(Event e){
		System.out.println("EVENTHANDLER = SUCCESS");
		System.out.println(((PositionComponent) ((onInRangeEvent)e).getP2().getComponent(PositionComponent.ID)));
		
		return true;
	}
}
