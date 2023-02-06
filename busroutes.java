import java.util.*;

class Pair{
    int first;
    int second;
    public Pair(int first,int second){
        this.first = first;
        this.second = second;
    }
}
public class busroutes {
    public static void main(String[] args) {
        int V = 5, E = 6;

        int[][] edges = {{1,2,2},{2,5,5},{2,3,4},{1,4,1},{4,3,3},{3,5,1}};

        busroutes obj = new busroutes();
        System.out.println(obj.shortestPath(V, E, edges));
        List < Integer > path = obj.shortestPath(V, E, edges);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {

        // Create an adjacency list of pairs of the form node1 -> {node2, edge weight}
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(); 
        for(int i = 0;i<=n;i++) {
            adj.add(new ArrayList<>()); 
        }
        for(int i = 0;i<m;i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2])); 
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2])); 
        }
        
        // priority queue for storing the nodes along with distances 
        PriorityQueue<Pair> pq = 
        new PriorityQueue<Pair>((x,y) -> x.first - y.first);

        // distance array for storing the updated distances and a parent array
        int[] dist = new int[n+1]; 
        int[] parent =new int[n+1]; 
        for(int i = 1;i<=n;i++) {
            dist[i] = (int)(1e9); 
            parent[i] = i; 
        }
        
        dist[1] = 0; 
        pq.add(new Pair(0, 1)); 
        while(pq.size() != 0) {

            // Topmost element of the priority queue is with minimum distance value.
            Pair it = pq.peek(); 
            int node = it.second;
            int dis = it.first; 
            pq.remove(); 
            
            // Iterate through the adjacent nodes of the current popped node.
            for(Pair iter : adj.get(node)) {
                int adjNode = iter.first; 
                int edW = iter.second;

                // Check if the previously stored distance value is 
                if(dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair(dis + edW, adjNode)); 

                    // Update the parent of the adjNode to the recent 
                    parent[adjNode] = node; 
                }
            }
        }

        // Store the final path in the ‘path’ array.
        List<Integer> path = new ArrayList<>();  

        // If distance to a node could not be found, return an array containing -1.
        if(dist[n] == 1e9) {
            path.add(-1); 
            return path; 
        }
        
        int node = n;
        // o(N)
        while(parent[node] != node) {
            path.add(node); 
            node = parent[node]; 
        }
        path.add(1); 
        Collections.reverse(path); 
        return path;
    }
}




    