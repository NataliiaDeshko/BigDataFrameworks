import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransposeMapReduce {

    public static List<String> readFile(String csvFile){
        BufferedReader br;
        List<String> linesList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line ;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();}
        return linesList;
    }

    public static void writeFile(String filename, String str) {
        FileOutputStream fos;
        try {
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(str+"\n");//appends the string to the file
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer,HashMap<Integer,String>> map(int key, String value){
        String cvsSplitBy = ",";
        String[] line = value.split(cvsSplitBy);
        HashMap<Integer,HashMap<Integer,String>> result =new HashMap<>();
        int counter = 0;
        for (String elem :line) {
            HashMap<Integer,String> newValue = new HashMap<>();
            newValue.put(key,elem);
            result.put(counter,newValue);
            counter++;
        }
        return result;
    }

    public static String reduce(Integer key, HashMap<Integer,HashMap<Integer,String>> valuesMapMap){
        HashMap<Integer, String> valuesMap = valuesMapMap.get(key);
        String resultLine = "";
        int length = valuesMap.keySet().size();
        for (int lineNumber : valuesMap.keySet() ) {
            length--;
            resultLine = resultLine + valuesMap.get(lineNumber).trim();
            if (length>0)
                resultLine = resultLine+ ", ";
        }
        System.out.println(resultLine);
        return resultLine;
    }

    public static void main(String[] args) {
        String inputFilename = "input.csv"; //"D:\\data.csv";
        List<String> linesList = readFile(inputFilename);
        int lineNumber = 0;

        HashMap<Integer, HashMap<Integer,String>> toReduceMap = new HashMap<>();
        HashMap<Integer,HashMap<Integer,String>> mappedLineMap;

        for (String line:linesList) {
            mappedLineMap = map(lineNumber, line);

            for (int wordNumber : mappedLineMap.keySet()) {
                if (lineNumber == 0)
                    toReduceMap.put(wordNumber, mappedLineMap.get(wordNumber));
                else {
                    HashMap<Integer, String> tmp = toReduceMap.get(wordNumber);
                    tmp.put(lineNumber,mappedLineMap.get(wordNumber).get(lineNumber));
                    toReduceMap.put(wordNumber,tmp);
                }
            }
            lineNumber++;
        }

        String outputFilename = "output.csv"; // "D:\\output.csv";
        for (int key : toReduceMap.keySet()) {
            String output = reduce(key,toReduceMap);
            writeFile(outputFilename,output);
        }
    }
}