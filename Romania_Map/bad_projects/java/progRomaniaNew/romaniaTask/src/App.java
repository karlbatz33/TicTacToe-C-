import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner; 



class To{
    int node;
    int weight;
    public To(int n, int w){
        node = n;
        weight = w;
    }
}

//Priority queue needs something to compare with and it needs to be compared with distance from start node
class Cur implements Comparable{
    int node;       //for current 
    int dist;
    int from;

    public Cur(int n, int d, int f){
        node = n;
        dist = d;
        from = f;
    }

    @Override
    public int compareTo(Object o) {
        return dist - ((Cur)o).dist;
    }

}



public class App {
    static int[] parent;
    static List<Integer> path = new ArrayList<>();
    @SuppressWarnings("unchecked")

    public static void main(String[] args) throws Exception 
    {
        //System.out.println("Hello, World!");
        Scanner in = new Scanner(System.in);


        final int N = in.nextInt() + 1;     //Number of Nodes and +1 is because we want to start index at 1 not 0
        final int E = in.nextInt();         //Number of Edges


        List<To>[] adjList = new List[N];
        int[] res = new int[N];             //Store the distances
        parent = new int[N];
        boolean[] visited   = new boolean[N];


        Arrays.fill(res, Integer.MAX_VALUE);
        
        for(int i = 0; i < N; i++){
            adjList[i] = new ArrayList<>();
         }
        
        for(int i = 0; i < E; i++){
            int s = in.nextInt();               //Starting node 
            int t = in.nextInt();               //Destination (To) node
            int w = in.nextInt();               //Weight of node
            adjList[s].add(new To(t, w));
            adjList[s].add(new To(s, w));
        }
        
        
        
        //Start DIjkstra's algorithm
        //Sort Current node's distance from source node
        
        PriorityQueue<Cur> q = new PriorityQueue<>();
        q.add(new Cur(1, 0, 1));
        res[1] = 0;

        while(!q.isEmpty()){
            int s = q.size();

            for(int i = 0; i < s; i++){
                Cur cur = q.poll();

                int node = cur.node;
                visited[node] = true;
                
                if(res[node] > cur.dist){
                    res[node] = cur.dist;
                    parent[node] = cur.from;
                }

                for(int j = 0; j < adjList[node].size(); j++){
                    int n = adjList[node].get(j).node;
                    int w = adjList[node].get(j).weight;

                    if(visited[n]) continue;
                    q.add(new Cur(n, cur.dist + w, node));
                    
                }

            }

        }

        for(int i = 1; i < res.length; i++) {
            if(res[i] == Integer.MAX_VALUE){
                System.out.println(-1);
            }
            System.out.println(res[i]);
        }
        
        
        //
        System.out.println(Arrays.toString(parent) + "\n");

        findPath(5);
        //
        System.out.println(path);
        
    }

    public static void findPath(int cur){
        if(cur == 1){
            path.add(1);
            Collections.reverse(path);
            return;
        }
        path.add(cur);
        findPath(parent[cur]);
    }

}



/*
8 being node and 11 being edges

8 11
1 2 2
1 3 1 
3 7 6
7 8 2
8 5 5 
3 4 7
2 4 5
2 6 2
6 5 4 
4 5 9
6 8 2



22 20




*/



