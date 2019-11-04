import org.junit.jupiter.api.Test;
import sample.Classes.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class JunitTestTest {

    @Test
    public void testDistance(){
        City first = new City("firstTest",25,50);
        City second = new City("secondTest",50,100);
        double result = City.calculateDistance(first,second);
        assertEquals(55.9,result,1);
    }

    @Test
    public void testDate(){
        JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar(1998,9,29));
        int year = jalaliCalendar.getYear();
        int month = jalaliCalendar.getMonth() - 1;
        int day = jalaliCalendar.getDay();
        assertEquals(1377,year);
        assertEquals(7,month);
        assertEquals(7,month);
    }

    @Test
    public void testCustomerOff(){
        Customer test = new Customer("test","test","test","test","test", Gender.UNKNOWN,new City("test",0,0));
        for(int i=0 ;i<6 ;i++){
            test.order();
        }
        boolean isDiscount = test.isDiscount();
        assertEquals(true,isDiscount);
    }

    @Test
    public void testValidatorCheckName(){
        boolean flag = Validator.checkName("a");
        assertEquals(false,flag);
    }

    @Test
    public void testValidatorCheckEmail(){
        String test1String = "@yahoo.com";
        String test2String = "amirreza@.com";
        String test3String = "amirreza.com";
        String test4String = "amirreza";
        String test5String = "amirvedadiyan@gmail.com";

        boolean test1 = Validator.checkEmail(test1String);
        boolean test2 = Validator.checkEmail(test2String);
        boolean test3 = Validator.checkEmail(test3String);
        boolean test4 = Validator.checkEmail(test4String);
        boolean test5 = Validator.checkEmail(test5String);

        assertEquals(test1,false);
        assertEquals(test2,false);
        assertEquals(test3,false);
        assertEquals(test4,false);
        assertEquals(test5,true);
    }

    @Test
    public void testValidatorCheckMobile(){
        String test1String = "0912";
        String test2String = "9123456789";
        String test3String = "0912345678a";
        String test4String = "091234567899";
        String test5String = "09123456789";

        boolean test1 = Validator.checkMobile(test1String);
        boolean test2 = Validator.checkMobile(test2String);
        boolean test3 = Validator.checkMobile(test3String);
        boolean test4 = Validator.checkMobile(test4String);
        boolean test5 = Validator.checkMobile(test5String);

        assertEquals(test1,false);
        assertEquals(test2,false);
        assertEquals(test3,false);
        assertEquals(test4,false);
        assertEquals(test5,true);
    }

    @Test
    public void testValidatorCheckNationalCode(){
        String test1String = "0021483227";
        String test2String = "123456780";
        String test3String = "002148asa";
        String test4String = "002589621154";
        String test5String = "0055805795";

        boolean test1 = Validator.checkNationalCode(test1String);
        boolean test2 = Validator.checkNationalCode(test2String);
        boolean test3 = Validator.checkNationalCode(test3String);
        boolean test4 = Validator.checkNationalCode(test4String);
        boolean test5 = Validator.checkNationalCode(test5String);

        assertEquals(test1,false);
        assertEquals(test2,false);
        assertEquals(test3,false);
        assertEquals(test4,false);
        assertEquals(test5,true);
    }
}