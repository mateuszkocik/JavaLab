package uj.java.pwj2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1File{

    public static String generateSha1FromFile(String filePath) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MessageDigest digest = null;
        byte[] resultByteArray = null;
        try{
            digest = MessageDigest.getInstance("SHA-1");
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
            byte[] bytes = new byte[1024];
            while(digestInputStream.read(bytes) > 0);
            resultByteArray = digest.digest();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return bytesToHexString(resultByteArray);
    }

    private static String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            int value = b & 0xFF;
            if(value < 16){
                sb.append("0");
            }
            sb.append(Integer.toHexString(value).toUpperCase());
        }
        return sb.toString();
    }
}
