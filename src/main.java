//
// Decliner by Arthurmeade12 (https://github.com/Arthurmeade12/declension)
// Compiled and tested with Temurin 17
//
package me.arthurmeade12.decliner;
import java.util.Scanner;
import java.io.IOException;
public class main {
    public static final byte version = 2;
    public static char gender;
    public static String nominative;
    public static String genitive;
        public static void exec(String nom, String gen){
        byte decl = latinutils.getdecl(nominative, genitive);
        msg.debug("Declension : " + decl);
        switch (decl) {
        case 1:
            first execfirst = new first(nominative, genitive);
            msg.debug("Gender : " + execfirst.gender);
            execfirst.complete();
            break;
        case 2:
            second execsecond = new second(nominative, genitive);
            msg.debug("Gender : " + execsecond.gender);
            execsecond.complete();
            break;
        case 3:
            third execthird = new third(nominative, genitive);
            msg.debug("Gender : " + execthird.gender);
            execthird.complete();
            break;
        case 4:
            fourth execfourth = new fourth(nominative, genitive);
            msg.debug("Gender : " + execfourth.gender);
            execfourth.complete();
            break;
        case 5:
            fifth execfifth = new fifth(nominative, genitive);
            msg.debug("Gender : " + execfifth.gender);
            execfifth.complete();
            break;
        default:
            msg.warn("Not done yet");
            break;
        }
    }
    public static void main(String[] args) {
        config.evalprops();
        Scanner input = new Scanner(System.in);
        switch (args.length) {
        case 3:
            gender = args[2].charAt(0);
        case 2:
            nominative = args[0];
            genitive = args[1];
            main.exec(nominative, genitive);
            break;
        case 0:
        case 1:
            eval.extraline = true;
            msg.out("Type 'quit' at nominative prompt to quit.");
            loop:
            while (true) {
                msg.out("Nominative ?");
                nominative = input.next();
                if (nominative.equals("quit")){
                    msg.out("Goodbye!");
                    break;
                }
                msg.out("Genitive ?");
                genitive = input.next();
                main.exec(nominative, genitive);
            }
            break;
        default:
            nominative = args[0];
            genitive = args[1];
            gender = args[2].charAt(0);
            for (byte i = 3; i < args.length; i++) {
                msg.warn("Argument " + args[i] + " ignored.");
            }
            main.exec(nominative, genitive);
        }
        input.close();
        msg.debug("Input closed.");
    }
}
