

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class edgeChanging {
    static class Node {
        public Node() {
            neighbours = new ArrayList<>();
        }

        ArrayList<Integer> neighbours;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int s = sc.nextInt();

        Node[] graph = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new Node();
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (graph[a].neighbours.contains(b)) {
                continue;
            } else {
                graph[a].neighbours.add(b);
                graph[b].neighbours.add(a);
            }
        }

        addEdges(graph, k);
        boolean[] visited = new boolean[n + 1];
        bfs(graph, s, visited);
    }

    private static void bfs(Node[] graph, int s, boolean[] visited) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            graph[v].neighbours.sort(Integer::compareTo);
            for (int neighbour : graph[v].neighbours) {
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
            }
            if (v == s) System.out.print(s);
            else System.out.print(" " + v);
        }
    }

    private static void addEdges(Node[] graph, int k) {
        int length = graph.length - 1;
        for (int i = 1; i <= length; i++) {
            //遍历所有邻居结点
            int neighbourLength = graph[i].neighbours.size();
            for (int j = 0; j < neighbourLength; j++) {
                for (int f = j + 1; f < neighbourLength; f++) {
                    if (graph[i].neighbours.get(j) == k * graph[i].neighbours.get(f)
                            || graph[i].neighbours.get(f) == k * graph[i].neighbours.get(j)) {
                        if (graph[graph[i].neighbours.get(j)].neighbours.contains(graph[i].neighbours.get(f))) {
                            continue;
                        } else {
                            graph[graph[i].neighbours.get(j)].neighbours.add(graph[i].neighbours.get(f));
                            graph[graph[i].neighbours.get(f)].neighbours.add(graph[i].neighbours.get(j));
                        }
                    }
                }
            }
        }
    }
}



