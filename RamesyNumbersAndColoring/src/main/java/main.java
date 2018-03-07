
import java.util.*;

public class main {

    public static void main(String[] args) {


      //  Scanner scan = new Scanner(System.in);
      //  int numOfVertices = scan.nextInt();
        int numOfVertices = 5;
        int Ks = 3;
        int Kt = 3;

        for (int i=1; i<Ks; i++){
            for (int j=1; j<Kt; j++){
                
            }
        }
        //create graph
        Graph graph = new Graph(numOfVertices);

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(1, 3, 0);
        graph.addEdge(1, 4, 0);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 5, 0);
        graph.addEdge(2, 4, 0);
        graph.addEdge(3, 5, 0);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 5, 1);


        boolean colorable = graph.colorAll();

        if(colorable){
            System.out.println("This graph is colorable. The coloring is:");
            graph.printColor();
        }
        else{
            System.out.println("This graph is not colorable. The odd cycle is as above.");
        }


    }

}
