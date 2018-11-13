import java.util.LinkedList;
import javax.swing.JLabel;

public class Consommateur extends Thread {

	private final File file;
	private final int tempsSommeil;
	private final JLabel pModif;
	private final JLabel affFile;

	public Consommateur(File file, int tempsSommeil, JLabel pnlModif, JLabel affFile) {
		this.file = file;
		this.tempsSommeil = tempsSommeil;
		this.setDaemon(true);
		this.pModif = pnlModif;
		this.affFile = affFile;
	}

	public void run() {
		while (true) {
			try {
				int montant = this.file.depiler();
				pModif.setText("Ce consommateur retire " + montant);
				String result = file.getFile();
				affFile.setText(result);
				sleep(this.tempsSommeil);
			}
			catch (InterruptedException e) {}
		}
	}
}
