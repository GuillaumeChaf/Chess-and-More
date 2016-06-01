package Chess;

public class Case {
	
	private int hauteur;
	private int largeur;
	private Element elem;
	
	public Case(int hauteur, int largeur,Element elem){
		
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.elem = null;
	}
	
	public int getHauteur(){
		
		return this.hauteur;
	}
	
	public int getLargeur(){
		
		return this.largeur;
	}
	
	public String toString(){
		
		String[] Lettre = {"A","B","C","D","E","F","G","H"};
		
		return Lettre[this.largeur-1] + this.hauteur;
		
	}
	
	public Element getElem(){
		
		return this.elem;
	}
	
	public void setElem(Element elem){
		
		this.elem = elem;
	}
	

}
