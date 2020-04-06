package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable
{



    @FXML
    private AnchorPane scene,progPane;

    @FXML
    private AnchorPane container;


    private GridPane grille;

    private int cptGrilles=0;


    static int WIDTH_PIECE=80,HEIGHT_PIECE=80;
    static int NB_PIECES_X=4,NB_PIECES_Y=5;
    static int WIDTH_PLATEAU=WIDTH_PIECE*NB_PIECES_X,HEIGHT_PLATEAU=HEIGHT_PIECE*NB_PIECES_Y;

    public void initialize(URL location, ResourceBundle rs)
    {
        initProg();
        nextIns();
    }

    private void initProg()

    {

        VBox box=new VBox();
        int cpt=0;

        try {
            Scanner sc=new Scanner(new File("src/gui/programme/test.txt"));

            while (sc.hasNext())
            {

                Label label=new Label(sc.nextLine());
                if(++cpt==1 || !sc.hasNext())
                {
                    label.getStyleClass().add("labelProgForm");
                }

                else
                {
                    label.getStyleClass().add("labelProg");

                }

                label.setAlignment(Pos.CENTER);
                box.getChildren().add(label);


            }
            progPane.getChildren().add(box);



            sc.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void nextIns()
    {
        cptGrilles++;
        container.getChildren().clear();
        if(cptGrilles>new File("src/gui/grilles").list().length)
        {
            cptGrilles=1;
        }
        int cpt=0;
        int cpt2=0;
        int cpt3=0;

        int[] entete=new int[5];
        int[][] plateau=null;
        int nbColonnes=0,nbLignes=0;
        int x=0;
        int y=0;
        int d=0;


        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/gui/grilles/grille"+cptGrilles+".txt")))) {

            String line="";

            while ((line = reader.readLine())!= null)
            {

                if(cpt==0)
                {
                    Scanner sc=new Scanner(line);

                    while (sc.hasNext() && cpt2<5)
                    {
                        entete[cpt2]=Integer.parseInt(sc.next());
                        cpt2++;
                    }
                    NB_PIECES_X=entete[0];
                    NB_PIECES_Y=entete[1];

                    plateau=new int[NB_PIECES_Y][NB_PIECES_X];

                }

                else
                {
                    Scanner sc=new Scanner(line);

                    cpt3=NB_PIECES_Y-cpt;
                    cpt2=0;

                    while (sc.hasNext())
                    {
                        plateau[cpt3][cpt2]=Integer.parseInt(sc.next());
                        cpt2++;
                    }
                }


                cpt++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }




        NB_PIECES_X=entete[0];
        NB_PIECES_Y=entete[1];
        x=entete[2];
        y=entete[3];
        d=entete[4];

        String tourner="";



        grille=new GridPane();
        grille.setPrefSize(WIDTH_PLATEAU,HEIGHT_PLATEAU);
        grille.setPadding(new Insets(13,38,13,38));
        grille.setHgap(1);
        grille.setVgap(1);

        for (int i=0 ; i!=NB_PIECES_Y ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_X ; j++)
            {

                Label figure=new Label(plateau[NB_PIECES_Y-i-1][j]+"");

                figure.setMinHeight(HEIGHT_PIECE);
                figure.setMinWidth(WIDTH_PIECE);
                figure.setAlignment(Pos.CENTER);


                if(i==NB_PIECES_Y-y-1 && j==x)
                {
                    switch (d)
                    {
                        case 0:
                            tourner="0 10 0 0;";
                            break;
                        case 1:
                            tourner="10 0 0 0;";
                            break;
                        case 2:
                            tourner="0 0 0 10;";
                            break;
                        case 3:
                            tourner="0 0 10 0;";
                            break;
                    }


                    figure.setStyle("-fx-border-color: #18ff2e;"+
                            "-fx-border-width:"+tourner
                            +"-fx-font-size: 25px;"+
                            "-fx-background-color: #ff4745;"
                            +"-fx-text-fill: #ffffff;");

                }
                else
                {
                    figure.setStyle("-fx-border-color: rgba(2,4,3,0.32);" +
                            "-fx-border-width: 2px;"
                            +"-fx-font-size: 25px;"+
                            "-fx-background-color: rgba(43,122,80,0.34);"
                            +"-fx-text-fill: rgb(255,255,255);");
                }


                grille.add(figure,j,i);
            }
        }


        container.getChildren().add(grille);
        grille.setAlignment(Pos.CENTER);

    }




}
