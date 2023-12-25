
import java.util.LinkedList;
import java.util.Scanner;
public class BuriedTreasure {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i = 0; i < n;i++){
            int length = sc.nextInt();
            int [] depth = new int[length];
            for(int j = 0; j < length; j++){
                depth[j] = sc.nextInt();
            }
            System.out.println(MaxArea(length, depth));
        }
    }

    private static long MaxArea(int length, int[] depth) {

        LinkedList<Integer> stack = new LinkedList<>();
        long maxArea = 0;
        int width,height;
        for(int i = 0; i < length; i++){
            if(stack.isEmpty() || depth[stack.peek()] < depth[i]){
                stack.push(i);
            }else{
             //如果i索引小于栈顶元素，说明i元素所在列将会作为后序面积的左边界
             //此时将栈顶元素作为右边界处理前面的面积
                while(!stack.isEmpty() && depth[stack.peek()] > depth[i]){
                    //依次弹出栈中元素作为左边界
                    //弹出元素依次递减，所以高度为每次弹出元素所在列深度
                    int pop = stack.pop();
                    height = depth[pop];
                    //宽度为i元素到每次弹出元素所在列的距离
                    if(stack.isEmpty()){
                        //如果栈顶没有元素，则宽度为0到i
                        width = i;
                    }else{
                        width = i - stack.peek() - 1;
                    }
                    maxArea = Math.max(maxArea,(long)height * width);
                }
                //i元素所在列将会作为后序面积的左边界
                stack.push(i);
            }
        }
        //栈中剩下的元素
        while(!stack.isEmpty()){
            height = depth[stack.pop()];
            if(stack.isEmpty()){
                width = length;
            }else {
                width = length - stack.peek() - 1;
            }
            maxArea = Math.max(maxArea,(long)height * width);
        }

        return maxArea;
    }
}
