package View;

import Controller.FightController;
import Model.Combat.CombatManager;
import Model.Combat.Spell;
import Model.Game.Character;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SpellView extends Stage {
    private Character player;
    private VBox view;
    private CombatManager combatManager;

    public SpellView(Character player, CombatManager combatManager)
    {
        this.combatManager = combatManager;
        this.setResizable(false);
        this.setTitle("Choix sorts");
        this.initModality(Modality.APPLICATION_MODAL);

        view = new VBox();

        this.setScene(new Scene(view));

        for (Spell spell : player.getSpells())
        {
            Button button = new Button(spell.toString());
            button.setFont(new Font(20));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    useSpell(spell);
                }
            });
            view.getChildren().add(button);
        }

        this.show();
    }

    private void useSpell(Spell spell)
    {
        if(FightController.getInstance().getCurrentPlayer().getMana() >= spell.getMana())
        {
            if (spell.getDegats() < 0) {
                AllySelectionView allySelectionView = new AllySelectionView(combatManager.getPlayers(), combatManager, spell, this);
            } else {
                EnemySelectionView enemySelectionView = new EnemySelectionView(combatManager.getMonsters(), combatManager, spell, this);
            }
            FightController.getInstance().showRectangles();
            FightController.getInstance().showLabels();
        }
    }
}
