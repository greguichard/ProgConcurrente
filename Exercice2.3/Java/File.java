import java.util.LinkedList;

public class File {

  private LinkedList<Integer> laFile;

  public File (LinkedList<Integer> f) {
    this.laFile = f;
  }

	public synchronized void empiler(int nombre) throws InterruptedException {
    //Si le nombre est négatif on sort de la méthode
    if (nombre <= 0) return;
    //Tant que la file est supérieur à 20, on attends pour rajouter un nombre dans la file
    while (this.laFile.size() >= 20) { wait(); }
    //On ajoute le nombre a la file
    laFile.add(nombre);
    notifyAll();
	}

	public synchronized int depiler() throws InterruptedException {
    //Si la file est vide, on attends pour enlever en nombre
    while (this.laFile.size() == 0) { wait(); }
    //On enleve le premier nombre de la file
    int PremierNombre = laFile.get(0);
    laFile.remove();
    notifyAll();
    return PremierNombre;
	}

  public String getFile() {
    String result;
    int i;
    result = "File = < ";
    for (i=0 ; i<this.laFile.size() ; i++) {
      result += this.laFile.get(i) + ", ";
    }
    result += "<";
    return result;
  }

  public int getNombre(int champs) {
    return laFile.get(champs);
  }


}
