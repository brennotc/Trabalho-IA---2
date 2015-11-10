import java.io.IOException;
import java.lang.System;
import java.util.HashMap;
import java.util.Scanner;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

public class Trabalho {
	
	@SuppressWarnings({ "unchecked" })
	public static <A, B> void main(String[] args) throws IOException, InterruptedException {
		Query q1 = new Query("consult", new Term[] { new Atom("C:\\Users\\Brenno\\Downloads\\wumpus_titular.pl") });
		System.out.println("consult " + ( q1.hasSolution() ? "succeeded" : "failed"));
		Query q2 = new Query("agente([X,Y],Z)");
		Query q3 = new Query("ouros_pegos(W)");
		Query q4 = new Query("vivo_agente(K)");
		Query q5 = new Query("execute(va-para-frente)");
		java.lang.String vivo = "sim";
		int i;
		int l;
		int m;
		char[][] mapa = new char[6][6];
		int posicaoX = 1;
		int posicaoY = 1;
		int qtdOuro = 0;
		Scanner ler = new Scanner(System.in);
		String s = null;
		
		for(l=0;l<6;l++)
			for(m=0;m<6;m++)
				mapa[l][m] = '_';
		mapa[0][0] = 'i';
		
		HashMap<A,B>[] solution = (HashMap<A,B>[]) q2.allSolutions();
		HashMap<A,B>[] solutionGold = (HashMap<A,B>[]) q3.allSolutions();
		HashMap<A,B>[] solutionLife = (HashMap<A,B>[]) q4.allSolutions();
		HashMap<A,B>[] solutionWay = (HashMap<A,B>[]) q5.allSolutions();
		
		do{
			
			if (solution != null) {
				for (i = 0; i < solution.length; i++){
					System.out.println("X = " + solution[i].get("X") + " Y = " + solution[i].get("Y") + " Z = " + solution[i].get("Z"));
					System.lineSeparator();  
				}
				posicaoX = Integer.parseInt(solution[i-1].get("X").toString());
				posicaoY = Integer.parseInt(solution[i-1].get("Y").toString());
				if(mapa[posicaoX][posicaoY] == '_')
					mapa[posicaoX][posicaoY] = '1';
				else if(mapa[posicaoX][posicaoY] == '1')
					mapa[posicaoX][posicaoY] = '2';
				else if(mapa[posicaoX][posicaoY] == '2')
					mapa[posicaoX][posicaoY] = '3';
				else if(mapa[posicaoX][posicaoY] == '3')
					mapa[posicaoX][posicaoY] = '4';
				else if(mapa[posicaoX][posicaoY] == '4')
					mapa[posicaoX][posicaoY] = '5';
			}
			if (solutionGold != null) {
				for (i = 0; i < solutionGold.length; i++){
					System.out.println("Quantidade de Ouro = " + solutionGold[i].get("W"));
					System.lineSeparator();  
				}
				
				if(solution[i-1].get("W") != null)
					qtdOuro = Integer.parseInt((solution[i-1].get("W").toString()));
			}
			if (solutionLife != null) {
				for (i = 0; i < solutionLife.length; i++){
					System.out.println("Está vivo? " + solutionLife[i].get("K"));
					vivo = solutionLife[i].get("K").toString();
					System.lineSeparator();  
				}
			}
			
			for(l=0;l<6;l++){
				System.out.println(mapa[l][0]+" "+mapa[l][1]+" "+mapa[l][2]+" "+mapa[l][3]+" "+mapa[l][4]+" "+mapa[l][5]);
			}
			
			q2 = new Query("agente([X,Y],Z)");
			q3 = new Query("ouros_pegos(W)");
			q4 = new Query("vivo_agente(K)");
			
			s = ler.next();
			

			if(s.equals("frente")){
				q5 = new Query("execute(va-para-frente)");
			}
			else if(s.equals("gira")) {
				q5 = new Query("execute(gira)");
			}
			else if(s.equals("abaixa")) {
				q5 = new Query("execute(abaixa)");
			}
			else if(s.equals("flecha")) {
				q5 = new Query("execute(flecha)");
			}
			else if(s.equals("subir")) {
				q5 = new Query("execute(subir)");
			}
			
			solution = (HashMap<A,B>[]) q2.allSolutions();
			solutionGold = (HashMap<A,B>[]) q3.allSolutions();
			solutionLife = (HashMap<A,B>[]) q4.allSolutions();
			solutionWay = (HashMap<A,B>[]) q5.allSolutions();
			System.out.println(( q5.hasSolution() ? "Andou" : "Não andou"));
			System.lineSeparator();  
			
			
		}while(qtdOuro < 3 && vivo.equals("sim"));
			
			
}
}
