package ru.Frames;

import ru.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class SimulationFrame extends JFrame {
    public Canvas c;
    public AppContext context;
    JButton black, blue, red, green, cellField, supercellField;
    JPanel colorButtons, layerButtons, commandPanel;
    GridBagConstraints gbc = new GridBagConstraints();
    public BeaconCellFrame beaconCellFrame;

    public SimulationFrame(AppContext context){
        this.context = context;

    }
    public void init(){
        setLayout(new GridBagLayout());
        red = new JButton("red");
        black = new JButton("black");
        blue = new JButton("blue");
        green = new JButton("green");
        JLabel colorText = new JLabel("Current: black");
        colorText.setPreferredSize(new Dimension(90,20));
        Canvas indicator = new Canvas();
        c = new Canvas();
        indicator.setSize(new Dimension(20,20));
        indicator.setBackground(Color.BLACK);
        cellField = new JButton("cell layer");
        cellField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.renderingField= 1;
            }
        });
        supercellField = new JButton("supercell layer");
        supercellField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.renderingField = 2;
            }

        });

        red.setPreferredSize(new Dimension(70, 20));
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.color = Color.red;
                colorText.setText("Current: red");
                indicator.setBackground(Color.RED);
            }
        });
        black.setPreferredSize(new Dimension(70, 20));
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.color = Color.black;
                colorText.setText("Current: black");
                indicator.setBackground(Color.BLACK);
            }
        });
        blue.setPreferredSize(new Dimension(70, 20));
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.color = Color.blue;
                colorText.setText("Current: blue");
                indicator.setBackground(Color.BLUE);
            }
        });
        green.setPreferredSize(new Dimension(70, 20));
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.color = Color.green;
                colorText.setText("Current: green");
                indicator.setBackground(Color.GREEN);
            }
        });
        setTitle("Test text");
        setResizable(false);
        c.setSize(600, 600);
        int line=0; int colomn=0;


        gbc.insets = new Insets(2,2,2,2);
        add(colorText,gbc);   // 0;0
        colomn++;
        gbc.gridx=colomn; gbc.gridy=line;
        add(green,gbc);   //1;0
        colomn++;
        gbc.gridx=colomn; gbc.gridy=line;
        add(blue,gbc);    //1;1
        colomn-=2; line++;
        gbc.gridx=colomn; gbc.gridy=line;
        add(indicator,gbc);
        colomn++;
        gbc.gridx = colomn; gbc.gridy=line;
        add(red,gbc);     //2;0
        colomn++;
        gbc.gridx=colomn; gbc.gridy=line;
        add(black,gbc);   //2;1
        gbc.insets = new Insets(0,20,0,0);

        JLabel layerText = new JLabel("Layer:");
        line--; colomn++;
        gbc.gridx=colomn; gbc.gridy=line;
        add(layerText,gbc);
        JButton layerButton = new JButton("Cells");
        layerButton.setPreferredSize(new Dimension(100,20));
        layerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (context.renderingField ==1){
                    context.renderingField =2;
                    layerButton.setText("Supercells");
                    beaconCellFrame.setVisible(true);

                }                                       // ЗАМЕНИТЬ НА TRUE FALSE
                else if (context.renderingField==2){
                    context.renderingField=1;
                    layerButton.setText("Cells");
                    beaconCellFrame.setVisible(false);
                }
            }
        });
        line++;
        gbc.gridy=line;
        add(layerButton,gbc);
        colomn++;
        gbc.gridx=colomn; gbc.gridwidth=2;




        gbc.insets = new Insets(0,0,0,0);
        colomn=0; line=3;
        gbc.gridx=colomn; gbc.gridy=line; gbc.gridwidth=8; gbc.gridheight=1;
        add(c,gbc);


        JButton startButton = new JButton("Start simulation");
        startButton.setPreferredSize(new Dimension(140,20));

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry entry:   context.beaconCells.entrySet()) {
                    Dimension coordinates = (Dimension) entry.getKey();
                    int x = (int)coordinates.getWidth();
                    int y = (int)coordinates.getHeight();
                    BeaconCell curBeaconCell = (BeaconCell)entry.getValue();
                    for (int i = 0; i < curBeaconCell.dependent.size(); i++) {
                        //UPD
                        if ((curBeaconCell.getType() == 1)||(curBeaconCell.getType() == 3)){
                            context.effectFromBeaconCells[x+(int)curBeaconCell.dependent.get(i).getWidth()][y+(int)curBeaconCell.dependent.get(i).getHeight()] += curBeaconCell.getInfluence(0);
                        }
                        if (curBeaconCell.getType() == 2){
                            context.effectFromBeaconCells[x+(int)curBeaconCell.dependent.get(i).getWidth()][y+(int)curBeaconCell.dependent.get(i).getHeight()] -= curBeaconCell.getInfluence(0);
                        }
                    }
                }
                context.processing = true;

            }
        });
        JButton closeButton = new JButton("Stop");
        closeButton.setPreferredSize(new Dimension(140,20));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.processing = false;
                try{
                    context.graphicsOutputManager.writeToFileSimulated("plotSimulated1.csv",1, false);
//                    context.manager.writeToFileBass("C:\\Users\\техносила\\Google Диск\\Java Projects\\Текущие\\Graph\\out\\plotBass.csv");
//                    context.manager.writeBothToFile("C:\\Users\\техносила\\Google Диск\\Java Projects\\Текущие\\Graph\\out\\bothPlots.csv");
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.gridx=0;gbc.gridy=1; gbc.gridwidth=1;
        gbc.insets = new Insets(5 ,20,5,20);
        panel.add(startButton,gbc);
        gbc.gridx=1;
        panel.add(closeButton,gbc);
        gbc.insets = new Insets(0,0,0,0);



        gbc.gridwidth=6;
        gbc.gridx=0; gbc.gridy=4;
        panel.setPreferredSize(new Dimension(600,30));
        add(panel,gbc);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        beaconCellFrame = new BeaconCellFrame(context);
        beaconCellFrame.setLocation(this.getX()+this.getWidth(),this.getY());
        beaconCellFrame.setVisible(false);

        setVisible(true);
        c.createBufferStrategy(2);    // ex. NUM_BUFFERS
        Mouse.create(c);
        Keyboard.create(c);
    }
}
