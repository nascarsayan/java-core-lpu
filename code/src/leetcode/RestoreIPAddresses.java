package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    static class Solution {
        String s;
        List<String> validIPs;
        String getIPFromOctets(List<String> octets) {
            return String.join(".", octets);
        }
        void recurse(int idx, List<String> octets) {
            // We have reached the end of the string.
            if (idx == s.length()) {
                if (octets.size() != 4) return;
                // We add it as valid IP only if we created 4 octets
                // and reached the end of the string.
                validIPs.add(getIPFromOctets(octets));
                return;
            }
            // We created 4 octets without using the full length of string. Fail.
            if (octets.size() == 4) return;
            for (int octetLen = 1; octetLen <= 3; octetLen++) {
                // We break for unusable cases:
                // When our pointer > size or when the octet is > 255.
                if (idx+octetLen > s.length()) break;
                var octet = s.substring(idx, idx+octetLen);
                int octetInt = Integer.parseInt(octet);
                if (octetInt > 255) break;
                // We cannot have any octet of len > 1 starting with 0.
                if (Integer.toString(octetInt).compareTo(octet) != 0) break;
                octets.addLast(octet);
                recurse(idx+octetLen, octets);
                octets.removeLast();
            }
        }
        public List<String> restoreIpAddresses(String s) {
            this.s = s;
            validIPs = new ArrayList<>();
            recurse(0, new ArrayList<>());
            return validIPs;
        }
    }
}
