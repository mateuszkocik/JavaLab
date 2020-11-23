package uj.java.pwj2020.insurance;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FloridaInsurance{

    private static final String ZIP_FILE_PATH = "FL_insurance.csv.zip";
    private static final String FILE_INSIDE_ZIP = "FL_insurance.csv";
    private static final String COUNT_PATH = "count.txt";
    private static final String TIV2012_PATH = "tiv2012.txt";
    private static final String MOST_VALUABLE_PATH = "most_valuable.txt";
    private static final List<InsuranceEntry> insuranceEntries = readEntriesFromZipFile();


    public static void main(String[] args){
        countCountries();
        sumTiv2012();
        mostValuable();
    }

    public static void countCountries(){
        try{
            var writer = new FileWriter(new File(COUNT_PATH));

            writer.write(
                    Integer.toString(
                            insuranceEntries
                                    .stream()
                                    .collect(Collectors.groupingBy(InsuranceEntry::country))
                                    .size()));

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void sumTiv2012(){
        try{
            var writer = new FileWriter(new File(TIV2012_PATH));

            writer.write(
                    insuranceEntries
                            .stream()
                            .map(InsuranceEntry::tiv2012)
                            .reduce(BigDecimal.ZERO, BigDecimal::add).toString());
                    /*insuranceEntries
                            .stream()
                            .collect(Collectors.reducing(
                                            BigDecimal.ZERO,
                                            InsuranceEntry::tiv2012,
                                            BigDecimal::add));*/

            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void mostValuable(){
        try{
            var writer = new FileWriter(new File(MOST_VALUABLE_PATH));
            writer.write("country,value\n");

            insuranceEntries
                    .stream()
                    .collect(Collectors.toMap(
                            InsuranceEntry::country,
                            e -> e.tiv2012().subtract(e.tiv2011()),
                            BigDecimal::add))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                    .limit(10)
                    .forEach(e -> writeOneEntryMV(writer, e));


            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void writeOneEntryMV(FileWriter writer, Map.Entry<String, BigDecimal> e){
        try{
            writer.write(e.getKey() + ',' + e.getValue().setScale(2, RoundingMode.HALF_UP) + '\n');
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }


    public static ArrayList<InsuranceEntry> readEntriesFromZipFile(){
        var entriesList = new ArrayList<InsuranceEntry>();
        try{
            InputStream is = getInputStreamFromZip();
            readInsuranceEntryFromCSV(entriesList, is);
        }catch(IOException e){
            System.err.println("Reading data from zip file failed");
            e.printStackTrace();
        }

        return entriesList;
    }

    private static InputStream getInputStreamFromZip() throws IOException{
        ZipFile zipFile = new ZipFile(ZIP_FILE_PATH);
        ZipEntry zipEntry = new ZipEntry(FILE_INSIDE_ZIP);
        return zipFile.getInputStream(zipEntry);
    }

    private static void readInsuranceEntryFromCSV(ArrayList<InsuranceEntry> entriesList, InputStream is) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String csvLine = br.readLine();
        while((csvLine = br.readLine()) != null){
            String[] csvLineArray = csvLine.split(",");
            entriesList.add(getInsuranceEntryFromCSVLine(csvLineArray));
        }
    }

    public static InsuranceEntry getInsuranceEntryFromCSVLine(String[] entry){
        int policyId = Integer.parseInt(entry[0]);
        String country = entry[2];
        BigDecimal tiv2011 = new BigDecimal(entry[7]);
        BigDecimal tiv2012 = new BigDecimal(entry[8]);
        Line line = Line.valueOf(entry[15].toUpperCase());
        String construction = entry[16];
        double latitude = Double.parseDouble(entry[13]);
        double longitude = Double.parseDouble(entry[14]);

        return new InsuranceEntry(policyId, country, tiv2011, tiv2012, line, construction, latitude, longitude);
    }

}
