package com.zolotuhinartem.lastfminfo;

/**
 * Created by zolotuhinartem on 16.12.16.
 */

public class StringManager {

    public static String deleteSpacesInStartAndEnd(String s){
        if (s != null) {

            int k;
            for(k = 0; k < s.length(); k++){
                if (charIsNotSpaceAndNewLineAndTab(s.charAt(k))){
                    break;
                }
            }
            int l;
            for(l = s.length() - 1; l >=0; l--){
                if (charIsNotSpaceAndNewLineAndTab(s.charAt(l))){
                    break;
                }
            }

            return s.substring(k, l + 1);
        } else {
            return "";
        }
    }

    private static boolean charIsNotSpaceAndNewLineAndTab(char c){
        return ((c != ' ') && (c != '\t') && (c != '\n'));
    }

}
