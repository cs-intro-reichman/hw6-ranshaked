import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		// image processing operations:
		Color[][] image;
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
	}
	public static Color[][] read(String fileName) {
	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int red = in.readInt();
                int green = in.readInt();
                int blue = in.readInt();
                image[i][j] = new Color(red, green, blue);
            }
		}
				return image;
				// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
	}	
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}
	private static void print(Color[][] image) {
		// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
		for(int i = 0; i < image.length; i++)
		{
			for(int j = 0 ; j < image[i].length ; j++)
			{
				print(image[i][j]);
			}
			System.out.println();
		}
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}
	public static Color[][] flippedHorizontally(Color[][] image) {
		/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */	if (image == null || image.length == 0 || image[0].length == 0) { return new Color[0][0];}
		Color[][] image1 = new Color[image.length][image[0].length];
		for(int i = 0 ; i < image1.length ; i++)
		{
			for(int j = 0 ; j < image1[0].length ; j++)
			{
				image1[i][image[0].length-1-j] = image[i][j];
			}
		}
		return image1;
	}
	public static Color[][] flippedVertically(Color[][] image){
		/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	if (image == null || image.length == 0 || image[0].length == 0) { return new Color[0][0];}
		Color[][] image1 = new Color[image.length][image[0].length];
		for(int i = 0 ; i < image1[0].length ; i++)
		{
			for(int j = 0 ; j < image1.length ; j++)
			{
				image1[image.length-1-i][j] = image[i][j];
			}
		}
		return image1;
	}
	private static Color luminance(Color pixel) {
			// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		int lum = (int)(0.299*r + 0.587*g + 0.114 * b);
		return new Color(lum, lum, lum);
	}
	public static Color[][] grayScaled(Color[][] image) {
		if (image == null || image.length == 0 || image[0].length == 0) { return new Color[0][0];}
		Color[][] image1 = new Color[image.length][image[0].length];
		for(int i = 0 ; i < image.length ; i++)
		{
			for(int j = 0 ; j < image[0].length; j++)
			{
				image1[i][j] = luminance(image[i][j]);
			}
		}
		return image1;
	}	
	public static Color[][] scaled(Color[][] image, int width, int height) {
			/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	if (image == null || image.length == 0 || image[0].length == 0) { return new Color[0][0];}
		Color[][]image1 = new Color[height][width];
		double newH =(double)image.length/height;
		double newW =(double)image[0].length/width;
		for(int i = 0 ; i < height ; i++)
		{
			for(int j = 0 ; j < width ; j++)
			{
				int origRow = (int) (i * newH);  
            	int origCol = (int) (j * newW);
				image1[i][j] = image[origRow][origCol];
			}
		}
		return image1;
	}
	public static Color blend(Color c1, Color c2, double alpha) {
			/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	if(alpha>1)
	{
		return c1;
	}
	int r1 = c1.getRed();
	int r2 = c2.getRed();
	double Nr = (alpha*(double)r1) + ((1-alpha)*(double)r2);
	int g1 = c1.getGreen();
	int g2 = c2.getGreen();
	double Ng = (alpha*(double)g1) + ((1-alpha)*(double)g2);
	int b1 = c1.getBlue();
	int b2 = c2.getBlue();
	double Nb = (alpha*(double)b1) + ((1-alpha)*(double)b2);
		return new Color((int)Nr,(int)Ng,(int)Nb);
	}
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	if (image1 == null || image2 == null || image1.length != image2.length || image1[0].length != image2[0].length) { return new Color[0][0];}
		Color[][] image3 = new Color[image1.length][image1[0].length];
		for(int i = 0 ; i < image1.length ; i++)
		{
			for(int j = 0 ; j < image1[0].length; j++)
			{
	
				image3[i][j] = blend(image1[i][j],image2[i][j], alpha);
			}
		}
		return image3;
	}
	public static void morph(Color[][] source, Color[][] target, int n) {
			/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
		for(int i = 0 ; i < n ; i++)
		{
			blend(source, target,1/n);
		}
		
		//// Replace this comment with your code
	}
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

