import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ReaderMain {

	public static void main(String[] args) {

		String csvFile = "Artist.csv";
		String line = "";
		String csvSplitBy = new String("\",");
		int numberOfLines = 0;
		int fieldIndex = 2;
		int numberOfFields = 471;

		Map<Integer, String> labelMap = new HashMap<Integer, String>();
		// Map<Integer,String> fieldMap = new HashMap<Integer, String>();
		Map<Integer, Vector<String>> superNodeLabelSetMap = new HashMap<Integer, Vector<String>>();

		Vector<Integer> nodeLabels = new Vector<Integer>();
		Vector<String> fieldLabels = new Vector<String>();

		// Vector<String> labelVector = new Vector<String>();
		//
		// //Add the possible entries in vector
		// labelVector.add("Ambassador");
		// labelVector.add("Archeologist");
		// labelVector.add("Architect");
		// labelVector.add("Aristocrat");
		// labelVector.add("Artist");
		// labelVector.add("Astronaut");
		// labelVector.add("Athlete");
		// labelVector.add("BeautyQueen");
		// labelVector.add("BusinessPerson");
		// labelVector.add("Celebrity");
		// labelVector.add("Chef");
		// labelVector.add("Cleric");
		// labelVector.add("Coach");
		// labelVector.add("Criminal");
		// labelVector.add("Economist");
		// labelVector.add("Egyptologist");
		// labelVector.add("Engineer");
		// labelVector.add("Farmer");
		// labelVector.add("FictionalCharacter");
		// labelVector.add("HorseTrainer");
		// labelVector.add("Journalist");
		// labelVector.add("Judge");
		// labelVector.add("Lawyer");
		// labelVector.add("Linguist");
		// labelVector.add("MemberResistanceMovement");
		// labelVector.add("MilitaryPerson");
		// labelVector.add("Model");
		// labelVector.add("Monarch");
		// labelVector.add("MovieDirector");
		// labelVector.add("Noble");
		// labelVector.add("OfficeHolder");
		// labelVector.add("OrganisationMember");
		// labelVector.add("Orphan");
		// labelVector.add("Philosopher");
		// labelVector.add("PlayboyPlaymate");
		// labelVector.add("Politician ");
		// labelVector.add("PoliticianSpouse ");
		// labelVector.add("Presenter");
		// labelVector.add("Producer");
		// labelVector.add("Psychologist");
		// labelVector.add("Referee");
		// labelVector.add("Religious");
		// labelVector.add("RomanEmperor");
		// labelVector.add("Royalty");
		// labelVector.add("Scientist");
		// labelVector.add("SportsManager");
		// labelVector.add("TelevisionDirector");
		// labelVector.add("TelevisionPersonlaity");
		// labelVector.add("TheatreDirector");
		// labelVector.add("Writer");

		// for(fieldIndex = numberOfFields-1; fieldIndex >= 2; fieldIndex--){
		//
		// System.out.print("\nProcessing the field index: " + fieldIndex);
		// numberOfLines = 0;
		// fieldLabels = new Vector<String>();
		//
		// try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
		// {
		//
		// while ((line = br.readLine()) != null) {
		//
		// // use comma as separator
		// numberOfLines++;
		//
		// if(numberOfLines >= 5){
		//
		// String[] country = line.split(cvsSplitBy);
		//
		// //System.out.print("\nRead line number: " + numberOfLines + "\n");
		// //String dbPediaURI = country[0];
		//
		// //System.out.print(dbPediaURI.substring(dbPediaURI.lastIndexOf("/")+1,
		// dbPediaURI.length()-1));
		// //break;
		//
		// //labelMap.put(numberOfLines,
		// dbPediaURI.substring(dbPediaURI.lastIndexOf("/")+1,
		// dbPediaURI.length()-1));
		//
		// //nodeLabels.add(numberOfLines);
		// fieldLabels.add(country[fieldIndex]);
		// //fieldMap.put(numberOfLines, country[fieldIndex]);
		// //System.out.print("\nAdded entry number: " + numberOfLines);
		// }
		//
		// }
		//
		// br.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// for(int i=0;i<fieldLabels.size()-1 &&
		// !fieldLabels.get(i).contains("NULL");i++){
		//
		// boolean matchFound = false;
		//
		// for(int j= i+1; j<fieldLabels.size() &&
		// !fieldLabels.get(i).contains("NULL");j++){
		//
		// if(fieldLabels.get(i).equalsIgnoreCase(fieldLabels.get(j))){
		//
		// System.out.print("\nFound one: " + fieldLabels.get(j));
		//
		// try {
		//
		// BufferedWriter bw = new BufferedWriter(new
		// FileWriter("edge_list.txt",true));
		// String edgeList = new Integer(fieldIndex).toString() + ", " + (i+5) +
		// ", " + (j+5) + ", " + fieldLabels.get(i);
		// bw.write(edgeList);
		// bw.write("\n");
		// bw.close();
		//
		// System.out.print("\nAdded entry for index: " + fieldLabels.get(i) +
		// ", " + fieldLabels.get(j));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// matchFound = true;
		// }
		// }
		//
		// if(!matchFound){
		//
		// try {
		//
		// BufferedWriter bw = new BufferedWriter(new
		// FileWriter("edge_list.txt",true));
		// String edgeList = new Integer(fieldIndex).toString() + ", " + (i+5) +
		// ", " + (i+5) + ", " + fieldLabels.get(i);
		// bw.write(edgeList);
		// bw.write("\n");
		// bw.close();
		//
		// System.out.print("\nAdded self-loop for index: " + fieldLabels.get(i)
		// + ", " + fieldLabels.get(i));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		//
		// }
		// }

		// try {
		// BufferedWriter bw = new BufferedWriter(new FileWriter("Field_Index_"
		// + new Integer(fieldIndex).toString()+".txt"));
		// numberOfLines = 0;
		//
		// for(Map.Entry<Integer, String> entry: labelMap.entrySet()){
		//
		// numberOfLines++;
		// bw.write(entry.getKey() + ", " + entry.getValue());
		// bw.write("\n");
		//
		// System.out.print("\nWriting entry number: " + numberOfLines);
		// }
		//
		// bw.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		numberOfLines = 0;
		Vector<String> masterLabelVector = new Vector<String>();

		try (BufferedReader br = new BufferedReader(
				new FileReader("Person.csv"))) {

			while ((line = br.readLine()) != null) {

				numberOfLines++;

				if (numberOfLines == 1) {

					String[] masterLabelArray = line.split(",");
					System.out.print("\nMaster Size: "
							+ masterLabelArray.length);

					for (int i = 2; i < masterLabelArray.length; i++) {

						// System.out.print("\n" +
						// masterLabelArray[i].substring(1,masterLabelArray[i].length()-2));
						masterLabelVector.add(masterLabelArray[i].substring(1,
								masterLabelArray[i].length() - 1).trim());
					}

					// System.exit(0);

				} else {
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		numberOfLines = 0;

		Vector<String> fieldValueVector = new Vector<String>();
		Vector<String> facetLabelVector = new Vector<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				numberOfLines++;

				if (numberOfLines == 1) {

					String[] facetLabels = line.split(csvSplitBy);

					for (int i = 2; i < facetLabels.length; i++) {

						facetLabelVector
								.add(facetLabels[i].substring(1).trim());
					}

				} else if (numberOfLines >= 5) {

					String[] fieldLabelArray = line.split(csvSplitBy);

					// BufferedWriter nodeInfoWriter = new BufferedWriter(new
					// FileWriter("Person_Node.txt", true));
					// nodeInfoWriter.write(fieldLabelArray[0] + "\n");
					// nodeInfoWriter.close();

					// System.out.print("\nSize: " + fieldLabelArray.length);
					for (int j = 2; j < fieldLabelArray.length; j++) {

						System.out.print("\nProcessing field index: " + (j)
								+ " Value: " + fieldLabelArray[j].substring(1)
								+ "\n");

						fieldValueVector = new Vector<String>();

						if (superNodeLabelSetMap.containsKey(j)) {

							fieldValueVector = superNodeLabelSetMap.get(j);

							if (!fieldValueVector.contains(fieldLabelArray[j])) {
								fieldValueVector.add(fieldLabelArray[j]
										.substring(1).trim());
							}

							superNodeLabelSetMap.remove(j);
							superNodeLabelSetMap.put(j, fieldValueVector);
						} else {

							fieldValueVector.add(fieldLabelArray[j]
									.substring(1).trim());
							superNodeLabelSetMap.put(j, fieldValueVector);
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		//
		// BufferedWriter bw1 = new BufferedWriter(new FileWriter("Debug.txt",
		// true));
		//
		// for (Map.Entry<Integer, Vector<String>> entry : superNodeLabelSetMap
		// .entrySet()) {
		//
		// bw1.write(entry.getValue().toString());
		// bw1.write("\n");
		// }
		// bw1.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.print("\nFacetlabel vector: " +
		// facetLabelVector.toString());
		// System.out.print("\nSupernodemap : " +
		// superNodeLabelSetMap.toString());
		// System.exit(0);

		Map<String, Vector<String>> labelValueMap = new HashMap<String, Vector<String>>();

		for (Map.Entry<Integer, Vector<String>> entry : superNodeLabelSetMap
				.entrySet()) {

			try {

				labelValueMap.put(facetLabelVector.get(entry.getKey() - 2),
						entry.getValue());
				// System.out.print("\nKey: " + entry.getKey() + " Value: " +
				// entry.getValue() + "\n");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("\nException caught!");
			}
		}

		// System.out.print("\nPrinting label value map: \n" +
		// labelValueMap.keySet().toString());
		// System.exit(0);

		// superNodeLabelSetMap.clear();

		// System.out.print("\nPrinting labelvalue map:\n" +
		// labelValueMap.toString());

		// System.exit(0);

		try {

			BufferedWriter superNodeCSVWriter = new BufferedWriter(
					new FileWriter("Master.txt", true));

			for (int i = 0; i < masterLabelVector.size(); i++) {

				System.out.print("\nWriting field index: " + (i + 1)
						+ " Field: " + masterLabelVector.get(i));

				if (labelValueMap.containsKey(masterLabelVector.get(i))) {

					System.out.print(" True\n");
					superNodeCSVWriter.write(" ||-_-|| ");

					for (int j = 0; j < labelValueMap.get(
							masterLabelVector.get(i)).size(); j++) {
						superNodeCSVWriter.write("@RS@ "
								+ labelValueMap.get(masterLabelVector.get(i))
										.get(j) + " @RS@ ");
					}
					// superNodeCSVWriter.write(labelValueMap.get(masterLabelVector.get(i)).toString());
				} else {

					System.out.print(" False\n");
					superNodeCSVWriter.write(" ||-_-|| ");
					superNodeCSVWriter.write("NULL");
				}
			}

			superNodeCSVWriter.write("\n");
			superNodeCSVWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			BufferedWriter nodeLabelMappingWriter = new BufferedWriter(
					new FileWriter("Node_Map.txt", true));
			nodeLabelMappingWriter.write(csvFile);
			nodeLabelMappingWriter.write("\n");
			nodeLabelMappingWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
}