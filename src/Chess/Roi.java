package Chess;

import java.util.ArrayList;

public class Roi extends Piece{

	public Roi(String nom,Joueur proprietaire){
		
		super(nom, proprietaire);
	}
	
	public String toString(){
		
		if(this.getProprietaire() == Joueur.Blanc){
			return "K";
		}
		else{
			return "k";
		}
	}
	
	public ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur){
		
		ArrayList<Case> AL = new ArrayList<Case>();
		
		for(int i = hauteur-1; i <= hauteur +1; i++){
			if(i > 0 && i < 9){
				for(int j = largeur-1; j <= largeur +1; j++){//v�rifier que la case existe est qu'elle est atteignable par le roi
					if(j > 0 && j < 9){
						if(D.findCase(i,j).getElem() == null || D.findCase(i,j).getElem().getEnnemi() == this.getProprietaire()){//v�rifier que la case d'arriv�e est vide ou occup� par un adversaire
							//v�rifier que cette case n'est attaquable par l'adversaire
							if(this.getProprietaire() == Joueur.Blanc){//cas Blanc
								if(!D.caseProtege(D.getPCN()).contains(D.findCase(i,j)) && !D.caseMenace(D.getPCN()).contains(D.findCase(i,j))){//v�rifier que la cible n'est ps menac� ou prot�g� par une pi�ce adverse
									AL.add(D.findCase(i, j));
								}
							}
							else{//cas Noir
								if(!D.caseProtege(D.getPCB()).contains(D.findCase(i,j)) && !D.caseMenace(D.getPCB()).contains(D.findCase(i,j))){
									AL.add(D.findCase(i, j));
								}
							}
						}
					}
				}
			}
		}
		return AL;
	}
	
	public ArrayList<Case> prisePossible(Damier D, int hauteur, int largeur){
		
ArrayList<Case> AL = new ArrayList<Case>();
		
		for(int i = hauteur-1; i <= hauteur +1; i++){
			if(i > 0 && i < 9){
				for(int j = largeur-1; j <= largeur +1; j++){//v�rifier que la case existe est qu'elle est atteignable par le roi
					if(j > 0 && j < 9){
						if(D.findCase(i,j).getElem() == null || D.findCase(i,j).getElem().getEnnemi() == this.getProprietaire()){//v�rifier que la case d'arriv�e est vide ou occup� par un adversaire
							AL.add(D.findCase(i, j));
							//on ne v�rifie pas que la case cible est d�fendue ou menac� car cette fonction est appel� seulement pour les rois donc meme si elle est menac� comme c'est le roi adverse qui dessus on peut prendre					
						}
					}
				}
			}
		}
		return AL;
	}
	
	public ArrayList<Case> protectionPossible(Damier D, int hauteur, int largeur){
		
		
	ArrayList<Case> AL = new ArrayList<Case>();
		
		for(int i = hauteur-1; i <= hauteur +1; i++){
			if(i > 0 && i < 9){
				for(int j = largeur-1; j <= largeur +1; j++){
					if(j > 0 && j < 9){
						if(D.findCase(i,j).getElem() != null && D.findCase(i,j).getElem().getEnnemi() != this.getProprietaire() && (i-hauteur)*(i-hauteur) + (j-largeur)*(j-largeur) !=0){
							if(this.getProprietaire() == Joueur.Blanc ){//cas Blanc
								AL.add(D.findCase(i, j));
							}
							else if (this.getProprietaire() == Joueur.Noir ){//cas Noir
								AL.add(D.findCase(i, j));
							}
						}
					}
				}
			}
		}
		return AL;
	}
	
}
