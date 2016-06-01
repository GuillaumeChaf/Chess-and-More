package Chess;

import java.util.ArrayList;

public class Damier {

	private Case tabCase[] = new Case[64];
	private ArrayList<Case> PCB;//(PullCaseBlanche) case posseder par les Blanc ce qui nous relie egalement aux pièces qu'il possede.
	private ArrayList<Case> PCN;//pratique pour connaitre instentanément toute les pièces que possèdent un joueur et ses cases	
	
	
	public Damier(){ // Tableau de 64 cases, dans l'ordre suivant : [0] = 11, [1] = 12, [2] = 13, ... [8] = 21 ...
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				this.tabCase[j + i*8] = new Case(i+1,j+1,null);
			}
		}
		this.PCB = new ArrayList<Case>();
		this.PCN = new ArrayList<Case>();
	}
	
	public String toString(){
		
		String s = "";
		
		for(int i = 7; i >= 0; i--){	
			s += "  --------------------------------\n" + (i+1) +" ";
			for(int j = 0; j < 8; j++){
				s += "| ";
				if(tabCase[i*8 + j].getElem()!= null){
				s += tabCase[i*8 + j].getElem();	
				}
				else{
					s += " ";
				}
				s += " ";
			}
			s += "|\n";
		}
		s += "  --------------------------------\n";
		s += "    A   B   C   D   E   F   G   H";
		return s;
	}
	
	public Case findCase(int hauteur, int largeur){//trouve la case du tableau qui coorespond a la hauteur et la largeur donné
		
		return tabCase[(hauteur-1)*8 + (largeur-1)];
	}
	
	public void setCase(int hauteur, int largeur, Element elem){
		
		this.findCase(hauteur,largeur).setElem(elem);
		if(elem != null && elem.getEnnemi() != null && elem.getEnnemi() == Joueur.Blanc){
			this.PCN.add(findCase(hauteur,largeur));
		}
		else if(elem != null && elem.getEnnemi() != null && elem.getEnnemi() == Joueur.Noir){
			this.PCB.add(findCase(hauteur,largeur));
		}
	}
	
	public void supprimer(int hauteur, int largeur){//supprime le contenu de la case ainsi que sa presence dans sa liste
		
		this.setCase(hauteur, largeur,null);
		boolean trouve = false;
		for(int i = 0; trouve == false && i < this.PCN.size() ;i++){
			if(this.PCN.get(i).getHauteur() == hauteur && this.PCN.get(i).getLargeur() == largeur){
				trouve = true;
				this.PCN.remove(i);
			}
		}
		for(int i = 0; trouve == false && i < this.PCB.size() ;i++){
			if(this.PCB.get(i).getHauteur() == hauteur && this.PCB.get(i).getLargeur() == largeur){
				trouve = true;
				this.PCB.remove(i);
			}
		}
	}
	
	public Case findKing(ArrayList<Case> AL){
		
		Case resultat = null;
		for(int i = 0; i < AL.size() && resultat ==	 null; i++){
			
			if(AL.get(i).getElem().toString() == "K" || AL.get(i).getElem().toString() == "k"){
				resultat = AL.get(i);	
			}
		}
		return resultat;
	}
	
	public ArrayList<Case> Deplacement(int hauteur, int largeur){
		
		if(caseBloque(hauteur,largeur) == true){
			return (this.findCase(hauteur, largeur).getElem().deplacementPossBloc(this,hauteur,largeur));
		}
		else{
			return this.findCase(hauteur, largeur).getElem().deplacementPossible(this, hauteur, largeur);
		}
	}
	
	public ArrayList<Case> Prise(int hauteur, int largeur){ 
		
		return this.findCase(hauteur, largeur).getElem().prisePossible(this, hauteur, largeur);
	}
	
	public ArrayList<Case> Protection(int hauteur, int largeur){
		
		return this.findCase(hauteur, largeur).getElem().protectionPossible(this, hauteur, largeur);
		
	}
	
	public ArrayList<Case> getPCB(){
		
		return PCB;
	}
	
	public ArrayList<Case> getPCN(){
		
		return PCN;
	}
	

	public void Remplir(){//on dispose les pions sur les cases et on remplit les ArrayList de ces cases
		
	
		for(int i = 1; i < 9; i++){
		
			setCase(2,i,new Pion("PionBlanc",Joueur.Blanc));
			setCase(7,i,new Pion("PionNoir",Joueur.Noir));
			
		}
		
		setCase(1,1,new Tour("TourBlanche",Joueur.Blanc));
		setCase(8,1,new Tour("TourNoire",Joueur.Noir));
		setCase(1,2,new Cavalier("CavalierBlanc",Joueur.Blanc));
		setCase(8,2,new Cavalier("CavalierNoir",Joueur.Noir));
		setCase(1,3,new Fou("FouBlanc",Joueur.Blanc));
		setCase(8,3,new Fou("FouNoir",Joueur.Noir));
		setCase(1,4,new Dame("DameBlanche",Joueur.Blanc));
		setCase(8,4,new Dame("DameNoire",Joueur.Noir));
		setCase(1,5,new Roi("RoiBlanc",Joueur.Blanc));
		setCase(8,5,new Roi("RoiNoir",Joueur.Noir));
		setCase(1,6,new Fou("FouBlanc",Joueur.Blanc));
		setCase(8,6,new Fou("FouNoir",Joueur.Noir));
		setCase(1,7,new Cavalier("CavalierBlanc",Joueur.Blanc));
		setCase(8,7,new Cavalier("CavalierNoir",Joueur.Noir));
		setCase(1,8,new Tour("TourBlanche",Joueur.Blanc));
		setCase(8,8,new Tour("TourNoire",Joueur.Noir));
	}
	
	public int findCaseAL(ArrayList <Case> AL, int hauteur,int largeur){//retourne l'indice de la case de la liste en parametre donc la hauteur et la largeur correspond a ceux demandé en parametre
		
		int i = 0;
		while(AL.get(i).getHauteur() != hauteur || AL.get(i).getLargeur() != largeur){
			
			i++;
		}
			return i;
	}
	
	public boolean deplacerPiece(int h1, int l1, int h2, int l2){// manque la mise a jour des ArrayList
		
		boolean resultat = false;
		
		if(h1 > 0 && h1 <= 8 && l1 > 0 && l1 <= 8 && findCase(h1,l1).getElem() != null){//la case de depart doit exister et ne pas etre vide
			
			if(this.Deplacement(h1,l1).contains(this.findCase(h2,l2))){// la case d'arrivée doit etre inclus dans les cases de depacement possible
				
				resultat = true;
				//mise à jour ArrayList
				if(this.findCase(h2,l2).getElem() != null && this.findCase(h2,l2).getElem().getEnnemi() != this.findCase(h1, l1).getElem().getEnnemi()){
					//si il y a prise
					if(this.findCase(h2, l2).getElem().getEnnemi() == Joueur.Blanc){//pour les blancs
						this.supprimer(h2,l2);
					}
					else{//pour les noirs
						this.supprimer(h2,l2);
					}
				}
				
				//cas basique de deplacement
				
				this.setCase(h2,l2,this.findCase(h1,l1).getElem());
				this.supprimer(h1,l1);
			}
			else{
				System.out.println("Deplacement impossible");
			}
		}
		else{
				System.out.println("La case que vous demandez est vide ou n'existe pas");
			
		}
		return resultat;
	}
	
	public ArrayList<Case> caseMenace(ArrayList<Case> AL){//Array List de toute les cases que menace une couleur
		
		ArrayList<Case> result = new ArrayList<Case>();
		for(int i = 0; i < AL.size() ; i++){	
			for(int j = 0; j < this.Prise(AL.get(i).getHauteur(), AL.get(i).getLargeur()).size(); j++){
				if(!result.contains(this.Prise(AL.get(i).getHauteur(), AL.get(i).getLargeur()).get(j))){
				result.add(this.Prise(AL.get(i).getHauteur(), AL.get(i).getLargeur()).get(j));
				}
			}
		}
		return result;
	}
	
	public ArrayList<Case> caseProtege(ArrayList<Case> AL){//ArrayList de toute les pièces d'une couleur qui sont protégés
	
		ArrayList<Case> result = new ArrayList<Case>();
		for(int i = 0; i < AL.size() ; i++){
			for(int j = 0; j < this.Protection(AL.get(i).getHauteur(), AL.get(i).getLargeur()).size(); j++){
				if(!result.contains(this.Protection(AL.get(i).getHauteur(), AL.get(i).getLargeur()).get(j))){
				result.add(this.Protection(AL.get(i).getHauteur(), AL.get(i).getLargeur()).get(j));
				}
			}
		}
		return result;
	}
	
	public boolean caseBloque(int hauteur, int largeur){
		
		return this.findCase(hauteur, largeur).getElem().estCloue(this,hauteur,largeur);
	}
	
	public boolean estEchec(Joueur J){
		
		if(J == Joueur.Blanc){
			
			return this.caseMenace(this.PCN).contains(this.findKing(this.PCB));
		}
		else{
			System.out.println("CM : " + this.caseMenace(this.PCB));
			System.out.println(" FK : " + this.findKing(this.PCN));

			return this.caseMenace(this.PCB).contains(this.findKing(this.PCN));
		}
	}
	
	public ArrayList<Case> caseCounterEchec(Joueur J){//cette fonction renvoit une liste de case qui correspond a toute les cases ou une piece peut s'interposer pour annuler un echec.
		
		ArrayList<Case> Resultat = new ArrayList<Case>();
		ArrayList<Case> AM = new ArrayList<Case>();//AM = va prendre la liste de toute les cases des pieces adverses. MECHANT
		ArrayList<Case> AG = new ArrayList<Case>();//AG = la liste de toute les cases de pieces possédé par J ( sert a trouver le roi) GENTIL 
		int val[] = new int[1];//Tableau dans lequel on va stocker l'indices de la case qui menace le roi, pourquoi un tableau :car si 2 case menace le roi ca va pas rentrer et le try-catch va les niquer
		
		if(J == Joueur.Blanc){
			AM = this.PCN;
			AG = this.PCB;
		}
		else{
			AM = this.PCB;
			AG = this.PCN;
		}
		
		int compt = 0;
		for(int i = 0; i < AM.size(); i++){//on compte d'abord le nombre de piece qui le mete en echec et on enregistre leurs indices dans l'arrayList. Si 2 pieces mete le roi en echec en meme temps cette fonction est inutile car il est impossible 
			//de bloquer les 2 en seul coup
			if(this.Prise(AM.get(i).getHauteur(),AM.get(i).getLargeur()).contains(this.findKing(AG))){
				try{
					val[compt] = i;
					i++;
				}
				catch(IndexOutOfBoundsException e){
					return Resultat;// si on trouve 2 valeur attaquant le  roi, on renvoit directement une liste vide
				}
			}
		}
		Resultat.add(AM.get(val[compt]));// on renvois deja la position de la piece attaquante qui sera forcement incluse
		if(!(AM.get(val[0]).getElem().toString() == "p" || AM.get(val[0]).getElem().toString() == "P" || AM.get(val[0]).getElem().toString() == "c" || AM.get(val[0]).getElem().toString() == "C")){
			//en cas de pion et cavalier, seul la prise de la piece permet de stoper l'echec 
		//	for(int i = 1; i < )
			
		}
		
	
	}
}


