package Lesson5;

public class Backpack {

    /**
     *
     * @param weight - массив значений веса предметов
     * @param val - массив значений ценности предметов
     * @param w - максимально допустимый вес вещей в рюкзаке
     * @param itemNum - элемент, который мы хотим положить на текущей итерации
     * @return максимально возможная ценность предметов, которые могут поместиться в рюкзаке
     */
    public static int recursive(int[] weight, int[] val, int w, int itemNum) {
        if (w == 0 || itemNum == weight.length) {
            return 0;
        }
        if (weight[itemNum] > w) {
            return recursive(weight, val, w,itemNum + 1);
        }
        // TODO вычислить ценность для случаев, когда мы берем и не берем в рюкзак предмет
        // TODO вернуть ценность для случая с наибольшей ценностью
        return -1;
    }

    public static void main(String[] args) {
        int[] weight = {2, 3, 3, 4};
        int[] val = {1, 2, 5, 9};
        int w = 7;

        System.out.println(recursive(weight, val, w, 0));
    }
}
