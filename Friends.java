
import java.util.*;
import java.io.*;

public class Friends {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graph g = null;

		boolean goodFile = false;
		while (!goodFile) {
			goodFile = true;
			System.out.print("Enter the friends graph file: ");
			String graphFile = sc.next();
			// String graphFile = "friends.txt";

			try {
				g = new Graph(graphFile);
			} catch (FileNotFoundException e) {
				System.out.println("You have entered an incorrect graph file.\n");
				goodFile = false;
			}
		}

		boolean cont = true;
		boolean first = true;
		while (cont) {
			System.out.println();

			if (!first)
				System.out.println("------------------------");

			System.out.println("(1) Shortest Intro Chain");
			System.out.println("(2) Groups at School");
			// System.out.println("(3) Connectors");
			System.out.println("(3) Quit");
			System.out.println("(4) Print Out Graph");
			System.out.println("(5) Print Out Graph File");
			System.out.print("Enter Option --> ");

			String input = null;
			int option = 0;
			try {
				input = sc.next();
				option = Integer.parseInt(input);
				if ((option <= 0) || (option >= 7))
					System.out.println(option + " is not in the valid range for input.");
			} catch (Exception e) {
				System.out.println("'" + input + "' is not a valid input.");
				sc.nextLine();
			}

			if (option == 1)
				g.shortestChain();

			if (option == 2)
				g.groups();

			// if (option == 3)
			// g.connectors();

			if (option == 3)
				cont = false;

			if (option == 4)
				g.printGraph();

			if (option == 5)
				g.printGraphFile();

			first = false;
		}
		sc.close();
		System.exit(0);
	}
}