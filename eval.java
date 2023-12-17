package me.arthurmeade12.decliner;
import java.util.Arrays;
public class eval {
    public static boolean print_vocatives;
    public static boolean print_locatives;
    public static boolean columns;
    public static byte padding = 1;
    public static final byte cases = 7;
    public static final byte elements = 3;
    public static byte from_base = 2;
    protected String[][] declension = new String[cases][elements];
    protected String base;
    protected char gender;
    protected byte decl;
    protected String nom;
    eval(String nominative, String gen, byte declnum){
        msg.debug("Nominative : " + nominative);
        nom = nominative;
        decl = declnum;
        base = latinutils.getbase(gen, decl);
        if (main.gender == null) {
            gender = latinutils.getgender(nom, gen, decl);
        } else {
            gender = main.gender;
        }
    }
    protected void exceptions(){}
    protected void makedecl(String[][] endings){
        for (byte a = 0; a < cases; a++) {
            for (byte b = 0; b < from_base; b++){
                declension[a][b] = base + endings[a][b];
            }
        }
        declension[0][0] = nom;
        if (gender == 'n'){
            msg.debug("Neuter detected");
            declension[3][0] = declension[0][0]; // nom = acc
            declension[0][1] = base + "a";
            declension[3][1] = base + "a";
        }
        this.exceptions();
        if (columns == false){
            for (byte g = 0; g < cases; g++) {
                declension[g][from_base] = " ".repeat(padding);
            }
        } else {
            this.spacing();
        }
    }
    private void spacing(){
        //// spacing
        // we are looping over cases again but this after exceptions are processed, so things are diffferent
        int[] spaces = new int[cases];
        for (byte d = 0; d < cases; d++){
            spaces[d] = declension[d][0].length();
        }
        msg.debug("Spaces: " + Arrays.toString(spaces));
        int length = Arrays.stream(spaces).max().getAsInt() + padding;
        msg.debug("Longest + " + padding + " (padding) : " + length);
        int[] spacing = new int[cases];
        for (byte e = 0; e < cases; e++) {
            spacing[e] = length - spaces[e];
        }
        msg.debug("Spacing: " + Arrays.toString(spacing));
        for (byte f = 0; f < cases; f++){
            declension[f][from_base] = " ".repeat(spacing[f]);
        }
    }
    public void complete(){
        System.out.println();
        msg.debug("complete() invoked.");
        System.out.println(declension[0][0] +  declension[0][2] +  declension[0][1]);
        System.out.println(declension[1][0] +  declension[1][2] +  declension[1][1]);
        System.out.println(declension[2][0] +  declension[2][2] +  declension[2][1]);
        System.out.println(declension[3][0] +  declension[3][2] +  declension[3][1]);
        System.out.println(declension[4][0] +  declension[4][2] +  declension[4][1]);
        if (print_vocatives) {
            System.out.println(declension[5][0] +  declension[5][2] +  declension[5][1]);
        }
        if (print_locatives) {
            System.out.println(declension[6][0] +  declension[6][2] +  declension[6][1]);
        }
    }
}
