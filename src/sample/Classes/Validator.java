package sample.Classes;

/**
 * @author Amirreza Vedadiyan
 */

public class Validator {

    public static boolean checkName(String name) {
        if(name.length() >= 3){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        if(email.equals("")){
            return  true;
        }
        if(email.indexOf("@") == -1){
            return false;
        }
        if(email.indexOf("@") == 0){
            return false;
        }
        if(email.indexOf("@") >= email.length() - 5){
            return false;
        }
        if(email.indexOf(".com") == -1){
            return false;
        }
        if(email.indexOf(".com") + 4 < email.length()){
            return false;
        }
        return true;
    }

    public static boolean checkMobile(String mobile) {
        if(mobile.equals("")){
            return  true;
        }
        if(mobile.length() != 11){
            return false;
        }
        if(mobile.indexOf("09") != 0){
            return false;
        }
        for(int i=0 ; i<mobile.length() ;i++){
            if(mobile.charAt(i) < 48 || mobile.charAt(i) > 57){
                return false;
            }
        }
        return true;
    }

    public static boolean checkNationalCode(String nationalCode) {
        if (nationalCode.length() == 8) {
            nationalCode = "00".concat(nationalCode);
        } else if (nationalCode.length() == 9) {
            nationalCode = "0".concat(nationalCode);
        } else if (nationalCode.length() < 8) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (nationalCode.charAt(i) - '0') * (10 - i);
        }
        int control = 0;
        if (sum % 11 < 2) {
            control = sum % 11;
        } else {
            control = 11 - (sum % 11);
        }
        return (nationalCode.charAt(9) - '0') == control;
    }

    public static boolean checkNumberOnly(String numberOnly){
        boolean flag = true;

        if(numberOnly.equals("")){
            return  false;
        }

        for(int i=0 ;i<numberOnly.length() ;i++){
            if((numberOnly.charAt(i) < 48 || numberOnly.charAt(i) > 57) && numberOnly.charAt(i)!='.'){
                flag = false;
                break;
            }
        }
        return flag;
    }

    private Validator() {
    }
}
