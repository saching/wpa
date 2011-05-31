package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class CommonUtils {

	/**
	 * method fetch profile image from facebook and save it in to local directory
	 * @param path 
	 * 			facebook url
	 * @return
	 * 			new created filename (without path)
	 */
	public static String saveFBProfileImage(String path) {
		String filename = null;
		try {
			System.out.println("CommonUtils.saveFBProfileImage()" + path);
			Random random = new Random();
			String destination = "C:/LocalDisk/JavaDevelopement/play/projects/wpa/public/uploads/profile_photo/";
			filename = "profile_" + random.nextInt() + ".jpg";
			URL url = new URL(path);
			BufferedImage bi = ImageIO.read(url);
			int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
			BufferedImage resizeImageJpg = ImageUtil.resizeImageWithHint(bi, type, bi.getWidth(), bi.getHeight());
			File f = new File(destination + filename);
			ImageIO.write(resizeImageJpg,"jpg", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
	}

	/**
	 * password encryption method
	 */
	public static String encryptPassword(String data) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(data.getBytes("UTF-8"));
            byte[] digestBytes = messageDigest.digest();
            
            String hex = null;
            for (int i = 0; i < digestBytes.length; i++) {
                hex = Integer.toHexString(0xFF & digestBytes[i]);
                if (hex.length() < 2) 
                    sb.append("0");
                sb.append(hex);
                }
            }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            }
       return new String(sb);
    }
	
	/**
	 * String to date convertor
	 * @throws ParseException 
	 */
	public static Date convertToDate(String date) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		return dateFormat.parse(date);
	}
	
	
}
