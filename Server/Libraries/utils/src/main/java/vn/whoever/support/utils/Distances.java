package vn.whoever.support.utils;

/**
 * for calculate distance get status 
 * @author spider man
 *
 */

public class Distances {
	
	public static double addDistance(int level) {
		double distance = 0.006;
		switch (level) {
		case 1:
			distance = 0.010;
			break;
		case 2:
			distance = 0.014;
			break;
		case 3:
			distance = 0.02;
			break;
		case 4:
			distance = 0.026;
			break;
		case 5:
			distance = 0.032;
			break;
		case 6:
			distance = 0.040;
			break;
		case 7:
			distance = 0.048;
			break;
		case 8:
			distance = 0.056;
			break;
		case 9:
			distance = 0.076;
			break;
		case 10:
			distance = 0.086;
			break;
		case 11:
			distance = 0.1;
			break;
		case 12:
			distance = 0.2;
			break;
		case 13:
			distance = 0.3;
			break;
		case 14:
			distance = 0.5;
			break;
		case 15: 
			distance = 0.7;
			break;
		case 16:
			distance = 1.0;
			break;
		default:
			distance = 0.006;
			break;
		}
		return distance;
	}
}
