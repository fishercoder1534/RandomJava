package sporadic;

/**
 * Created by stevesun on 1/22/17.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**This is a program to play around with regex, I asked a question on SO: http://stackoverflow.com/questions/41774435/how-to-split-a-string-based-on-in-java*/
public class RegexTestPatternMatcher {
    public static final String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";

    public static void main(String[] args) {
        String string = "[{1000, 500, 1}, {1001, 501, 1}, {1002, 501, 2}]";
        Matcher m = Pattern.compile("\\{[0-9, ]+\\}").matcher(string);
        while(m.find()){
            System.out.println(m.group());
        }

        Pattern pattern = Pattern.compile("\\w+");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
        // check all occurance
        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
        // now create a new pattern and matcher to replace whitespace with tabs
        Pattern replace = Pattern.compile("\\s+");
        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        System.out.println(matcher2.replaceAll("\t"));

        //use Java to print out a long string with \n inside
        System.out.println("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDF3xdggzWiOsbZ\nETP5JSQswsCA7PGijDAM19Vg78rPii2kWBJ7mT8SbE+3UVZgZQeHpXsD4dz+7PY2\n3HJVxPI/xHFmukarX9LM9FRcPCWnjZDC8OqlbpDPwUyHU98EMfAxCtmie/LtnxCv\nbtXLEpCV/E8Y14aJOMzwQAwc/qbcTX4cqw/gMgcNogYHcuNCIxlNAIOnsXsNm6jx\nx0spL9Z/5Cny0ys/+GkxMi1rTZROESdYI0xwHSMcl8SjO2z1aejdRC2U/3jb4X/n\nmbjAzHfFTdjtxU5b9e9xPuD2+/tv8ug8cm1HjNrxhTwcW7chqUeQAxB6Dd5kPV3n\n7pkHy/8HAgMBAAECggEAGVclvkVvc/RFDP2IA2s/Q8A5OQmfNGrxqGNnvz9WYNfY\nWoiw7UVF740RdG+lOTkXCsclhuzBCaC9M29t8RAE2ifFQhuu+zmNXJQZzaoiRF/7\n3wtNcZxETWb67wXNtNLUaONz1bw78zSAxYbTOGuLOroSQyMu5pnwnQAGzRvLsMC9\n1RnrV7kPgdf0iVbKjVGXW2TzLmiVNxnglXFAYM406vblcZaWBcdSPuSAZs7WPlle\nYOgtwcNX1TPBoujy3tMRBjjJPUt1yixgp74iKThG5FC6YCy9/MWfX9WE27/SsIh6\nC+nFJaVMwl/2iLQ123UH0kouIb9Nckmdsk6R+uSN8QKBgQD8QJhiNoPY3HARZLgZ\nqj9gbp0/170z2nii7wT/8ynQij207kFYjUnOC78KJsFmrKyf2dicoIxu6fj0cL6A\nOdLIFpmVByy6pAJpSW73xirzhmYt2f05a4D/A3LkfmwdsZ1x4DQx4U9PgKgJpIYs\n+cuGzSee0uylImLnlwqbQG0LCQKBgQDIz6qX5USrGWPInOTSb3h1N3FuOqfxrtFp\nKWjWimdU9BmuArVHHIrrHW9/xQsnpX3iaQPfTbeDpy32VZulhLhJs028JZu+/fGb\nqYUiDgwOJ+sYCC/3H5/KGt2/Xjfi213Fh9XiWj1ZbZvdj9Dygwn4yGiP4dUGVQGo\nP9zQj7BtjwKBgAQZZKCL9D6M7oFJ2rgIDTma8pE8B0YVccpsCe/C2tYZuQD8sjEn\nMqDXjgYzNQdfHPsIBj4dWcrfoH0Qa/gXeHZp75r9X9u0mJlvaQ87uCz27Sgnl7bc\nKV97hd1ytH0TCtTz6MU6vRg0pgZqFwgaExWgtdkd4lyYn3TV/oUhWeRJAoGAJX+R\n7ZdkgUbWeUnC+QDTz5+w0NKnNdxdQnP3HcjujtCeUv4yd7r+vfTbM1LKSHcA0Nyn\nWnWaxTzculk2HWxxNWIELBQhx0KIcXbwY3GYErSlk/FDc7Q2FHl72xZu/S/VjONW\nr1QGjMKJDpCihgauQAS8cdHMA8iv7IkwZMnQOkUCgYEA6yVXJ9It1fE1eisK2NY5\nfkLw1iviBWpZ4mgipTTglP5K3CEG1ygDs7PJOP0JEiDWPWf8PmvROUp1k9x+nKFP\nekd2qwZEul9Pr0jaD3y9dnpK3Sg4qxKhlRy0to3xzszbyL7gmP8l/9yxcbTyIRLT\nWDZrtCj706FGRFx/QkUkku0=");
    }
}

