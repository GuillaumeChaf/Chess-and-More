package Chess;

public class Partie {

	private Damier Damier;
	private Joueur Blanc;
	private Joueur Noir;
	
	public Partie(){
		
		this.Blanc = Joueur.Blanc;
		this.Noir = Joueur.Noir;
		this.Damier = new Damier();
	}
	
	public boolean echecEstMat(){
		
		return true;
	}
	
	public void Tour(){
		
		
	}
}
