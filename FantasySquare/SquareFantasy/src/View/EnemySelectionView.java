package View;

import Controller.FightController;
import Model.Combat.CombatManager;
import Model.Combat.Spell;
import Model.Game.Monster;
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

public class EnemySelectionView extends Stage {
    private ArrayList<Monster> monsters;
    private CombatManager combatManager;
    private VBox view;

    public EnemySelectionView(ArrayList<Monster> monsters, CombatManager combatManager)
    {
        this.monsters = monsters;
        this.combatManager = combatManager;

        view = new VBox();
        view.setSpacing(30);
        view.setAlignment(Pos.CENTER);

        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(view));

        for (Monster monster : monsters)
        {
            Button button = new Button(monster.getName());
            button.setFont(new Font(20));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    attackMonster(monster);
                }
            });
            view.getChildren().add(button);
        }

        this.show();
    }

    public EnemySelectionView(ArrayList<Monster> monsters, CombatManager combatManager, Spell spell, SpellView spellView)
    {
        this.monsters = monsters;
        this.combatManager = combatManager;

        view = new VBox();
        view.setSpacing(30);
        view.setAlignment(Pos.CENTER);

        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(view));

        for (Monster monster : monsters)
        {
            Button button = new Button(monster.getName());
            button.setFont(new Font(20));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    attackMagicMonster(monster, spell, spellView);
                }
            });
            view.getChildren().add(button);
        }

        this.show();
    }

    public void attackMonster(Monster monster)
    {
        monster.phyDamage(FightController.getInstance().getCurrentPlayer().getAttack());

        if(monster.getIsDead())
        {
            FightController.getInstance().getCombatManager().getMonsters().remove(monster);
        }

        FightController.getInstance().nextPlayer();
        FightController.getInstance().showRectangles();
        FightController.getInstance().showLabels();

        this.close();
    }

    public void attackMagicMonster(Monster monster, Spell spell, SpellView spellView)
    {
        monster.magDamage(FightController.getInstance().getCurrentPlayer().getIntel(), spell.getDegats());
        FightController.getInstance().getCurrentPlayer().setMana(FightController.getInstance().getCurrentPlayer().getMana() - spell.getMana());

        if(monster.getIsDead())
        {
            FightController.getInstance().getCombatManager().getMonsters().remove(monster);
        }

        FightController.getInstance().nextPlayer();
        FightController.getInstance().showRectangles();
        FightController.getInstance().showLabels();


        this.close();
        spellView.close();
    }
}
