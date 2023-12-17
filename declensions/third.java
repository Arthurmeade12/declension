package me.arthurmeade12.decliner;
public class third extends eval {
    public static final String[][] endings = {
        {"-", "es"}, // nominative // set sing. to - since nom. sing has no rules
        {"is", "um"}, // genitive
        {"i", "ibus"}, // dative
        {"em", "es"}, // accusative
        {"e", "ibus"}, // ablative
        {"-", "es"}, // vocative // will set to nom. in exceptions()
        {"is", "ibus"} // locative
    };
    public static final byte num = 3;
    third(String nom, String gen) {
        super(nom, gen, num);
        super.makedecl(endings);
    }
    private static boolean isConsonant(char c) {
        return "AEIOUaeiou".indexOf(c) == -1;
    } // used for i-stem tests. Credit to https://stackoverflow.com/a/19161184
    @Override
    public void exceptions(){
        declension[5][0] = nom;
        // Partial i-stem tests
        if (((nom.endsWith("es") || nom.endsWith("is")) && (nom.length() == declension[1][0].length())) || ((nom.endsWith("s") || nom.endsWith("x")) && (isConsonant(base.charAt(base.length() - 1)) && isConsonant(base.charAt(base.length() - 2))))) {
            msg.debug("I-stem detected.");
            declension[1][1] = base + "ium";
        }
        // Full i-stem tests
        if (nom.endsWith("mare")){ // TODO change condition to neuter i-stem
            declension[4][0] = base + "i";
            declension[0][1] = base + "ia";
            declension[1][1] = base + "ium";
            declension[3][1] = base + "ia";
        }
    }
}
