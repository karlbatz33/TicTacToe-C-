// C Program to demonstrate use of #include
#include <stdio.h>
#include <utility>
#include <vector>
#include <iostream>

//Class for Map
class RomaniaMapList
{
private:
    int V; //# of vertices

    //private: LinkedList<Integer> adj[]; //edges
    typedef struct node{
        int data;           //Stores data of each node
        node* next;         //Point to the next node
    }* nodePtr;


    nodePtr head;           // Start of the Node Instead of node* head;
    nodePtr curr;           // Current Node
    nodePtr temp;           // Temporary Node


//Declaring function prototypes
public:
    List();                 //This will let rest of prog know that the three intial values up will be set after list item is created

    //Add edges below
    void addEdge(int addVerticies);




};




// Driver Code
int main()
{
    printf("This is a program");
    return 0;
}
