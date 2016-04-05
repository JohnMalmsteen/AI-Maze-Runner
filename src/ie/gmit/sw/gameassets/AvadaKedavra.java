package ie.gmit.sw.gameassets;


import ie.gmit.sw.maze.Node;
import ie.gmit.sw.threads.KillerSpell;

public class AvadaKedavra implements Spell {
	
	public void use(Node current){
		Runnable spell = new KillerSpell(current.getCell());
		new Thread(spell).start();
	}
	
	
}
