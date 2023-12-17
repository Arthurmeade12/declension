package me.arthurmeade12.decliner;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;
public class config {
    public static final String configfile = "declension.properties";
    public static final String url = "https://raw.githubusercontent.com/Arthurmeade12/declension/main/" + configfile;
    public static void evalprops() throws IOException {
        FileReader config = new FileReader(configfile);
        Properties p = new Properties();
        p.load(config);
        if (p.getProperty("config_version") != main.version) {
            msg.warn("The application is out of date. Please update.");
        }
        eval.print_vocatives = Boolean.parseBoolean(p.getProperty("print_vocatives"));
        eval.print_locatives = Boolean.parseBoolean(p.getProperty("print_locatives"));
        msg.debug = Boolean.parseBoolean(p.getProperty("debug"));
        eval.padding = Byte.parseByte(p.getProperty("padding"));
        eval.columns = Boolean.parseBoolean(p.getProperty("columns"));
        latinutils.case_sensitive = Boolean.parseBoolean(p.getProperty("case_sensitive"));
    }
    public static void createprops(){
        try {
            config.realcreateprops();
        }
        catch (MalformedURLException a) {
            a.printStackTrace();
            msg.die("Invalid URL.", 5);
        }
        catch (IOException b) {
            b.printStackTrace();
            msg.die("Could not contact the remote server at " + url + ".");
        }
    }
    private static void realcreateprops() throws MalformedURLException, IOException, FileNotFoundException {
        msg.warn("Downloading default config.");
        URL remote = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(remote.openStream());
        FileOutputStream output = new FileOutputStream(configfile);
        FileChannel channel = output.getChannel();
        channel.transferFrom(rbc, 0, Long.MAX_VALUE);
        msg.out("Download of default config complete. Please restart the application.");
        System.exit(0);
    }
}
