package View;

import Model.Misc.Inventory;
import Model.Misc.Item;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryViewer extends Stage {
    private Inventory inventory;
    private HBox view;
    private VBox viewItem;
    private VBox viewConsumable;

    public InventoryViewer(Inventory inventory)
    {
        this.inventory = inventory;
        this.setTitle("Inventaire");
        this.setWidth(1000);
        this.setHeight(400);
        this.initModality(Modality.APPLICATION_MODAL);

        view = new HBox();
        viewItem = new VBox();
        viewConsumable = new VBox();
        view.setSpacing(20);
        view.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Scene scene = new Scene(view);
        this.setScene(scene);

        Label gils = new Label(inventory.getGilsCount() + " Gils");
        gils.setFont(new Font("Verdana", 30));
        gils.setTextFill(Color.GREY);
        gils.setPadding(new Insets(20, 40, 20, 60));

        view.getChildren().add(viewItem);
        for(Item item : inventory.getItemSection().getItems())
        {
            viewItem.getChildren().add(new ItemView(item, inventory));
        }
        viewItem.getChildren().add(gils);

        view.getChildren().add(new Separator(Orientation.VERTICAL));

        view.getChildren().add(viewConsumable);
        for(Item item : inventory.getConsumableSection().getItems())
        {
            viewConsumable.getChildren().add(new ItemView(item, inventory));
        }

        this.show();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
