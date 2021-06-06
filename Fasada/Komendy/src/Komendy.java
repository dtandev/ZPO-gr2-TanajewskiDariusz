import java.util.Scanner;

class File {

    void Open() {
        System.out.println("Otwieram plik...");
    }
}

class FontProperties {

    void Bold() {
        System.out.println("Czcionka pogrubiona");
    }

    void  Italic() {
        System.out.println("Czcionka pochyła");
    }
}

class FasadaOperator {
    FontProperties fontProperties;
    File file;
    Scanner sc= new Scanner(System.in); //System.in is a standard input stream
    String str;

    FasadaOperator(FontProperties fontProperties, File file) {
        this.file = file;
        this.fontProperties = fontProperties;
    }

    void runTerminal() {
        System.out.print("Enter a command: ");
        this.str= sc.nextLine();              //reads string
    }

    void Terminal() {
        runTerminal();
        if (str.equals("setBold")) {
            fontProperties.Bold();
        } else if (str.equals("setItalic")) {
            fontProperties.Italic();
        } else if (str.equals("openFile")) {
            file.Open();
        } else {
            System.out.println("Nieznana komenda");
        }
    }
}


public class Komendy {
    public static void main(String[] args) {
        File file = new File();
        FontProperties fontProperties = new FontProperties();
        FasadaOperator fasadaOperator = new FasadaOperator(fontProperties,file);
        fasadaOperator.Terminal();
    }
}
