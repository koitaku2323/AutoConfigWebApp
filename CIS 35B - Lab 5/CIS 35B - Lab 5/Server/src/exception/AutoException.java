package exception;

import model.Automobile;
import util.FileIO;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {
    // Error number enumeration.
    public enum ErrorNo {
        INVALID_BASE_PRICE, MISSING_OPTIONSET_NAME, MISSING_OPTIONSET_DATA, MISSING_OPTION_NAME, MISSING_OPTION_PRICE, MISSING_FILE_NAME
    }

    // Error messages corresponding to the above error numbers.
    private String[] strArray = { "Auto base price is invalid",
            "OptionSet name is missing", "OptionSet data is missing",
            "Option name is missing", "Option price is invalid",
            "File name is missing" };

    private ErrorNo errNo;

    // default constructor
    public AutoException() {
        super();
        printmyproblem();
    }

    // parameterized constructor
    public AutoException(ErrorNo errNo) {
        super();
        this.errNo = errNo;
        printmyproblem();
    }

    public AutoException(int errNum) {
        super();
        this.errNo = ErrorNo.values()[errNum];
        printmyproblem();
    }

    public ErrorNo getErrorno() {
        return errNo;
    }

    public void setErrorno(ErrorNo errNo) {
        this.errNo = errNo;
    }

    public void printmyproblem() {
        System.out.println("AutoException Error No: " + errNo
                + ", Error Message: " + strArray[errNo.ordinal()]);
    }

    // logToFile() appends an error log to the given file.
    public void logToFile(String logFile) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        try {
            // Write output to disk
            FileOutputStream file = new FileOutputStream(logFile, true);
            BufferedOutputStream buff = new BufferedOutputStream(file);
            DataOutputStream data = new DataOutputStream(buff);
            data.writeChars("Timestamp: " + ts + " Error No: " + errNo.ordinal()
                    + " Error Message: " + strArray[errNo.ordinal()] + "\n");
            data.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    // fix() creates an object of Fix1to6 to fix the current error. Returns the
    // fixed Automobile object.
    public Automobile fix() {
        Automobile autoObj = null;
        FixErrors f1 = new FixErrors();

        switch (errNo) {
            case INVALID_BASE_PRICE:
                return f1.fixInvalidBasePrice();

            case MISSING_OPTIONSET_NAME:
                return f1.fixMissingOptionSetName();

            case MISSING_OPTIONSET_DATA:
                return f1.fixMissingOptionSetData();

            case MISSING_OPTION_NAME:
                return f1.fixMissingOptionName();

            case MISSING_OPTION_PRICE:
                return f1.fixMissingOptionPrice();

            case MISSING_FILE_NAME:
                return f1.fixMissingFileName();

            default:
                System.out.println("Unexpected error");
                System.exit(0);
        }
        return null;
    }
}