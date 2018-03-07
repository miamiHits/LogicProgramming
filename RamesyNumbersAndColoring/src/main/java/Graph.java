import java.util.*;

public class Graph {
    //use adjacency list representation

    int numOfVertices;
    GraphNode[] vertices;
    private int adjacencyMatrix[][];

    public Graph(int numOfVertices){
        this.numOfVertices = numOfVertices;
        this.vertices = new GraphNode[numOfVertices];
        adjacencyMatrix = new int[numOfVertices][numOfVertices];

        for(int i=0;i<numOfVertices;i++){
            this.vertices[i] = new GraphNode(i+1);
        }

    }

    public void addEdge(int v1, int v2, int color){
        this.vertices[v1-1].addNeighbor(v2, color);
        this.vertices[v2-1].addNeighbor(v1, color);
    }

    public void addEdgeToMat() {

        for (int k=0; k< vertices.length; k++)
        {
            for(Map.Entry<Integer, Integer> entry : vertices[k].neighbors.entrySet()) {
                adjacencyMatrix[k][entry.getKey()-1] = entry.getValue();

            }
        }

    }

    public void BFS(int startNode){

        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(this.vertices[startNode-1]);

        while(!queue.isEmpty()){

            GraphNode node = queue.poll();
            if(!node.visited){
                node.visited = true;
                System.out.println(node.nodeIndex);
                for(int i=0;i<node.neighbors.size();i++){
                    int neighborNode = node.neighbors.get(i);
                    if(!this.vertices[neighborNode-1].visited){
                        queue.offer(this.vertices[neighborNode-1]);
                    }

                }
            }

        }

    }

    public void printOddCycle(GraphNode node, GraphNode neighbor){

        //int nodeIndex = node.nodeIndex;
        //int neighborIndex = neighbor.nodeIndex;

        boolean meetNeighbor = false;

        GraphNode v = node;
        while(v != null){
            System.out.println(v.nodeIndex);
            if(v.nodeIndex == neighbor.nodeIndex){
                meetNeighbor = true;
                break;
            }
            v = v.parent;
        }

        if(meetNeighbor){
            return;
        }

        v = neighbor;
        while(v.parent != null){
            System.out.println(v.nodeIndex);
            v = v.parent;
        }

        return;
    }

    public boolean colorTree(int startNode) {
        //return if the graph is colorable

        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        GraphNode root = this.vertices[startNode - 1];
        //color it as red
        root.color = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {

            GraphNode node = queue.poll();
            if (!node.visited) {
                node.visited = true;
                for (Map.Entry<Integer, Integer> entry : node.neighbors.entrySet()) {
                    int neignborColor = entry.getValue();

                    for (Map.Entry<Integer, Integer> seconedEntry : vertices[entry.getKey()-1].neighbors.entrySet()) {
                        if (seconedEntry.getKey() == node.nodeIndex) continue;
                        for (Map.Entry<Integer, Integer> thirdEntry : vertices[seconedEntry.getKey() - 1].neighbors.entrySet()) {
                            if (thirdEntry.getKey() ==  vertices[seconedEntry.getKey()-1].nodeIndex)continue;
                            if (thirdEntry.getKey()==node.nodeIndex)
                            {
                                // so there is circle of 3 with same colors
                                if (thirdEntry.getValue() == neignborColor && seconedEntry.getValue() == neignborColor)
                                    return false;
                                break;
                            }
                        }
                    }
                    if(!this.vertices[entry.getKey()-1].visited){
                        this.vertices[entry.getKey()-1].parent = node;
                        queue.offer(this.vertices[entry.getKey()-1]);
                    }
                }

            }


        }
        return true;
    }

    public boolean colorAll(){

        for(int v=1;v<=this.numOfVertices;v++){
            if(!this.vertices[v-1].visited){
                boolean colorable = this.colorTree(v);
                if(!colorable){
                    return false;
                }
            }
        }

        return true;
    }

    public void printColor(){
        addEdgeToMat();
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class GraphNode{

    //node index, from 1 to |V|
    int nodeIndex;
    //neighbors of the node
    Map<Integer, Integer> neighbors;
    //if this node is visited
    boolean visited;
    //the parent node in the BFS tree
    GraphNode parent;
    //the color of the node, -1: no color, 0: red, 1: blue
    int color;

    public GraphNode(int nodeIndex){
        this.nodeIndex = nodeIndex;
        this.neighbors = new HashMap<>();
        this.visited = false;
        this.parent = null;
        this.color = -1;
    }



    public void addNeighbor(int neighborIndex, int neighborColor){
        this.neighbors.put(neighborIndex, neighborColor);
    }



}

class GraphEdge{
    //node index, from 1 to |V|
    int nodeIndexA;
    int nodeIndexB;
    //if this node is visited
    boolean visited;
    //the color of the node, -1: no color, 0: red, 1: blue
    int color;
    public GraphEdge(int nodeIndexA, int nodeIndexB){
        this.nodeIndexA = nodeIndexA;
        this.nodeIndexB = nodeIndexB;
        this.color = -1;
    }
}