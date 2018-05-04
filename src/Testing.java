import org.junit.Assert;
import org.junit.Test;

public class Testing {
/*
    @Test

    // тестирование метода простой замены валидным ключом
    // ожидаемое значение переменной b = true

    public void SimpleChangeModeTestWithValidKey()  {
        byte[] arraydata = new DataProvider().read();  // считываем блок
        byte[] array = new GostChangeMethod().encodeprocess(arraydata,new Key().keygenerate()); //  зашифровываем блок
        array = new GostChangeMethod().decodeprocess(array,new Key().keygenerate()); // расшифровываем зашифрованный блок тем же ключом

        // проверяем что первоначальный блок совпадает с расшифрованным

        boolean c = true;
        for (int i = 0 ; i < array.length ; i++){
            if (array[i]!=arraydata[i])
                c = false;
        }

        Assert.assertTrue(c); // тест пройден если b = true

    }

    @Test

    // тестирование метода простой замены невалидным ключом
    // ожидаемое значение переменной b = false

    public void SimpleChangeModeTestWithNOTValidKey()  {
        byte[] arraydata = new DataProvider().read();  // считываем блок
        byte[] key = new Key().keygenerate();
        key[0] +=2; // чуть чуть меняем ключ
        byte[] array = new GostChangeMethod().encodeprocess(arraydata,key); //  зашифровываем блок
        array = new GostChangeMethod().decodeprocess(array,new Key().keygenerate()); // расшифровываем зашифрованный блок тем же ключом

        // проверяем что первоначальный блок совпадает с расшифрованным

        boolean c = true;
        for (int i = 0 ; i < array.length ; i++){
            if (array[i]!=arraydata[i])
                c = false;
        }

        Assert.assertFalse(c);  // тест пройден если b = false

    }

    @Test

    // тестирование режима PFB валидным ключом
    // ожидаемое значение переменной b = true

    public void PFBModeTestWithValidKey(){
        byte[] arraydata = new DataProvider().read();  // считываем блок
        byte[] array = new PFBMode().cipher(arraydata,new Key().keygenerate(),new PFBMode().VectorGenerator()); //  зашифровываем блок
        array = new PFBMode().cipher(array,new Key().keygenerate(),new PFBMode().VectorGenerator());     // расшифровываем зашифрованный блок тем же ключом

        // проверяем что первоначальный блок совпадает с расшифрованным

        boolean c = true;
        for (int i = 0 ; i < array.length ; i++){
            if (array[i]!=arraydata[i])
                c = false;
        }

        Assert.assertTrue(c);  // тест пройден если b = true
    }

    @Test

    // тестирование режима PFB невалидным ключом
    // ожидаемое значение переменной b = false

    public void PFBModeTestWithNOTValidKey(){
        byte[] arraydata = new DataProvider().read();  // считываем блок
        byte[] key = new Key().keygenerate();
        key[0]+=2;  // чуть чуть меняем ключ
        byte[] array = new PFBMode().cipher(arraydata,key,new PFBMode().VectorGenerator()); //  зашифровываем блок
        array = new PFBMode().cipher(array,new Key().keygenerate(),new PFBMode().VectorGenerator());     // расшифровываем зашифрованный блок тем же ключом

        // проверяем что первоначальный блок совпадает с расшифрованным

        boolean c = true;
        for (int i = 0 ; i < array.length ; i++){
            if (array[i]!=arraydata[i])
                c = false;
        }

        Assert.assertFalse(c);  // тест пройден если b = false
    }*/

}

