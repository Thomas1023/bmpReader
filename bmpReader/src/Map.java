import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

 class Map {
 String file;
 boolean Alpha;
 byte turn[];
 int height;
 int width;
Map(String path) throws IOException{
	//ArrayList<Byte> ListData= new ArrayList<Byte>();
	BufferedImage img = ImageIO.read(new File(path));
	final int w = img.getWidth();
	final int h = img.getHeight();
	width=w;
	height=h;
	System.out.println(path+" has been loaded with a size of "+w+"/"+h);
	byte[][][] pic = new byte[w][h][4];
	 turn = new byte[w*h*4];
	//creates layers separately
	byte[][] red = new byte[w][h];
	byte[][] green = new byte[w][h];
	byte[][] blue = new byte[w][h];
	byte[][] alpha = new byte[w][h];
	//scans image rgba and isolates layers
	for(int x=0; x<w; x++){
	  for(int y=0; y<h; y++){
	     int color = img.getRGB(x,y);
	     alpha[x][y] = (byte)(color>>24);
	     red[x][y] = (byte)(color>>16);
	     green[x][y] = (byte)(color>>8);
	     blue[x][y] = (byte)(color);
	  }
	}
	
	//puts all layers into 1 3d array
	//Key for pic:
	//pic[x location of pixel][y loc of pixel][layer number(0=r,1=green,2=blue,3=alpha)]
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
			
				pic[j][i][0]=red[i][j];
				pic[j][i][1]=green[i][j];
				pic[j][i][2]=blue[i][j];
				pic[j][i][3]=alpha[i][j];
				
		}
	} 
	//condenses 3d array into 1d array with 4x the size and organized linearly
	int p=0;
	for (byte[][] bs : pic) {
		for (byte[] bs2 : bs) {
			for (byte bs3 : bs2) {
				turn[p]=(byte)Byte.toUnsignedInt(bs3);
				p++;
			}
		}
	}
		
	
}
public void textDisplay() {
	for (int i = 0; i < turn.length; i++) {
		if(i%4==0) {
			System.out.print("|");

		}
		if(i%(width*4)==0) {
			System.out.print("\n");
		}
		System.out.print(Byte.toUnsignedInt(turn[i])+",");
	}
}
public byte[] cropTri(int x1,int y1,int x2,int y2,int x3,int y3){
	return turn;
	
}

 }
