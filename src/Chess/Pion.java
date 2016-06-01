package Chess;

import java.util.ArrayList;

public class Pion extends Piece{

	public Pion(String nom,Joueur proprietaire){
		
		super(nom,proprietaire);
	}
	
	public String toString(){
		
		if(this.getProprietaire() == Joueur.Blanc){
			return "P";
		}
		else{
			return "p";
		}
	}
	
	
	public ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur){//l'hauteur et la largeur correspond a la position du this, et le damier sert a savoir ou sont les autres pieces.
		//Cette fonction renvoit toute les cases que le pion peut prendre sur un tour
		
		ArrayList<Case> AL = new ArrayList<Case>();
		
		if(this.getProprietaire() == Joueur.Blanc){//cas pion Blanc
			
			if(D.findCase(hauteur + 1,largeur).getElem() == null && hauteur != 8){//avance d'une case
				AL.add(D.findCase(hauteur+1,largeur));
			}
			if(hauteur == 2 && D.findCase(hauteur+2, largeur).getElem() == null && D.findCase(hauteur+1, largeur).getElem() == null){//avance de 2 cases
				AL.add(D.findCase(hauteur+2, largeur));
			}
			if(largeur != 1 && hauteur != 8 &&  D.findCase(hauteur+1, largeur-1).getElem() != null && D.findCase(hauteur+1, largeur-1).getElem().getEnnemi() == Joueur.Blanc){//mange a gauche
				AL.add(D.findCase(hauteur+1, largeur-1));
			}
			if(largeur != 8 && hauteur != 8 &&  D.findCase(hauteur+1, largeur+1).getElem() != null && D.findCase(hauteur+1, largeur+1).getElem().getEnnemi() == Joueur.Blanc){//mange a droite
			AL.add(D.findCase(hauteur+1, largeur+1));
			}
		}
		else{//cas pion noir
			
			if(D.findCase(hauteur - 1,largeur).getElem() == null && hauteur != 1){
				AL.add(D.findCase(hauteur-1,largeur));
			}
			if(hauteur == 7 && D.findCase(hauteur-2, largeur).getElem() == null && D.findCase(hauteur-1, largeur).getElem() == null){
				AL.add(D.findCase(hauteur-2, largeur));
			}
			if(largeur != 1 && hauteur != 1 &&  D.findCase(hauteur-1, largeur-1).getElem() != null && D.findCase(hauteur-1, largeur-1).getElem().getEnnemi() == Joueur.Noir){
				AL.add(D.findCase(hauteur-1, largeur-1));
			}
			if(largeur != 8 && hauteur != 1 &&  D.findCase(hauteur-1, largeur+1).getElem() != null && D.findCase(hauteur-1, largeur+1).getElem().getEnnemi() == Joueur.Noir){
			AL.add(D.findCase(hauteur-1, largeur+1));
			}
		}
		return AL;
	}
	
	public ArrayList<Case> prisePossible(Damier D, int hauteur, int largeur){
		
		ArrayList<Case> AL = new ArrayList<Case>();
		
		if(this.getProprietaire() == Joueur.Blanc){
			if(largeur != 1 && hauteur != 8 &&  (D.findCase(hauteur+1, largeur-1).getElem() == null || D.findCase(hauteur+1, largeur-1).getElem().getEnnemi() == Joueur.Blanc)){//mange a gauche
				AL.add(D.findCase(hauteur+1, largeur-1));
			}
			if(largeur != 8 && hauteur != 8 &&  (D.findCase(hauteur+1, largeur+1).getElem() == null || D.findCase(hauteur+1, largeur+1).getElem().getEnnemi() == Joueur.Blanc)){//mange a droite
			AL.add(D.findCase(hauteur+1, largeur+1));
			}
		}
		else{
			if(largeur != 1 && hauteur != 1 &&  (D.findCase(hauteur-1, largeur-1).getElem() == null || D.findCase(hauteur-1, largeur-1).getElem().getEnnemi() == Joueur.Noir)){
				AL.add(D.findCase(hauteur-1, largeur-1));
			}
			if(largeur != 8 && hauteur != 1 &&  (D.findCase(hauteur-1, largeur+1).getElem() == null || D.findCase(hauteur-1, largeur+1).getElem().getEnnemi() == Joueur.Noir)){
			AL.add(D.findCase(hauteur-1, largeur+1));
			}
		}
		
		return AL;
	}

	public ArrayList<Case> protectionPossible(Damier D, int hauteur, int largeur){
		
		ArrayList<Case> result = new ArrayList<Case>();
		
		if(this.getProprietaire() == Joueur.Blanc){//cas blanc
			if(largeur != 1 && hauteur != 8 && D.findCase(hauteur+1, largeur-1).getElem() != null && D.findCase(hauteur+1, largeur-1).getElem().getEnnemi() == Joueur.Noir){
				result.add(D.findCase(hauteur+1, largeur-1));
			}
			if(largeur != 8 && hauteur != 8 && D.findCase(hauteur+1, largeur+1).getElem() != null &&  D.findCase(hauteur+1, largeur+1).getElem().getEnnemi() == Joueur.Noir){
				result.add(D.findCase(hauteur+1, largeur+1));
			}
		}
		else{//cas noir
			if(largeur != 1 && hauteur != 1 && D.findCase(hauteur-1, largeur-1).getElem() != null &&  D.findCase(hauteur-1, largeur-1).getElem().getEnnemi() == Joueur.Blanc){
				result.add(D.findCase(hauteur-1, largeur-1));
			}
			if(largeur != 8 && hauteur != 1 && D.findCase(hauteur-1, largeur+1).getElem() != null &&  D.findCase(hauteur-1, largeur+1).getElem().getEnnemi() == Joueur.Blanc){
				result.add(D.findCase(hauteur-1, largeur+1));
			}
		}
		return result;
	}
}
