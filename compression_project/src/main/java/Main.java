
import java.io.IOException;
import java.util.Scanner;
import tiralabra.compression_project.Gui;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);
        Gui gui = new Gui(reader);
        gui.start();

        /*
        LZCompression lzComp = new LZCompression();
        //String[] text = {"badadadabaab"};
        MyArrayList<String> compressedText = lzComp.compress3(text);
        */
    }

}
