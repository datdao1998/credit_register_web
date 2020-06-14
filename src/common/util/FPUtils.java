package common.util;
// Author: anhnv

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FPUtils {

    public static String SHA1Encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static boolean checkTimeIsIn(String time, String startTime, String endTime) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(time);
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startTime);
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endTime);
        if(date.compareTo(startDate) < 0) return false;
        if(date.compareTo(endDate) > 0) return false;
        return true;
    }

    public static boolean validateDateFormat(String date) {
        try {
            Date temp = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean validateTimeDateFormat(String date) {
        try {
            Date temp = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy").parse(date);
        }catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String toTimeOut(String timeIn) {
        String timeOut = new String();
        String h = timeIn.substring(0, 2);
        String [] s = timeIn.split("");
        if (s[3].equals("0")) {
            h = String.valueOf(Integer.parseInt(h) + 1);
            if (h.length() == 1) {
                timeOut = "0" + h + ":" + "3" + timeIn.substring(4, timeIn.length());
            }
            else {
                timeOut = h + ":" + "3" + timeIn.substring(4, timeIn.length());
            }
        }
        else {
            h = String.valueOf(Integer.parseInt(h) + 2);
            if (h.length() == 1) {
                timeOut = "0" + h + ":" + "0" + timeIn.substring(4, timeIn.length());
            }
            else {
                timeOut = h + ":" + "0" + timeIn.substring(4, timeIn.length());
            }
        }
        return timeOut;
    }

}
