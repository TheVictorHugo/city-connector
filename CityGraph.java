/*
 * CityGraph.java 
 * Author: Irene Alvarado 
 * Program that uses GraphDraw to display
 * a graph of cities. It executes Prim's algorithm to get connected cities
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class CityGraph
{
	GraphDraw panel;
	ArrayList<City> cities;
	double[][] weights; // matrix with distances between cities
	boolean[][] adjacency; // adjacency matrix

	public CityGraph(ArrayList<City> theCities)
	{
		panel = new GraphDraw();
		cities = theCities;
		weights = new double[cities.size()][cities.size()];
		adjacency = new boolean[cities.size()][cities.size()];
	}

	public void calculateWeights() // calculates the distance between two
												// cities with the distance formula
	{
		for (int i = 0; i < weights.length; i++)
		{
			for (int j = 0; j < weights.length; j++)
			{
				weights[i][j] = Math.pow(Math.pow(cities.get(i).longitude
					- cities.get(j).longitude, 2)
					+ Math.pow(cities.get(i).latitude - cities.get(j).latitude, 2),
					0.5);
			}
		}
	}

	public void Prim() // executes Prims algorithm
	{
		for (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i).known == false) // if the city is not known
			{
				cities.get(i).known = true; // set to known
				if (i == 0)
				{
					cities.get(i).minDist = 0;
				}
				for (int j = 0; j < cities.size(); j++) // find all cities to
																		// which this city
																		// connects to with min
																		// distance
				{
					if (cities.get(j).known == false
						&& ((cities.get(j).minDist == -1) || (weights[i][j] < cities
							.get(j).minDist)))
					{
						cities.get(j).minDist = weights[i][j];
						cities.get(j).nextCity = i;
					}
				}
			}
		}
	}

	public void createAdjacency() // updates the adjacency matrix by setting each
											// city and each next city to true
	{
		for (int i = 0; i < adjacency.length; i++)
		{
			adjacency[i][cities.get(i).nextCity] = true;
		}
	}

	public void drawGraph(int maxx, int maxy) // code to draw the graph by
															// creating a GraphDraw object
	{
		JFrame frame = new JFrame("City Connector");
		JScrollPane scrollPane = new JScrollPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.add(scrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(maxx + 50, maxy + 50));
		frame.setVisible(true);
		draw(panel);
	}

	int drawx;
	int grid = 30, margin = 50, maxLevel;

	public void draw(GraphDraw frame)
	{
		drawx = 0;
		maxLevel = 0;
		frame.clear();
		frame.setSize((drawx) * grid + margin, (maxLevel + 1) * grid + margin);
	}

	public void addNodes() // adds nodes to the graph, and adds edges between
									// those that the adjacency matrix shows true
	{
		for (int i = 0; i < cities.size(); i++)
		{
			panel.addNode(cities.get(i).name, cities.get(i).latitude, cities
				.get(i).longitude);
		}
		for (int i = 0; i < cities.size(); i++)
		{
			for (int j = 0; j < cities.size(); j++)
			{
				if (adjacency[i][j] == true)
				{
					panel.addEdge(i, j);
				}
			}
		}
	}
}
