
import java.util.*;
public class QueueDisorder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextLong();
        }
        long count = countByMergeSort(arr, 0, length - 1);
        System.out.println(count);
    }
    public static long countByMergeSort(long arr[],int left,int right){
        int mid = (right + left) / 2;
        if(right <= left){
            return 0;
        }
        long count = 0;
        count += countByMergeSort(arr,left,mid);
        count += countByMergeSort(arr,mid + 1,right);
        count += merge(arr,left,right);
        return count;
    }
    public static long merge(long arr[],int left,int right) {
        int mid = (right + left) / 2;
        int length = right - left + 1;
        long [] temp = new long[length];
        int i = left;//左数组指针
        int k = 0;//空数组指针
        int j = mid + 1;//右数组指针
        long count = 0;
        while(i <= mid && j <= right && k < length){
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else if(arr[j] < arr[i]){
                temp[k++] = arr[j++];
                count += mid - i + 1; // 3 4 7 2 1
            }
        }
        if((i<=mid || j<=right) && k < length){
            //此时仍有一个数组没有放完
            while(i<=mid && k < length){
                temp[k++] = arr[i++];
            }
            while(j<=right && k < length){
                temp[k++] = arr[j++];
            }
        }
        for (int i1 = 0; i1 < length; i1++) {
            arr[left + i1] = temp[i1];
        }
        return count;
    }
}
