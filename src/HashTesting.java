import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class HashTesting {

    @Test

    public void GostHashTest() {
        String textdata = "This is message, length=32 bytes";
        System.out.print("73 3D 2C 20 65 68 65 73 74 74 67 69 79 67 61 20 62 6E 73 73 20 65 73 69 32 6C 65 68 33 20 6D 54"+"\n");
        System.out.print("11 0C 73 3D 0D 16 65 68 13 0E 74 74 06 41 79 67 1D 00 62 6E 16 1A 20 65 09 0D 32 6C 4D 39 33 20"+"\n");
        System.out.print("80 B1 11 F3 73 0D F2 16 85 00 13 F1 C7 E1 F9 41 62 0C 1D FF 3A BA E9 1A 3F A1 09 F2 F5 13 B2 39"+"\n");
        System.out.print("A0 E2 80 4E FF 1B 73 F2 EC E2 7A 00 E7 B8 C7 E1 EE 1D 62 0C AC 0C C5 BA A8 04 C0 5E A1 8B 0A EC"+"\n");
        System.out.println("\n");
        byte[] data = textdata.getBytes();
        GoshHash goshHash = new GoshHash();
        goshHash.process(data);
    }


}
