package tristan.core.utils;

public class Utils{

    public boolean isWholeNumber(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

}
