import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    // function to read csv-file with input data
    public static List<String> readFile(String csvFile){
        BufferedReader br;
        List<String> linesList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line ;
            // read while there are lines left
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();}
        return linesList;
    }

    // function to write csv-file containing output transposed matrix
    public static void writeFile(String filename, String str) {
        FileOutputStream fos;
        try {
            // we have to provide a second parameter as "true" so that we don't create a new file every time
            FileWriter fw = new FileWriter(filename,true);
            // in the end of the string we have to pass to the new line
            fw.write(str+"\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // works for the file when all the lines have the same number of strings



    // map function
    public static HashMap<Integer,HashMap<Integer,String>> map(int key, String value){
        // strings in a file are separated by comma 
        String cvsSplitBy = ",";
        // to get separate words:
        String[] line = value.split(cvsSplitBy);
        // output of the map function is a map which contains:
        
        // key=position number of a string in a line, 
        // value=map(number of line which is given as a key variable from the input, string)
        
        // here string is mapped together with its line number so that we know the consequence in which the oputput strings should be placed,
        // and position number of a string will be number of the line in the output
        
        HashMap<Integer,HashMap<Integer,String>> result =new HashMap<>();
        // counter = string position in a line
        int counter = 0;
        for (String elem :line) {
            HashMap<Integer,String> newValue = new HashMap<>();
            newValue.put(key,elem);
            result.put(counter,newValue);
            counter++;
        }
        return result;
    }

    // reduce function
    public static String reduce(Integer key, HashMap<Integer,HashMap<Integer,String>> valuesMapMap){
        HashMap<Integer, String> valuesMap = valuesMapMap.get(key);
        String resultLine = "";
        
        // from map function for each key which is number of a line in the output, we get the string and its initial line number,
        // which helps to keep the right order of the values (strings) in the same line (former column)
        
        int length = valuesMap.keySet().size();
        for (int lineNumber : valuesMap.keySet() ) {
            length--;
            // trim function is used so that we don't have unnecessary spaces
            resultLine = resultLine + valuesMap.get(lineNumber).trim();
            if (length>0)
                // strings in a new file will be separated by comma again
                resultLine = resultLine+ ", ";
        }
        System.out.println(resultLine);
        return resultLine;
    }

    // main function, "driver" ?
    public static void main(String[] args) {
        String inputFilename = "input.csv";
        // reading a file
        List<String> linesList = readFile(inputFilename);
        int lineNumber = 0;

        HashMap<Integer, HashMap<Integer,String>> toReduceMap = new HashMap<>();
        HashMap<Integer,HashMap<Integer,String>> mappedLineMap;

        // iterating over lines in a file, applying map function to all of them
        
        for (String line:linesList) {
            mappedLineMap = map(lineNumber, line);

            for (int wordNumber : mappedLineMap.keySet()) {
                // for first iteration we should create a new map with the result
                if (lineNumber == 0)
                    toReduceMap.put(wordNumber, mappedLineMap.get(wordNumber));
                // for the further iterations we have to add a value to an existing map
                else {
                    HashMap<Integer, String> tmp = toReduceMap.get(wordNumber);
                    tmp.put(lineNumber,mappedLineMap.get(wordNumber).get(lineNumber));
                    toReduceMap.put(wordNumber,tmp);
                }
            }
            lineNumber++;
        }

        // writing a file with an output transposed matrix
        String outputFilename = "output.csv"; 
        for (int key : toReduceMap.keySet()) {
            String output = reduce(key,toReduceMap);
            writeFile(outputFilename,output);
        }
    }
}
