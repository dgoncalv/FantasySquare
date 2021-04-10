package View;

import Controller.FightController;
import Model.Combat.CombatManager;
import Model.Combat.Spell;
import Model.Game.Character;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AllySelectionView extends Stage {
    private ArrayList<Character> characters;
    private CombatManager combatManager;
    private VBox view;

    public AllySelectionView(ArrayList<Character> characters, CombatManager combatManager, Spell spell, SpellView spellView)
    {
        this.characters = characters;
        this.combatManager = combatManager;

        view = new VBox();
        view.setSpacing(30);
        view.setAlignment(Pos.CENTER);

        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(view));

        for (Character character : characters)
        {
            Button button = new Button(character.getName());
            button.setFont(new Font(20));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    selectAlly(character, spell, spellView);
                }
            });
            view.getChildren().add(button);
        }

        this.show();
    }

    public void selectAlly(Character character, Spell spell, SpellView spellView)
    {
        if(FightController.getInstance().getCurrentPlayer().getMana() >= spell.getMana())
        {
            character.addHp(-spell.getDegats());

            FightController.getInstance().nextPlayer();
            FightController.getInstance().showRectangles();
            FightController.getInstance().showLabels();
        }

        this.close();
        spellView.close();
    }
}
