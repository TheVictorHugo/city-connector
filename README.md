CityConnector

CityConnector reads an input file with city locations and creates a fully connected graph with weights computed by the distance formula. It executes Prim's algorithm and displays the graph of the cities connected using the minimum distance tree. Nodes are labeled with city names and they overlap in very dense areas. "Screenshot.png" is a screenshot of the program.

To compile: simply use the makefile.

To run:
  $ java CityConnector cities.txt 
