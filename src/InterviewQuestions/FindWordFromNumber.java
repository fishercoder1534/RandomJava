package InterviewQuestions;

/**With Cracking group on 11/23/2013, a very fruitful day.

 *
 *Problem statement (Dropbox interview question):
 *Given a defined dictionary, test if a 7-digit number can represent any
 *valid words from this dictionary.
 *The digit to alphabet matching relationship is exactly the same as from the cellphone keyboard.
 */

/**The idea here is that:
 *This 7-digit number can only be separated into the following three situations:
 *a 3-letter word followed by a 4-letter word;
 *a 4-letter word followed by a 3-letter word;
 *a 7-letter word.
 *Because we assume there's no word with only one letter or two letters in our dictionary.*/



/**Then we construct another three HashMaps to store all 3-letter words, all 4-letter words and
 * all 7-letter words in the dictionary respectively. */
import java.util.ArrayList;
import java.util.HashMap;

public class FindWordFromNumber {

/**The KEY thing to bare in mind is that in HashMap,
 *you can only get values using key, NOT the other way around!*/

        public static void main(String args[]){

		/**We construct our first HashMap, ATTENTION: in this HashMap, the key is the alphabet
		 *while the value is its corresponding digit, because we want to find the digit from
		 *the alphabet.*/
            HashMap<Character, Integer> charToNum = new HashMap();
            charToNum.put('a', 2);
            charToNum.put('b', 2);
            charToNum.put('c', 2);
            charToNum.put('d', 3);
            charToNum.put('e', 3);
            charToNum.put('f', 3);
            charToNum.put('g', 4);
            charToNum.put('h', 4);
            charToNum.put('i', 4);
            charToNum.put('j', 5);
            charToNum.put('k', 5);
            charToNum.put('l', 5);
            charToNum.put('m', 6);
            charToNum.put('n', 6);
            charToNum.put('o', 6);
            charToNum.put('p', 7);
            charToNum.put('q', 7);
            charToNum.put('r', 7);
            charToNum.put('s', 7);
            charToNum.put('t', 8);
            charToNum.put('u', 8);
            charToNum.put('v', 8);
            charToNum.put('w', 9);
            charToNum.put('x', 9);
            charToNum.put('y', 9);
            charToNum.put('z', 9);

		/**I should use the above HashMap to automatically put the words in the dictionary into
		 *their corresponding HashMaps and, inside their corresponding HashMaps to their corresponding
		 *key.*/

		/**So I commented the following manual assigning methods out.*/

		/**HashMap<Integer, String> threeLetterWord = new HashMap();
		threeLetterWord.put(567, "lop");
		threeLetterWord.put(432, "ifc");
		threeLetterWord.put(527, "lap");
		threeLetterWord.put(827, "tap");
		threeLetterWord.put(567, "lmp");
		threeLetterWord.put(438, "get");

		HashMap<Integer, String> fourLetterWord = new HashMap();
		fourLetterWord.put(5678, "jmpu");
		fourLetterWord.put(8432, "thea");
		fourLetterWord.put(7843, "ruif");
		fourLetterWord.put(8432, "vida");

		HashMap<Integer, String> sevenLetterWord = new HashMap();
		sevenLetterWord.put(5678432, "kostiea");
		sevenLetterWord.put(5678432, "kmpugec");
		 */

//		Declare an array of type String to hold the whole dictionary.
            String dict[] = {"lop", "ifc", "lap", "tap", "lmp", "get",
                    "jmpu", "thea", "ruif", "vida", "kostiea", "kmpugec"};


		/**Attention: the parameters inside the HashMap: one is Integer which represents digit,
		 * the other is ArrayList<String> which is used to store all possible valid words that this
		 * number can represent. Because one number can represent a couple different words.*/
            HashMap<Integer, ArrayList<String>> threeLetterWord = new HashMap();
            HashMap<Integer, ArrayList<String>> fourLetterWord = new HashMap();
            HashMap<Integer, ArrayList<String>> sevenLetterWord = new HashMap();

            for(int i = 0; i < dict.length; i++)
            {
                if(dict[i].length() == 3){
                    String num = "";
                    for(int j = 0; j < dict[i].length(); j++){
                        String temp = String.valueOf(charToNum.get(dict[i].charAt(j)));
                        num = num + temp;
                    }
                    int _3LetterWordNum = Integer.parseInt(num);
				/**Here we need to test if this number has already had one valid word
				 *put in the HashMap,
				 *
				 *if so, we need to get whole ArrayList elements out
				 *first, then use ArrayList.add() method to append this word at the end of
				 *the last word that this number represents, then put it in the HashMap;
				 *
				 *if not, this means this word is the first word that this number
				 *represents in the dictionary, so we just put
				 *it in the ArrayList, then put it in the HashMap. */
                    if(threeLetterWord.containsKey(_3LetterWordNum))
                    {	ArrayList<String> allString = new ArrayList<String>();
                        allString = threeLetterWord.get(_3LetterWordNum);
                        allString.add(dict[i]);
                        threeLetterWord.put(_3LetterWordNum, allString);
                    }
                    else{
                        ArrayList<String> allString = new ArrayList<String>();
                        allString.add(dict[i]);
                        threeLetterWord.put(_3LetterWordNum, allString);
                    }
                }
                else if (dict[i].length() == 4){
                    String num = "";
                    for(int j = 0; j < dict[i].length(); j++){
                        String temp = String.valueOf(charToNum.get(dict[i].charAt(j)));
                        num = num + temp;
                    }
                    int _4LetterWordNum = Integer.parseInt(num);
                    if(fourLetterWord.containsKey(_4LetterWordNum))
                    {
                        ArrayList<String> allString = new ArrayList<String>();
                        allString = fourLetterWord.get(_4LetterWordNum);
                        allString.add(dict[i]);
                        fourLetterWord.put(_4LetterWordNum, allString);
                    }
                    else{
                        ArrayList<String> allString = new ArrayList<String>();
                        allString.add(dict[i]);
                        fourLetterWord.put(_4LetterWordNum, allString);
                    }
                }
                else if (dict[i].length() == 7){
                    String num = "";
                    for(int j = 0; j < dict[i].length(); j++){
                        String temp = String.valueOf(charToNum.get(dict[i].charAt(j)));
                        num = num + temp;
                    }
                    int _7LetterWordNum = Integer.parseInt(num);
                    if(sevenLetterWord.containsKey(_7LetterWordNum))
                    {	ArrayList<String> allString = new ArrayList<String>();
                        allString = sevenLetterWord.get(_7LetterWordNum);
                        allString.add(dict[i]);
                        sevenLetterWord.put(_7LetterWordNum, allString);
                    }
                    else{
                        ArrayList<String> allString = new ArrayList<String>();
                        allString.add(dict[i]);
                        sevenLetterWord.put(_7LetterWordNum, allString);
                    }
                }
            }

		/**Use HashMap.values() method to get all the VALUES inside this HashMap.*/
//		System.out.println(threeLetterWord.values());
//		System.out.println(fourLetterWord.values());
//		System.out.println(sevenLetterWord.values());

		/*Use HashMap.keySet() method to get all the KEYS inside this HashMap.*/
//		System.out.println(threeLetterWord.keySet());
//		System.out.println(fourLetterWord.keySet());
//		System.out.println(sevenLetterWord.keySet());

            String input = "1020";

            if(input.length() <= 2){
                System.out.println("Your number has less than 3 digits," +
                        " cannot be used to represent any valid words" +
                        " in our dictionary, please enter a seven-digit number");
            }

            else if(input.length() == 3)/*When the input string has 3 digits.*/
            {
                if(threeLetterWord.containsKey(Integer.parseInt(input)))
                {
                    ArrayList<String> temp3word = new ArrayList<String>();
                    temp3word = threeLetterWord.get(Integer.parseInt(input));
                    for(String i : temp3word)
                        System.out.println(i);
                }
                else
                    System.out.print("No valid word combinations for this 3-digit number" +
                            ", please enter another number.");
            }

            else if(input.length() == 4)/*When the input string has 4 digits.*/
            {
                if(fourLetterWord.containsKey(Integer.parseInt(input)))
                {
                    ArrayList<String> temp4word = new ArrayList<String>();
                    temp4word = fourLetterWord.get(Integer.parseInt(input));
                    for(String i : temp4word)
                        System.out.println(i);
                }
                else
                    System.out.println("No valid word combinations for this 4-digit number" +
                            ", please enter another number.");
            }
            else if(input.length() == 5)
                System.out.println("No valid word combinations for a 5-digit number," +
                        " please enter another number.");
            else if(input.length() == 6)
                System.out.println("No valid word combinations for a 6-digit number," +
                        " please enter another number.");

            else if(input.length() == 7)/**When the input string has 7 digits, there are three possible
		situations for this, as follows:*/
            {
                if(sevenLetterWord.containsKey(Integer.parseInt(input)))
                //the following is the situation that there's only seven-letter word
                { ArrayList<String> temp7word = new ArrayList<String>();
                    temp7word = sevenLetterWord.get(Integer.parseInt(input));
                    System.out.println("This 7-digit number as a whole macthes valid words in the dictonary," +
                            " they are: ");
                    for(String i : temp7word)
                    {
                        System.out.println(i);
                    }
                }

                if(threeLetterWord.containsKey(Integer.parseInt(input.substring(0, 3)))){
                    {
                        //the following is the situation that there's three-letter word followed by a four-letter word
                        ArrayList<String> temp3word = new ArrayList<String>();
                        ArrayList<String> temp4word = new ArrayList<String>();
                        String s1 = input.substring(0, 3);/*Pay ATTENTION to the substring method:
					it can take char at index 0 but cannot take char at index 3, so in order to get
					the first three chars of the String, we need to use .substring(0, 3) instead of
					.substring(0, 2).*/
                        //System.out.println(s1);
                        String s2 = input.substring(3, 7);
                        //System.out.println(s2);
                        if(fourLetterWord.containsKey(Integer.parseInt(s2)))
                        { System.out.println("This number can represent one 3-letter word followed by" +
                                " a 4-letter word, they are:");
                            temp3word = threeLetterWord.get(Integer.parseInt(s1));
                            temp4word = fourLetterWord.get(Integer.parseInt(s2));
                            for(String i : temp3word)
                                for(String j : temp4word){
                                    System.out.println(i + " + " + j);
                                }
                        }
                        else
                            System.out.println("There's valid word for the first 3 digits but no valid words" +
                                    " for the last 4-digit number.");
                    }
                }

                //the following is the situation that there's four-letter word followed by a three-letter word
                if(fourLetterWord.containsKey(Integer.parseInt(input.substring(0, 4))))
                {
                    {
                        String s1 = input.substring(0, 4);
                        //System.out.println(s1);
                        ArrayList<String> temp4word = new ArrayList<String>();
                        ArrayList<String> temp3word = new ArrayList<String>();

                        String s2 = input.substring(4, 7);
                        //System.out.println(s2);
                        if(threeLetterWord.containsKey(Integer.parseInt(s2)))
                        {	System.out.println("This number can represent one 4-letter word followed by" +
                                " a 3-letter word, they are:");
                            temp4word = fourLetterWord.get(Integer.parseInt(s1));
                            temp3word = threeLetterWord.get(Integer.parseInt(s2));
                            for(String i : temp4word)
                                for(String j : temp3word){
                                    System.out.print(i + " + " + j);
                                }
                        }
                        else
                            System.out.println("There is valid word combinations for the first 4-digit of" +
                                    "this number, but there's no valid word for the last 3-digit.");
                    }
                }

                else
                    System.out.println("This 7-digit number doesn't not match any 3-letter word, 4-letter word or" +
                            " 7-letter word in the dictionary.");
            }
            else
                System.out.println("Too long input, please enter a valid number.");
        }
}
