/**
 * 
 */
package dev.wonderworld.main;

import dev.wonderland.gfx.DisplayManager;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 10.10.2015
 * @vesion 0.1
 */
public class MainClass {
	
	public static void main(String args[]){
		
		DisplayManager.createDisplay();
		
		while(!DisplayManager.isCloseRequested()){
			
			DisplayManager.updateDisplay();

			System.out.println("End of programm!");
		}
		DisplayManager.closeDisplay();
		
	}
}
