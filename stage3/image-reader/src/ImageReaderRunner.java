import imagereader.Runner;

public final class ImageReaderRunner {
    public static void main(String[] args) {
        MyImageIO imageioer = new MyImageIO();
        MyImageProcessor processor = new MyImageProcessor();
        Runner.run(imageioer, processor);
    } 
}
