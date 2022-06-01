import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNum {
    I(1, "\u2160"), IV(4, "\u2163"), V(5, "\u2164"), IX(9,"\u2168"), X(10, "\u2169"),
    XL(40, "\u2169\u216C"), L(50, "Ⅼ"), XC(90, "ⅩⅭ" ), C(100, "Ⅽ"),
    CD(400, "ⅭⅮ"), D(500, "Ⅾ"), CM(900, "ⅭⅯ"), M(1000, "Ⅿ");

    private int value;
    private String strValue;

    RomanNum(int value, String strValue) {
        this.value = value;
        this.strValue = strValue;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return strValue;
    }
    public static List<RomanNum> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNum e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
