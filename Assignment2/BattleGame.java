
import java.util.*;
public class BattleGame {
    public static String up = "U";
    public static final int UP = 1;
    public static final int DOWN = -1;
    static class Player{
        int addKey;
        int floor;
        int hp;
        int direction; // UP/DOWN
        public Player(int addKey,int floor, int hp, int direction) {
            this.addKey = addKey;//添加顺序
            this.floor = floor;
            this.hp = hp;
            this.direction = direction;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();// "\n"
        List<Player> players = new ArrayList<>();
        List<Player> survivors = new ArrayList<>();

        for(int i = 0; i < n; i++){
            players.add(new Player(i,sc.nextInt(),sc.nextInt(),sc.next().equals(up) ? UP:DOWN));
        }
        //按照楼层排序,后序等处理完了再按照key重新排序输出
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if(o1.floor < o2.floor){
                    return -1;
                }else if(o1.floor > o2.floor){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        LinkedList<Player> stack = new LinkedList<>();
        for(Player p: players){
            //遍历整个列表，从低楼层至高楼层
            //遇到向上的压入栈中
            if(p.direction == UP){
                stack.push(p);
            }else{
                //遇到向下的
                //1、弹出栈顶向上的player，battle
                while(!stack.isEmpty()){
                    Player p1 = stack.pop();
                    //battle
                    if(p.hp > p1.hp){
                        p1.hp = 0;
                        p.hp -= 1;
                    }else if(p.hp < p1.hp){
                        p.hp = 0;
                        p1.hp -= 1;
                        stack.push(p1);
                        break;
                    }else{
                        //血量相同，一起消灭
                        //将血量设置为0，直接break
                        p1.hp = p.hp = 0;
                        break;
                    }
                }
                //2、如果栈顶为空,当前p加入survivors
                if(stack.isEmpty() && p.hp != 0){
                   survivors.add(p);
                }
            }

        }
        //battle结束之后将栈中的元素加入到survivors中
        while(!stack.isEmpty()){
            survivors.add(stack.pop());
        }
        Collections.sort(survivors, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if(o1.addKey < o2.addKey){
                    return -1;
                }else if(o1.addKey > o2.addKey){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        for(Player p : survivors){
            System.out.println(p.hp);
        }
    }
}
