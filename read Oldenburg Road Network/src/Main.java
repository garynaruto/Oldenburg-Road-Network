import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class Main {

	public static final int node = 6105;
	public static final int edge = 7035;
	public static final int num = 50;
	public class Node{
		public int nodeID;
		public double nodeX;
		public double nodeY;
		public boolean visited;
		public Node(int nodeID, double nodeX,  double nodeY){
			this.nodeID = nodeID;
			this.nodeX = nodeX;
			this.nodeY = nodeY;	
			this.visited = false;
		}
		public String toString(){
			String s= nodeID+" "+nodeX+" "+nodeY;
			return s;
		}
	}
	public class Edge{
		public int edgeID;
		public int a;
		public int b;
		public double distance;
		public Edge(int edgeID, int nodeID, int nodeID2, double distance){
			this.edgeID = edgeID;
			this.a = nodeID;
			this.b = nodeID2;
			this.distance = distance;
		}
		public String toString(){
			String s= edgeID+" "+a+" "+b+" "+distance;
			return s;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new  Main();
		Node[] nodes = m.readnode();
		Edge[] edges = m.readedge();

		Node[] nodes100 = new Node[num];
		Edge[] outEdge = new Edge[num*3];
		m.getSmallGraph(num, nodes, edges, nodes100);
		int count=0;
		
		for(int i=0; i<edges.length; i++){
			if(m.findNodeByID(edges[i].a, nodes100)!=null){
				if(m.findNodeByID(edges[i].b, nodes100)!=null){
					outEdge[count++]=edges[i];
				}
			}
		}
		System.out.println("---------------");
		for(Edge n :outEdge){
			System.out.println(n);
		}
		
	}
	public void printNode(Node node){
		System.out.println(node);
	}
	public void getSmallGraph(int num, Node[] nodes, Edge[] edges,Node[] outNode ){
		
		int count=0;
		Queue<Node> nodeQueue =new LinkedList();
		nodeQueue.add(nodes[3000]);
		System.out.println(">>>>>>>>>>>>");
		printNode(nodes[3000]);
		outNode[count++]=nodes[3000];
		nodes[3000].visited = true;
		while(!nodeQueue.isEmpty()) {
			Node node = nodeQueue.remove();
			Node child=getUnvisitedChildNode(node,edges, nodes);
			while(child !=null){
				outNode[count++]=child;
				printNode(child);
				//System.out.println(count);
				nodeQueue.add(child);
				if(count>=num)
					return;
				child=getUnvisitedChildNode(node,edges, nodes);
			}
		}
	}
	
	public Node getUnvisitedChildNode(Node node, Edge[] edges, Node[] nodes){
		for(int i=0; i<edges.length; i++){
			if( node.nodeID == edges[i].a){
				Node tmp = findNodeByID( edges[i].b, nodes);
				if(tmp.visited == false){
					tmp.visited = true;
					return tmp;
				}
			}else if( node.nodeID == edges[i].b){
				Node tmp = findNodeByID(edges[i].a, nodes);
				if(tmp.visited == false){
					tmp.visited = true;
					return tmp;
				}
			}
			
		}
		return null;
	}
	public Node findNodeByID(int ID,  Node[] nodes){
		for(int i=0; i<nodes.length; i++){
			if(nodes[i].nodeID==ID){
				return nodes[i];
			}
		}
		return null;
	}
	public Edge findEdgeByID(int ID,  Edge[] edges){
		for(int i=0; i<edges.length; i++){
			if(edges[i].edgeID==ID){
				return edges[i];
			}
		}
		return null;
	}
	
	public Edge[] readedge() {
		Edge[] out = new Edge[edge];
		int edgeID;
		int a;
		int b;
		double distance;
		try{
			File file = new File("./data/OL.cedge.txt");
			Scanner s = new Scanner(file);

			for (int i = 0; i < edge; i++) {
				edgeID = s.nextInt();
				a = s.nextInt();
				b = s.nextInt();
				distance = s.nextDouble();
				out[i] = new Edge(edgeID,a, b, distance);
			}
			s.close();
		}catch(Exception e){
			e.toString();
		}
		return out;
	}
	public Node[] readnode() {
		Node[] out = new Node[node];
		int nodeNum = 0;
		double nodeX = 0.0;
		double nodeY = 0.0;

		try{
			File file = new File("./data/OL.cnode.txt");
			Scanner s = new Scanner(file);

			for (int i = 0; i < node; i++) {
				nodeNum = s.nextInt();
				nodeX = s.nextDouble();
				nodeY = s.nextDouble();
				out[i] = new Node(nodeNum, nodeX, nodeY);
			}
			s.close();
		}catch(Exception e){
			e.toString();
		}
		return out;
	}
}
