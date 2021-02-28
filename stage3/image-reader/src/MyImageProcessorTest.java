import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyImageProcessorTest extends TestCase {
    private MyImageIOTest ioTest;
    private MyImageProcessor myProcessor;
    private String filePath = "../bmptest/";
    private Image exampleImg1; 
    private Image exampleImg2;

    public static void main(String args[]) {
        junit.textui.TestRunner.run(MyImageProcessorTest.class);
    }

    @Before
    public void setUp() throws Exception {
        ioTest = new MyImageIOTest();
        myProcessor = new MyImageProcessor();
        exampleImg1 = ImageIO.read(new File(filePath + "1.bmp"));
        exampleImg2 = ImageIO.read(new File(filePath + "2.bmp"));
    }

    @Test
    public void testShowChanelR() throws IOException {
        Image redImg1 = myProcessor.showChanelR(exampleImg1);
        Image expectRedImg1 = ImageIO.read(new File(filePath + "goal/1_red_goal.bmp"));
        assertEquals(true, ioTest.compare(redImg1, expectRedImg1));

        Image redImg2 = myProcessor.showChanelR(exampleImg2);
        Image expectRedImg2 = ImageIO.read(new File(filePath + "goal/2_red_goal.bmp"));
        assertEquals(true, ioTest.compare(redImg2, expectRedImg2));
    }

    @Test
    public void testShowChanelG() throws IOException {
        Image greenImg1 = myProcessor.showChanelG(exampleImg1);
        Image expectGreenImg1 = ImageIO.read(new File(filePath + "goal/1_green_goal.bmp"));
        assertEquals(true, ioTest.compare(greenImg1, expectGreenImg1));

        Image greenImg2 = myProcessor.showChanelG(exampleImg2);
        Image expectGreenImg2 = ImageIO.read(new File(filePath + "goal/2_green_goal.bmp"));
        assertEquals(true, ioTest.compare(greenImg2, expectGreenImg2));
    }

    @Test
    public void testShowChanelB() throws IOException {
        Image blueImg1 = myProcessor.showChanelB(exampleImg1);
        Image expectBludImg1 = ImageIO.read(new File(filePath + "goal/1_blue_goal.bmp"));
        assertEquals(true, ioTest.compare(blueImg1, expectBludImg1));

        Image blueImg2 = myProcessor.showChanelB(exampleImg2);
        Image expectBludImg2 = ImageIO.read(new File(filePath + "goal/2_blue_goal.bmp"));
        assertEquals(true, ioTest.compare(blueImg2, expectBludImg2));
    }

    @Test
    public void testShowGray() throws IOException {
        Image grayImg1 = myProcessor.showGray(exampleImg1);
        Image expectGrayImg1 = ImageIO.read(new File(filePath + "goal/1_gray_goal.bmp"));
        assertEquals(true, ioTest.compare(grayImg1, expectGrayImg1));

        Image grayImg2 = myProcessor.showGray(exampleImg2);
        Image expectGrayImg2 = ImageIO.read(new File(filePath + "goal/2_gray_goal.bmp"));
        assertEquals(true, ioTest.compare(grayImg2, expectGrayImg2));
    }
}
