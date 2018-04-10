import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;


public class GostGammaMethod {

    public long provideparcel(String key){

        // генерируем синхрпосылку

        return Long.parseUnsignedLong(DigestUtils.sha256Hex(key).substring(0,16),16);

    }

    public long handlier(long value ){
        int n1 = (int) ( (value & (-4294967296L) ) >>> 32);   // выделение из блока 64 бит первого блока 32 бит
        int n2 = (int) ( value & 4294967295L );            // выделение из блока 64 бит второго блока 32 бит
        n2 = (n2 + 16843009 );
        if (n1 <= 4278124283L)
            n1 = n1 + 16843012;
        else
            n1 = n1 + 16843012 + 1;
        return ( Integer.toUnsignedLong(n1) << 32 ) | (( (long) n2 ) & 4294967295L ) ;
    }

    public ArrayList<Long> process(byte[] arrayListfromfile, int[] arrayListkey, long sync,Main main) {

        ArrayList<Long> arrayList = new ArrayList<>();
        long b = 0;
        int v =arrayListfromfile.length%8;
        main.setK(v);  // для того чтобы знать сколько недостающих байтов от 8 потом записывать

        // собираем блоки 64 бит из байтов

        for (int i = 0 ; i < arrayListfromfile.length - v ; i++){
            Long s = Byte.toUnsignedLong(arrayListfromfile[i]);
            b = b | s;
            if ( ( i + 1 ) % 8 == 0){
                arrayList.add(b);
                b = 0;
            }
            else
                b = b << 8;
        }

        b = 0;
        int k = 0;

        for (int i = arrayListfromfile.length - v; i < arrayListfromfile.length ; i++){
            Long s = Byte.toUnsignedLong(arrayListfromfile[i]);
            b = b | (s << ( 56 - k ) );
            k+=8;
        }

        if (v!=0)
            arrayList.add(b);

        for (int i = 0 ; i < arrayList.size() ; i++){
            arrayList.set(i,arrayList.get(i) ^ function(handlier(sync),arrayListkey));
        }
        return arrayList;
    }

    public Long function(long value , int[] key){
        return new GostChangeMethod().function(value,key);
    }

}
