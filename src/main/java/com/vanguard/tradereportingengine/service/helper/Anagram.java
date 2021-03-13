package com.vanguard.tradereportingengine.service.helper;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Anagram {

    public static boolean isAnagram(String input1, String input2) {
        if (input1.length() != input2.length()) {
            return false;
        }
        Multiset<Character> multiset1 = HashMultiset.create();
        Multiset<Character> multiset2 = HashMultiset.create();
        for (int i = 0; i < input1.length(); i++) {
            multiset1.add(input1.charAt(i));
            multiset2.add(input2.charAt(i));
        }
        return multiset1.equals(multiset2);
    }

}
