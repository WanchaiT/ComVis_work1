import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Scanner;

class Practice1{

    public static BufferedImage brightnessInverse(BufferedImage img){
        WritableRaster raster = img.getRaster();
        int[] imgPixel = new int[1];
        imgPixel[0] = 0;
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();

        for (int height = 0;height < imgHeight ;height++ ) {
            for (int width = 0;width < imgWidth ;width++ ) {
                raster.getPixel(width ,height ,imgPixel);
                imgPixel[0] = 255 - imgPixel[0];
                raster.setPixel(width ,height ,imgPixel);
            }
        }

        return img;
    }

    public static void writeImage(BufferedImage img ,String path){
        String[] destPath = path.split("\\.");
        int index = destPath[0].lastIndexOf("/");
        String folder = path.substring(0 ,index);
        File outTest = new File(folder);

        if (!outTest.exists()) {
            System.out.println("Error writing image 1");
            return;
        }

        try {
            File outFile = new File(path);
            ImageIO.write(img, destPath[1] ,outFile);
        } catch(IOException e) {
            System.err.println("Error writing image");
            return ;
        }
    }

    public static BufferedImage readImage(String path){
        BufferedImage img = null;

        try {
            File imgFile = new File(path);
            img = ImageIO.read(imgFile);
            System.out.println("Loading Done");
        } catch (IOException e) {
            System.err.println("Error loading image");
            System.exit(0);
        }

        return img;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String sourcePath = scan.nextLine();
        BufferedImage img = readImage(sourcePath);
        String destPath = scan.nextLine();
        img = brightnessInverse(img );
        writeImage(img ,destPath);
    }
}
