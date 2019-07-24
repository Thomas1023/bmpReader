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
	System.out.println("1");
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
	System.out.println("2");

	//puts all layers into 1 3d array
	//Key for pic:
	//pic[x location of pixel][y loc of pixel][layer number(0=r,1=green,2=blue,3=alpha)]
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
			
				pic[i][j][0]=red[i][j];
				pic[i][j][1]=green[i][j];
				pic[i][j][2]=blue[i][j];
				pic[i][j][3]=alpha[i][j];
				
		}
	} 
	System.out.println("3");

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
		
	System.out.println("4");

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
	byte[][] visMap = new byte[width][height];
	
	for (int i = 0; i < width;i++) {
		for (int j = 0; j < height; j++) {
			visMap[i][j]=(byte) 255;
			System.out.print(i+"/"+j+" ");
		}
		System.out.println();
	}
	visMap[x1][y1]=111;
	visMap[x2][y2]=111;
	visMap[x3][y3]=111;
	

	double slope12=(y1-y2)/(x1-x2);
	
	
	double slope23=(y2-y3)/(x2-x3);
	double slope13=(y3-y1)/(x3-x1);
	System.out.println(slope13);
	for (int i = 0; i < width; i++) {
		if((int) (i+slope13*i)>0&&(int) (i+slope13*i)<width) {
		visMap[i][(int) (i+slope13*i)]=111;
		}
	}
	for (int i = 0; i < height; i++) {
		if((int) (i+slope13*i)>0&&(int) (i+slope13*i)<width) {
		visMap[(int) (i+slope13*i)][i]=111;
		}
	}
//	for (int i = 0; i < width; i++) {
//		if((int) (i+slope12*i)>0&&(int) (i+slope12*i)<width)
//		visMap[i][(int) (i+slope12*i)]=111;
//	}
	//visual
		for (int i = 0; i < visMap.length; i++) {
			for (int j = 0; j < visMap.length; j++) {	
				if(Byte.toUnsignedInt(visMap[i][j])==255) {
					System.out.print("--- ");
				}else {
					System.out.print("### ");
				}
				//System.out.print(Byte.toUnsignedInt(visMap[i][j])+" ");
			}
			System.out.println("\n");
		}
		//vis
	return turn;
}

 }
