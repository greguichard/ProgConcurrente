import java.util.Random;
import java.util.LinkedList;
import javax.swing.JLabel;


public class Producteur extends Thread {

	private final File file;
	private final int tempsSommeil;
	private final int montantMax;
	private final Random generateur = new Random(java.lang.System.currentTimeMillis());
	private final JLabel pModif;
	private final JLabel affFile;

	public Producteur(File file, int tempsSommeil, int montantMax, JLabel pnlModif, JLabel affFile) {
		this.file = file;
		this.tempsSommeil = tempsSommeil;
		this.montantMax = montantMax;
		this.setDaemon(true);
		this.pModif = pnlModif;
		this.affFile = affFile;
	}

	public void run() {
		while (true) {
			try {
				int montant = 1 + generateur.nextInt(this.montantMax);
				this.file.empiler(montant);
		    pModif.setText("Ce producteur ajoute " + montant);
				String result = file.getFile();
				affFile.setText(result);
				sleep(this.tempsSommeil);
			}
			catch (InterruptedException e) {}
		}

	}
}
