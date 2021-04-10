package View;

import Controller.ExploraterController;
import Model.Game.Character;
import Model.Misc.Consumable;
import Model.Misc.Inventory;
import Model.Misc.Item;
import Model.Exploration.PartyNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemView extends HBox {
    private Item item;
    private Inventory inventory;
    private Button actionButton;

    public ItemView(Item item, Inventory inventory)
    {
        this.item = item;
        this.inventory = inventory;
        this.setSpacing(5);

        actionButton = new Button("Interagir");
        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Consumable consumable = (Consumable) item;

                Stage selection = new Stage();
                selection.setTitle("Utiliser un objet");
                selection.initModality(Modality.APPLICATION_MODAL);

                VBox view = new VBox();
                view.setSpacing(15);
                view.setPadding(new Insets(20, 0, 10, 40));

                Scene itemUse = new Scene(view);
                selection.setWidth(500);
                selection.setHeight(300);
                selection.setScene(itemUse);

                for(Character player : PartyNode.getCharacterList())
                {
                    Button useButton = new Button(player.getName());
                    useButton.setFont(new Font(20));
                    useButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            consumable.use(useButton.getText());
                            inventory.removeItem(item);

                            ExploraterController.getInstance().reloadInventory();

                            selection.close();
                        }
                    });

                    view.getChildren().add(useButton);
                }
                selection.show();

            }
        });


        this.getChildren().add(actionButton);

        if(!(item instanceof Consumable))
        {
            actionButton.setVisible(false);
        }

        this.getChildren().add(new Label(item.toString()));
    }
}
