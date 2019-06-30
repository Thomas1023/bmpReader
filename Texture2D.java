import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Texture2D {
int width;
int height;
int bytesPerPixel;
byte[] pixels;
public byte[] bmpToByte (String bmpPath){
	
ArrayList<Byte> ListData= new ArrayList<Byte>();
	
	BufferedImage img = null;
	try {
		img = ImageIO.read(new File(bmpPath));
	} catch (IOException e) {
		e.printStackTrace();
	}
	final int H=img.getTileHeight();
	final int W=img.getTileWidth();
	width=H;
	height=W;
	byte[][][] pic = new byte[W][H][3];
	byte[][] red = new byte[W][H];
	byte[][] green = new byte[W][H];
	byte[][] blue = new byte[W][H];
	//trash is alpha layer
	byte[][] TRASH = new byte[W][H];
	for(int x=0; x<W; x++){
	  for(int y=0; y<H; y++){
	     int color = img.getRGB(x,y);
	     //
	     TRASH[x][y] = (byte)(color>>24);
	     red[x][y] = (byte)(color>>16);
	     green[x][y] = (byte)(color>>8);
	     blue[x][y] = (byte)(color);
	  }
	}
	//converts 3d array to 3 2d arrays
	System.out.println(Byte.toUnsignedInt(red[0][0]));
	for (int i = 0; i < TRASH.length; i++) {
		for (int j = 0; j < TRASH.length; j++) {
			
				pic[j][i][0]=red[i][j];
				pic[j][i][1]=green[i][j];
				pic[j][i][2]=blue[i][j];
				pic[j][i][3]=TRASH[i][j];
				
		}
	} 
	//format bit value 1-255 and point [x],[y]
	pixels = new byte[W*H*4];
	//three 2d arrays into 1 1D array
	
	int x=0;
	int y=0;
	for (int i = 0; i < pixels.length; i++) {
		pixels[i]=red[x][y];
		pixels[i+1]=green[x][y];
		pixels[i+2]=blue[x][y];
		pixels[i+3]=TRASH[x][y];
		x++;
		if(x>W) {
			x=0;
			y++;
		}
	}
	for (byte bs : pixels) {
		System.out.println(bs);
	}
	//graphical representation
//	for (byte[][] bs : pic) {
//		for (byte[] bs2 : bs) {
//			System.out.print("(");
//			for (byte bs3 : bs2) {
//				System.out.print(Byte.toUnsignedInt(bs3)+",");
//			}
//			System.out.print(")");
//		}
//		System.out.print("\n");
//	}
	
	
	return pixels;
	
}
}
