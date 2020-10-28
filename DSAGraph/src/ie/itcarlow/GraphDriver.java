/**
 * 
 */
package ie.itcarlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author diarm
 *
 */
public class GraphDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = null;
		try {
			//read from a file using scanner
			input = new Scanner (new File("C:\\DSAProject2\\input.txt"));
		}catch(FileNotFoundException exception) {
			//throw an exception if the file is not found
			System.out.println("Exception " + exception) ;
			
		}
		
		
		String menu ;
		int option = 0 ;
		//display the menu options for the graph
		menu = 		"\t\t####MENU####\n" ;
    	menu +=	 "\n1.	Open and input a graph from an external file" ;
		menu +=	 "\n2.	Search for a site" ;
		menu +=	 "\n3	Insert and Search for an edge (site-site) " ;
		menu +=	 "\n4.	Given a site, display all sites connected to it " ;
		menu +=	 "\n5.	Given a site, display the closest site to it " ;
		menu +=	 "\n6.	Exit" ;
		menu += 	"\n\t\t##############\n" ;
		
		int numNodes = 8 ;
		
		//create a instance of the graph class
		Graph walkingTour = new Graph(numNodes) ;
		
		Scanner opt = new Scanner(System.in);
		boolean result = false;
		do {
			try {
				//read from a file using scanner
				System.out.println(menu)  ;
		    	System.out.print("Please select an option(1-6) :	")  ;
		    	option = opt.nextInt();
		    	result = true ;
			}catch(Exception exception) {
				//throw an exception if the file is not found
				 
				System.out.println("You must enter an integer ") ;
				opt.nextLine();// discard input so user can try again
			}
			
		}
		while(result == false) ;
    	
    	
    	Scanner in = new Scanner(System.in);
    	while(option != 6) {
    		switch(option)
    		{
    			
    		   	case 1 ://OPTION ONE Open and input a graph from an external file
    		   		System.out.println("\nOption 1 :	Open and input a graph from an external file") ;
    		   		walkingTour.inputGraphDetails(input) ;
    				break ;
    			case 2	://OPTION 2 SEARCH THE LOCATIONARRAY TO SEE IF A DESTINATION IS IN THE GRAPH
    				System.out.print("\nOption 2 :	Search for a site") ;
    				System.out.print("\nEnter location to be searched for in the walking tour :	") ;
    				String searchName = in.nextLine() ;
    				System.out.println() ;
    				int location = walkingTour.searchLocation(searchName);
    				if(location == -1) {
    					System.out.println(searchName + " was not found in the walking tour") ;
    				}
    				else {
    					System.out.println(searchName + " is at position " + location + " on the tour. Its x, y coordinates are "
    							+ walkingTour.getXaxisArray()[location] + ", " + walkingTour.getYaxisArray()[location]) ;
    				}
    				System.out.println() ;
    				break ;
    			case 3 ://OPTION 3 SEARCH THE ARRAY FOR A LOCATION AND RETURN WHAT EDGES IT AS
    				System.out.println("\nOption 3 :	Insert and Search for an edge (site-site)") ;
    				walkingTour.returnLocationArray() ;
    				System.out.print("Enter first location to connect on the tour  :	") ;
    				String searchEdges = in.nextLine() ;
    				location = walkingTour.searchLocation(searchEdges);
    				if(location == -1) {
    					System.out.println(searchEdges + " is not in the walking tour") ;
    				}
    				else {
    					System.out.print("\nEnter second location to connect on the tour  :	") ;
        				String searchEdges1 = in.nextLine() ;
        				System.out.println() ;
        				location = walkingTour.searchLocation(searchEdges1);
        				if(location == -1) {
        					System.out.println(searchEdges1 + " is not in the walking tour") ;
        				}
        				else {
        					walkingTour.searchEdges(searchEdges, searchEdges1);
        				}
    				}
    				
    				
    				System.out.println() ;
    				break ;
    			case 4 ://OPTION 4 SEARCH THE ARRAY FOR A LOCATION AND RETURN WHAT NODES ARE CONNECTED TO IT
    				System.out.println("\nOption 4 :	Given a site, display all sites connected to it") ;
    				walkingTour.returnLocationArray() ;
    				System.out.println("\nEnter location of site to view all its connected sites on the tour	:	") ;
    				String searchNodes = in.nextLine() ;
    				walkingTour.searchNodes(searchNodes);
    				System.out.println() ;
    				break ;	
    			case 5 ://OPTION 5 SEARCH THE ARRAY FOR A LOCATION AND RETURN WHAT NODE IS CLOSEST TO IT
    				System.out.println("Option 5 :	Given a site, display the closest site to it ") ;
    				walkingTour.returnLocationArray() ;
    				System.out.print("\nEnter node to search for closest connected node in the tour	:	") ;
    				
    				String searchClosest = in.nextLine() ;
    				walkingTour.searchClosest(searchClosest);
    				System.out.println() ;
    		    	break ;
    		    case 6 ://OPTION 6 EXIT
    		    	System.exit(0);
    				break ;
    			default : System.out.println("\nThis is not an option. Enter a number 1-6") ;
    				System.out.println() ;
    				break ;
    	}
    		//System.out.println(menu)  ;
    		result = false ;
    		do {
    			try {
    				//read from a file using scanner
    				System.out.println(menu)  ;
    		    	System.out.print("Please select an option(1-6) :	")  ;
    		    	option = opt.nextInt();
    		    	result = true ;
    			}catch(Exception exception) {
    				//throw an exception if the file is not found
    				 
    				System.out.println("You must enter an integer ") ;
    				opt.nextLine();// discard input so user can try again
    			}
    			
    		}
    		while(result == false) ;
    	}
    	System.out.println("You have exited the program ") ;
	}

}