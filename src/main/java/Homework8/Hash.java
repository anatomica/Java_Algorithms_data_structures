package Homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hash {
    public static void main(String[] args) throws IOException {

        T data;
        int key, size, n, keysPerCell;
        System.out.print("Введите размер хеш таблицы: ");
        size = getInt();
        System.out.print("Введите количество элементов: ");
        n = getInt();
        keysPerCell = 10;
        boolean doIt = true;

        HashTable theHashTable = new HashTable(size);
        for(int j = 0; j < n; j++){
            key = (int)(Math.random() * keysPerCell * size);
            data = new T(key);
            theHashTable.add(data);
        }

        while(doIt){
            System.out.print("Введите: '1' - show, '2' - add, '3' - remove, '4' - contains, '5' - Выход : ");
            char choice = getChar();
            switch(choice){
                case '1':
                    theHashTable.display();
                    break;
                case '2':
                    System.out.print("Ведите значение для добавления: ");
                    key = getInt();
                    data = new T(key);
                    theHashTable.add(data);
                    break;
                case '3':
                    System.out.print("Введите значение, которое нужно удалить: ");
                    key = getInt();
                    theHashTable.remove(key);
                    break;
                case '4':
                    System.out.print("Введите значение, которое нужно найти: ");
                    key = getInt();
                    data = theHashTable.contains(key);
                    if (data != null){
                        System.out.println("Найдено: " + key);
                        break;
                    } else {
                        System.out.println("Нет такого значения: " + key);
                        break;
                    }
                case '5':
                    System.out.print("Выход!");
                    doIt = false;
                    break;
                default:
                    System.out.print("Некорректный ввод! \n");
            }
        }
    }

    private static String getString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    private static char getChar() throws IOException {
        return getString().charAt(0);
    }

    private static int getInt() throws IOException {
        return Integer.parseInt(getString());
    }

    private static class HashTable {
        private T[] hashArr;
        private int arrSize;
        private T nonT;

        private HashTable(int size){
            this.arrSize = size;
            hashArr = new T[arrSize];
            nonT = new T(-1);
        }

        private void display(){
            for(int i = 0; i < arrSize; i++){
                if(hashArr[i] != null){
                    System.out.println(hashArr[i].getKey());
                } else {
                    System.out.println("<--->");
                }
            }
        }

        private int hashFunc(int key){
            return key % arrSize;
        }

        private int hashFuncDouble(int key){
            return 5 - key % 5;
        }

        private void add(T item){
            int key = item.getKey();
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
                hashVal += stepSize;
                hashVal %= arrSize;
            }

            hashArr[hashVal] = item;
        }

        private void remove(int key){
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key){
                    T temp = hashArr[hashVal];
                    hashArr[hashVal] = nonT;
                    System.out.println("Успешно удалено: " + key);
                    return;
                }
                hashVal += stepSize;
                hashVal %= arrSize;
            }
            System.out.println("Нет такого элемента: " + key);
        }

        private T contains(int key){
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key){
                    return hashArr[hashVal];
                }
                hashVal += stepSize;
                hashVal %= arrSize;
            }
            return null;
        }

        private int getPrime(int min){
            for(int i = min + 1; true; i++)
                if(isPrime(i))
                    return i;
        }

        private boolean isPrime(int n){
            for(int j = 2; (j * j <= n); j++)
                if( n % j == 0)
                    return false;
            return true;
        }
    }

    private static class T {
        private int data;

        private T(int data){
            this.data = data;
        }

        private int getKey(){
            return this.data;
        }
    }
}
