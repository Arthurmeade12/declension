package me.arthurmeade12.decliner;
public class fifth extends eval {
    public static final String[][] endings = {
        {"es", "es"},
        {"ei", "erum"},
        {"ei", "ebus"},
        {"em", "es"},
        {"e", "ebus"},
        {"es", "es"},
        {"ei", "ibus"}
    };
    public static final byte num = 5;
    fifth(String nom, String gen) {
        super(nom, gen, num);
        super.makedecl(endings);
    }
    @Override // even though there are no exceptions we still need to override to prevent the eval class from complaining
    public void exceptions(){}
}
