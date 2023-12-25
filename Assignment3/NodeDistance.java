

import java.util.Scanner;


import java.util.ArrayList;
public class NodeDistance {

    static class TreeNode {
        int color;
        int weight;//与其父结点边的权重
        ArrayList<TreeNode> children;

        public TreeNode(int color, int weight) {
            this.color = color;
            this.weight = weight;
            this.children = new ArrayList<>();
        }

    }
        static long totalDistance = 0;
        static int blackCount = 0;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int n = scanner.nextInt();
            int[] colors = new int[n];
            for (int i = 0; i < n; i++) {
                colors[i] = scanner.nextInt();
                if(colors[i] == 1){
                    blackCount ++;//计算黑色结点
                }
            }
            // 创建树结构
            TreeNode[] nodes = new TreeNode[n + 1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new TreeNode(colors[i - 1], 0);
            }
            for (int i = 2; i <= n; i++) {//n-1行   1 1   第一个为父结点，第二个为新建结点和父结点的权重
                //从第一行开始，第二个结点被创建  i为当前结点的编号
                int fatherNodeIndex = scanner.nextInt();
                nodes[i].weight = scanner.nextInt();
                nodes[fatherNodeIndex].children.add(nodes[i]);
            }
            TreeNode root = nodes[1];
            // 调用函数计算距离
            dfs(root);
            // 输出结果

            System.out.println(totalDistance);
        }

    public static int dfs(TreeNode node) {
        int subtreeBlackSize = node.color;// 如果是黑色结点自己也算上
        for(TreeNode child : node.children){
            subtreeBlackSize += dfs(child);
        }
        totalDistance += (long)node.weight * (blackCount - subtreeBlackSize) * subtreeBlackSize;
        return subtreeBlackSize;
    }
}
