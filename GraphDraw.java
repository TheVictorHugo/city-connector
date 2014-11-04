/*
 * Simple graph drawing class. Author: Updated for homework 4 Bert Huang COMS 3137 Data
 * Structures and Algorithms, Spring 2009. 
 * 
 * New version operates as a JPanel and eliminates enhanced for loops. This
 * should alleviate some of the multithreading exceptions that were occurring
 * from the previous version.
 * 
 * JPanel also allows us to work with multiple GUI elements in the JFrame, but
 * you'll need to manage the JFrame from your calling program.
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GraphDraw extends JPanel
{
	int width;
	int height;
	ArrayList<Node> nodes;
	ArrayList<Edge> edges;

	public GraphDraw()
	{
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		width = 30;
		height = 30;
	}

	class Node
	{
		int x, y;
		String name;

		public Node(String myName, int myX, int myY)
		{
			x = myX;
			y = myY;
			name = myName;
		}
	}

	class Edge
	{
		int i, j;

		public Edge(int ii, int jj)
		{
			i = ii;
			j = jj;
		}
	}

	public void clear()
	{
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		this.repaint();
	}

	public void addNode(String name, int x, int y)
	{
		nodes.add(new Node(name, x, y));
		this.repaint();
	}

	public void addEdge(int i, int j)
	{
		edges.add(new Edge(i, j));
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		Font font = new Font("serif", Font.PLAIN, 10) ;
		g.setFont(font) ;
		
		FontMetrics f = g.getFontMetrics();
		int nodeHeight = Math.max(height, f.getHeight());
		g.clearRect(g.getClipBounds().x, g.getClipBounds().y,
			g.getClipBounds().width, g.getClipBounds().height);
		g.setColor(Color.black);
		for (int i = 0; i < edges.size(); i++)
		{
			Edge e = edges.get(i);
			g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y, nodes.get(e.j).x, nodes
				.get(e.j).y);
		}
		for (int i = 0; i < nodes.size(); i++)
		{
			Node n = nodes.get(i);
			int nodeWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
			g.setColor(Color.white);
			g.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2, nodeWidth,
				nodeHeight);
			g.setColor(Color.black);
			g.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2, nodeWidth,
				nodeHeight);
			g.drawString(n.name, n.x - f.stringWidth(n.name) / 2, n.y
				+ f.getHeight() / 2);
		}
	}
}


