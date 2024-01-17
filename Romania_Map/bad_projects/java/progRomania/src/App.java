import java.io.*;
import java.util.*;

class Graph
{
    private int V;
    private LinkedList<Integer> adj[];      //Edges 
    private LinkedList<Integer> adjCost[];      //Edges 

    
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for(int i=0; i<v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w, int cost){
        adj[v].add(w);
        
        adjCost = new LinkedList[cost];
        for(int i=0; i<v; i++){
            adjCost[i] = new LinkedList<>();
        }
        
        // The destination node will have a cost of whatever the cost is 
        //adj[w].add(cost);
    }

    void printAdjacent(int v, int w, int cost){

    }



    void dijkstra(int s, int d){
  

    }//End of the Dijkstra search

    


    
        void BFS(int s, int d){
            Queue<Integer> queue = new LinkedList<Integer>();
            ArrayList<Integer> seen = new ArrayList<Integer>();
            queue.add(s); //add the start to the search

            while(!queue.isEmpty())
            {
                int curr = queue.poll();  //Pull a node 
                if(!seen.contains(curr)){
                    seen.add(curr);
                    
                    if(curr == d){
                        System.out.println("\n\n Found your destination! \n\n");

                        for(Integer n : seen){
                            System.out.print(", " + n);
                        }
                        System.out.println("\n\n");
                        return;
                    }
                }
            
                for(Integer adjacent : adj[curr]){
                    if(!seen.contains(adjacent)){
                        queue.add(adjacent);
                        System.out.println("\n" + adjacent + "\n");
                    }
                }

            }
        }//End of the BFS search
    

}



public class App {

    //public static void main(String[] args) throws Exception {
    public static void main(String[] args){

        int a, b;
        
        Graph g = new Graph(20);
        //Adding edges to map


        //Arad ---> Zerind, Timisoara, Sibiu
        g.addEdge(0, 1, 75);
        g.addEdge(0, 3, 118);       
        g.addEdge(0, 4, 140);

        g.addEdge(1, 0, 75);
        g.addEdge(1, 2, 71);

        g.addEdge(2, 1, 71);
        g.addEdge(2, 4, 151);

        g.addEdge(3, 0, 118);
        g.addEdge(3, 5, 111);

        g.addEdge(4, 2, 151);
        g.addEdge(4, 0, 140);
        g.addEdge(4, 8, 80);
        g.addEdge(4, 9, 99);
        
        g.addEdge(5, 3, 111);
        g.addEdge(5, 6, 70);

        g.addEdge(6, 5, 70);
        g.addEdge(6, 7, 75);

        g.addEdge(7, 6, 75);
        g.addEdge(7, 11, 120);

        g.addEdge(8, 4, 80);
        g.addEdge(8, 11, 146);
        g.addEdge(8, 10, 97);
        
        g.addEdge(9, 4, 99);
        g.addEdge(9, 12, 211);

        g.addEdge(10, 8, 97);
        g.addEdge(10, 11, 138);
        g.addEdge(10, 12, 101);

        g.addEdge(11, 10, 138);
        g.addEdge(11, 8, 146);
        g.addEdge(11, 7, 120);


        g.addEdge(12, 10, 101);
        g.addEdge(12, 9, 211);
        g.addEdge(12, 13, 85);
        g.addEdge(12, 14, 90);


        g.addEdge(13, 12, 85);
        g.addEdge(13, 16, 98);
        g.addEdge(13, 15, 142);
        

        g.addEdge(14, 12, 90);


        g.addEdge(15, 13, 142);
        g.addEdge(15, 18, 92);


        g.addEdge(16, 13, 98);
        g.addEdge(16, 17, 86);

        g.addEdge(17, 16, 86);



        g.addEdge(18, 15, 92);
        g.addEdge(18, 19, 87);

        g.addEdge(19, 18, 87);






        



        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b = in.nextInt();
        
        g.BFS(a, b);
        //g.BFS(a, b);



        System.out.println("\n\n\nHello, World!\n\n");
    }
}


/*
 
0: Arad
1: Zerind
2: Oradea
3: Timisoara
4: Sibiu 
5: Lugoj
6: Mehadia
7: Dobreta
8: Rimnicu Vilcea
9: Fagaras
10: Pitesti
11: Craiova
12: Bucharest
13: Urziceni
14: Giurgiu
15: Vasuil
16: Hirsova
17: Eforie
18: Iasi
19: Neamt



*/

