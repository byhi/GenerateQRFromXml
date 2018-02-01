package gen;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
 
import javax.imageio.ImageIO;
 


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class GeneratorFromXML {

	private String myCodeText;
	private String filePath;
	private String fileType;
	private File myFile;
	private int size = 75;
	private ArrayList<String> data = new ArrayList<>();
	
	public GeneratorFromXML(String imgPath, ArrayList<String> arrayList) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy-hh-mm-ss");
		this.myCodeText =  sdf.format(new Date()) + "QR";
		this.filePath = "C:/Test/";
		this.size = 75;
		this.fileType = "png";
		this.data = arrayList;
		//File myFile = new File(filePath);    Date date = new Date();

		
	}
	
	public void makeImages(){
		int index = 0;
		for (String string : data) {
			
			myFile = new File(filePath+myCodeText+index+"."+fileType);
		try {
			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(string, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
 
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, myFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		index++;
		}
		System.out.println("\n\nYou have successfully created QR Code.");
	}

}
