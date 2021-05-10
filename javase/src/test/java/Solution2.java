import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author water33
 */
public class Solution2 {

    /**
     * 10
    A * 10 7  6  3  1
    B * 9  8  5  4  2
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,10,10,10};
        int min = new Solution2().min(arr);
        System.out.println(min);
    }

    public int min(int[] arr){
        List<Integer> list = new ArrayList();

        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        list.sort((i1,i2)->{
            return i2-i1;
        });
        int num1,num2;
        num1 = list.get(0)+list.get(3)+list.get(4)+list.get(7)+list.get(8);
        num2 = list.get(1)+list.get(2)+list.get(5)+list.get(6)+list.get(9);
        return (num1-num2)<0?num2-num1:num1-num2;
    }

}
