package ru;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Innokentiy on 07.06.2016.
 * 3:40
 */
public class GraphicsOutputManager {
    public AppContext context;

                                            //m- предельное количество пользователей
    public final double p = 0.03;    // коэффициент инновации (вероятность появления заинтересованности засчет масс-медиа или других внешн. факторов.
    public final double q = 0.38;    //  коэффициент имитации (вероятность появления заинтересованности засчет людей, уже пользующихся)
    private List outputPointsArray = new ArrayList<Integer>();
    private List outputDeltaArray = new ArrayList<Float>();
    private List outputSrDeltaArray = new ArrayList<Float>();

    public void addPoint(int amount) {
        outputPointsArray.add(amount);
    }
    private void countDelta(){
        for (int position = 0; position < (outputPointsArray.size()-1); position++) {
            outputDeltaArray.add(   (int)outputPointsArray.get(position+1) - (int)outputPointsArray.get(position)   );
        }
    }
    private void countSrDelta(int dotsPerPoint){
        int sum=0;
        if ((sum !=0)&&(outputSrDeltaArray.size() % dotsPerPoint >0.0001)) {
            outputSrDeltaArray.add((float)sum / (float)((outputPointsArray.size() % dotsPerPoint)));
        }
        int count=0;
        for (int position = 0; position < outputDeltaArray.size(); position++) {
            sum += (int)outputDeltaArray.get(position);
            count++;
            if (count == dotsPerPoint){
                outputSrDeltaArray.add((float)sum/(float)dotsPerPoint);
                count=0;
                sum = 0;
            }
        }
    }


    public void writeToFileSimulated(String pathToFile,int dotsPerPoint, boolean withAvg ) throws IOException, InterruptedException {

        countDelta();
        System.out.println(outputPointsArray.size());
        countSrDelta(dotsPerPoint);
        FileWriter writer = new FileWriter(pathToFile, false);
        String line=null;
        for (int i = 0; i < outputPointsArray.size(); i++) {

            if (i <outputSrDeltaArray.size() && withAvg){

                    line = outputPointsArray.get(i)+";"+outputSrDeltaArray.get(i);

            }
            else{
                line = outputPointsArray.get(i)+"";
            }
            line = line.replace(".",",");
            writer.write(line);
            writer.write("\r\n");

        }

        writer.flush();
        writer.close();

    }
    public List bassModel(double m, double p, double q){
        List<String> lines = new ArrayList<>();
        double f;
        for (double t = 0; t < 15 ; t+=0.01) {
            f = m* ((1-Math.pow(Math.E,-(p+q)*t))/(1+q/p*Math.pow(Math.E,-(p+q)*t)));

            String b = /*t+";"+*/f+"\r\n";
            b = b.replace(".",",");
            lines.add(b);
        }
        return lines;
    }
    public void writeToFileBass(String pathToFile) throws IOException {
        List<String> lines = bassModel(10000,p,q);
        FileWriter writer = new FileWriter(pathToFile, false);
        for (String line : lines) {
            writer.write(line);
        }
        writer.flush();
        writer.close();

    }
    public void writeBothToFile(String pathToFile) throws IOException {
        List<String> lines = bassModel(10000,0.1,q);
        FileWriter writer = new FileWriter(pathToFile, false);
        String line;
        for (int i = 0; i < outputPointsArray.size(); i++) {

            if (i <outputPointsArray.size()){

                line = outputPointsArray.get(i)+";"+lines.get(i);

            }
            else{
                line = lines.get(i)+"";
            }
            line = line.replace(".",",");
            writer.write(line);

        }
    }
}

