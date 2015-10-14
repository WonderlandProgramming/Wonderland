package dev.wonderland.gfx.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String, BufferedImage> imageList;
	
	public static BufferedImage loadImage(String path){
		if(imageList == null){
			imageList = new HashMap<>();
		}
		BufferedImage toReturn = imageList.get(path);
		if(toReturn == null){
			try {
				toReturn = ImageIO.read(new File("./" + path));
				imageList.put(path, toReturn);
				return toReturn;
			} catch (Exception e) {
				System.out.println("Image not found: " + path);
			}
			return null;
		}
		else{
			return toReturn;
		}
	}
}
