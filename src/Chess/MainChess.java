package Chess;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;


public class MainChess {

	public static void main(String[] args) {
		
		Damier D1 = new Damier();
		
		D1.Remplir();
		
		D1.deplacerPiece(2, 4, 4, 4);
		System.out.println(D1.deplacerPiece(7, 5, 5, 5));
		System.out.println(D1.deplacerPiece(5, 5, 4, 4));
		System.out.println(D1.deplacerPiece(1, 4, 4, 4));
		System.out.println(D1.deplacerPiece(8, 4, 7, 5));
		System.out.println(D1.deplacerPiece(4, 4, 5, 5));
		System.out.println(D1.deplacerPiece(8, 6, 7, 5));
		System.out.println(D1.deplacerPiece(7, 5, 6, 6));
		System.out.println(D1.deplacerPiece(6, 5, 6, 1));
		System.out.println(D1.deplacerPiece(5, 5, 6, 5));
		System.out.println(D1.deplacerPiece(7, 4, 6, 5));
		System.out.println(D1.deplacerPiece(2, 5, 4, 5));
		System.out.println(D1.deplacerPiece(1, 6, 4, 3));
		System.out.println(D1.deplacerPiece(4, 3, 6, 5));
		System.out.println(D1.deplacerPiece(6, 6, 3, 3));
		System.out.println(D1.deplacerPiece(8, 7, 6, 6));
		System.out.println(D1.deplacerPiece(6, 6, 4, 5));
		System.out.println(D1.deplacerPiece(7, 5, 6, 5));
		System.out.println(D1.deplacerPiece(4, 5, 5, 3));
		






		
		System.out.println("Roi Blanc est echec : " + D1.estEchec(Joueur.Blanc) + " Roi noir est Echec : " + (D1.estEchec(Joueur.Noir)));
		
		
		System.out.println(D1);
		System.out.println(D1.getPCB());
		System.out.println(D1.getPCN());
		System.out.println(D1.Prise(6, 5));

		
	}
}
	