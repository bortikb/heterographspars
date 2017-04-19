import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ReaderMain {

    public static void main(String[] args) {

        String csvFile = "Person.csv";
        String line = "";
        String cvsSplitBy = ",";
        int numberOfLines = 0;
        int fieldIndex = 2;
        int numberOfFields = 471;
        Map<Integer, String> labelMap = new HashMap<Integer, String>();
        //Map<Integer,String> fieldMap = new HashMap<Integer, String>();
      
        Vector<Integer> nodeLabels = new Vector<Integer>();
        Vector<String> fieldLabels = new Vector<String>();
      
        for(fieldIndex = numberOfFields-1; fieldIndex >= 2; fieldIndex--){
        	
        	System.out.print("\nProcessing the field index: " + fieldIndex);
        	numberOfLines = 0;
        	fieldLabels = new Vector<String>();
        	
        	try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    numberOfLines++;
                	
                    if(numberOfLines >= 5){
                    	
                    	String[] country = line.split(cvsSplitBy);
                    	
                    	//System.out.print("\nRead line number: " + numberOfLines + "\n");
                    	//String dbPediaURI = country[0];
                    	
                    	//System.out.print(dbPediaURI.substring(dbPediaURI.lastIndexOf("/")+1, dbPediaURI.length()-1));
                    	//break;
                    	
                    	//labelMap.put(numberOfLines, dbPediaURI.substring(dbPediaURI.lastIndexOf("/")+1, dbPediaURI.length()-1));

                    	//nodeLabels.add(numberOfLines);
                    	fieldLabels.add(country[fieldIndex]);
                    	//fieldMap.put(numberOfLines, country[fieldIndex]);
                    	//System.out.print("\nAdded entry number: " + numberOfLines);
                    }
                    
                }

            br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            for(int i=0;i<fieldLabels.size()-1 && !fieldLabels.get(i).contains("NULL");i++){
            	
            	boolean matchFound = false;
            	
            	for(int j= i+1; j<fieldLabels.size() && !fieldLabels.get(i).contains("NULL");j++){
            		
            		if(fieldLabels.get(i).equalsIgnoreCase(fieldLabels.get(j))){
            			
            			System.out.print("\nFound one: " + fieldLabels.get(j));
            			
            			try {
    						
            				BufferedWriter bw = new BufferedWriter(new FileWriter("edge_list.txt",true));
    						String edgeList = new Integer(fieldIndex).toString() + ", " + (i+5) + ", " + (j+5) + ", " + fieldLabels.get(i);
    						bw.write(edgeList);
    						bw.write("\n");
    						bw.close();
    						
    						System.out.print("\nAdded entry for index: " + fieldLabels.get(i) + ", " + fieldLabels.get(j));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
            			
            			matchFound = true;
            		}
            	}
            	
            	if(!matchFound){
            		
            		try {
    					
        				BufferedWriter bw = new BufferedWriter(new FileWriter("edge_list.txt",true));
    					String edgeList = new Integer(fieldIndex).toString() + ", " + (i+5) + ", " + (i+5) + ", " + fieldLabels.get(i);
    					bw.write(edgeList);
    					bw.write("\n");
    					bw.close();
    					
    					System.out.print("\nAdded self-loop for index: " + fieldLabels.get(i) + ", " + fieldLabels.get(i));
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
            	}
            	
            	
            }
        }

        
//        try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter("Field_Index_" + new Integer(fieldIndex).toString()+".txt"));
//			numberOfLines = 0;
//			
//			for(Map.Entry<Integer, String> entry: labelMap.entrySet()){
//				
//				numberOfLines++;
//				bw.write(entry.getKey() + ", " + entry.getValue());
//				bw.write("\n");
//				
//				System.out.print("\nWriting entry number: " + numberOfLines);
//			}
//			
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

}