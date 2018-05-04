import java.util.ArrayList;

public class PFBMode {

    public byte[] VectorGenerator(){
        String s = "12345679"; // блок вектора инициализации 64 бит
        return s.getBytes();
    }

    public byte[] cipher(byte[] arrayListfromfile, byte[] arraykey,byte[] vector) {

        int[] arrayListkey = new KeyProvider().keyprovideencode(arraykey); // получаем массив из ключа

        ArrayList<Long> arrayList = new ArrayList<>();
        long b = 0;

        // собираем блоки 64 бит из байтов и кладем их в массив

        for (int i = 0 ; i < arrayListfromfile.length ; i++){
            Long s = Byte.toUnsignedLong(arrayListfromfile[i]);
            b = b | s;
            if ( ( i + 1 ) % 8 == 0){
                arrayList.add(b);
                b = 0;
            }
            else
                b = b << 8;
        }

        // собираем вектор инициализации по подобию как мы собирали блоки

        b = 0;

        long Vector_Long_value = 0;

        for (int i = 0 ; i < vector.length ; i++){
            Long s = Byte.toUnsignedLong(vector[i]);
            b = b | s;
            if ( ( i + 1 ) % 8 == 0){
                Vector_Long_value = b;
            }
            else
                b = b << 8;
        }

        //arrayList.set(0,new GostChangeMethod().cryptfunction(Vector_Long_value,arrayListkey) ^ arrayList.get(0));  // определяем C(1)

        // выполняем для остальных C(i>1)

        for (int i = 1 ; i < arrayList.size() ; i++){
             //arrayList.set(i,new GostChangeMethod().cryptfunction(arrayList.get(i-1),arrayListkey) ^ arrayList.get(i));  // выполняем операции над блоками 64 бит
        }
        return new DataProvider().handlier(arrayList);
    }
}

