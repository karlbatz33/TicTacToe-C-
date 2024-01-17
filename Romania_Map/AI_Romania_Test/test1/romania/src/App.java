import java.io.*;
import java.util.*;
import java.util.stream.Stream; 




public class App {
    //@Getter
    //@Setter
    //@RequiredArgsConstructor
    public class Node implements Comparable<Node>{
        private /*final*/ String name;                                  //Name of the node
        //@Getter(AccessLevel.PRIVATE)
        private Integer distance = Integer.MAX_VALUE;                   //Distance between the nodes    (.max_value is used as infinity)
        private List<Node> shortestPath = new LinkedList<>();           //List to store the shortest path from source node later updated by dijkstra's algorithm
        private Map<Node, Integer> adjacentNodes = new HashMap<>();     //Store adjacentNodes and edgeweights seperating each node

        /*This function is to add the weight of the adjacent nodes*/
        public void addAdjacentNode(Node node, int weight){
            adjacentNodes.put(node, weight);
        }

        @Override
        public int compareTo(Node node){ //App.Node o){
            return Integer.compare(this.distance, node.getDistance());
        }

        private int getDistance() {
            return distance;
        }

        public int setDistance(int i){
            distance = i;
            return distance;
        }

        public Map<App.Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public Collection<App.Node> getShortestPath() {
            return shortestPath;
        }


    }


    public void calculateShortestPath(Node source){
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<Node>();
        Queue<Node>unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        //While queue isn't empty
        while(!unsettledNodes.isEmpty()){
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes().entrySet().stream().filter(entry -> !settledNodes.contains(entry.getKey())).forEach(entry -> {

                evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                unsettledNodes.add(entry.getKey());

            });

            settledNodes.add(currentNode);

        }
    }
    



    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) { //App.Node key, Integer value, App.Node currentNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if(newDistance < adjacentNode.getDistance()){
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList());
        }

    }





    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        Node nodeA = new Node("A");

        
    }
}
