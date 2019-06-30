package bmpReader;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {
public static void main(String[] args) throws IOException {

	
}
public static byte[] ImageToByte2(Image img)
{
    using (var stream = new MemoryStream())
    {
        img.Save(stream, System.Drawing.Imaging.ImageFormat.Png);
        return stream.ToArray();
    }
}
}
//alpha(color>>24);
//red(color>>16);
//green(color>>8);
//blue(color);