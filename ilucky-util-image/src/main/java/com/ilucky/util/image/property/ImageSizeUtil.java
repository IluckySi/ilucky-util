package com.ilucky.util.image.property;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

/**
 *  获取图片属性
 * @author IluckySi
 * @since 20170330
 */
public class ImageSizeUtil {

	/**
	 *  获取本地图片的属性
	 * @param filePath
	 * @throws IOException
	 */
	public static int[] getImageSizeFromFilePath(String filePath) throws IOException{
        File picture = new File(filePath);
        BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture)); 
        int[] size = new int[2];
        size[0] = sourceImg.getWidth();
        size[1] = sourceImg.getHeight();
        return size;
	}
	
	/**
	 *  获取服务器上图片的属性
	 * @param imageUrl
	 * @throws IOException
	 */
	public static int[] getImageSizeFromUrl(String imageUrl) throws IOException{
		URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedImage image = ImageIO.read(connection.getInputStream());  
		int[] size = new int[2];
		size[0] = image.getWidth();
		size[1] = image.getHeight();
		return size;
	}
}
