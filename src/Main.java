import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    String key;
    String file;
    String newfile;
    int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

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
        System.out.println("\nChoose Mode :\n1) Simple Change Method ( for 64 bit only )\n2) Gamma mode\n");
        GostGammaMethod gostGammaMethod;
        KeyProvider keyProvider;
        GostChangeMethod gostChangeMethod;
        FileProvider fileProvider;
        int b ;

        Scanner scanner = new Scanner(System.in);
        b = scanner.nextInt();

        if ( b == 1 ){
            keyProvider = new KeyProvider();
            gostChangeMethod = new GostChangeMethod();
            fileProvider = new FileProvider();
            System.out.println("\nChoose :\n1) Encode\n2) Decode\n");
            scanner = new Scanner(System.in);
            int c = scanner.nextInt();
            System.out.println("\nWrite key\n");
            scanner = new Scanner(System.in);
            main.setKey(scanner.nextLine());
            System.out.println("\nWrite input file\n");
            scanner = new Scanner(System.in);
            main.setFile(scanner.nextLine());
            System.out.println("\nWrite output file\n");
            scanner = new Scanner(System.in);
            main.setNewfile(scanner.nextLine());
            if ( c == 1 ){
                int [] arraykey = keyProvider.keyprovideencode(main.getKey());
                byte [] arrayfromfile = fileProvider.readfile(main.getFile());
                ArrayList<Long> arrayList = gostChangeMethod.process(arrayfromfile,arraykey);
                fileProvider.writefile(main.getNewfile(),arrayList,main);
            }
            if (c == 2){
                int [] arraykey = keyProvider.keyprovidedecode(main.getKey());
                byte [] arrayfromfile = fileProvider.readfile(main.getFile());
                ArrayList<Long> arrayList = gostChangeMethod.process(arrayfromfile,arraykey);
                fileProvider.writefile(main.getNewfile(),arrayList,main);
            }
        }

        if (b == 2) {
            keyProvider = new KeyProvider();
            gostGammaMethod = new GostGammaMethod();
            fileProvider = new FileProvider();
            System.out.println("\nChoose :\n1) Encode\n2) Decode\n");
            scanner = new Scanner(System.in);
            int c = scanner.nextInt();
            System.out.println("\nWrite key");
            scanner = new Scanner(System.in);
            main.setKey(scanner.nextLine());
            System.out.println("\nWrite sync");
            scanner = new Scanner(System.in);
            long sync = gostGammaMethod.provideparcel(scanner.nextLine());
            System.out.println("\nWrite input file\n");
            scanner = new Scanner(System.in);
            main.setFile(scanner.nextLine());
            System.out.println("\nWrite output file\n");
            scanner = new Scanner(System.in);
            main.setNewfile(scanner.nextLine());
            int [] arraykey = keyProvider.keyprovideencode(main.getKey());
            byte [] arrayfromfile = fileProvider.readfile(main.getFile());
            ArrayList<Long> arrayList = gostGammaMethod.process(arrayfromfile,arraykey,sync,main);
            fileProvider.writefile(main.getNewfile(),arrayList,main);
        }

    }
}

