
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {
public static void main(String[] args) throws IOException {
	ArrayList<Byte> ListData= new ArrayList<Byte>();
	byte[][][] pic = new byte[3][3][3];
	BufferedImage img = ImageIO.read(new File("test.bmp"));
	byte[][] red = new byte[3][3];
	byte[][] green = new byte[3][3];
	byte[][] blue = new byte[3][3];
	byte[][] TRASH = new byte[3][3];
	for(int x=0; x<3; x++){
	  for(int y=0; y<3; y++){
	     int color = img.getRGB(x,y);
	     TRASH[x][y] = (byte)(color>>24);
	     red[x][y] = (byte)(color>>16);
	     green[x][y] = (byte)(color>>8);
	     blue[x][y] = (byte)(color);
	  }
	}
	System.out.println(Byte.toUnsignedInt(red[0][0]));
	for (int i = 0; i < TRASH.length; i++) {
		for (int j = 0; j < TRASH.length; j++) {
			
				pic[j][i][0]=red[i][j];
				pic[j][i][1]=green[i][j];
				pic[j][i][2]=blue[i][j];
				
		}
	} 
	for (byte[][] bs : pic) {
		for (byte[] bs2 : bs) {
			System.out.print("(");
			for (byte bs3 : bs2) {
				System.out.print(Byte.toUnsignedInt(bs3)+",");
			}
			System.out.print(")");
		}
		System.out.print("\n");
	}
	
	
}
}
//alpha(color>>24);
//red(color>>16);
//green(color>>8);
//blue(color);
