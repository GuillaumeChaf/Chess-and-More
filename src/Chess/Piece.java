package Chess;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JEditorPane;

public abstract class Piece extends Element{
	
	private Joueur proprietaire;
	
	public Piece(String nom, Joueur proprietaire){
		
		super(nom);
		this.proprietaire = proprietaire;
		
	}

	public abstract String toString();
	public abstract ArrayList<Case> deplacementPossible(Damier D, int hauteur, int largeur);
	public abstract ArrayList<Case> prisePossible(Damier D, int hauteur, int largeur);
	public abstract ArrayList<Case> protectionPossible(Damier D, int hauteur, int largeur);
	
	public Joueur getEnnemi(){
		
		if(this.proprietaire == Joueur.Blanc){
			return Joueur.Noir;
		}
		else{
			return Joueur.Blanc;
		}
	}
		
	public Joueur getProprietaire(){
			
			return this.proprietaire;
		
	}
	
	public boolean estCloue(Damier D, int hauteur, int largeur){// fonction qui determine si la piece est cloué a cause du roi ou non.
		// !!!!!! A MODIFIER LORS DE L'INSERTION DE BONUS !!!!!!
		
		boolean resultat = true;//on part de l'hypothese que la piece est bloqué et on verifié qui toute les options qui permettrat de la débloquer, dès qu'on en trouve une, la variable prends false
		ArrayList<Case> AL;
		if(this.proprietaire == Joueur.Blanc){
			AL = D.getPCB();
		}
		else{
			AL = D.getPCN();
		}
		
		// on verifie l'alignement : 3 cas possible pour qu'une piece soit cloué:
		Case CaseK = D.findKing(AL);
		if(hauteur == CaseK.getHauteur()){// si elle est a la meme hauteur que le roi
			
			int pkl = CaseK.getLargeur() - largeur;//position roi largeur, la position du roi par rapport a la pièce sur la largeur
			for(int i = largeur + (pkl/Math.abs(pkl)); i != CaseK.getLargeur() && resultat == true; i=i+(pkl/Math.abs(pkl))){//on verifie qu'il n'y a aucune piece entre la piece et le roi
				if(D.findCase(hauteur,i).getElem() != null){
					resultat = false;
				}
			}
			boolean trouve = false; // Si on trouve un piece dans le prolongement toi piece ou pas, des qu'on en trouve une, cette variable prends true
			for(int i = largeur + (pkl/Math.abs(pkl))*(-1); i > 0 && i < 9 && trouve == false && resultat == true; i = i+(pkl/Math.abs(pkl))*(-1)){//on va chercher la piece suivante dans l'alignement du roi et de la piece
				System.out.println(i);
				if(D.findCase(hauteur, i).getElem() != null){//dès qu'on trouve une pièce
					trouve = true;
					ArrayList<String> valeurAcc = new ArrayList<>(Arrays.asList("D","d","T","t"));
					if(!(D.findCase(hauteur, i).getElem().getEnnemi() != this.getEnnemi() && valeurAcc.contains((D.findCase(hauteur, i).getElem().toString())))){//il faut que ce soit une dame ou une tour adverse pour que ce soit vrai
						resultat = false;
					}
				}
			}
			resultat = (resultat == true) && (trouve == true);
			
		}
		
		else if(largeur == CaseK.getLargeur()){//si elle est a la meme largeur que le roi
			
			int pkh = CaseK.getHauteur() - hauteur;//position roi hauteur, la position du roi par rapport a la pièce sur la hauteur
			for(int i = hauteur + (pkh/Math.abs(pkh)); i != CaseK.getHauteur() && resultat == true; i=i+(pkh/Math.abs(pkh))){//on verifie qu'il n'y a aucune piece entre la piece et le roi
				if(D.findCase(i,largeur).getElem() != null){
					resultat = false;
				}
			}
			boolean trouve = false;
			for(int i = hauteur + (pkh/Math.abs(pkh))*(-1); i > 0 && i < 9 && trouve == false && resultat == true; i = i+(pkh/Math.abs(pkh))*(-1)){//on va chercher la piece suivante dans l'alignement du roi et de la piece
				if(D.findCase(i, largeur).getElem() != null){//dès qu'on trouve une pièce
					trouve = true;
					ArrayList<String> valeurAcc = new ArrayList<>(Arrays.asList("D","d","T","t"));
					if(!(D.findCase(i, largeur).getElem().getEnnemi() != this.getEnnemi() && valeurAcc.contains((D.findCase(i, largeur).getElem().toString())))){//il faut que ce soit une dame ou une tour adverse pour que ce soit vrai
						resultat = false;
					}
				}
			}
			resultat = (resultat == true) && (trouve == true);
		}
		
		else if(Math.abs(largeur - CaseK.getLargeur()) == Math.abs(hauteur - CaseK.getHauteur())){// si elle est dans la diagonale du roi
			
			int pkl = CaseK.getLargeur() - largeur;
			int pkh = CaseK.getHauteur() - hauteur;
			for(int i = 1; i < Math.abs(CaseK.getHauteur() - hauteur) && resultat == true; i++){
				if(D.findCase(hauteur + (pkh/Math.abs(pkh) * i), largeur + (pkl/Math.abs(pkl))).getElem()!= null){
					resultat = false;
				}	
			}
			boolean trouve = false;
			for(int i = 1; hauteur + (((pkh/Math.abs(pkh))*-1) * i) > 0 && hauteur + (((pkh/Math.abs(pkh))*-1) * i) < 9 && largeur + (((pkl/Math.abs(pkl))*-1) * i) > 0 && largeur + (((pkl/Math.abs(pkl))*-1) * i) < 9 && trouve == false && resultat == true ;i++){
				if(D.findCase(hauteur + (((pkh/Math.abs(pkh))*-1) * i), largeur + (((pkl/Math.abs(pkl))*-1) * i)).getElem() != null){
					trouve = true;
					ArrayList<String> valeurAcc = new ArrayList<>(Arrays.asList("D","d","F","f"));

					if(!(D.findCase(hauteur + (((pkh/Math.abs(pkh))*-1) * i), largeur + (((pkl/Math.abs(pkl))*-1) * i)).getElem().getEnnemi() != this.getEnnemi() 
					&& valeurAcc.contains((D.findCase(hauteur + (((pkh/Math.abs(pkh))*-1) * i), largeur + (((pkl/Math.abs(pkl))*-1) * i)).getElem().toString())))){
						
						resultat = false;
					}
				}
			}
			resultat = (resultat == true) && (trouve == true);//du a un bug: si aucune piece avait ete trouve dans l'alignement : affiché quand meme true
		}
		else{
			resultat = false;
		}
		return resultat;
	}
	
	public ArrayList<Case> deplacementPossBloc(Damier D, int hauteur, int largeur){//Les deplacements possible d'une piece si elle est bloqué a cause de son roi
		
		ArrayList<Case> AL = new ArrayList<Case>();
		int pkh = 0;
		int pkl = 0;
		
		if(this.proprietaire == Joueur.Blanc){
			pkl = D.findKing(D.getPCB()).getLargeur() - largeur;
			pkh = D.findKing(D.getPCB()).getHauteur() - hauteur;
		}
		else{
			pkl = D.findKing(D.getPCN()).getLargeur() - largeur;
			pkh = D.findKing(D.getPCN()).getHauteur() - hauteur;
		}
		
		if(this.estCloue(D,hauteur,largeur) == true){

			int i = 1;
			boolean trouve = false;
			while(trouve == false){
					if(this.deplacementPossible(D,hauteur,largeur).contains(D.findCase((hauteur+ (int)((Math.signum(pkh))*-i)), largeur+(int)(Math.signum(pkl))*-i))){
						AL.add(D.findCase(hauteur+ (int)((Math.signum(pkh))*-i), largeur+(int)((Math.signum(pkl))*-i)));
				}
				if(D.findCase(hauteur+ (int)((Math.signum(pkh))*-i), largeur+ (int)(Math.signum(pkl))*-i).getElem() != null){//dès qu'on rencontre une piece on arrete la boucle
					trouve = true;
				}
				i++;
			}
		}
		return AL;
	}
}
