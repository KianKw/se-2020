import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class MyImageProcessor implements IImageProcessor {
    // Define the color flags
    private static int gray  = 0;
    private static int red   = 1;
    private static int green = 2;
    private static int blue  = 3;

    // Converts the source image to red
    @Override
    public Image showChanelR(Image sourceImage) {
        return showColor(sourceImage, red);
    }

    // Converts the source image to green
    @Override
    public Image showChanelG(Image sourceImage) {
        return showColor(sourceImage, green);
    }

    // Converts the source image to blue
    @Override
    public Image showChanelB(Image sourceImage) {
        return showColor(sourceImage, blue);
    }

    // Converts the source image to gray
    @Override
    public Image showGray(Image sourceImage) {
        return showColor(sourceImage, gray);
    }
    // Input the selected color of the selected color and return the corresponding color image
    public Image showColor(Image sourceImage, int color) {
        ColorFilter filter = new ColorFilter(color);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Image img = toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), filter));
        return img;
    }
}

class ColorFilter extends RGBImageFilter {
    // Define the color flags
    private static int red   = 1;
    private static int green = 2;
    private static int blue  = 3;

    // Define the color channal 
    private static int redChannel   = 0xffff0000;
    private static int greenChannel = 0xff00ff00;
    private static int blueChannel  = 0xff0000ff;

    // Define the color 
    private static int getRed     = 0x00ff0000;
    private static int getGreen   = 0x0000ff00;
    private static int getBlue    = 0x000000ff;
    private static int getHyaline = 0xff000000;

    private int color;

    // Constructor of the class, with arguments for color
    public ColorFilter(int c) {
        color = c;
        canFilterIndexColorModel = true;
    }
    // I = 0.299 * R + 0.587 * G + 0.114 *B --- algorithm of get the Gray
    public int filterRGB(int x, int y, int rgb) {
        if (color == red) {
            return (rgb & redChannel);
        } else if (color == green) {
            return (rgb & greenChannel);
        } else if (color == blue) {
            return (rgb & blueChannel);
        } else {
            int gray = (int) (((rgb & getRed) >> 16) * 0.299 + ((rgb & getGreen) >> 8) * 0.587 + ((rgb & getBlue)) * 0.114);
            return (rgb & getHyaline) + (gray << 16) + (gray << 8) + gray;
        }
    }
}
