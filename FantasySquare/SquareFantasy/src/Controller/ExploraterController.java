package Controller;

import Model.Combat.Bestiary;
import Model.Game.Character;
import Model.Game.Monster;
import Model.Misc.Consumable;
import Model.Misc.Item;
import Model.Exploration.*;
import Model.History.LocationQuest;
import Model.History.Quest;
import Model.History.QuestManager;
import Model.Utility.Vector2D;
import View.InventoryViewer;
import View.ShopViewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExploraterController implements Initializable {
    @FXML
    private GridPane mainScreen;
    @FXML
    private AnchorPane screen;

    private static ExploraterController instance;

    private ExplorationManager explorationManager;
    private QuestManager questManager;
    private PartyNode player;
    private int currentWorldSize;
    private int cellSize = 50;
    private InventoryViewer inventoryViewer;

    public void movementHandler(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.D)
        {
            explorationManager.move("D");
        }
        else if(keyEvent.getCode() == KeyCode.S)
        {
            explorationManager.move("B");
        }
        else if(keyEvent.getCode() == KeyCode.Q)
        {
            explorationManager.move("G");
        }
        else if(keyEvent.getCode() == KeyCode.Z)
        {
            explorationManager.move("H");
        }
        else if(keyEvent.getCode() == KeyCode.I)
        {
            inventoryViewer = new InventoryViewer(player.getInventory());
        }
        setMap(player.getActualMap().getName());
    }

    public void startFight(ArrayList<Monster> monsters)
    {
        FightController fightController = new FightController();
        Stage fightStage = new Stage();

        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/Combat.fxml"));
            Scene scene = new Scene(root);
            fightStage.setScene(scene);

            FightController.getInstance().fight(PartyNode.getCharacterList(), monsters, fightStage);
        }
        catch (IOException exception)
        {
            System.out.println("Erreur");
        }
    }

    public static ExploraterController getInstance()
    {
        return instance;
    }

    public void reloadInventory()
    {
        inventoryViewer.close();
        inventoryViewer = new InventoryViewer(player.getInventory());
    }

    public void shopTrigger(ShopkeeperNode shopkeeperNode)
    {
        ShopViewer shopViewer = new ShopViewer(shopkeeperNode);
    }

    public static void questShow(Quest quest)
    {
        if(quest instanceof LocationQuest)
        {
            Alert popup = new Alert(Alert.AlertType.INFORMATION, quest.getQuestDescription(), ButtonType.OK);
            popup.setTitle("Quète active");
            popup.setHeaderText(quest.getQuestName());
            popup.showAndWait();
        }
    }

    public static boolean questAccept(Quest quest)
    {
        if(quest instanceof LocationQuest)
        {
            String contentText = quest.getQuestDescription() + "\n" + "But : Allez à " + ((LocationQuest) quest).getDestination().getName() + " " + ((LocationQuest) quest).getLocation();

            Alert popup = new Alert(Alert.AlertType.CONFIRMATION, contentText, ButtonType.YES, ButtonType.NO);
            popup.setTitle("Nouvelle quète");
            popup.setHeaderText(quest.getQuestName());
            popup.showAndWait();
            if(popup.getResult() == ButtonType.YES)
            {
                return true;
            }
        }
        return false;
    }

    public static void questFinish(Quest quest)
    {
        String contentText = "Vous avez finis la quète \"" + quest.getQuestName() + "\"";

        Alert popup = new Alert(Alert.AlertType.CONFIRMATION, contentText, ButtonType.YES);
        popup.setTitle("Quète terminé");
        popup.setHeaderText("Quète terminé");
        popup.showAndWait();
    }

    private void setMap(String name)
    {
        World worldToLoad = World.getWorld(name);

        int cellToCompensate = currentWorldSize - worldToLoad.getSize();
        if(cellToCompensate < 0)
        {
            cellToCompensate = 0;
        }

        mainScreen = new GridPane();
        screen.getChildren().add(mainScreen);

        if(player.getCoordinate() == null)
        {
            worldToLoad.placeNode(player, new Vector2D(4, 1));
        }


        for (int i=0; i<worldToLoad.getSize() + cellToCompensate; i++)
        {
            mainScreen.getRowConstraints().add(new RowConstraints(cellSize));
            for (int j=0; j<worldToLoad.getSize() + cellToCompensate; j++)
            {
                Vector2D currentCoordinate = new Vector2D(i, j);

                if(i == 0)
                {

                    mainScreen.getColumnConstraints().add(new ColumnConstraints(cellSize));
                }

                if(currentCoordinate.getX() < worldToLoad.getSize() && currentCoordinate.getY() < worldToLoad.getSize())
                {
                    mainScreen.add(new Rectangle(cellSize, cellSize, worldToLoad.getCellBackgroundMap(currentCoordinate).getColor()), j, i);
                    if(worldToLoad.getNode(currentCoordinate.reverseVector()) != null)
                    {
                        Rectangle rectangle = new Rectangle(cellSize/2, cellSize/2);
                        if(worldToLoad.getNode(currentCoordinate.reverseVector()) instanceof PartyNode)
                        {
                            rectangle.setFill(Color.BLACK);
                            mainScreen.add(rectangle, j, i);
                        }
                        else if(worldToLoad.getNode(currentCoordinate.reverseVector()) instanceof WorldNode)
                        {
                            rectangle.setFill(Color.DARKRED);
                            mainScreen.add(rectangle, j, i);
                        }
                        else
                        {
                            rectangle.setFill(Color.BLUE);
                            mainScreen.add(rectangle, j, i);
                        }
                        GridPane.setHalignment(rectangle, HPos.CENTER);
                        GridPane.setValignment(rectangle, VPos.CENTER);
                    }
                }
                else
                {
                    mainScreen.add(new Rectangle(21, 21, Set.NONE.getColor()), j, i);
                }
            }
        }
        currentWorldSize = player.getActualMap().getSize();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;

        player = new PartyNode(World.getWorld("Village"));

        PartyNode.setCharacterList();

        player.getInventory().addItem(Item.getItem("Os"));
        player.getInventory().addItem(Item.getItem("Pépite de fer"));
        player.getInventory().addItem(Item.getItem("Pépite d'or"));
        player.getInventory().addItem(Consumable.getConsumable("Potion de soin"));
        player.getInventory().addItem(Consumable.getConsumable("Potion de mana"));

        questManager = new QuestManager(player);
        explorationManager = new ExplorationManager(player, questManager);
        setMap(player.getActualMap().getName());
    }

}
