package Controller;

import Model.Combat.CharacterClass;
import Model.Combat.CombatManager;
import Model.Game.Character;
import Model.Game.Entity;
import Model.Game.Monster;
import View.EnemySelectionView;
import View.SpellView;
import View.XpView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class FightController implements Initializable {

    @FXML
    private GridPane fight_grid;
    @FXML
    private Button attack_button;
    @FXML
    private Button spell_button;
    @FXML
    private Button escape_button;
    @FXML
    private VBox players_vbox;
    @FXML
    private VBox monsters_vbox;
    @FXML
    private Label logLabel;

    private ArrayList<Entity> attackOrder;
    private Character currentPlayer;
    private Character playerMemory;

    private CombatManager combatManager;
    private Stage stage;

    private static FightController instance;

    public final void attackPressed(ActionEvent event){
        EnemySelectionView enemySelectionView = new EnemySelectionView(combatManager.getMonsters(), combatManager);
    }

    public final void spellPressed(ActionEvent event){
        if(!currentPlayer.getCharClass().equals(CharacterClass.WARRIOR))
        {
            new SpellView(currentPlayer, combatManager);
        }
        else
        {
            FightController.getInstance().getLogLabel().setText("Un Guerrier n'a pas de sorts.");
            showRectangles();
            showLabels();
        }
    }

    public final void escapePressed(ActionEvent event) {
        if (ThreadLocalRandom.current().nextInt(0, 6) == 1)
        {
            stage.close();
        }
        else
        {
            logLabel.setText("Echec de la fuite.");
            nextPlayer();
            showLabels();
            showRectangles();
        }
    }

        public static FightController getInstance() {
        return instance;
    }

    public Character getCurrentPlayer() {
        return currentPlayer;
    }

    public CombatManager getCombatManager() {
        return combatManager;
    }

    public void showRectangles(){
        verify();

        fight_grid.getChildren().clear();
        for (int i = 0; i < combatManager.getPlayers().size(); i++){
            Rectangle rectangle =  new Rectangle(20, 20, Color.YELLOW);
            if(combatManager.getPlayers().get(i) == currentPlayer)
            {
                rectangle.setFill(Color.GREEN);
            }
            fight_grid.add(rectangle, 0, i);
            GridPane.setHalignment(rectangle, HPos.CENTER);
            GridPane.setValignment(rectangle, VPos.CENTER);
        }

        for (int i = 0; i < combatManager.getMonsters().size(); i++){
            Rectangle rectangle =  new Rectangle(20, 20, Color.DARKRED);
            fight_grid.add(rectangle, 1, i);
            GridPane.setHalignment(rectangle, HPos.CENTER);
            GridPane.setValignment(rectangle, VPos.CENTER);
        }
    }

    private int nextIndex()
    {
        if(combatManager.getPlayers().indexOf(currentPlayer) + 1 < combatManager.getPlayers().size())
        {
            return combatManager.getPlayers().indexOf(currentPlayer) + 1;
        }
        return 0;
    }

    public void showLabels(){
        players_vbox.getChildren().clear();
        for(Character c : combatManager.getPlayers()){
            Label label = new Label(c.toString());
            label.setFont(new Font("ARIAL BLACK", 20));
            players_vbox.getChildren().add(label);
        }

        monsters_vbox.getChildren().clear();
        for(Monster m : combatManager.getMonsters()){
            Label label = new Label(m.toString());
            label.setFont(new Font("ARIAL BLACK", 20));
            monsters_vbox.getChildren().add(label);
        }
    }

    public void fight(ArrayList<Character> players, ArrayList<Monster> monsters, Stage stage)
    {
        this.stage = stage;
        attackOrder = combatManager.set(players, monsters);

        nextPlayer();

        stage.show();
        draw();
    }

    public void nextPlayer()
    {
        showLabels();
        showRectangles();
        if(currentPlayer != null)
        {
            for(int i = attackOrder.indexOf(currentPlayer) + 1; i < attackOrder.size(); i++)
            {
                if(attackOrder.get(i) instanceof Character)
                {
                    currentPlayer = (Character) attackOrder.get(i);
                    return;
                }
                else
                {
                    combatManager.aiAction(combatManager.getPlayers(), (Monster) attackOrder.get(i));
                    currentPlayer = null;
                }
            }
        }
        if(currentPlayer == null)
        {
            for(Entity entity : attackOrder)
            {
                if(entity instanceof Character)
                {
                    currentPlayer = (Character) entity;
                    return;
                }
                else
                {
                    combatManager.aiAction(combatManager.getPlayers(), (Monster) entity);
                }
            }
        }
    }

    private void verify()
    {
        boolean isActive = false;
        for (Character character : combatManager.getPlayers())
        {
            if(!character.getIsDead())
            {
                isActive = true;
            }
            else
            {
                attackOrder.remove(character);
            }
        }

        if(combatManager.getMonsters().size() == 0)
        {
            isActive = false;
        }

        if(!isActive && stage.isShowing())
        {
            XpView xpView = new XpView(combatManager.getPlayers(), combatManager.getXpReward());
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        combatManager = new CombatManager();
        instance = this;
        combatManager = new CombatManager();
        logLabel.setAlignment(Pos.CENTER);
    }

    public void draw()
    {
        showLabels();
        showRectangles();
    }

    public Label getLogLabel() {
        return logLabel;
    }
}
