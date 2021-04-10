package View;

import Model.Exploration.ShopkeeperNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShopViewer extends Stage {
    private ShopkeeperNode shopkeeperNode;

    public ShopViewer(ShopkeeperNode shopkeeperNode)
    {
        this.shopkeeperNode = shopkeeperNode;

        GridPane root = new GridPane();
        this.setScene(new Scene(root));
        this.initModality(Modality.APPLICATION_MODAL);

        Button buyButton = new Button("Acheter");

        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                 BuyView buyView = new BuyView(shopkeeperNode);
            }
        });

        Button sellButton = new Button("Vendre");

        Button exitButton = new Button("Quitter");

        Stage temp = this;

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                temp.close();
            }
        });

        root.add(buyButton, 1, 1);
        root.add(sellButton, 1, 2);
        root.add(exitButton, 1, 3);

        this.show();
    }
}
