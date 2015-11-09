import java.lang.System;
import java.util.HashMap;
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

public class Trabalho {
	
	@SuppressWarnings({ "unchecked" })
	public static <A, B> void main(String[] args) {
		Query q1 = new Query("consult", new Term[] { new Atom("C:\\Users\\Brenno\\Downloads\\wumpus_1 (1).pl") });
		System.out.println("consult " + ( q1.hasSolution() ? "succeeded" : "failed"));
		Query q2 = new Query("agente([X,Y],Z)");
		Query q3 = new Query("ouros_pegos(W)");
		Query q4 = new Query("vivo_agente(K)");
		String vivo = "sim";
		int i;
		int posicaoX = 1;
		int posicaoY = 1;
		String posicaoZ = null;
		int qtdOuro = 0;
		
		HashMap<A,B>[] solution = (HashMap<A,B>[]) q2.allSolutions();
		HashMap<A,B>[] solutionGold = (HashMap<A,B>[]) q3.allSolutions();
		HashMap<A,B>[] solutionLife = (HashMap<A,B>[]) q4.allSolutions();
		if (solution != null) {
			for (i = 0; i < solution.length; i++){
				System.out.println("X = " + solution[i].get("X") + " Y = " + solution[i].get("Y") + " Z = " + solution[i].get("Z"));
				System.lineSeparator();  
			}
		}
		if (solutionGold != null) {
			for (i = 0; i < solutionGold.length; i++){
				System.out.println("Quantidade de Ouro = " + solutionGold[i].get("W"));
				System.lineSeparator();  
			}
			
			if(solution[i-1].get("W") != null)
				qtdOuro = Integer.parseInt(((String)solution[i-1].get("W")));
		}
		if (solutionLife != null) {
			for (i = 0; i < solutionLife.length; i++){
				System.out.println("Está vivo? " + solutionLife[i].get("K"));
				System.lineSeparator();  
			}
			vivo = (String) solution[i-1].get("K");
		}
		
		while(qtdOuro < 3 && vivo == "sim"){
			q2 = new Query("proximo_comando([X,Y])");
			
			q2 = new Query("agente([X,Y],Z)");
			q3 = new Query("ouros_pegos(W)");
			q4 = new Query("vivo_agente(K)");
			
			solution = (HashMap<A,B>[]) q2.allSolutions();
			solutionGold = (HashMap<A,B>[]) q3.allSolutions();
			solutionLife = (HashMap<A,B>[]) q4.allSolutions();
			
			if (solution != null) {
				for (i = 0; i < solution.length; i++){
					System.out.println("X = " + solution[i].get("X") + " Y = " + solution[i].get("Y") + " Z = " + solution[i].get("Z"));
					System.lineSeparator();
				}
				
				posicaoX = Integer.parseInt(((String)solution[i-1].get("X")));
				posicaoY = Integer.parseInt(((String)solution[i-1].get("Y")));
				posicaoZ = (String)solution[i].get("Z");
			}
			if (solutionGold != null) {
				for (i = 0; i < solutionGold.length; i++){
					System.out.println("Quantidade de Ouro = " + solutionGold[i].get("W"));
					System.lineSeparator();  
				}
				qtdOuro = Integer.parseInt(((String)solution[i-1].get("W")));
			}
			if (solutionLife != null) {
				for (i = 0; i < solutionLife.length; i++){
					System.out.println("Está vivo? " + solutionLife[i].get("K"));
					System.lineSeparator(); 
				}
				vivo = (String) solution[i-1].get("K");
			}
		}
		
		
	}
}
