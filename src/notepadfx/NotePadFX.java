/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadfx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class NotePadFX extends Application {
    
    public String tobesaved;
    public void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
          //  Logger.getLogger(SaveFileWithFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        //Pane
        BorderPane pane = new BorderPane();
        // Scene
        Scene scene = new Scene(pane, 500, 500);
        // Nodes in the app
        MenuBar bar = new MenuBar();
        TextArea inputarea = new TextArea();
        // Menus 
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        // Creating File Menu Items
        MenuItem itemnew = new MenuItem("New");
        itemnew.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        MenuItem itemopen = new MenuItem("Open");
        MenuItem itemsave = new MenuItem("Save");
        MenuItem itemexit = new MenuItem("Exit");
        // Creating Edit Menu Items
        MenuItem itemundo = new MenuItem("Undo");
        MenuItem itemcut = new MenuItem("Cut");
        MenuItem itemcopy = new MenuItem("Copy");
        MenuItem itempaste = new MenuItem("Paste");
        MenuItem itemdelete = new MenuItem("Delete");
        MenuItem itemselectall = new MenuItem("Select All");
        // Creating Help menu items
        MenuItem itemaboutnotepad = new MenuItem("About NotePad");

        //adding items to menus
        file.getItems().addAll(itemnew, itemopen, itemsave, new SeparatorMenuItem(), itemexit);
        edit.getItems().addAll(itemundo, new SeparatorMenuItem(), itemcut, itemcopy, itempaste, itemdelete, new SeparatorMenuItem(), itemselectall);
        help.getItems().addAll(itemaboutnotepad);

        // adding menus to menu bar
        bar.getMenus().addAll(file, edit, help);

        // adding nodes ( bar & text area)
        pane.setTop(bar);
        pane.setCenter(inputarea);

        // Handling New menuItem
        itemnew.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                inputarea.clear();
            }
        });

        //Handling Open menuitem
        itemopen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    FileInputStream fis = new FileInputStream("temp.txt");
                    DataInputStream dis = new DataInputStream(fis);

                    String lastsaved = dis.readLine();
                    inputarea.setText(lastsaved);
                    dis.close();
                } catch (IOException ex) {
                    //  Logger.getLogger(NotePadFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Handling save menuitem
        itemsave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    FileOutputStream fos = new FileOutputStream("temp.txt");
                    DataOutputStream dos = new DataOutputStream(fos);
                    tobesaved = inputarea.getText();
                    dos.writeUTF(tobesaved);
                    dos.close();
                } catch (IOException ex) {
                    //  Logger.getLogger(NotePadFX.class.getName()).log(Level.SEVERE, null, ex);
                }
                FileChooser fileChooser = new FileChooser();
                //Set extension filter for text files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                //Show save file dialog
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) {
                    saveTextToFile(tobesaved, file);
                }
            }
        });
        
        
 

    // Handling Exit option

    itemexit.setOnAction (
             
        new EventHandler<ActionEvent>() {
            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("A Confirmation Dialog");
            alert.setContentText("Are you sure you want to EXIT?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                primaryStage.close();
            } else {
                alert.close();
            }
        }
    }

    );
        // Handling Undo option 
    itemundo.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                inputarea.undo();
        }
    }

    );
        // Handling Copy option 
    itemcopy.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                inputarea.copy();
        }
    }

    );
        // Handling Cut option 
    itemcut.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                inputarea.cut();
        }
    }

    );
        // Handling Paste option 
    itempaste.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                inputarea.paste();
        }
    }

    );
        // Handling Delete option 
    itemdelete.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                
                inputarea.deleteText(inputarea.getSelection());
        }
    }

    );
        // Handling SelectAll option 
    itemselectall.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                inputarea.selectAll();
        }
    }

    );
        // Handling Help option 
    itemaboutnotepad.setOnAction (
             
        new EventHandler<ActionEvent>() {

            @Override
        public void handle
        (ActionEvent event
                
        
            ) {
                Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Information about application:");
            alert.setContentText("This NotePad application is made by Mohammed H. Alnaggar \nInformation Technology Institute - Cloud Development ");

            alert.showAndWait();
        }
    }

    );
        // adding scene to stage & showing the stage
    primaryStage.setTitle (
            

    "NotePad V1.0");
    primaryStage.setScene (scene);

    primaryStage.show ();
}

public static void main(String[] args) {
        launch(args);
    }

}
