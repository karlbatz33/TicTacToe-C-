#include <string>
#include <iostream>
using namespace std;

// Declare string of 20 cities from 1-20.  //0 is used as dummy node/city so; This means V = 21
string cities[] = {"dummyTestNode", "Arad", "Zerind", "Oradea", "Timisoara",
"Sibiu", "Lugoj", "Mehadia", "Dobreta", "Rimnicu_Vilcea",
"Fagaras", "Pitesti", "Craiova", "Bucharest", "Urziceni",
"Giurgiu", "Vaslui", "Hirsova", "Eforie", "Iasi", "Neamt"};

//Declare variables
string sourceLocationInput;
string sourceDestinationInput;
string exitCheck;
int sourceLocation;
int destinationLocation;
bool isValidSource = false;
bool isValidDestination = false;
bool isExiting = false;

//Class of adjacent nodes of the cities
class adjacencyList {
    public:
    //Each node has an id and a cost to it's neighbour node/city
    int id;
    int cost;
    //Point to next node in adjacency list
    adjacencyList *next;
    adjacencyList(int id, int cost) {
        //Set value of node key
        this->id = id;
        this->cost = cost;
        //next set to null to make room before assigning adjacent node
        this->next = NULL;
    }
};

//Class where in every city there is a list of adjacent nodes/ (List of a List)
class Vertices_Cities {
    public:
    int data;
    adjacencyList *next;
        Vertices_Cities()
        {
            this->data = 0;
            this->next = NULL;
        }
    Vertices_Cities(int data) {
        this->data = data;
        this->next = NULL;
    }
};

class MyGraph {
    public:
    //number of Vertices/Cities
    int size;
    Vertices_Cities *node;
    MyGraph(int size) {
        //set value
        this->size = size;
        node = new Vertices_Cities[size];
        this->setInitData();
    }
    //setInitData() is to initialise node/city value
    void setInitData() {
        if (node == NULL) {
            cout << "\nGraph is empty";
        }
        else
        {
            for (int index = 0; index < this->size; index++)
            {
                node[index] = index;
            }
        }
    }




    //Create edges by linking two adjacent nodes together
    void linkNodes(int start, int dest, int cost)
    {
        //Pointer of class adjacencyList which has destination and cost
        adjacencyList *new_edge = new adjacencyList(dest, cost);
        //If the next node relative to start is null, assign new_edge.
        //node[start] would be for the current value of start node. every node makes a new class of Verticies_Cities / similar to array
        if (node[start].next == NULL)
        {
            //assign new_edge
            node[start].next = new_edge;
        }
        else
        {
            //next node from the start(current node) is assigned to Temporary pointer of adjacencyList
            adjacencyList *temp = node[start].next;
            while (temp->next != NULL)
            {
                temp = temp->next;
            }
            //retrieve object's value for next node and assign it as new edge
            temp->next = new_edge;
        }
    }
    //Function adds egde between two nodes through connecting the adjacent nodes
    void addEdge(int start, int dest, int cost) {
        //node can't be null as you won't be able to link/add an edge to a null node.
        if (start >= 0 && start < this->size && dest >= 0 && dest < this->size && node != NULL)
        {
            this->linkNodes(start, dest, cost);
            //if source/start to destination aren't the same, then link. we can't link a node to itself and a node can't be adjacent to itself
            if (start != dest)
            {
                this->linkNodes(dest, start, cost);
            }
        }
        else
        {
            cout << "\nError";
        }
    }






    //Recursive Function of viewing the shortest path. Implemented in dijkstra's function
    void viewPath(int path[], int currLocation) {
        //If there are no more paths and destination is reached.
        if (path[currLocation] == -1)
        {
            return;
        }
        //recursively executing function
        this->viewPath(path, path[currLocation]);
        cout << " -> " << cities[currLocation];
    }

    //needed because in main, you need to initialise and print graph
    void print_graph() {
        if (this->size > 0 &&node != NULL)
        {
            for (int index = 0; index < this->size; index++) {
                //cout << "\nAdjacency list of vertex " << index << " :";
                adjacencyList *temp = node[index].next;
                while (temp != NULL) {
                    //cout << " " << node[temp->id].data;
                    temp = temp->next;
                }
            }
        }
    }





    //Function calculates the minimum distance from each position
    int shortestDistance(int distance[], int visited[]) {
        int currPosition = -1;
        for (int i = 0; i < this->size; ++i) {
            if (visited[i] == 0) {
                if (currPosition == -1)
                {
                    currPosition = i;
                }
                else if (distance[currPosition] > distance[i])
                {
                    currPosition = i;
                }
            }
        }
        return currPosition;
    }

    //Dijkstra's Algorithm function
    void dijkstraAlgorithm(int src) {
        //In order to have dijkstra's algorithm graph we need node to not be NULL
        if (node != NULL)
        {
            int *visited = new int[size];
            int *path = new int[size];
            int *distance = new int[size];
            int currentPos = 0;
            int i = 0;
            //For all nodes have max distance and make visited empty by assigning 0 for all visited[i]. i being nodes
            //Foreach vertex/node i, in graph, make distance infinity
            for (i = 0; i < this->size; ++i) {
                visited[i] = 0;
                path[i] = -1;
                distance[i] = INT_MAX;
            }
            distance[src] = 0;
            //Assign NULL to the temporary pointer of adjacency list as initialiser
            adjacencyList *temp = NULL;

            for (i = 0; i < this->size; ++i)
            {
                //Value of shortest path
                currentPos = this->shortestDistance(distance, visited);
                if (distance[currentPos] != INT_MAX)
                {
                    //Next node from position, where the distance from position isn't infinite.
                    temp = node[currentPos].next;
                    //While temporary pointer isn't empty
                    while (temp != NULL) {
                        //If alternative path/node is shorter than current distance then, assign it as new distance.
                        //*distance being assigned the shorter path
                        if (distance[currentPos] + temp->cost < distance[temp->id])
                        {
                            distance[temp->id] = distance[currentPos] + temp->cost;
                            path[temp->id] = currentPos;
                        }
                        //Move onto next verticies/node/city through temporary pointer of adjacency list
                        temp = temp->next;
                    }
                }
                if (currentPos != -1)
                {
                    visited[currentPos] = 1;
                }
            }
            //Printing out UI
            cout << "\n\n  | _____ Location _____ |      | _____ Destination _____ |      | _____ Distance _____ |                             \n";
            cout << "   \n";
            for (i = 0; i < this->size; ++i)
            {
                //Check to see if distance has value of infinity
                if (distance[i] == INT_MAX)
                {
                    cout << " " << i << " \t INF\n";
                }
                //Check to see if the destination has been reached, If so, print the starting node, destination node and calculated shortest distance
                else if(i = destinationLocation)
                {
                    cout << "            " << cities[sourceLocation] <<  "            \t        " << cities[i] << "                \t          " << distance[i];// << cities[src];
                    break;
                }
                cout << "\n\n";
            }
            cout << "\n\nThe Shortest Path:\n";
            for (i = 0; i < this->size; ++i)
            {
                //Check to see if distance has value of infinity
                if (distance[i] == INT_MAX)
                {
                    cout << " " << i << " \t INF\n";
                }
                //Check to see if the destination has been reached, If so, print the path (path being the calculated optimal path from start to destination)
                else if(i = destinationLocation)
                {
                    //Prints the starting node
                    cout << " " << cities[src];
                }
                //Print the rest of the path until it reaches i the final destination
                this->viewPath(path, i);
                cout << "\n\n\n";
                break;
            }
        }
        else
        {
            cout << "Graph is Empty";
        }
    }
};

//This function created to ask for input of the cities as strings
void dropCities(){
    cout<< "\n\nPlease Enter the city of your Starting Location:\n";
    cin >> sourceLocationInput;
    if(sourceLocationInput == "EXIT"){
        exit(0);
    }
    cout<< "Please Enter the city of your Destination Location:\n";
    cin >> sourceDestinationInput;
    if(sourceDestinationInput == "EXIT"){
        exit(0);
    }
    //0 - 20        // 1-20 being the cities and 0 being the dummy
    for(int i=0; i<21; i++){
        if(sourceLocationInput == cities[i])
        {
            isValidSource = true;
            sourceLocation = i;
        }
        if(sourceDestinationInput == cities[i])
        {
            isValidDestination = true;
            destinationLocation = i;
        }
    }
    if(isValidSource == false || isValidDestination == false){
        system("cls");
        cout << "ERROR, Please try input again!!\n\n" << endl;
    }
}

//Main Program
int main() {
    //21 implies the number of nodes in graph from 0 - 20 including the dummy
    MyGraph g =  MyGraph(21);
    //g.addEdge(starting_node, destination_node, cost from start to destination nodes);
    //dummyTestNode is number 0. It is done because with undirected weighted graph there will be an infinite loop of an error.
    g.addEdge(0, 1, 10);   //dummy nodes   from dummyTestNode -> Arad
    g.addEdge(1, 0, 10);   //dummy nodes   from Arad -> dummyTestNode

    g.addEdge(1, 2, 75);   //Arad -> Zerind
    g.addEdge(2, 1, 75);   //Zerind -> Arad

    g.addEdge(1, 4, 118);  //Arad -> Timisoara
    g.addEdge(4, 1, 118);  //Timisoara -> Arad

    g.addEdge(1, 5, 140);  //Arad -> Sibiu
    g.addEdge(5, 1, 140);  //Sibiu -> Arad

    g.addEdge(2, 3, 71);   //Zerind -> Oradea
    g.addEdge(3, 2, 71);   //Oradea -> Zerind

    g.addEdge(3, 5, 151);  //Oradea -> Zerind
    g.addEdge(5, 3, 151);  //Zerind -> Oradea

    g.addEdge(4, 6, 111);  //Timisoara -> Lugoj
    g.addEdge(6, 4, 111);  //Lugoj -> Timisoara

    g.addEdge(6, 7, 70);   //Lugoj -> Mehadia
    g.addEdge(7, 6, 70);   //Mehadia -> Lugoj

    g.addEdge(7, 8, 75);   //Mehadia -> Dobreta
    g.addEdge(8, 7, 75);   //Dobreta -> Mehadia

    g.addEdge(5, 9, 80);   //Sibiu -> Rimnicu Vilcea
    g.addEdge(9, 5, 80);   //Rimnicu Vilcea -> Sibiu

    g.addEdge(5, 10, 99);  //Sibiu -> Fagaras
    g.addEdge(10, 5, 99);  //Fagaras -> Sibiu

    g.addEdge(8, 12, 120); //Dobreta -> Craiova
    g.addEdge(12, 8, 120); //Craiova -> Dobreta

    g.addEdge(9, 12, 146); //Rimnicu Vilcea -> Craiova
    g.addEdge(12, 9, 146); //Craiova -> Rimnicu Vilcea

    g.addEdge(9, 11, 97);  //Rimnicu Vilcea - Pitesti
    g.addEdge(11, 9, 97);  //Pitesti -> Rimnicu Vilcea

    g.addEdge(11, 12, 138);    //Pitesti -> Craiova
    g.addEdge(12, 11, 138);    //Craiova -> Pitesti

    g.addEdge(10, 13, 211);    //Fagaras -> Bucharest
    g.addEdge(13, 10, 211);    //Bucharest -> Fagaras

    g.addEdge(11, 13, 101);    //Pitesti -> Bucharest
    g.addEdge(13, 11, 101);    //Bucharest -> Pitesti

    g.addEdge(13, 15, 90);     //Bucharest -> Giurgiu
    g.addEdge(15, 13, 90);     //Giurgiu -> Bucharest

    g.addEdge(13, 14, 85);     //Bucharest -> Urziceni
    g.addEdge(14, 13, 85);     //Urziceni -> Bucharest

    g.addEdge(14, 17, 98);     //Urziceni -> Hirsova
    g.addEdge(17, 14, 98);     //Hirsova -> Urziceni

    g.addEdge(17, 18, 86);     //Hirsova -> Eforie
    g.addEdge(18, 17, 86);     //Eforie -> Hirsova

    g.addEdge(14, 16, 142);    //Urziceni -> Vaslui
    g.addEdge(16, 14, 142);    //Vaslui -> Urziceni

    g.addEdge(16, 19, 92);     //Vaslui -> Iasi
    g.addEdge(19, 16, 92);     //Iasi -> Vaslui

    g.addEdge(19, 20, 87);     //Iasi -> Neamt
    g.addEdge(20, 19, 87);     //Neamt -> Iasi

    //Menu for Finding shortest Path
    while(!isExiting)
    {
        while(isValidSource == false || isValidDestination == false)
        {
            cout << "\nType 'EXIT' anytime to quit the Program\n\n";
            dropCities();
        }
        ///*Testing print to see values*/cout << "\n\n " << sourceLocation << "\n" << destinationLocation << "\n\n " << endl;
        if(isValidSource == true && isValidDestination == true)
        {
            g.print_graph();
            g.dijkstraAlgorithm(sourceLocation);
        }
        cout << "\n" << endl;
        cout<< "\n\nIf you would like to exit the program, Enter 'Y'\n";
        cin >> exitCheck;
        //Check to see if user wants to exit prog after completing his search for shortest path
        if(exitCheck == "Y" || exitCheck == "y" || exitCheck == "Yes" || exitCheck == "yes" || exitCheck == "YES"){
            isExiting = true;
            return 0;
        }
        else
        {
            isExiting = false;
            system("cls");
            //Reset the booleans like switches
            isValidSource = false;
            isValidDestination = false;
        }
    }
}