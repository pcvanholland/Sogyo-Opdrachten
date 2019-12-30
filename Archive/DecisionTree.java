import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester
{
	public static void main(String[] args)
	{
		String file = "decision-tree-data.txt";
		String delimiter = ", ";
		DecisionTree decTree = new DecisionTree(file, delimiter);

		decTree.startDeciding();
	}
}

class DecisionTree
{
	final int NUMBER_OF_ITEMS_IN_NODE = 2;
	final int NUMBER_OF_ITEMS_IN_EDGE = 3;

	final int POSITION_OF_NODEID_IN_RAW_NODE = 0;
	final int POSITION_OF_TEXT_IN_RAW_NODE = 1;

	// The variables below would not be needed if the edges
	// would have been a seperate class,,,
	final int POSITION_OF_ORIGIN_IN_RAW_EDGE = 0;
	final int POSITION_OF_DESTINATION_IN_RAW_EDGE = 1;
	final int POSITION_OF_ANSWER_IN_RAW_EDGE = 2;

	final int POSITION_OF_DESTINATION_IN_EDGE = 1;
	final int POSITION_OF_ANSWER_IN_EDGE = 0;

	// Save the nodes in an array list.
	ArrayList<Node> nodes = new ArrayList<Node>();

	String startingPoint;

	/**
	 * The constructor used to create a decision tree from a given file name.
	 *
	 * This function is too large, it could be improved by, at least, splitting the
	 * file content reading into a seperate function.
	 * Also having the edges as a seperate class might help?
	 *
	 * @param {String} file - The file to read the tree from.
	 * @param {String} delimiter - The delimiter to use for splitting the line into components.
	 */
	DecisionTree(String fileName, String delimiter)
	{
		String[] fileContents = FileIO.readFileLBL(fileName);

		// Temporarily store all the read edges,
		// they are coupled to the right nodes later on.
		ArrayList<String[]> edges = new ArrayList<String[]>();

		// Read every line, create a new node for nodes (duh,,,)
		// and save the edges in an array, for later use.
		for (String line : fileContents)
		{
			String[] splitLine = line.split(delimiter);

			// We assume any line with NUMBER_OF_ITEMS_IN_NODE seperated strings is a node...
			// We could also check for "splitLine[0].startsWith("N")" if necessary.
			if (splitLine.length == NUMBER_OF_ITEMS_IN_NODE)
			{
				addNode(splitLine);
			}
			// ...and any line with NUMBER_OF_ITEMS_IN_EDGE seperated strings is an edge.
			// We could also check for "splitLine[0].startsWith("N") && splitLine[1].startsWith("N")" if necessary.
			else if (splitLine.length == NUMBER_OF_ITEMS_IN_EDGE)
			{
				edges.add(splitLine);
			}
			else
			{
				UserIO.printString("This line was neither recognised as a node nor as an edge: " + line);
			}
		}

		addEdgesToNodes(edges);
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
		nodes.add(new Node(
			node[POSITION_OF_NODEID_IN_RAW_NODE],
			node[POSITION_OF_TEXT_IN_RAW_NODE]
		));
	}

	/**
	 * Adds edges to the currently known nodes.
	 * This could be elaborated to warn the user that there is an unused edge.
	 *
	 * @param {String[]} node - The node to process.
	 */
	private void addEdgesToNodes(ArrayList<String[]> edges)
	{
		// Loop over all edges and check whether there is any node
		// that may be the origin of the edge.
		for (String[] edge : edges)
		{
			for (Node node : nodes)
			{
				if (edge[POSITION_OF_ORIGIN_IN_RAW_EDGE].equals(node.nodeID))
				{
					node.addEdge(edge[
						POSITION_OF_ANSWER_IN_RAW_EDGE],
						edge[POSITION_OF_DESTINATION_IN_RAW_EDGE
					]);

					// We can break thereafter for it would be strange
					// for an edge to have multiple origin nodes and breaking
					// can save us a few cycles.
					// It would perhaps also be useful to warn a user when there is an
					// edge having multiple origin node.
					break;
				}
			}
		}
	}

	/**
	 * Start the decision tree.
	 *
	 * Ouch! This is a bad name for this function ;(
	 * Moreover it should probably be split into seperate functions.
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
			// If the node contains any branches (edges) ask the user for an answer.
			if (nodes.get(currentNodeLocation).edges.size() > 0)
			{
				String nextNode = nodes.get(currentNodeLocation).getNextNode();
				currentNodeLocation = getNodeLocation(nextNode);
			}
			// Otherwise it is an end node, so print the string in the node and break the loop.
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
						returns "-1" if the node was not found.
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
	 * If there are multiple nodes which can be used as begin point the
	 * decision tree is badly designed (or the code is) and the first one encountered is taken;
	 * that usually boils down to the one highest in the file.
	 *
	 * @param {ArrayList<String[]>} edges - The edges to use.
	 * @return {String} - The starting node.
	 */
	private String getStartingPoint(ArrayList<String[]> edges)
	{
		String beginPoint = "BogusBecauseAnyStringWouldSuffice";

		for (Node node : nodes)
		{
			boolean foundBeginPoint = false;

			for (String[] edge : edges)
			{
				if (node.nodeID.equals(edge[POSITION_OF_DESTINATION_IN_EDGE]))
				{
					foundBeginPoint = true;
					break;
				}
			}
			if (!foundBeginPoint)
			{
				beginPoint = node.nodeID;
			}
		}
		return beginPoint;
	}
}

class Node
{
	final int POSITION_OF_DESTINATION_IN_EDGE = 1;
	final int POSITION_OF_ANSWER_IN_EDGE = 0;

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
		// These positions ought not to be hardcoded but should
		// use the constants set above. Probably by using an
		// "Edge"-class.
		edges.add(new String[] {answer, destination});
	}

	/**
	 * Checks the answer given to this node and returns the nodeID of the next node.
	 *
	 * Ouch! This is a bad name for this function ;(
	 * Moreover it should probably be split into seperate functions.
	 *
	 * @return {String} - The destination ID of the node to go to next.
	 */
	public String getNextNode()
	{
		// We list the options for each node, for convenience.
		// Format: "[Option1/Option2/Option3]".
		String options = " [";
		for (int i = 0; i < edges.size(); i++)
		{
			options += edges.get(i)[POSITION_OF_ANSWER_IN_EDGE] + (i == edges.size() - 1 ? "]" : "/");
		}
		while (true)
		{
			// This is case-sensitive on purpose, since the anwers on the
			// questions might be case-sensitive as well.
			String answer = UserIO.askString(nodeString + options);
			for (String[] edge : edges)
			{
				if (edge[POSITION_OF_ANSWER_IN_EDGE].equals(answer))
				{
					return edge[POSITION_OF_DESTINATION_IN_EDGE];
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
		while (true)
		{
			try
			{
				input = System.console().readLine(textToAsk + "\t");
				break;
			} catch (IllegalArgumentException ex)
			{
				// Just ask again nicely.
			}
		}
		return input;
	}
}
