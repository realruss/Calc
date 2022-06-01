import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;


public class Main {
    final static char  ROME_OFFSET = 0x215F;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression:");
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc (String input) {
        boolean arabic = true;
        int arg1 = 0, arg2 = 0;

        String output = null;
        String[] subStr = input.split(" ");
        if (subStr.length != 3) {// check members number
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Expression format error");
            }
            System.exit(1);
        }

        try {//check arguments format
            arg1 = Integer.parseInt(subStr[0]);
            if (arg1 < 0 || arg1 > 10) {
                throw new DataFormatException();
            }
            arg2 = Integer.parseInt(subStr[2]);
            if (arg2 < 0 || arg2 > 10) {
                throw new DataFormatException();
            }
        }catch (NumberFormatException e ) {
            arg1 = romanToArabicNumber(subStr[0]);
            arg2 = romanToArabicNumber(subStr[2]);
            if (arg1 < 1 || arg2 < 1) {
                try {
                    throw new Exception();
                } catch (Exception e2) {
                    System.out.println("It is not integer or Roman");
                    System.exit(1);
                }
            }
            else {
                arabic = false;
            }
        } catch (DataFormatException e) {
            System.out.println("Iligal arguments int (is not in range)");
            System.exit(1);
        }

        switch (subStr[1]) { //select operation
            case "*" :
                if (arabic) {
                    output = Integer.toString(arg1 * arg2);
                } else {
                    output = arabicToRomanNumber(arg1 * arg2);
                }

                break;
            case "/" :
                if (arabic) {
                    output = Integer.toString(arg1 / arg2);
                } else {
                    output = arabicToRomanNumber(arg1 / arg2);
                }
                break;
            case "+" :
                if (arabic) {
                    output = Integer.toString(arg1 + arg2);
                } else {
                    int tmp = arg1 + arg2;
                    if (tmp > 0) {
                        output = arabicToRomanNumber(tmp);
                    } else {
                        try {
                            throw new Exception();
                        } catch (Exception e) {
                            System.out.println("Negative result of an expression with Roman numerals");
                            System.exit(1);
                        }
                    }
                }
                break;
            case "-" :
                if (arabic) {
                    output = Integer.toString(arg1 - arg2);

                } else {
                    output = arabicToRomanNumber(arg1 - arg2);
                }
                break;
            default:
                System.out.println(" Error operartor ");
                System.exit(1);
        }
        // System.out.println(output);
        return output;
    }
    static int  romanToArabicNumber(String input) { //convert Roman number to Arabic
        int outNumber = -1;
        if (input.length() == 1) {
            char ch = input.charAt(0);
            int tmp = ch - ROME_OFFSET;
            //System.out.println("ch = " + tmp);
            if (tmp > 0x00 && tmp < 0x0B) {
                outNumber = tmp;
            } else if (tmp > 0x10 && tmp < 0x1A) {
                outNumber = tmp - 0x10;
            }
        }
        return outNumber;
    }

    static String  arabicToRomanNumber(int number) { //convert Arabic number to Roman
        if ((number <= 0) || (number > 4000)) {
            try{
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println(number + " is not in range ");
            }
        }

        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNum currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.toString());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}

