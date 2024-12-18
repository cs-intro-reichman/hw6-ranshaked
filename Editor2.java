import java.awt.Color;
/**
 * Demonstrates the scaling (resizing) operation featured by Runigram.java. 
 * The program recieves three command-line arguments: a string representing the name
 * of the PPM file of a source image, and two integers that specify the width and the
 * height of the scaled, output image. For example, to scale/resize ironman.ppm to a width
 * of 100 pixels and a height of 900 pixels, use: java Editor2 ironman.ppm 100 900
 */
public class Editor2 {

	public static void main (String[] args){
		System.out.println();
		String fileName = args[0];
		String fileName2 = args[1];
		//int H = Integer.parseInt(args[1]);
		//int W = Integer.parseInt(args[2]);
		Color[][] imageIn = Runigram.read(fileName);
		Color[][] imageOut = Runigram.read(fileName2);
		Color[][] x = Runigram.blend(imageIn, imageOut, 0.2);
		Runigram.setCanvas(imageIn);
		Runigram.display(imageIn);
		StdDraw.pause(10); 
		Runigram.display(x);

		

	}
}
