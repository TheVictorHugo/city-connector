/*
 * CityConnector.java 
 * Author: Irene Alvarado 
 * Program that reads an input file
 * with city coordinates, creates a fully connected graph with weights taken
 * from the distance formula, executes Prim's Algorithm and shows a graph with
 * connected cities
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CityConnector
{
	static ArrayList<City> cities;
	static int maxLong;
	static int maxLat;

	public static void main(String[] args) throws IOException
	{
		File file = new File(args[0]); // reads from the cities text file
		Scanner input = new Scanner(file);
		input.nextLine();
		cities = new ArrayList<City>(); // creates an Array List of cities
		while (input.hasNextLine()) // read each line of the test file
		{
			String line = input.nextLine();
			String[] city = line.split("\t");
			String name = city[0]; // name of the city
			int latitude = (Double.valueOf(city[1])).intValue(); // its latitude
			int longitude = (Double.valueOf(city[2])).intValue(); // its longitude
			cities.add(new City(name, latitude * 25, longitude * 25)); // multiplied
			// by 25 to make them spread out enough to appear on the graph
		}
		scaleCoordinates(); // negative values are taken out
		
		CityGraph cityGraph = new CityGraph(cities);
		cityGraph.calculateWeights(); // calculates the distances between cities
		cityGraph.Prim(); // executed Prim's algorithm
		cityGraph.createAdjacency(); // updates adjacency
		
		cityGraph.drawGraph(maxLat, maxLong); // draws the graph
		cityGraph.addNodes();
	}

	public static void scaleCoordinates() // gets rid of negative values
	{
		int minLong = 0;
		int minLat = 0;
		
		maxLong = 0;
		maxLat = 0;
		
		for (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i).longitude > minLong)
			{
				minLong = cities.get(i).longitude;
			}
			if (cities.get(i).longitude < maxLong)
			{
				maxLong = cities.get(i).longitude;
			}
			if (cities.get(i).latitude < minLat)
			{
				minLat = cities.get(i).latitude;
			}
			if (cities.get(i).latitude > maxLat)
			{
				maxLat = cities.get(i).latitude;
			}
		}
		
		for (int i = 0; i < cities.size(); i++)
		{
			cities.get(i).longitude = Math.abs(cities.get(i).longitude - minLong) + 50;
			cities.get(i).latitude = cities.get(i).latitude + Math.abs(minLat)
				+ 50;
		}
		
		maxLong = Math.abs(maxLong - minLong) + 50;
		maxLat = maxLat + Math.abs(minLat) + 50;
		
		minLong = 50;
		minLat = 50;
	}
}
