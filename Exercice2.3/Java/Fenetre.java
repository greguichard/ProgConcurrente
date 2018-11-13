
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;

public class Fenetre extends JFrame {
  private JPanel zoneBouton = new JPanel();
  private JPanel zoneModif = new JPanel();

  private JPanel container = new JPanel();

  private JLabel afffile = new JLabel("La file");
  private JLabel modProd = new JLabel("Producteur");
  private JLabel modCons1 = new JLabel("Consommateur 1");
  private JLabel modCons2 = new JLabel("Consommateur 2");

  private JButton boutonStart = new JButton("Start");
  private JButton boutonStop = new JButton("Stop");


  private LinkedList<Integer> laFile = new LinkedList<Integer>();
  private Producteur p;
  private Consommateur c1, c2;


  public Fenetre(){

    this.setTitle("Exercice 2.3");
    this.setSize(1000, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    boutonStop.setEnabled(false);

    //La zone nord
    container.setLayout(new BorderLayout());
    container.add(afffile, BorderLayout.NORTH);

    //La zone centrale
    zoneModif.setLayout(new GridLayout());
    zoneModif.add(modProd);
    zoneModif.add(modCons1);
    zoneModif.add(modCons2);
    container.add(zoneModif, BorderLayout.CENTER);

    //la zone sud
    zoneBouton.add(boutonStart);
    zoneBouton.add(boutonStop);
    container.add(zoneBouton, BorderLayout.SOUTH);

    //Ce sont maintenant nos classes internes qui écoutent nos boutons
    boutonStart.addActionListener(new BoutonStartListener());
    boutonStop.addActionListener(new BoutonStopListener());

    this.setContentPane(container);

    this.setVisible(true);

  }

  //Classe écoutant notre bouton start
  class BoutonStartListener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent arg0) {
      boutonStart.setEnabled(false);
      boutonStop.setEnabled(true);
      File file = new File(laFile);


  		p = new Producteur(file, 1000, 100, modProd, afffile);
      c1 = new Consommateur(file, 3000, modCons1, afffile);
      c2 = new Consommateur(file, 3000, modCons2, afffile);

  		p.start();
      c1.start();
      c2.start();

    }
  }

  //Classe écoutant notre buton stop
  class BoutonStopListener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent e) {
      boutonStart.setEnabled(true);
      boutonStop.setEnabled(false);

      p.stop();
      c1.stop();
      c2.stop();

    }
  }

}
