
import java.util.*;

public class DivineIngenuity {
    static class coordinate {
        public int x, y;
        public int distance;
        public coordinate(int inputX, int inputY,int inputDistance) {
            x = inputX;
            y = inputY;
            distance = inputDistance;
        }
    }
    public static coordinate b = new coordinate(-1,-1,0);
    public static coordinate f = new coordinate(-1,-1,0);
    public static int gm,gn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();//行数
        gm = m;
        int n = sc.nextInt();//列数
        gn = n;
        char[][] domain = new char[m][n];
        for (int i = 0; i < m; i++) {
            String row = sc.next();
            for (int j = 0; j < n; j++) {
                domain[i][j] = row.charAt(j);
                if (domain[i][j] == 'i') {
                    b.x = i;
                    b.y = j;
                } else if (domain[i][j] == 'j') {
                    f.x = i;
                    f.y = j;
                }
            }
        }
        int result = findMinimal(domain, m, n);
        System.out.println(result);

    }
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    private static int findMinimal(char[][] domain, int m, int n) {
        boolean [][] visited = new boolean[m][n]; // to check if the node has been settled
        PriorityQueue<coordinate> heap = new PriorityQueue<>(Comparator.comparingInt(coordinate -> coordinate.distance));
        b.distance = 0;//initialize the begin position
        heap.add(b);
        while (!heap.isEmpty()) {
            coordinate t = heap.poll();
            if(visited[t.x][t.y]) continue; // check if t has been settled
            visited[t.x][t.y] = true;
            if(t.x == f.x && t.y == f.y){ // reach j (final position)
                return t.distance;
            }
            List<coordinate> neighbours = getNeighbours(domain,t); // get neighbours of t (along with the assignment of their distance)
            for(coordinate neighbour : neighbours){
                if(!visited[neighbour.x][neighbour.y]){
                    heap.add(neighbour);
                }
            }
        }
        return -1;
    }


    private static List<coordinate> getNeighbours(char[][] domain, coordinate t) {
        List<coordinate> neighbours = new ArrayList<>();
        for(int i = 0; i < 4 ; i++){
            int x = t.x + dx[i], y = t.y + dy[i];
            if(domain[t.x][t.y] == 'i'){
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance));
                }
                continue;
            }
            if(domain[t.x][t.y] == 'w' && dx[i] == -1 && dy[i] == 0){
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance));
                }
            }else if(domain[t.x][t.y] == 'a' && dx[i] == 0 && dy[i] == -1){
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance));
                }
            }else if(domain[t.x][t.y] == 's' && dx[i] == 1 && dy[i] == 0){
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance));
                }
            }else if(domain[t.x][t.y] == 'd' && dx[i] == 0 && dy[i] == 1){
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance));
                }
            }else {
                if(safe(x,y)){
                    neighbours.add(new coordinate(x,y,t.distance + 1));
                }
            }
        }
        return neighbours;
    }

    private static boolean safe(int x, int y) {
        if(x < 0  ||  x > gm-1 || y < 0 || y > gn - 1){
            return false;
        }
        return true;
    }
}
