package ie.itcarlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {
	
	private int numOfLocations = 0 ;
	private int lineNumber = 0 ;
	private String locationArray[] ;
	private int adjacencyArray[][] ;
	private int xaxisArray [] ;
	private int yaxisArray [] ;
	private int dataNumber = 0 ;
	private int parseNum ;
	
	
	
	//empty constructor
	public Graph() {
		
	}
	public Graph(int numOfLocations) {
		this.numOfLocations = numOfLocations;
		locationArray = new String[numOfLocations] ;
		adjacencyArray = new int [numOfLocations][numOfLocations] ;
		xaxisArray = new int[numOfLocations] ;
		yaxisArray = new int[numOfLocations] ;
	}

	public String[] getLocationArray() {
		return locationArray;
	}
	public void setLocationArray(String[] locationArray) {
		this.locationArray = locationArray;
	}
	public int[][] getAdjacencyArray() {
		return adjacencyArray;
	}
	public void setAdjacencyArray(int[][] adjacencyArray) {
		this.adjacencyArray = adjacencyArray;
	}
	public int[] getXaxisArray() {
		return xaxisArray;
	}
	public void setXaxisArray(int[] xaxisArray) {
		this.xaxisArray = xaxisArray;
	}
	public int[] getYaxisArray() {
		return yaxisArray;
	}
	public void setYaxisArray(int[] yaxisArray) {
		this.yaxisArray = yaxisArray;
	}
	public void returnLocationArray() {
		System.out.print("Locations are		:	");
		for(int i =0; i < numOfLocations; i++) {
			System.out.print("[" + i  + "] " + locationArray[i] + " ") ;
		}
		System.out.println();
		System.out.println();
	}
	public void returnXaxisArray() {
		System.out.print("X coordinates are	:	");
		for(int i =0; i < numOfLocations; i++) {
			System.out.print("[" + i  + "] " + xaxisArray[i] + " 	") ;
		}
		System.out.println();
		System.out.println();
	}
	public void returnYaxisArray() {
		System.out.print("Y coordinates are	:	");
		for(int i =0; i < numOfLocations; i++) {
			System.out.print("[" + i  + "] " + yaxisArray[i] + " 	") ;
		}
		System.out.println();
		System.out.println();
	}
	public void returnAdjacencyArray() {
		System.out.println("The adjoining locations index positions and distances apart are:");
		for(int i =0; i < numOfLocations; i++) {
			System.out.print("	[" + i  + "] ") ;
		}
		System.out.println();
		System.out.println();
		for(int i =0; i < numOfLocations; i++) {
			System.out.print("[" + i  + "] 	") ;
			for(int j =0; j < numOfLocations; j++) {
				System.out.print(adjacencyArray[i][j] + "	 ") ;
			}
			System.out.println();
			
		}
	}

	
	
	public void inputGraphDetails(Scanner input) {
			while(input.hasNextLine()) {
				dataNumber = 0 ;
				Scanner line = new Scanner(input.nextLine());
					while(line.hasNext()) {
						if(lineNumber == 0) {
							locationArray[dataNumber] = line.next();
						}
						else if(lineNumber == 1) {
							parseNum = Integer.parseInt(line.next());
							xaxisArray[dataNumber] = parseNum;
						}
						else if(lineNumber == 2) {
							parseNum = Integer.parseInt(line.next());
							yaxisArray[dataNumber] = parseNum;
						}
						
						else {
							parseNum = Integer.parseInt(line.next());
							adjacencyArray[lineNumber-3][dataNumber] = parseNum;
						}
						dataNumber++ ;
					}
					lineNumber++;	
				}
			System.out.println();
			//method for output locations
			returnLocationArray() ;
			returnXaxisArray() ;
			returnYaxisArray() ;
			returnAdjacencyArray() ;
			System.out.println();
		
	}
				
	
	public int searchLocation(String name) {
		int position = -1 ;
		try {
			int index ;
			for(index = 0; index < numOfLocations; index++) {
				if(locationArray[index].equalsIgnoreCase(name)) {
					position = index ;
				}
			}
			return position ;
		}catch(Exception exception) {
			System.out.println("You must enter a file first(option 1) ") ;
		}
		return position ;
		
	}
	
	public void searchEdges(String name, String name2) {
		try {
			int position = searchLocation(name) ;
			int nextPosition = searchLocation(name2) ;
			int index ;
			if(adjacencyArray[position][nextPosition] > 0) {
					System.out.println(name + " and " + name2 + " are already connected in the walking tour. They are " + adjacencyArray[position][nextPosition] + " miles away from each other. ");
				}
			else {
					Scanner newWeight = new Scanner(System.in);
					boolean result = false ;
		    		do {
		    			try {
		    				//read from a file using scanner
		    				
		    				System.out.print("\nPlease enter the distance of the connection you would like to insert in the graph between " + name + " and " + name2 + " 	:") ;
		    				int weight = newWeight.nextInt() ;
		    				adjacencyArray[position][nextPosition] = weight ;
		    				adjacencyArray[nextPosition][position] = weight ;
		    		    	result = true ;
		    			}catch(Exception exception) {
		    				System.out.println("You must enter an integer ") ;
		    				newWeight.nextLine();// discard input so user can try again
		    			}
		    			
		    		}
		    		while(result == false) ;
					
				}
				returnAdjacencyArray() ;
				System.out.println() ;
				System.out.println(name + " and " + name2 + " are now connected on the walking tour.") ;
		}catch(Exception exception) {
			System.out.println("You must enter a file first(option 1) ") ;
		}
		

		}
	
	public void searchNodes(String name) {
		try {
			int position = searchLocation(name) ;
			int index ;
			if(position == -1) {
				System.out.println(name + " is not on the walking tour. ");
			}
			else {
				for(index = 0; index < numOfLocations; index++) {
					if(adjacencyArray[position][index] > 0) {
						System.out.println(name + " is connected with " + locationArray[index] + ", with a distance of " + adjacencyArray[position][index] +
								" miles apart. ");
					}
				}
			}
		}catch(Exception exception) {
			System.out.println("You must enter a file first(option 1) ") ;
		}
		
	}	
			

	public void searchClosest(String name) {
		try {
			int position = searchLocation(name) ;
			int farthest;
			int closest ;
			int index ;
			int holdPos = 0;
			if(position == -1) {
				System.out.println(name + " is not on the walking tour. ");
			}
			else {
				farthest = adjacencyArray[position][0] ;
				for(index = 0; index < numOfLocations; index++) {
						if(adjacencyArray[position][index] > farthest) {
							farthest = adjacencyArray[position][index];
							holdPos = index ;
						}
				}
				//System.out.println(name + " is furthest from " + locationArray[holdPos] + ", with a weight of " + adjacencyArray[position][holdPos]);
				closest = farthest;
				for(index = 0; index < numOfLocations; index++) {
					if(adjacencyArray[position][index] > 0) {
						if(adjacencyArray[position][index] < closest) {
							closest = adjacencyArray[position][index];
							holdPos = index ;
						}
						
					}
				}
				
				System.out.println("The closest connected site to " + name + " on the tour is the " + locationArray[holdPos] + ", with a distance of " + adjacencyArray[position][holdPos] + " miles apart.");
			}
		}catch(Exception exception) {
			System.out.println("You must enter a file first(option 1) ") ;
		}
		
		
	}
	}
		
