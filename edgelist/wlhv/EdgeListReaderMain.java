import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


public class EdgeListReaderMain {

	static String fileName = new String("edge_list.txt");
	static final int numberOfEntities = 9;
	static final int numberOfEdgeTypes = 471;
	static final double sparsityThreshold = 0.5;
	static double[][] similarityMatrix = new double[numberOfEntities][numberOfEntities];
	
	private static Vector<Double> initializeGlobalVector(int numberOfEdgeTypes){
		
		Vector<Double> globalOccurenceCountVector = new Vector<Double>();
		
		for(int i=0;i<numberOfEdgeTypes;i++){
			
			globalOccurenceCountVector.add(0.0);
		}
		
		return globalOccurenceCountVector;
	}
	
	private static Map<Integer, Vector<Double> > initializeLocalOccurenceVector(int numberOfNodes, int numberOfEdgeTypes){
		
		int i,j;
		
		Map<Integer, Vector<Double> > localOccVector = new HashMap<Integer, Vector<Double> >();
		
		for(i=0;i<numberOfNodes;i++){
			
			Vector<Double> heterogeneityVector = new Vector<Double>();
			
			for(j=0;j<numberOfEdgeTypes;j++){
				
				heterogeneityVector.add(0.0);
			}
			
			localOccVector.put(i, heterogeneityVector);
		}
		
		return localOccVector;
	}
	
	public static double cosineSimilarity(Vector<Double> vectorA, Vector<Double> vectorB) {
	 
		double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    
	    for (int i = 0; i < vectorA.size(); i++) {
	        dotProduct += vectorA.get(i) * vectorB.get(i);
	        normA += Math.pow(vectorA.get(i), 2);
	        normB += Math.pow(vectorB.get(i), 2);
	    }   
	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	
	public static void main(String[] args) {
		
		String line = new String();
		
		Vector<Double> globalHeterogeneityVector = new Vector<Double>();
		Map<Integer, Vector<Double> > nodeLocalOccurenceMap = new HashMap<Integer, Vector<Double> >();
		Set<Integer> nodeSet = new HashSet<Integer>();
		
		globalHeterogeneityVector = initializeGlobalVector(numberOfEdgeTypes);
		nodeLocalOccurenceMap = initializeLocalOccurenceVector(numberOfEntities,numberOfEdgeTypes);
		
		long startOccurenceMatrix = System.currentTimeMillis();
		try {
			
			BufferedReader edgeListReader = new BufferedReader(new FileReader(fileName));
			
			while((line = edgeListReader.readLine())!= null){
				
				String[] lineContents = line.split(" ");
				
				int edgeType = Integer.parseInt(lineContents[0])-1; //0 indexing
				int node1 = Integer.parseInt(lineContents[1])-1;
				int node2 = Integer.parseInt(lineContents[2])-1;
				
				if(node1 > 9 || node2 > 9){
					continue;
				}
				
				nodeSet.add(node1);
				nodeSet.add(node2);
				//System.out.print("\nReading edgetype: " + edgeType);
				double currentHeterogeneity = globalHeterogeneityVector.get(edgeType);
				currentHeterogeneity++;
				globalHeterogeneityVector.set(edgeType, currentHeterogeneity);
				
				//System.out.print("Node 1: " + node1 + " Node 2: " + node2 + "\n" + nodeLocalOccurenceMap.toString());
				
				double currentHeterogeneityNode1 = nodeLocalOccurenceMap.get(node1).get(edgeType);
				double currentHeterogeneityNode2 = nodeLocalOccurenceMap.get(node2).get(edgeType);
				
				currentHeterogeneityNode1++;
				currentHeterogeneityNode2++;
				
				Vector<Double> localHeterogeneityVectorNode1 = nodeLocalOccurenceMap.get(node1);
				Vector<Double> localHeterogeneityVectorNode2 = nodeLocalOccurenceMap.get(node2);
				
				localHeterogeneityVectorNode1.set(edgeType, currentHeterogeneityNode1);
				localHeterogeneityVectorNode2.set(edgeType, currentHeterogeneityNode2);
				
				nodeLocalOccurenceMap.remove(node1);
				nodeLocalOccurenceMap.put(node1, localHeterogeneityVectorNode1);
				
				nodeLocalOccurenceMap.remove(node2);
				nodeLocalOccurenceMap.put(node2, localHeterogeneityVectorNode2);
				
			}
			edgeListReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Normailzing globaloccurencerate vector
		int totalEdges = 0;
		
		for(int i=0;i<globalHeterogeneityVector.size();i++){
			
			totalEdges+= globalHeterogeneityVector.get(i);
		}
		
		for(int i=0;i<globalHeterogeneityVector.size();i++){
			
			totalEdges+= globalHeterogeneityVector.get(i);
		}
		
		for(int i=0;i<globalHeterogeneityVector.size();i++){
			
			double currentCount = globalHeterogeneityVector.get(i);
			globalHeterogeneityVector.set(i,currentCount/(double)totalEdges);
		}
		
		Map<Integer, Vector<Double> > wlhvVectorMap = new HashMap<Integer, Vector<Double> >();
		
		for(Map.Entry<Integer, Vector<Double>> entry: nodeLocalOccurenceMap.entrySet()){
			
			Vector<Double> localOccurence = entry.getValue();
			
			for(int i=0;i<localOccurence.size();i++){
				
				double currentCount = localOccurence.get(i);
				localOccurence.set(i,currentCount*globalHeterogeneityVector.get(i));
			}
			
			wlhvVectorMap.put(entry.getKey(), localOccurence);
		}
		
		long endOccMatrix = System.currentTimeMillis();
		nodeLocalOccurenceMap.clear();
		
		System.out.print("\nLocal occurence matrix created\nTime taken: " + (endOccMatrix-startOccurenceMatrix) + " msecs");
		
		long startMatrixCreation = System.currentTimeMillis();
		//Find cosine similarity
		for(int i =0;i<numberOfEntities;i++){
			for(int j=0;j<numberOfEntities;j++){
				
				similarityMatrix[i][j] = cosineSimilarity(wlhvVectorMap.get(i), wlhvVectorMap.get(j));
			}
		}
		
		long endMatrixCreation = System.currentTimeMillis();
		System.out.print("\nSimilarity matrix created\nTime taken:" + (endMatrixCreation-startMatrixCreation) + " msecs");
		
		long startSparsify = System.currentTimeMillis();
		try {
			BufferedReader edgeListReader = new BufferedReader(new FileReader("edge_list.txt"));
			
			while((line = edgeListReader.readLine())!= null){
				
				String[] lineContents = line.split(" ");
				
				int edgeType = Integer.parseInt(lineContents[0])-1; //0 indexing
				int node1 = Integer.parseInt(lineContents[1])-1;
				int node2 = Integer.parseInt(lineContents[2])-1;
				
				if(node1 > 9 || node2 > 9){
					continue;
				}
				
				//System.out.print("\n" + similarityMatrix[node1][node2]);
				
				if(similarityMatrix[node1][node2] >= sparsityThreshold){
					
					BufferedWriter sparseWriter = new BufferedWriter(new FileWriter("sparse_edge_list.txt",true));
					sparseWriter.write(line + "\n");
					sparseWriter.close();
				}
			}
			
			edgeListReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endSparsify = System.currentTimeMillis();
		System.out.print("\nSparse edgelist created.\nTime taken: " + (endSparsify-startSparsify) + " msecs");

	//System.out.print("\nPrinting:\n" + wlhvVectorMap.toString());
	//System.out.print("\nPrinting 2:\n" + nodeSet.toString());
	
	}

}
