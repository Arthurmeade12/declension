package me.arthurmeade12.decliner;
import java.util.Scanner;
public class latinutils {
    public static boolean case_sensitive = false;
    public static byte getdecl(String nominative, String genitive) {
        String ending;
        if (genitive.length() < 3) {
            ending = genitive;
        } else {
            ending = genitive.substring(genitive.length()-2);
        }
        if (case_sensitive = false){
            ending = ending.toLowerCase();
        }
        byte ans = 0;
        switch (ending) {
        case "ae":
            ans = 1;
            break;
        case "is":
            ans = 3;
            break;
        case "us":
            ans = 4;
            break;
        case "ei":
            ans = 5;
            break;
        default:
            if (genitive.endsWith("i")) {
                ans = 2;
            } else {
                ans = 0;
            }
            break;
        }
        if (latinutils.sanenominative(nominative, ans)) {
            return ans;
        } else {
            msg.warn(nominative + " does not fall into any declension. ");
            return 0;
        }
    }
    public static boolean sanenominative(String nominative, byte decl) {
        boolean value = false;
        switch (decl) {
        case 1:
            if (nominative.endsWith("a")) {
                value = true;
            }
            break;
        case 2:
            switch (nominative.substring(nominative.length() - 2)) {
            case "us":
            case "um":
            case "er":
            case "ir":
                value = true;
            }
            break;
        case 3:
            value =  true;
        case 4:
            if (nominative.endsWith("u") || nominative.endsWith("us")) {
                value = true;
            }
            break;
        case 5:
            if (nominative.endsWith("es")) {
                value = true;
            }
            break;
        default:
            value = false;
        }
        msg.debug("Sane nominative: " + value);
        return value;
    }
    public static String getbase(String nominative, String genitive, byte decl) {
        if (decl == 0) { // fail hard immediately to avoid repeating work
            return "";
        }
        if (genitive.length() > 3) { // prefer getting base from genitive; it's proper
            msg.debug("Detected full genitive.");
            switch (decl) {
            case 1:
            case 3:
            case 4:
            case 5:
                return genitive.substring(0, genitive.length() - 2);
            case 2:
                return genitive.substring(0, genitive.length() -    1);
            default:
                return "";
            }
        } else {
            return latinutils.buildbase(nominative, decl);
        }
    }
    public static String buildbase(String nominative, byte decl) { // go from nominative if we have to
        msg.debug("Building a genitive (only ending was given)");
        String minusone = nominative.substring(0, nominative.length() - 1);
        String minustwo = nominative.substring(0, nominative.length() - 2); // cache these since we use them so often
        switch (decl) {
        case 1:
            return minusone;
        case 2:
            switch (nominative.substring(nominative.length() - 2)) {
            case "us":
            case "um":
                return minustwo;
            case "ir":
                return nominative;
            case "er":
                msg.out("Is the genitive of " + nominative + " " + nominative + "i or " + minustwo + "ri ? ");
                msg.out ("Answer 1 for the first, 2 for the second.");
                Scanner gendersecond = new Scanner(System.in);
                String ans; // make string to prevent user from crashing the app
                for (byte i = 0; i < 3; i++) {
                    ans = gendersecond.next();
                    switch (ans) {
                    case "1":
                        return nominative;
                    case "2":
                        return minustwo + "r";
                    default:
                        msg.warn("Answer was not 1 or 2.");
                    }
                }
                break;
            }
            msg.die("Three incorrect attempts. ", 2);
            return "";
        case 3:
            //TODO Attempt to resolve genitive automatically
            msg.out("You must enter the full genitive for third declension.");
            msg.out("Please enter the full genitive : ");
            Scanner genitivethird = new Scanner(System.in);
            String ans;
            for (byte i = 0; i < 3; i++) {
                ans = genitivethird.next();
                if (ans.endsWith("is") && ans.length() > 2) {
                    return ans.substring(0, ans.length() -2);
                } else {
                    msg.warn("The entered answer does not conform to the third declension (-is) and/or is not long enough.");
                }
            }
            msg.die("Three incorrect attempts. ", 2);
            return "";
        case 4:
            if (nominative.endsWith("u")) {
                return minusone;
            } else { // endsWith("us")
                return minustwo;
            }
        case 5:
            return minustwo;
        default:
            return "";
        }
    }
    private static boolean nomgenpair(String nominative, String nomending, String genitive, String genending) { // exclusively used in determining gender of thirds
        if (nominative.endsWith(nomending) && genitive.endsWith(genending)) {
            return true;
        } else {
            return false;
        }
    }
    public static char getgender(String nominative, String genitive, byte decl) {
        switch (decl) {
        case 1:
            switch (nominative) {
            case "poeta":
            case "agricola":
            case "incola":
            case "nauta":
                return 'm';
            default:
                return 'f';
            }
            // no neuter 1sts
        case 2:
            if (nominative.endsWith("um")) {
                return 'n';
            } else {
                switch (nominative) {
                case "alvus":
                case "carbasus":
                case "colus":
                case "humus":
                case "vannus":
                case "arctus":
                case "methodus":
                    // feminine 2nds taken from https://dcc.dickinson.edu/grammar/latin/2nd-declension-stem-paradigm-and-gender
                    return 'f';
                // the same webpage has -us neuters BUT they're few & weird
                default:
                    return 'm';
                }
            }
        case 3:
            // here we go (!!)
            if (nominative.endsWith("mare") || nomgenpair(nominative, "men", genitive, "minis") || nomgenpair(nominative, "us", genitive, "oris")) {
                return 'n';
            } else if (nomgenpair(nominative, "tas", genitive, "tatis") || nomgenpair(nominative, "tus", genitive, "tudis")) {
                return 'f';
            } else {
                msg.out("What is the gender of " + nominative + " ?");
                msg.out("m for masculine, f for feminine, n for neuter : ");
                Scanner genderthird = new Scanner(System.in);
                char ans = ' ';
                for (byte i = 0; i < 3; i++) {
                    ans = genderthird.next().charAt(0);
                    switch (ans) {
                    case 'm':
                    case 'f':
                    case 'n':
                        return ans;
                    case 'M':
                    case 'F':
                    case 'N':
                        return Character.toLowerCase(ans);
                    default:
                        msg.warn("Answer was not of m, f, or n.");
                    }
                }
                msg.die("Three incorrect attempts. ", 2);
                return '0';
            }
        case 4:
            if (nominative.endsWith("u")) {
                return 'n';
            } else {
                switch (nominative) {
                case "acus":
                case "anus":
                case "colus":
                case "domus":
                case "manus":
                case "nurus":
                case "porticus":
                case "quinquatrus":
                case "socrus":
                case "tribus":
                    // This list of feminines taken from https://dcc.dickinson.edu/grammar/latin/4th-declension-stem-paradigm-and-gender
                    return 'f';
                default:
                    return 'm';
                }
            }
        case 5:
            if (nominative.endsWith("dies")) {
                return 'm';
            } else {
                return 'f';
            }
        default:
            return '0';
        }
    }
}
