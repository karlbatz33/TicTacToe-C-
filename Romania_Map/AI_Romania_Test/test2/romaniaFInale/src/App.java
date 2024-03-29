import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static class DijkstraAlgo{
        /* Dijkstra Algorithm
         * 
         *
         */
            public static void computePaths(Node source){
                source.shortestDistance=0;
        
                //implement a priority queue
                PriorityQueue<Node> queue = new PriorityQueue<Node>();
                queue.add(source);
        
                while(!queue.isEmpty()){
                    Node u = queue.poll();
        
                    /*visit the adjacencies, starting from 
                    the nearest node(smallest shortestDistance)*/
                    
                    for(Edge e: u.adjacencies){
        
                        Node v = e.target;
                        double weight = e.weight;
        
                        //relax(u,v,weight)
                        double distanceFromU = u.shortestDistance+weight;
                        if(distanceFromU<v.shortestDistance){
        
                            /*remove v from queue for updating 
                            the shortestDistance value*/
                            queue.remove(v);
                            v.shortestDistance = distanceFromU;
                            v.parent = u;
                            queue.add(v);
        
                        }
                    }
                }
            }
        
            public static List<Node> getShortestPathTo(Node target){
        
                //trace path from target to source
                List<Node> path = new ArrayList<Node>();
                for(Node node = target; node!=null; node = node.parent){
                    path.add(node);
                }
        
        
                //reverse the order such that it will be from source to target
                Collections.reverse(path);
        
                return path;
            }
        
        
        
            public static void main(String[] args) throws Exception {
        
                //initialize the graph base on the Romania map
                Node n1 = new Node("Arad");
                Node n2 = new Node("Zerind");
                Node n3 = new Node("Oradea");
                Node n4 = new Node("Sibiu");
                Node n5 = new Node("Fagaras");
                Node n6 = new Node("Rimnicu Vilcea");
                Node n7 = new Node("Pitesti");
                Node n8 = new Node("Timisoara");
                Node n9 = new Node("Lugoj");
                Node n10 = new Node("Mehadia");
                Node n11 = new Node("Drobeta");
                Node n12 = new Node("Craiova");
                Node n13 = new Node("Bucharest");
                Node n14 = new Node("Giurgiu");
        
                //initialize the edges
                n1.adjacencies = new Edge[]{
                    new Edge(n2,75),
                    new Edge(n4,140),
                    new Edge(n8,118)
                };
        
                n2.adjacencies = new Edge[]{
                    new Edge(n1,75),
                    new Edge(n3,71)
                };
        
                n3.adjacencies = new Edge[]{
                    new Edge(n2,71),
                    new Edge(n4,151)
                };
        
                n4.adjacencies = new Edge[]{
                    new Edge(n1,140),
                    new Edge(n5,99),
                    new Edge(n3,151),
                    new Edge(n6,80),
                };
        
                n5.adjacencies = new Edge[]{
                    new Edge(n4,99),
                    new Edge(n13,211)
                };
        
                n6.adjacencies = new Edge[]{
                    new Edge(n4,80),
                    new Edge(n7,97),
                    new Edge(n12,146)
                };
        
                n7.adjacencies = new Edge[]{
                    new Edge(n6,97),
                    new Edge(n13,101),
                    new Edge(n12,138)
                };
        
                n8.adjacencies = new Edge[]{
                    new Edge(n1,118),
                    new Edge(n9,111)
                };
        
                n9.adjacencies = new Edge[]{
                    new Edge(n8,111),
                    new Edge(n10,70)
                };
        
                n10.adjacencies = new Edge[]{
                    new Edge(n9,70),
                    new Edge(n11,75)
                };
        
                n11.adjacencies = new Edge[]{
                    new Edge(n10,75),
                    new Edge(n12,120)
                };
        
                n12.adjacencies = new Edge[]{
                    new Edge(n11,120),
                    new Edge(n6,146),
                    new Edge(n7,138)
                };
        
                n13.adjacencies = new Edge[]{
                    new Edge(n7,101),
                    new Edge(n14,90),
                    new Edge(n5,211)
                };
        
                n14.adjacencies = new Edge[]{
                    new Edge(n13,90)
                };
        
                Node[] nodes = {n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14};
        
                //compute paths
                computePaths(n1);
        
                //print shortest paths
                /*
                for(Node n: nodes){
                    System.out.println("Distance to " + 
                        n + ": " + n.shortestDistance);
                    List<Node> path = getShortestPathTo(n);
                    System.out.println("Path: " + path);
                }*/
        
                List<Node> path = getShortestPathTo(n13);
                System.out.println("Path: " + path);
        
            }
        
        
        }
        
        
        //define Node
        class Node implements Comparable<Node>{
            
            public final String value;
            public Edge[] adjacencies;
            public double shortestDistance = Double.POSITIVE_INFINITY;
            public Node parent;
        
            public Node(String val){
                value = val;
            }
        
            public String toString(){
                    return value;
            }
        
            public int compareTo(Node other){
                return Double.compare(shortestDistance, other.shortestDistance);
            }
        
        }
        
        //define Edge
        class Edge{
            public final Node target;
            public final double weight;
            public Edge(Node targetNode, double weightVal){
                target = targetNode;
                weight = weightVal;
            }
        }






//    public static void main(String[] args) throws Exception {
//        System.out.println("Hello, World!");
//    }
}
