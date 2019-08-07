package spells;
import game.MainGame;
import game.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CastSpellHandler implements EventHandler<ActionEvent>{
	private MainGame someGame;
	
	/**
	 * 
	 * @param AGAME - Needs to reference a MainGame object to call the spell's attack method
	 */
	public CastSpellHandler(MainGame AGAME) {
		someGame = AGAME;
	}
	
	@Override
	public void handle(ActionEvent event) {
		someGame.spellAttackEnemies();
	}
}
