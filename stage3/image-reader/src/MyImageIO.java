import imagereader.IImageIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class MyImageIO implements IImageIO {
    // 处理的图片
    private Image img; 
    private static int dWordSize = 4;
    private static int colorBits = 3;

    @Override
    public Image myRead(String filePath) throws IOException {
        // 创建一个 File 结构体
        File file = new File(filePath);

        // 创建一个输入流，读取二进制数据
        FileInputStream fileInStream = new FileInputStream(file);

        // 创建数组bmpHead和bmpInfo来存储消息
        byte bmpHead[] = new byte[14];
        byte bmpInfo[] = new byte[40];

        fileInStream.read(bmpHead, 0, 14);
        fileInStream.read(bmpInfo, 0, 40);

        // 以字节为单位获取bmp文件的全部大小
        @SuppressWarnings("unused")
        int fileSize = bytesToInt(bmpHead, 2, dWordSize);


        // 获取bmpInfo并以像素为单位存储bmp的大小，每个像素需要 3 字节存储
        int imageSize = bytesToInt(bmpInfo, 20, dWordSize);

        // 获取以像素为单位的bmp宽度
        int imageWidth = bytesToInt(bmpInfo, 4, dWordSize);

        // 获取以像素为单位的bmp高度
        int imageHeight = bytesToInt(bmpInfo, 8, dWordSize);

        // 获取bmp图像的字节数并判断bmp是否是一幅24位彩色像素的图像
        int bitsForPixel = bytesToInt(bmpInfo, 14, 2);

        // 如果图像是24位的，则每个像素的字节不是4的倍数，所以需要拓展
        if (bitsForPixel == 24) {
            // 计算每行的空字节数
            int numEmptyByteOfRow = imageSize / imageHeight - colorBits * imageWidth;

            // 如果图片可以轻易处理，就不需要拓展
            if (numEmptyByteOfRow == dWordSize) {
                numEmptyByteOfRow = 0;
            }

            // 获取像素信息，按照从上到下、从左到右的顺序保存到 pixelArray 中
            int pixel = 0;
            int pixelArray[] = new int[imageWidth * imageHeight];
            byte pixelBytes[] = new byte[imageSize];
            fileInStream.read(pixelBytes, 0, imageSize);

            for (int row = imageHeight - 1; row >= 0; row--) {
                for (int col = 0; col < imageWidth; col++) {
                    int pixelLoc = imageWidth * row + col;
                    pixelArray[pixelLoc] = bytesToInt(pixelBytes, pixel, colorBits);
                    pixelArray[pixelLoc] |= (0xff << 24);
                    pixel += colorBits;
                }
                // 跳过空字节
                pixel += numEmptyByteOfRow;
            }

            img = Toolkit.getDefaultToolkit().createImage(
                (ImageProducer) new MemoryImageSource(imageWidth, imageHeight,
                                                      pixelArray, 0, imageWidth));
        }

        // 关闭文件流
        fileInStream.close();
        return img;
    }

    @Override
    public Image myWrite(Image img, String filepath) {
        try {
            File imgFile = new File(filepath);
            BufferedImage buffer = new BufferedImage(img.getWidth(null),
                                                     img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D graph = buffer.createGraphics();
            graph.drawImage(img, 0, 0, null);
            graph.dispose();
            ImageIO.write(buffer, "bmp", imgFile);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    // 将字节数据转化为整数
    private int bytesToInt(byte[] bytes, int offset, int length) {
        int result = 0;
        for (int i = 0; i < length; i++) {
            result |= (bytes[offset + i] & 0xff) << (i * 8);
        }
        return result;
    }
}
