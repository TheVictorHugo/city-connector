/*
 * City.java
 * Author: Irene Alvarado
 * Class that represents a city and stores its name, location, latitude, and other data
 */

public class City
{
	public String name; //name of the city
	public int longitude;
	public int latitude;
	public boolean known; //if it is known by Prim's algorithm
	public double minDist; //the minimum distance to it
	public int nextCity = 0; //the closest city to this city

	public City(String theName, int theLatitude, int theLongitude)
	{
		name = theName;
		longitude = theLongitude;
		latitude = theLatitude;
		known = false;
		minDist = -1;
	}
}