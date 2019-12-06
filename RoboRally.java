import java.util.ArrayList;

public class RoboRally
{
	public static void main(String[] args)
	{
		Robot robot = new Robot();

		robot.PrintStatus();

		robot.GoForwards(2);
		robot.GoBackwards();
		robot.GoTurnLeft();

		robot.PrintStatus();

		robot.ExecuteOrders();
		robot.PrintStatus();
	}
}

class Robot
{
	Position pos;

	// Store orders as an array of arrays [[forward/backward, rotation], [order2 ...]];
	ArrayList<int[]> orderQueue = new ArrayList<int[]>();

	/**
	 * Initialises the default settings for a robot.
	 */
	public Robot()
	{
		pos = new Position();
	}

	/**
	 * Initialises the given settings for a robot.
	 *
	 * @param {int} posX - The initial x-coordinate. (Can be negative.)
	 * @param {int} posY - The initial y-coordinate. (Can be negative.)
	 * @param {int} theta - The initial angle. (Can be negative.)
	 */
	public Robot(int posX, int posY, int theta)
	{
		pos = new Position(posX, posY, theta);
	}

	/**
	 * Initialises the given settings for a robot.
	 *
	 * @param {int} posX - The initial x-coordinate. (Can be negative.)
	 * @param {int} posY - The initial y-coordinate. (Can be negative.)
	 * @param {String} facing - The name of the cardinal direction which the robot
	 *		will be facing initially (will be converted to an int internally).
	 */
	public Robot(int posX, int posY, String facing)
	{
		int theta = GetAngleFromFacingString(facing);
		pos = new Position(posX, posY, theta);
	}

	/**
	 * Moves the robot the given amount of tiles.
	 *
	 * @param {int} dx - The amount of x-tiles to move. (Can be negative.)
	 * @param {int} dy - The amount of y-tiles to move. (Can be negative.)
	 */
	private void Move(int dx, int dy)
	{
		pos.Move(dx, dy);
	}

	/**
	 * Rotates the robot the given amount of degrees.
	 *
	 * @param {int} dTheta - The amount of degrees to rotate. (Can be negative.)
	 */
	private void Rotate(int dTheta)
	{
		pos.Rotate(dTheta);
	}

	/**
	 * Prints the current status of the robot.
	 */
	public void PrintStatus()
	{
		int[] currentPosition = GetPosition();
		System.out.print("Coordinates: [" + currentPosition[0] + ", " + currentPosition[1] + "]\t");
		System.out.println("Facing: " + GetFacingStringFromAngle(currentPosition[2]));

		//PrintOrders();
	}

	/**
	 * Prints the current orderQueue of the robot.
	 */
	private void PrintOrders()
	{
		System.out.println("Order Queue:");
		if (orderQueue.size() == 0)
		{
			System.out.println("[ ]");
			return;
		}
		for (int i = 0; i < orderQueue.size(); ++i)
		{
			System.out.println("[" + orderQueue.get(i)[0] + ", " + orderQueue.get(i)[1] + "]");
		}
	}

	/**
	 * Returns the current position of the robot.
	 */
	private int[] GetPosition()
	{
		return pos.GetPosition();
	}

	/**
	 * Converts a given facing string to an angle.
	 *
	 * @param {String} facing - The string to be converted. (Only the cardinal directions supported.)
	 * @return {int} - The angle belonging to the cardinal direction.
	 */
	private int GetAngleFromFacingString(String facing)
	{
		switch (facing)
		{
			case "North":
				return 0;
			case "East":
				return 90;
			case "South":
				return 180;
			case "West":
				return 270;
			default:
				System.out.println("There was an invalid facing given in GetAngleFromFacingString. \"North\" used.");
		}
		return 0;
	}

	/**
	 * Converts a given angle to a facing string.
	 * (Only cardinal directions are supported.)
	 *
	 * @param {int} angle - The angle belonging to convert.
	 * @return {String} - The string to be used.
	 */
	private String GetFacingStringFromAngle(int angle)
	{
		if (angle > 315 && angle < 360 || angle <= 45)
		{
			return "North";
		}
		if (angle < 135)
		{
			return "East";
		}
		if (angle < 225)
		{
			return "South";
		}
		if (angle <= 315)
		{
			return "West";
		}
		System.out.println("There was an invalid angle given at GetFacingStringFromAngle.");
		return "";
	}

	/**
	 * Moves the robot one step forward or backwards, depending on the given direction.
	 *
	 * @param {boolean} inLOS - Whether to move forward inLOS == true, or backwards.
	 */
	private void MoveForwardsOrBackwards(boolean inLOS)
	{
		int modifier = inLOS ? 1 : -1;
		switch (GetFacingStringFromAngle(GetPosition()[2]))
		{
			case "North":
				Move(0, 1 * modifier);
				break;
			case "East":
				Move(1 * modifier, 0);
				break;
			case "South":
				Move(0, -1 * modifier);
				break;
			case "West":
				Move(-1 * modifier, 0);
				break;
			default:
				System.out.println("There was an invalid facing returned by GetFacingStringFromAngle in MoveForwards.");
		}
	}

	/**
	 * Moves the robot forward the specified number of times.
	 *
	 * @param {int} amount - The amount of times to move.
	 */
	private void MoveForwards(int amount)
	{
		for (int i = 0; i < amount; i++)
		{
			MoveForwardsOrBackwards(true);
		}
	}

	/**
	 * Moves the robot backward the specified number of times.
	 *
	 * @param {int} amount - The amount of times to move.
	 */
	private void MoveBackwards(int amount)
	{
		for (int i = 0; i < amount; i++)
		{
			MoveForwardsOrBackwards(false);
		}
	}

	/**
	 * Adds an order to the queue.
	 *
	 * @param {int[]} order - The order to add. { forward/backward motion, angle to turn }
	 */
	private void AddOrder(int[] order)
	{
		orderQueue.add(order);
	}

	/**
	 * Tells the the robot to move backwards.
	 */
	public void GoForwards()
	{
		int[] order = { 1, 0 };
		AddOrder(order);
	}

	/**
	 * Tells the the robot to move forwards a specified amount of steps.
	 *
	 * @param {int} steps - The amount of steps to go forward.
	 */
	public void GoForwards(int steps)
	{
		if (steps < 1 || steps > 3)
		{
			System.out.println("Sorry, apparently it is not allowed to go forward that many steps!");
			return;
		}
		int[] order = { steps, 0 };
		AddOrder(order);
	}

	/**
	 * Tells the the robot to move backwards.
	 */
	public void GoBackwards()
	{
		int[] order = { -1, 0 };
		AddOrder(order);
	}

	/**
	 * Tells the the robot to move backwards a specified amount of steps.
	 *
	 * @param {int} steps - The amount of steps to go back.
	 */
	private void GoBackwards(int steps)
	{
		int[] order = { -steps, 0 };
		AddOrder(order);
	}

	/**
	 * Tells the the robot to turn left.
	 */
	public void GoTurnLeft()
	{
		int[] order = { 0, -90 };
		AddOrder(order);
	}

	/**
	 * Tells the the robot to turn right.
	 */
	public void GoTurnRight()
	{
		int[] order = { 0, 90 };
		AddOrder(order);
	}

	/**
	 * Execute orders in the order they were given.
	 */
	public void ExecuteOrders()
	{
		// Execute orders one by one.
		for (int i = 0; i < orderQueue.size(); ++i)
		{
			int movement = orderQueue.get(i)[0];
			int rotation = orderQueue.get(i)[1];
			if (movement < 0)
			{
				MoveBackwards(movement * -1);
			}
			else if (movement > 0)
			{
				MoveForwards(movement);
			}
			if (rotation != 0)
			{
				Rotate(rotation);
			}
		}

		// Clear the order queue.
		orderQueue.clear();
	}
}

class Position
{
	int x;
	int y;
//	int z;

	int rotation;

	/**
	 * Default initialisation of the position of the object.
	 */
	public Position()
	{
		x = 0;
		y = 0;

		rotation = 0;
	}

	/**
	 * Initialises the position of the object with given values.
	 *
	 * @param {int} x - The initial x-value of the position.
	 * @param {int} y - The initial y-value of the position.
	 * @param {int} rotation - The initial rotation of the object.
	 */
	public Position(int x0, int y0, int Theta0)
	{
		x = x0;
		y = y0;

		rotation = Theta0;
		CorrectRotation();
	}

	/**
	 * Moves the object the given amount of tiles.
	 *
	 * @return {Object} - The amount of y-tiles to move. (Can be negative.)
	 *			x - The x-coordinate. (Can be negative.)
	 * 			y - The y-coordinate. (Can be negative.)
	 * 			theta - The current rotation.
	 */
	public int[] GetPosition()
	{
		int[] pos = new int[] { x, y, rotation };
		return pos;
	}

	/**
	 * Moves the object the given amount of tiles.
	 *
	 * @param {int} dx - The amount of x-tiles to move. (Can be negative.)
	 * @param {int} dy - The amount of y-tiles to move. (Can be negative.)
	 */
	public void Move(int dx, int dy)
	{
		x += dx;
		y += dy;
	}

	/**
	 * Rotates the object the given amount of degrees.
	 *
	 * @param {int} dTheta - The amount of degrees to rotate. (Can be negative.)
	 */
	public void Rotate(int dTheta)
	{
		rotation += dTheta;
		CorrectRotation();
	}

	/**
	 * Correct the rotation such that it falls within a circle.
	 * This should be called every time the rotation changes.
	 */
	private void CorrectRotation()
	{
		// If the rotation becomes negative after the operation, add a full circle.
		while (rotation < 0)
		{
			rotation += 360;
		}

		// If the rotation becomes larger than 360 after the operation, add a full circle.
		while (rotation >= 360)
		{
			rotation -= 360;
		}
	}
}