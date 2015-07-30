package com.parse.starter;

/**
 * Created by Tony on 15-07-30.
 */
public class HelperMethods {

    public static String datify(String DateString){
        String delims = "[T]";
        String[] tokens = DateString.split(delims);
        String dateTime = tokens[0];
        String timeStamp = tokens[1];
        String[] tokens2 = timeStamp.split("[.]");
        String timeTime = tokens2[0];

        String outputString = "Date: " + dateTime + " Time: " + timeTime;

        return outputString;
    }
}
