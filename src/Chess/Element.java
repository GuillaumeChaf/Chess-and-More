package Chess;

import java.util.ArrayList;

public abstract class Element {
	
	private String nom;

	public Element(String nom){
		
		this.nom = nom;
	}
	
	public abstract String toString();
	public abstract Joueur getEnnemi();
	public abstract ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur);//renvoit toute les cases que peut prendre la piece à ces coordonnes sur un tour.
	public abstract ArrayList<Case> prisePossible(Damier D, int hauteur, int largeur);//renvoit toute les cases ou la piece pourrait faire une prise si il y avait une autre piece.
	public abstract ArrayList<Case> protectionPossible(Damier D, int hauteur, int largeur);//renvoit toute les cases remplit que protege la piece sur ces coordonnes
	public abstract boolean estCloue(Damier D, int hauteur, int largeur);// return true si la piece sur cette case est cloué, false sinon
	public abstract ArrayList<Case> deplacementPossBloc(Damier D,int hauteur, int largeur);
}
