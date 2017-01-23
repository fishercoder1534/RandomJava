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
    }
}

