package me.arthurmeade12.decliner;
public class second extends eval {
    public static final String[][] endings = {
        {"us", "i"},
        {"i", "orum"},
        {"o", "is"},
        {"um", "os"},
        {"o", "is"},
        {"e", "i"},// vocative exceptions in exceptions()
        {"i", "is"}
    };
    public static final byte num = 2;
    second(String nom, String gen) {
        super(nom, gen, num);
        super.makedecl(endings);
    }
}
