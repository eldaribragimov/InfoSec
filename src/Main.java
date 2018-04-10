import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    String key;
    String file;
    String newfile;

    public String getNewfile() {
        return newfile;
    }

    public void setNewfile(String newfile) {
        this.newfile = newfile;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.setKey("1asdaas");
        System.out.println("Choose :\n1) Encode\n2) Decode\n");
        Scanner scanner = new Scanner(System.in);
        int b = scanner.nextInt();
        System.out.println("\nSelect input file\n");
        scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        main.setFile(s);
        System.out.println("\nSelect output file\n");
        scanner = new Scanner(System.in);
        s = scanner.nextLine();
        main.setNewfile(s);
        KeyProvider keyProvider = new KeyProvider();
        FileProvider fileProvider = new FileProvider();
        int[] arrayListkey;
        byte[] array;

        if (b==1) {
            arrayListkey = keyProvider.keyprovideencode(main.getKey());
            array = fileProvider.readfileencode(main.getFile());
        }

        else {
            arrayListkey = keyProvider.keyprovidedecode(main.getKey());
            array = fileProvider.readfiledecode(main.getFile());
        }
            GostChangeMethod gostChangeMethod = new GostChangeMethod();
            ArrayList<Long> arrayList = gostChangeMethod.process(array, arrayListkey);
            fileProvider.writefile(main.getNewfile(), arrayList);
        System.out.println("\nSuccess\n");
    }

}
