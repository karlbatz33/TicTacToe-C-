import java.util.*; 

class Graph_pq { 
    int dist[];
    Set<Integer> visited;
    PriorityQueue<Node> pqueue; 
    int V; // Number of vertices 
    List<List<Node> > adj_list;
    
    List<Integer> shortestPath = new LinkedList<>();
    List<List<Integer> > shortestPaths;





    //class constructor
    public Graph_pq(int V) { 
        this.V = V; 
        dist = new int[V];  
        visited = new HashSet<Integer>(); 
        pqueue = new PriorityQueue<Node>(V, new Node()); 
    } 
   
    // Dijkstra's Algorithm implementation 
    public void algo_dijkstra(List<List<Node> > adj_list, int src_vertex) 
    { 
        this.adj_list = adj_list; 
   
        for (int i = 0; i < V; i++) 
        {
            dist[i] = Integer.MAX_VALUE;
        }

        // first add source vertex to PriorityQueue 
        pqueue.add(new Node(src_vertex, 0)); 
        
        //System.out.println(src_vertex);   // 7 is the output
        //System.out.println(dist[src_vertex]); max value
        
        
        // Distance to the source from itself is 0 
        dist[src_vertex] = 0;
        while (visited.size() != V) { 
 
        // u is removed from PriorityQueue and has min distance  
            int u = pqueue.remove().node;
            //System.out.println(u);                                    // 7 1 5 2 0 0 0 3 3 3 3 4 4 4 6
   
            // add node to finalized list (visited)
            visited.add(u);
            
            shortestPath.add(u);

            //add to a new list of paths    that    ******************************************

            //System.out.println(visited);                                // 7 1 5 2 0 0 0 3 3 3 3 4 4 4 6       //Prints out arrays
            graph_adjacentNodes(u); 
        } 
    } 


  // this methods processes all neighbours of the just visited node 
    private void graph_adjacentNodes(int u)   { 
        int edgeDistance = -1; 
        int newDistance = -1; 
   
        // process all neighbouring nodes of u 
        for (int i = 0; i < adj_list.get(u).size(); i++) { 
            Node v = adj_list.get(u).get(i); 

            //System.out.println(i + ", " + v);
   
            //  proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
   
                // compare distances 
                if (newDistance < dist[v.node])
                { 
                    dist[v.node] = newDistance;
                } 
   
                // Add the current vertex to the PriorityQueue 
                pqueue.add(new Node(v.node, dist[v.node])); 
            }
            //System.out.println(pqueue); 
        }
         
    } 
}



public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        //20 cities from 0-19.  //This means V = 20
        String cities[] = {"Arad", "Zerind", "Oradea", "Timisoara", 
        "Sibiu", "Lugoj", "Mehadia", "Dobreta", "Rimnicu Vilcea", 
        "Fagaras", "Pitesti", "Craiova", "Bucharest", "Urziceni", 
        "Giurgiu", "Vaslui", "Hirsova", "Eforie", "Iasi", "Neamt"};




        int V = 8; 
        int source = 7; 
        // adjacency list representation of graph
        List<List<Node> > adj_list = new ArrayList<List<Node> >(); 
        /**********************/
  


        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj_list.add(item); 
            
            //System.out.println(item);                   //Empty arrays

        }


/* 
        // Initialise adjacency list for all paths
        List<List<Integer> > shortestPaths = new ArrayList<List<Integer> >();

        for (int i = 0; i < V; i++) { 
            List<Integer> itemPaths = new ArrayList<Integer>();
            shortestPaths.add(itemPaths);
            
            System.out.println(itemPaths);             
        }
*/



        // Input graph edges 
        adj_list.get(0).add(new Node(1, 5)); 
        adj_list.get(0).add(new Node(2, 3)); 
        adj_list.get(0).add(new Node(3, 2)); 
        adj_list.get(0).add(new Node(4, 3));
        adj_list.get(0).add(new Node(5, 3));

        adj_list.get(2).add(new Node(1, 2)); 
        adj_list.get(2).add(new Node(3, 3)); 
        
        adj_list.get(3).add(new Node(6, 7)); 

        adj_list.get(4).add(new Node(6, 2));
        
        adj_list.get(1).add(new Node(7, 3)); 
        adj_list.get(5).add(new Node(7, 4)); 

        
        



        adj_list.get(1).add(new Node(0, 5)); 
        adj_list.get(2).add(new Node(0, 3)); 
        adj_list.get(3).add(new Node(0, 2)); 
        adj_list.get(4).add(new Node(0, 3));
        adj_list.get(5).add(new Node(0, 3));

        adj_list.get(1).add(new Node(2, 2)); 
        adj_list.get(3).add(new Node(2, 3));
        adj_list.get(6).add(new Node(3, 7)); 

        adj_list.get(6).add(new Node(4, 2));

        adj_list.get(7).add(new Node(1, 3)); 
        adj_list.get(7).add(new Node(5, 4)); 






        // call Dijkstra's algo method  
        Graph_pq dpq = new Graph_pq(V); 
        dpq.algo_dijkstra(adj_list, source); 
   





        // Print the shortest path from source node to all the nodes 
        System.out.println("\n\nThe shorted path from source node to other nodes:\n"); 
        System.out.println("| Location | \t" + "| Destination |     " + "| Distance |");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < dpq.dist.length; i++){
                System.out.println("    " + cities[source] + " \t  " + cities[i] + "    \t          "  +  dpq.dist[i] + "           \t          "  +  dpq.dist[i]);
        }



        System.out.println("\n\n"); 

       
        


        //System.out.println("\n\n"); 

 
    }
}



// Node class  
class Node implements Comparator<Node> { 
    public int node; 
    public int cost; 
    public Node() { } //empty constructor 
   
    public Node(int node, int cost) { 
        this.node = node; 
        this.cost = cost; 
    } 
    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
}














//
//
//
//
//
//
//
//
//

//
//
//
//
//
//
//
//
//
//
///
//
//
//
//
//
//
//
//
///
//
//
//
////
//
//
//
//
//
//
///
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
///
//
//
//
//
//
//
//
//
//
///
//
//
///
//