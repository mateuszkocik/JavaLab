package uj.java.pwj2020.insurance;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FloridaInsurance {

    private static final String zipFilePath = "FL_insurance.csv.zip";
    private static final String zippedFile = "FL_insurance.csv";


    public static void main(String[] args) {
        List<InsuranceEntry> list = readEntriesFromZipFile();


    }

    public static ArrayList<InsuranceEntry> readEntriesFromZipFile(){
        var entriesList = new ArrayList<InsuranceEntry>();
        try{
            ZipFile zipFile = new ZipFile(zipFilePath);
            ZipEntry zipEntry = new ZipEntry(zippedFile);
            InputStream is = zipFile.getInputStream(zipEntry);
            readFromCSVFile(entriesList, is);
        }catch(IOException e){
            System.err.println("Reading data from zip file failed");
            e.printStackTrace();
        }

        return entriesList;

    }

    private static void readFromCSVFile(ArrayList<InsuranceEntry> entriesList, InputStream is) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String csvLine = br.readLine();
        while((csvLine = br.readLine()) != null){
            String[] csvLineArray = csvLine.split(",");
            entriesList.add(getInsuranceEntryFromCSVLine(csvLineArray));
        }
    }

    public static InsuranceEntry getInsuranceEntryFromCSVLine(String[] entry){
        int policyId = Integer.valueOf(entry[0]);
        String country = entry[2];
        BigDecimal tiv2011 = BigDecimal.valueOf(Double.valueOf(entry[7]));
        BigDecimal tiv2012 = BigDecimal.valueOf(Double.valueOf(entry[8]));
        Line line = entry[15] == "Residential" ? Line.RESIDENTIAL : Line.COMMERCIAL;
        String construction = entry[16];
        double latitude = Double.valueOf(entry[13]);
        double longitude = Double.valueOf(entry[14]);

        return new InsuranceEntry(policyId, country, tiv2011, tiv2012, line, construction, latitude, longitude);
    }

}
