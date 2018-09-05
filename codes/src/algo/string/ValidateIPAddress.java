package algo.string;

public class ValidateIPAddress {
    public boolean validNumber(String str) {
        if (str.length() > 3 || str.length() < 1)
            return false;
        if (str.charAt(0) == '0' && str.length() > 1) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += (str.charAt(str.length() - 1 - i) - '0') * Math.pow(10, i);
        }
        return sum >= 0 && sum < 256;
    }

    public boolean validBits(String str) {
        if (str.length() > 4 || str.length() < 1)
            return false;
        if (str.length() == 1 && str.charAt(0) == '0') {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9')
                    || (str.charAt(i) >= 'a' && str.charAt(i) <= 'f')
                    || (str.charAt(i) >= 'A' && str.charAt(i) <= 'F'))) {
                return false;
            }
        }
        return true;
    }

    public String validIPAddress(String IP) {
        String[] subStrs = IP.split("\\.");
        if (subStrs.length == 4 && !IP.startsWith(".")
                && !IP.endsWith(".")) {
            for (String subStr :
                    subStrs) {
                if (!validNumber(subStr)) {
                    return "Neither";
                }
            }
            return "IPv4";
        }

        subStrs = IP.split(":");
        if (subStrs.length == 8 && !IP.startsWith(":")
                && !IP.endsWith(":")) {
            for (String subStr :
                    subStrs) {
                if (!validBits(subStr)) {
                    return "Neither";
                }
            }
            return "IPv6";
        }
        return "Neither";
    }
}
