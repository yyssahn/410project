package project.flowerVisualizer;

import java.awt.Color;

/**
 * A class for the standard colors to use with our project. 
 * The values are taken from http://flatuicolors.com/
 * @author Mike
 *
 */
public class Palette {
	//Greens
	public static final Color TURQUOISE = new Color(26, 188, 156); 
	public static final Color EMERALD = new Color(46, 204, 113); 
	public static final Color GREEN_SEA = new Color(22, 160, 133); 
	public static final Color NEPHRITIS = new Color(39, 174, 96); 
	//Reds
	public static final Color SUN_FLOWER = new Color(241, 196, 15); 
	public static final Color ORANGE = new Color(243, 156, 18); 
	public static final Color CARROT = new Color(230, 126, 34); 
	public static final Color PUMPKIN = new Color(211, 84, 0); 
	public static final Color ALIZARIN = new Color(231, 76, 60); 
	public static final Color POMEGRANATE = new Color(192, 57, 43);
	//Blues
	public static final Color PETER_RIVER = new Color(52, 152, 219); 
	public static final Color BELIZE_HOLE = new Color(41, 128, 185);
	//Purples
	public static final Color AMETHYST = new Color(155, 89, 182); 
	public static final Color WISTERIA = new Color(142, 68, 173);
	//Whites to grays
	public static final Color CLOUDS = new Color(236, 240, 241); 
	public static final Color SILVER = new Color(189, 195, 199); 
	public static final Color CONCRETE = new Color(149, 165, 166); 
	public static final Color ASBESTOS = new Color(127, 140, 141);
	//Blacks
	public static final Color WET_ASPHALT = new Color(52, 73, 94); 
	public static final Color MIDNIGHT_BLUE = new Color(44, 62, 80); 
	
	/**A useful method to make add transparency to any given color.
	 * 
	 * @param baseColor - the Color to add transparency to.
	 * @param alpha - a number in [0, 255] that shows how much transparency to add 
	 *  (0 - no transparency, 255 - full transparency)
	 * @return A Color with RGB components equal to the baseColor and with alpha component set to alpha.
	 */
		public static Color setTransparency(Color baseColor, int alpha)
		{
			if (alpha > 255)
				alpha = 255;
			else if (alpha < 0)
				alpha = 0;
			return new Color(baseColor.getRed(),baseColor.getGreen(), baseColor.getBlue(), alpha);
		}

}
