package ru.Frames;

import ru.AppContext;
import ru.Keyboard;
import ru.Main;
import ru.Mouse;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    public Canvas c;
    public AppContext context;
    JButton black, blue, red, green, cellField, supercellField;
    JPanel colorButtons, layerButtons, commandPanel;
    JSlider diameter;
    JLabel diameterText, radiusText;
    GridBagConstraints gbc = new GridBagConstraints();

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
        diameter = new JSlider(JSlider.HORIZONTAL, 1, 500, 1);
        diameter.setPreferredSize(new Dimension(140,20));
        diameterText = new JLabel("Area of supercells: 0");
        radiusText = new JLabel();
        diameter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                context.areaEffectDiameter = diameter.getValue();
                diameterText.setText("Area of supercells: "+context.areaEffectDiameter);
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

                }                                       // ЗАМЕНИТЬ НА TRUE FALSE
                else if (context.renderingField==2){
                    context.renderingField=1;
                    layerButton.setText("Cells");
                }
            }
        });
        line++;
        gbc.gridy=line;
        add(layerButton,gbc);
        colomn++;
        gbc.gridx=colomn; gbc.gridwidth=2;
        add(diameter,gbc);
        line--;
        gbc.gridy=line; gbc.gridwidth=1;
        diameterText.setPreferredSize(new Dimension(135,20));
        add(diameterText,gbc);
        gbc.insets = new Insets(0,0,0,0);

        colomn=0; line=3;
        gbc.gridx=colomn; gbc.gridy=line; gbc.gridwidth=6; gbc.gridheight=1;
        add(c,gbc);


        JButton startButton = new JButton("Start simulation");
        startButton.setPreferredSize(new Dimension(140,40));
        gbc.gridy=4; gbc.gridx =1; gbc.gridwidth=1;

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.processing = true;

            }
        });
        add(startButton,gbc);
        JButton closeButton = new JButton("Stop");
        closeButton.setPreferredSize(new Dimension(140,40));
        gbc.gridy = 4; gbc.gridwidth=1;
        colomn=3; gbc.gridx=colomn;
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
        add(closeButton,gbc);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        c.createBufferStrategy(2);    // ex. NUM_BUFFERS
        Mouse.create(c);
        Keyboard.create(c);
    }
}
