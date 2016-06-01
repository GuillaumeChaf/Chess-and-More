package Chess;

import java.util.ArrayList;

public class Fou extends Piece{
	
	public Fou(String nom,Joueur proprietaire){
		
		super(nom,proprietaire);
	}

	public String toString(){
		
		if(this.getProprietaire() == Joueur.Blanc){
			return "F";
		}
		else{
			return "f";
		}
	}
	
	public ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur){
		
	ArrayList<Case> AL = new ArrayList<Case>();
	
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(i != 0 && j != 0){
					int facteur = 1;
					while(0 < hauteur+(i*facteur) && hauteur+(i*facteur) <= 8
							&& largeur + (j*facteur) > 0 && largeur + (j*facteur) <= 8 &&
							D.findCase(hauteur + (i*facteur),largeur + (j*facteur)).getElem() == null){
							AL.add(D.findCase(hauteur +(i*facteur),largeur + (j*facteur)));
							facteur++;
					}
					if(0 < hauteur+(i*facteur) && hauteur+(i*facteur) <= 8
							&& largeur + (j*facteur) > 0 && largeur + (j*facteur) <= 8 &&
							D.findCase(hauteur + (i*facteur),largeur + (j*facteur)).getElem().getEnnemi() == this.getProprietaire()){
						AL.add(D.findCase(hauteur +(i*facteur),largeur + (j*facteur)));
					}
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
		
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(i != 0 && j != 0){
					int facteur = 1;
					while(0 < hauteur+(i*facteur) && hauteur+(i*facteur) <= 8
							&& largeur + (j*facteur) > 0 && largeur + (j*facteur) <= 8 &&
							D.findCase(hauteur + (i*facteur),largeur + (j*facteur)).getElem() == null){
							facteur++;
					}
					if(0 < hauteur+(i*facteur) && hauteur+(i*facteur) <= 8
							&& largeur + (j*facteur) > 0 && largeur + (j*facteur) <= 8 &&
							D.findCase(hauteur + (i*facteur),largeur + (j*facteur)).getElem().getEnnemi() != this.getProprietaire()
							&& i*i+j*j != 0){
						AL.add(D.findCase(hauteur +(i*facteur),largeur + (j*facteur)));
					}
				}
			}
		}
		return AL;
	}
}
