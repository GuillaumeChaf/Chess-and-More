package Chess;

import java.util.ArrayList;

public class Cavalier extends Piece{

	public Cavalier(String nom, Joueur proprietaire){
		
		super(nom,proprietaire);
	}
	
	public String toString(){
		
		if(this.getProprietaire() == Joueur.Blanc){
			return "C";
		}
		else{
			return "c";
		}
	}
	
	public ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur){
		
		ArrayList<Case> AL = new ArrayList<Case>();
		
		for(int i = -2; i <= 2; i++){
			for(int j = -2; j <= 2; j++){
				if(i != 0 && j != 0 && hauteur + i > 0 && hauteur +i <= 8 && largeur + j > 0 && largeur + j <= 8
					&& Math.abs(i) + Math.abs(j) == 3 && (D.findCase(hauteur + i, largeur +j).getElem() == null 
					|| D.findCase(hauteur +i, largeur +j).getElem().getEnnemi() == this.getProprietaire())){
						AL.add(D.findCase(hauteur+i,largeur+j));
				}
			}
		}
		return AL;
	}
	
	public ArrayList<Case> prisePossible(Damier D, int hauteur, int largeur){
		
		return this.deplacementPossible(D, hauteur, largeur);
	}
	
	public ArrayList<Case> protectionPossible(Damier D, int hauteur, int largeur){
		
	ArrayList<Case> AL = new ArrayList<Case>();
		
		for(int i = -2; i <= 2; i++){
			for(int j = -2; j <= 2; j++){
				if(i != 0 && j != 0 && hauteur + i > 0 && hauteur +i <= 8 && largeur + j > 0 && largeur + j <= 8
					&& Math.abs(i) + Math.abs(j) == 3 && D.findCase(hauteur + i, largeur +j).getElem() != null 
					&& D.findCase(hauteur +i, largeur +j).getElem().getEnnemi() != this.getProprietaire()){
						AL.add(D.findCase(hauteur+i,largeur+j));
				}
			}
		}
		return AL;
	}
}
