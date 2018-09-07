package interviewQuestions;

public class CirclePairing {

  /**warm up round:
   * Given a list of words, find the longest common prefix.
   *
   * ["flower","flow","flight"] return "fl"
   * ["dog","racecar","car"] return ""
   *
   * what's the time complexity of your solution?
   * what's the space complexity of your solution?
   * */

  /**Solution 1: Horizontal scanning*/
  public String longestCommonPrefix_horizontalScanning(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }
    return prefix;
  }

  /**Solution 2: vertical scanning*/
  public String longestCommonPrefix_verticalScanning(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    for (int i = 0; i < strs[0].length() ; i++){
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j ++) {
        if (i == strs[j].length() || strs[j].charAt(i) != c)
          return strs[0].substring(0, i);
      }
    }
    return strs[0];
  }

  /**Solution 2: vertical scanning, verbose version*/
  public String longestCommonPrefix_verticalScanningVerbose(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    String shortestWord = strs[0];
    for (String word : strs) {
      if (shortestWord.length() > word.length()) {
        shortestWord = word;
      }
    }
    for (int i = 0; i < shortestWord.length(); i++) {
      for (int j = 0; j < strs.length; j++) {
        if (strs[j].charAt(i) != shortestWord.charAt(i)) {
          return i == 0 ? "" : shortestWord.substring(0, i);
        }
      }
    }
    return shortestWord;
  }

  /**
   * Round 2: (if candidate passed warm-up round smoothly.)
   *
   * Suppose the given list of words are highly sorted and there're over 10 billion words.
   * Design an algorithm that works efficiently.
   *
   * e.g. input:
   * abandon
   * ability
   * able
   * about
   * above
   * abroad
   * absence
   * absent
   * absolute
   * abusive
   * academic
   * ...
   * zoo
   * zyzzyva
   *
   * Trie should be the answer.
   *
   * what's the time complexity of your solution?
   * what's the space complexity of your solution?
   * */


  /**
   * Round 3: (if candidate passed round 2 smoothly, this should be rare already.)
   *
   * What if the given list needs to support more than English characters? For instance, we have international markets like Japan,
   * how do we support Japanase characters?*/


  public static void main(String... args) {
    CirclePairing circlePairing = new CirclePairing();
    System.out.println("Hello world!");
    //String[] strs = new String[]{"flower","flow","flight"};
    String[] strs = new String[]{"dog","racecar","car"};
    System.out.println(circlePairing.longestCommonPrefix_verticalScanningVerbose(strs));
  }
}
