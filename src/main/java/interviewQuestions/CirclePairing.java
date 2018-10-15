package interviewQuestions;

public class CirclePairing {

  /**Round 1: give two words, find the longest common prefix between the two.
   *  This should be super easy. Just to warm up the candidate.
   *
   * Round 2: what if you're given N words
   *
   * ["flower","flow","flight"] return "fl"
   * ["dog","racecar","car"] return ""
   *
   * what's the time complexity of your solution? O(S) where S is all the chars in all strings, since in the worst case, all the strings are the same
   * what's the space complexity of your solution? O(1) no additional space needed.
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
   * Round 3:
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

  public static class Solution1 {
    class TrieNode {

      char val;
      boolean isWord;
      TrieNode[] children = new TrieNode[26];

      // Initialize your data structure here.
      public TrieNode() {
      }

      public TrieNode(char c) {
        this.val = c;
      }
    }

    public class Trie {
      private TrieNode root;

      public Trie() {
        root = new TrieNode();
        root.val = ' ';//initialize root to be an empty char, this is a common practice as how Wiki defines Trie data structure as well
      }

      // Inserts a word into the trie.
      public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
          if (node.children[word.charAt(i) - 'a'] == null) {
            node.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
          }
          node = node.children[word.charAt(i) - 'a'];
        }
        node.isWord = true;
      }

      // Returns if the word is in the trie.
      public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
          if (node.children[word.charAt(i) - 'a'] == null) {
            return false;
          }
          node = node.children[word.charAt(i) - 'a'];
        }
        return node.isWord;
      }

      // Returns if there is any word in the trie
      // that starts with the given prefix.
      public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
          if (node.children[prefix.charAt(i) - 'a'] == null) {
            return false;
          }
          node = node.children[prefix.charAt(i) - 'a'];
        }
        return true;
      }
    }

    // Your Trie object will be instantiated and called as such:
    // Trie trie = new Trie();
    // trie.insert("somestring");
    // trie.search("key");
  }

  /**
   * Round 4:
   *
   * What if the given list needs to support more than English characters? For instance, we have international markets like Japan,
   * how do we support Japanase characters?*/


   /** Round 5: How to support constantly adding new words and removing existing words, also return longest common prefix at any given timestamp*/


  public static void main(String... args) {
    CirclePairing circlePairing = new CirclePairing();
    System.out.println("Hello world!");
    //String[] strs = new String[]{"flower","flow","flight"};
    String[] strs = new String[]{"dog","racecar","car"};
    System.out.println(circlePairing.longestCommonPrefix_verticalScanningVerbose(strs));
  }
}
