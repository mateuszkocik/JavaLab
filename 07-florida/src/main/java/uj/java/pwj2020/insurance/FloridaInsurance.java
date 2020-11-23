package uj.java.pwj2020.insurance;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FloridaInsurance {

    private static final String ZIP_FILE_PATH = "FL_insurance.csv.zip";
    private static final String ZIPPED_FILE = "FL_insurance.csv";


    public static void main(String[] args) {
        List<InsuranceEntry> insuranceEntries = readEntriesFromZipFile();

        System.out.println(
                insuranceEntries
                        .stream()
                        .collect(Collectors.groupingBy(InsuranceEntry::country))
                        .size()
        );

        System.out.println(
                insuranceEntries
                        .stream()
                        .map(InsuranceEntry::tiv2012)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        /*insuranceEntries
                .stream().collect(Collectors.reducing(BigDecimal.ZERO, InsuranceEntry::tiv2012, BigDecimal::add));*/


        Map<String,BigDecimal> map =
                insuranceEntries
                        .stream()
                        .collect(Collectors.toMap(
                                e -> e.country(),
                                e -> e.tiv2012().subtract(e.tiv2011()),
                                (existing,actual) -> existing.add(actual)))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                        .limit(10)
                        .collect(Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue()
                        ));

        System.out.println(map);





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
        ZipEntry zipEntry = new ZipEntry(ZIPPED_FILE);
        InputStream is = zipFile.getInputStream(zipEntry);
        return is;
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
