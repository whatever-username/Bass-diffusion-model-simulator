package ru.Frames;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.AppContext;
import ru.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Innokentiy on 19.01.2017.
 */
public class BeaconCellFrame extends JFrame {
    AppContext context;
    XYDataset data;
    XYSeriesCollection dataset;
    XYSeries series;
    JFreeChart chart;
    JPanel panel;
    JLabel xPositionLabel, yPositionLabel, areaLabel,influenceLabel,
            xPosition, yPosition,
            alphaLabel, sigmaLabel, startLabel, durationLabel;
    JTextField area, alpha, sigma, start, duration;
    JButton updateGraph;

    double alphaValue = 4;
    double sigmaValue = 3;
    int startValue = 100;
    int durationValue = 1000;
    public BeaconCellFrame(AppContext context){
        this.context = context;
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        xPositionLabel = new JLabel("x: ");
        yPositionLabel = new JLabel("y: ");
        areaLabel = new JLabel("area: ");
        influenceLabel = new JLabel("influence: ");

        xPosition = new JLabel("");
        xPosition.setPreferredSize(new Dimension(22,16));
        yPosition = new JLabel("");
        yPosition.setPreferredSize(new Dimension(22,16));

        area = new JTextField(3);
        area.setPreferredSize(new Dimension(29,16));

        gbc.gridx = 0; gbc.gridy = 0;
        add(xPositionLabel,gbc);
        gbc.gridx = 1;
        add(xPosition,gbc);
        gbc.insets = new Insets(0,100,0,0);
        gbc.gridx = 2;
        add(yPositionLabel,gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 3;
        add(yPosition,gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth=2;
        add(areaLabel,gbc);
        gbc.insets = new Insets(0,100,0,0);
        gbc.gridx = 1;
        add(area,gbc);
        gbc.insets = new Insets(10,100,0,0);

        alphaLabel = new JLabel("α: ");
        gbc.gridx = 0; gbc.gridy = 2;
        add(alphaLabel,gbc);
        sigmaLabel = new JLabel("σ: ");
        gbc.gridy = 3;
        add(sigmaLabel,gbc);
        startLabel = new JLabel("Start on turn: ");
        gbc.gridy = 4;
        add(startLabel,gbc);
        durationLabel = new JLabel("duration: ");
        gbc.gridy = 5;
        add(durationLabel,gbc);

        alpha = new JTextField(3);
        gbc.gridx = 1; gbc.gridy = 2;
        add(alpha,gbc);
        sigma = new JTextField(3);
        gbc.gridx = 1; gbc.gridy = 3;
        add(sigma,gbc);

        start = new JTextField(3);
        gbc.gridx = 1; gbc.gridy = 4;
        add(start,gbc);

        duration = new JTextField(3);

        gbc.gridx = 1; gbc.gridy = 5;
        add(duration,gbc);
        dataset = new XYSeriesCollection();
        series = new XYSeries("data",true,false);
        java.util.List<Double> dataList = Util.calculateData(3,3,100,0,1);
        double x=0;
        for (int i = 0; i < dataList.size(); i++) {
            series.add(x,dataList.get(i));
            x+=0.1;
        }
        dataset.addSeries(series);

        data = dataset;
        chart = ChartFactory.createXYLineChart("Influence","Time","Efficency",dataset);

        chart = initChart(alphaValue,sigmaValue,durationValue,startValue);

        gbc.gridx=0; gbc.gridy=6; gbc.gridwidth=3;
        panel =new ChartPanel(chart);
        panel.setSize(new Dimension(200,200));
        panel.setPreferredSize(new Dimension(200,200));
        panel.setMinimumSize(new Dimension(200,200));
        add(panel,gbc);
        panel.setVisible(true);
        JFrame thisFrame = this;
        updateGraph = new JButton("⟲");
        updateGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alphaValue = Double.parseDouble(alpha.getText());
                sigmaValue = Double.parseDouble(sigma.getText());
                durationValue = Integer.parseInt(duration.getText());
                startValue = Integer.parseInt(start.getText());
                thisFrame.getContentPane().remove(panel);
                chart = initChart(alphaValue,sigmaValue,durationValue,startValue);
                panel = new ChartPanel(chart);
                panel.setSize(new Dimension(200,200));
                panel.setPreferredSize(new Dimension(200,200));
                panel.setMinimumSize(new Dimension(200,200));
                gbc.gridx=0; gbc.gridy=6; gbc.gridwidth=3;
                thisFrame.add(panel,gbc);

                thisFrame.revalidate();
                thisFrame.repaint();
                //REPAINT FRAME

            }
        });
        updateGraph.setPreferredSize(new Dimension(40,30));
        updateGraph.setSize(new Dimension(40,30));
        updateGraph.setMinimumSize(new Dimension(40,30));
        gbc.gridy = 7; gbc.gridwidth = 4;
        add(updateGraph,gbc);

        setVisible(true);
        setSize(new Dimension(400,600));

    }
    public void updateInfo(int x, int y){
        try{
            xPosition.setText(""+x);
            yPosition.setText(""+y);
            area.setText(""+context.beaconCells.get(new Dimension(x,y)).getAreaOfEffect());



        }catch (NullPointerException e){}

    }
    public JFreeChart initChart (double a, double s, int duration, int startTime){
        this.dataset = new XYSeriesCollection();
        this.series = new XYSeries("data",true,false);
        java.util.List<Double> dataList = Util.calculateData(a,s,duration,startTime,1);
        double x=0;
        for (int i = 0; i < dataList.size(); i++) {
            this.series.add(x*10,dataList.get(i));
            x+=0.1;
        }
        this.dataset.addSeries(series);

        this.data = dataset;
        System.out.println("da");
        return ChartFactory.createXYAreaChart("Influence","Time","Efficency",this.dataset);
    }

}
