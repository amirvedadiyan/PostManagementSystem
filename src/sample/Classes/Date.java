package sample.Classes;

/**
 * <h1 style="color:red;"> Date Class </h1>
 * <p>This is solar hijri Date</p>
 * @author Amirreza Vedadiyan
 * <br>
 * <a href="mailto:amirvedadiyan@gmail.com">Amirreza Vedadiyan
 * {@code @}gmail.com</a>
 * <br>
 * <a href="http://amirvedadiyan.ir/">Website</a>
 * @version 1.0.0
 */

public class Date {
    private int year;
    private int month;
    private int day;

    /**
     * This Construct and Initialize an object of Date
     * @param year Year of Date
     * @param month Month of Date
     * @param day Day of Date
     */
    public Date(int year, int month, int day) {
        checkAndSetDate(year, month, day);
    }

    /**
     * This Construct and Initialize an object of Date
     * @param date Complete Date that have Year and Month and Day
     */
    public Date(Date date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    /**
     * This Check if the date is possible and correct and if it's correct set it else set year to 0 and month,day to 1
     * @param year  Year of Date
     * @param month Month of Date
     * @param day   Day of Date
     */
    private void checkAndSetDate(int year, int month, int day) {
        if (checkInputs(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            this.year = 0;
            this.month = 1;
            this.day = 1;
        }
    }

    /**
     * This Check if the year and month and day are possible in calendar
     * @param year  Year of Date
     * @param month Month of Date
     * @param day   Day of Date
     * @return a boolean that show is it possible or not?
     */
    private boolean checkInputs(int year, int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31 || month > 6
                && day == 31) {
            return false;
        }
        return month != 12 || day != 30 || isLeapYear(year);
    }

    public void setDate(int year, int month, int day) {
        checkAndSetDate(year, month, day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        checkAndSetDate(year, this.month, this.day);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        checkAndSetDate(this.year, month, this.day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        checkAndSetDate(this.year, this.month, day);
    }

    /**
     * This make string that we can use in System.out.Println() method
     * @return string that show the detail we want from this object
     */
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    /**
     * This make another object from date class and Initialize it with next day details
     * @return date object of next day
     */
    public Date nextDay() {
        Date curDate = new Date(this);
        Date nextDate = new Date(this);
        if (curDate.month == 12) {
            handleTheLastMonth(curDate, nextDate);
        } else if (curDate.day < 30) {
            nextDate.day++;
        } else if (curDate.day == 30 && curDate.month < 7) {
            nextDate.day++;
        } else {
            nextDate.day = 1;
            nextDate.month++;
        }
        return nextDate;
    }

    /**
     * This Handle number of LastMonth
     * @param curDate currentDate
     * @param nextDate Date of next day
     */
    private void handleTheLastMonth(Date curDate, Date nextDate) {
        int endOfMonthDay = 29;
        if (isLeapYear(curDate.year)) {
            endOfMonthDay = 30;
        }
        if (curDate.day == endOfMonthDay) {
            nextDate.year++;
            nextDate.month = 1;
            nextDate.day = 1;
        } else {
            nextDate.day++;
        }
    }

    /**
     * This check if the year is leap year or not
     * @param year year that we want to is leap year or not
     * @return boolean that if it's leap year return true else return false
     */
    private boolean isLeapYear(int year) {
        int firstFraction, secondFraction;
        final double a = 0.025d;
        final double b = 266d;
        double c, d;
        if (year > 0) {
            c = ((year + 38) % 2820) * 0.24219 + a;
            d = ((year + 39) % 2820) * 0.24219 + a;
        } else if (year < 0) {
            c = ((year + 39) % 2820) * 0.24219 + a;
            d = ((year + 40) % 2820) * 0.24219 + a;
        } else {
            return false;
        }
        firstFraction = (int) ((c - Math.floor(c)) * 1000);
        secondFraction = (int) ((d - Math.floor(d)) * 1000);
        return firstFraction <= b && secondFraction > b;
    }
}