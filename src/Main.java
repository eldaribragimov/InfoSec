import java.io.IOException;
import java.util.ArrayList;

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
        main.setFile("inf_seti.doc");
        main.setNewfile("inf_seti_encrypted.doc");
        KeyProvider keyProvider = new KeyProvider();
        ArrayList<String> arrayListkey = keyProvider.keyprovide(main.getKey());
        FileProvider fileProvider = new FileProvider();
        ArrayList arrayList = fileProvider.fileprovide(main.getFile());
        GostChangeMethod gostChangeMethod = new GostChangeMethod();
        gostChangeMethod.process(arrayList,arrayListkey,main);
    }

}
