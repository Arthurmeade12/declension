package me.arthurmeade12.decliner;
public class first extends eval {
    public static final String[][] endings = {
        {"a", "ae"},
        {"ae", "arum"},
        {"ae", "is"}, // see note for ablative
        {"am", "as"},
        {"ā", "is"}, // check for abus in exceptions
        {"a", "ae"},
        {"ae", "is"}
    };
    public static final byte num = 1;
    first(String nom, String gen) {
        super(nom, gen, num);
        super.makedecl(endings);
    }
    @Override
    public void exceptions() {
        switch (nom) {
        case "dea":
        case "filia":
            declension[2][1] = base + "abus";
            declension[4][1] = base + "abus";
            break;
        }
    }
}
