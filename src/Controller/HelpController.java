package Controller;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HelpController {

    public javafx.scene.control.ScrollPane ScrollPane;
    public AnchorPane ScrollAnchorPane;
    public TextField txtSearchId;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public AnchorPane popupPane1;
    public Label lblPopupText1;
    public AnchorPane Popup2pane1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    public AnchorPane paneHelp;
    public Label lblStudRegGuidL;
    public AnchorPane paneStudRegGuid;
    public AnchorPane paneStudRegQ;


    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
         if(mouseEvent.getTarget().equals(paneStudRegQ)){
             paneStudRegGuid.setVisible(true);
             paneHelp.setOpacity(0.15);
         }else{
             paneHelp.setOpacity(1);
             paneStudRegGuid.setVisible(false);
         }

    }


    public void lblStudRegGuidLOnMouseClicked(MouseEvent mouseEvent) {
        paneStudRegGuid.setVisible(true);
        paneHelp.setOpacity(0.15);


    }
}
