package Homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hash {
    public static void main(String[] args) throws IOException {
        Item aDataItem;
        int aKey, size, n, keysPerCell;
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        // Создание таблицы
        HashTable theHashTable = new HashTable(size);
        for(int j = 0; j < n; j++){
            aKey = (int)(Math.random() * keysPerCell * size);
            aDataItem = new Item(aKey);
            theHashTable.insert(aDataItem);
        }

        while(true){
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch(choice){
                case 's':
                    theHashTable.display();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Item(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null){
                        System.out.println("Found " + aKey);
                    } else {
                        System.out.println("Could not find " + aKey);
                        break;
                    }
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    private static class HashTable {
        private Item[] hashArr;
        private int arrSize;
        private Item nonItem;

        public HashTable(int size){
            this.arrSize = size;
            hashArr = new Item[arrSize];
            nonItem = new Item(-1);
        }

        public void display(){
            for(int i = 0; i < arrSize; i++){
                if(hashArr[i] != null){
                    System.out.println(hashArr[i].getKey());
                } else {
                    System.out.println("***");
                }
            }
        }

        public int hashFunc(int key){
            return key % arrSize;
        }

        // двойное хеширование
        public int hashFuncDouble(int key){
            return 5 - key % 5;
        }


        public void insert(Item item){
            int key = item.getKey();
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
//            ++hashVal;   - одинарное хеширование
                // двойное хеширование
                hashVal += stepSize;
                hashVal %= arrSize;
            }

            hashArr[hashVal] = item;
        }

        public Item delete(int key){
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key){
                    Item temp = hashArr[hashVal];
                    hashArr[hashVal] = nonItem;
                    return temp;
                }
//            ++hashVal;
                hashVal += stepSize;
                hashVal %= arrSize;
            }
            return null;
        }

        public Item find(int key){
            int hashVal = hashFunc(key);
            int stepSize = hashFuncDouble(key);
            while (hashArr[hashVal] != null) {
                if (hashArr[hashVal].getKey() == key){
                    return hashArr[hashVal];
                }
//            ++hashVal;
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

    private static class Item {
        private int data;

        public Item(int data){
            this.data = data;
        }

        public int getKey(){
            return this.data;
        }
    }
}
