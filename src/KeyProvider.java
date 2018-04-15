
public class KeyProvider {

    public int[] keyprovideencode(byte[] arraykey){

        // режим кодирования
        // генерируем массив элементы которого по 32 бит

        int[] array = new int[32];
        int k = -1;

        for (int i = 0 ; i < 8 ; i++) {
            int b = 1;
            for (int j = k+1 ; ((j < arraykey.length) && (b==1) ) ; j++){
                array[i] = (array[i]+arraykey[j]);
                if ((j+1)%4 !=0)
                    array[i] = array[i] << 8;
                else
                    b=0;
                k = j;
            }
        }

        k = 0;

        for (int i = 8 ; i < array.length ; i++){
            if (i<24) {
                array[i] = array[k];
            }
            else {
                array[i] = array[23%k];
            }
            k++;
        }
        return array;
    }


    public int[] keyprovidedecode(byte[] arraykey){

        // режим декодирования

        // генерируем массив элементы которого по 32 бит


        int[] array = new int[32];

        int k = -1;

        for (int i = 0 ; i < 8 ; i++) {
            int b = 1;
            for (int j = k+1 ; ((j < arraykey.length) && (b==1) ) ; j++){
                array[i] = (array[i]+arraykey[j]);
                if ((j+1)%4 !=0)
                    array[i] = array[i] << 8;
                else
                    b=0;
                k = j;
            }
        }

        k = 7;

        for (int i = 8 ; i < array.length ; i++){
            array[i] = array[k];
            k--;
            if (k < 0)
                k = 7;
        }
        return array;
    }

}
