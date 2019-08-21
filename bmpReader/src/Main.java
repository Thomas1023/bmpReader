import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Main {
public static void main(String[] args) throws IOException {
Texture map = new Texture("test.bmp");
//map.printRGBAData();
//System.err.println("\n"+map.getPixAt(0.5f,0.5f)[0]+","+map.getPixAt(0.5f,0.5f)[1]+","+map.getPixAt(0.5f,0.5f)[2]+","+map.getPixAt(0.5f,0.5f)[3]);
map.load("10x10.bmp");
map.load("yee.bmp");
map.reload();

//map.printRGBAData();


}


}
