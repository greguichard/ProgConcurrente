import java.util.Scanner;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) throws InterruptedException {

		Fenetre fen = new Fenetre();
/*
		//Creation d'une linkedList
    LinkedList<Integer> laFile = new LinkedList<Integer>();

		File file = new File(laFile);

		// Creation des threads.
		Producteur p = new Producteur(file, 1000, 100, fen);
		Consommateur c1  = new Consommateur(file, 3000, fen);
		Consommateur c2  = new Consommateur(file, 3000, fen);

		// Lancement des threads.
		p.start();
		c1.start();
		c2.start();
*/
		// Attente frappe utilisateur pour stopper les threads.
		(new Scanner(System.in)).nextLine();
	}
}
