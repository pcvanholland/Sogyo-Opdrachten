import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester
{
	public static void main(String[] args)
	{
		// Initialise the decision tree, with the specified file
		// and delimiter string.
		DecisionTree decTree = new DecisionTree("decision-tree-data.txt", ", ");

		// We have to specify the starting point, for
		// there is no logical manner of finding one in the file.
		decTree.startDeciding();
	}
}

class DecisionTree
{
	// Save the nodes in an array list.
	ArrayList<Node> nodes = new ArrayList<Node>();

	String startingPoint;

	/**
	 * The constructor used to create a decision tree from a given file name.
	 *
	 * @param {String} file - The file to read the tree from.
	 * @param {String} delimiter - The delimiter to use for splitting the line into components.
	 */
	DecisionTree(String fileName, String delimiter)
	{
		// Store the file contents.
		String[] fileContents = FileIO.readFileLBL(fileName);

		// Temporarily store all the read edges,
		// they are coupled to the right nodes later on.
		ArrayList<String[]> edges = new ArrayList<String[]>();

		// Read every line, create a new node for nodes (duh,,,)
		// and save the edges in an array, for later use.
		for (int i = 0; i < fileContents.length; ++i)
		{
			String[] splitLine = fileContents[i].split(delimiter);

			// We assume any line with two seperated strings is a node...
			// We could also check for "splitLine[0].startsWith("N")" if necessary.
			if (splitLine.length == 2)
			{
				addNode(splitLine);
			}
			// ...and any line with three seperated strings is an edge.
			// We could also check for "splitLine[0].startsWith("N") && splitLine[1].startsWith("N")" if necessary.
			else if (splitLine.length == 3)
			{
				edges.add(splitLine);
			}
			else
			{
				System.out.println("This line was neither recognised as a node nor an edge: " + fileContents[i]);
			}
		}

		addEdges(edges);
		startingPoint = getStartingPoint(edges);
	}

	/**
	 * Adds a node.
	 * This could be elaborated by warning when two nodes with the same ID would be created.
	 *
	 * @param {String[]} node - The node to process.
	 */
	private void addNode(String[] node)
	{
		nodes.add(new Node(node[0], node[1]));
	}

	/**
	 * Adds edges to the currently known nodes.
	 * This could be elaborated to warn the user that there is an unused edge.
	 *
	 * @param {String[]} node - The node to process.
	 */
	private void addEdges(ArrayList<String[]> edges)
	{
		// Loop over all edges and check whether there is any node
		// that may be the origin of the edge.
		for (int i = 0; i < edges.size(); ++i)
		{
			for (int j = 0; j < nodes.size(); j++)
			{
				if (edges.get(i)[0].equals(nodes.get(j).nodeID))
				{
					// Add the corresponding edge to the node.
					// We can break thereafter since it would be strange
					// for an edge to have multiple origin nodes and breaking
					// can save us a few cycles.
					nodes.get(j).addEdge(edges.get(i)[2], edges.get(i)[1]);
					break;
				}
			}
		}
	}

	/**
	 * Start the decision tree.
	 */
	public void startDeciding()
	{
		int currentNodeLocation = getNodeLocation(startingPoint);
		if (currentNodeLocation == -1)
		{
			UserIO.printString("Invalid starting point chosen! Terminating the program.");
			return;
		}
		while (true)
		{
			// If the node contains any branches ask the user for an answer.
			if (nodes.get(currentNodeLocation).edges.size() > 0)
			{
				String nextNode = nodes.get(currentNodeLocation).getNextNode();
				currentNodeLocation = getNodeLocation(nextNode);
			}
			// Otherwise it is an end branch, so print the string in the node and terminate the loop.
			else
			{
				nodes.get(currentNodeLocation).printString();
				break;
			}
		}
	}

	/**
	 * Get the location of the node in the node array.
	 *
	 * @param {String} nodeID - The ID of the node to check the location for.
	 * @return {int} - The location of the node in the node-array.
	 */
	private int getNodeLocation(String nodeID)
	{
		for (int i = 0; i < nodes.size(); i++)
		{
			if (nodes.get(i).nodeID.equals(nodeID))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finds the starting point of the decision tree.
	 * This is done by checking which node does not have an edge pointing towards it.
	 *
	 * @param {ArrayList<String[]>} edges - The edges to use.
	 * @return {String} - The starting node.
	 */
	private String getStartingPoint(ArrayList<String[]> edges)
	{
		String beginPoint = "Bogus";

		for (int i = 0; i < nodes.size(); i++)
		{
			boolean foundOne = false;

			for (int j = 0; j < edges.size(); j++)
			{
				if (nodes.get(i).nodeID.equals(edges.get(j)[1]))
				{
					foundOne = true;
					break;
				}
			}
			if (!foundOne)
			{
				beginPoint = nodes.get(i).nodeID;
			}
		}
		return beginPoint;
	}
}

class Node
{
	String nodeString;
	String nodeID;

	// An array of connected edges of the form [ "answer", "destinationNode" ].
	ArrayList<String[]> edges = new ArrayList<String[]>();

	/**
	 * The initialiser of a node. Edges can be added later.
	 * A node without edges will be an end node.
	 *
	 * @param {String} node - The nodeID.
	 * @param {String} string - The string to show when printing this node.
	 */
	Node(String node, String string)
	{
		nodeID = node;
		nodeString = string;
	}

	/**
	 * Adds an edge to this node. A node without edges will be an end node.
	 *
	 * @param {String} answer - The answer that triggers the destination node.
	 * @param {String} destination - The node to go to when the given answer is given.
	 */
	public void addEdge(String answer, String destination)
	{
		edges.add(new String[] {answer, destination});
	}

	/**
	 * Checks the answer given to this node and returns the nodeID of the next node.
	 *
	 * @return {String} - The destination ID of the node to go to next.
	 */
	public String getNextNode()
	{
		String options = " [";
		for (int i = 0; i < edges.size(); i++)
		{
			options += edges.get(i)[0] + (i == edges.size() -1 ? "]" : "/");
		}
		while (true)
		{
			String answer = UserIO.askString(nodeString + options);
			for (int i = 0; i < edges.size(); i++)
			{
				if (edges.get(i)[0].equals(answer))
				{
					return edges.get(i)[1];
				}
			}
		}
	}

	/**
	 * Prints the corresponding string.
	 */
	public void printString()
	{
		UserIO.printString(nodeString);
	}
}

class FileIO
{
	/**
	 * This reads a file line by line and returns its content as an array.
	 *
	 * @param {String} fileName - The name of the file.
	 * @return {String[]} - The content of the file line by line.
	 */
	public static String[] readFileLBL(String fileName)
	{
		ArrayList<String> fileContent = new ArrayList<String>();
        String line = null;
		try
		{
			FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
				fileContent.add(line);
            }   

            bufferedReader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		String[] result = new String[fileContent.size()];
		return fileContent.toArray(result);
	}
}

class UserIO
{
	/**
	 * Print the text to the console.
	 *
	 * @param {String} textToPrint - The string to show to the user.
	 */
	public static void printString(String textToPrint)
	{
			System.out.println(textToPrint);
	}

	/**
	 * This asks a string.
	 *
	 * @param {String} textToAsk - The accompanying text.
	 * @return {String} - The string the player has entered (in lower case).
	 */
	public static String askString(String textToAsk)
	{
		String input;
		while (true) {
			try {
				input = System.console().readLine(textToAsk + "\t");
				break;
			} catch (IllegalArgumentException ex) {
			}
		}
		return input;
	}
}
