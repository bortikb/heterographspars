import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class ParserMain {

	private static String[] splitBy(String line, String separator) {

		Vector<String> separatedFields = new Vector<String>();

		while (line.contains(separator)) {

			// System.out.print("\nSplitting: " +
			// line.substring(0,line.indexOf(separator)) + " " +
			// line.substring(0,line.indexOf(separator)).trim().length());

			if (line.substring(0, line.indexOf(separator)).trim().length() > 0) {

				separatedFields.add(line.substring(0, line.indexOf(separator))
						.trim());
			}

			line = line.substring(line.indexOf(separator) + separator.length())
					.trim();

		}

		separatedFields.add(line.trim());

		return separatedFields.toArray(new String[separatedFields.size()]);
	}

	public static void main(String[] args) {

		int i, j;
		String line = new String();
		String fieldSeparator = "||-_-||";
		String inFieldValueSeparator = "@RS@";
		Set<String> disallowedCommonElements = new HashSet<String>();

		disallowedCommonElements.add("NULL");
		disallowedCommonElements.add("NULL\"");

		for (i = 0; i < 469; i++) {

			Vector<Set<String>> filedValueContainerVector = new Vector<Set<String>>();
			Set<String> fieldValueSet = new HashSet<String>();
			
			System.out.print("\nProcessing field index number: " + new Integer(i+1).toString());

			try {
				BufferedReader br = new BufferedReader(new FileReader(
						"Master.txt"));

				while ((line = br.readLine()) != null) {

					// System.out.print("\nReading line: " + line);

					try{
					fieldValueSet = new HashSet<String>();

					String[] fieldValueArray = splitBy(line, fieldSeparator);

					// for(int index = 0; index<fieldValueArray.length;index++){
					//
					// System.out.print("\nNew: " + fieldValueArray[index]);
					//
					// }

					String fieldValue = fieldValueArray[i];

					if (fieldValue.trim().contains(inFieldValueSeparator)) {

						String[] fieldValues = splitBy(fieldValue,
								inFieldValueSeparator);

						for (j = 0; j < fieldValues.length; j++) {

							fieldValueSet.add(fieldValues[j].trim().contains(
									"NULL") ? "NULL" : fieldValues[j].trim());
						}
					} else {
						fieldValueSet.add(fieldValue);
					}

					filedValueContainerVector.add(fieldValueSet);
				
					}catch(Exception e){
						continue;
					}
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			System.out.print("\nThe vector is: "
//					+ filedValueContainerVector.toString());
			//System.exit(0);

			for (j = 0; j < filedValueContainerVector.size() - 1; j++) {

				Set<String> compareeSet = filedValueContainerVector.get(j);
//				System.out
//						.print("\nComparee Set is: " + compareeSet.toString());

				for (int k = j + 1; k < filedValueContainerVector.size(); k++) {

					Set<String> comparedSet = filedValueContainerVector.get(k);

//					System.out.print("\nCompared Set is: "
//							+ comparedSet.toString());
					// Find intersection
					Set<String> commonElements = new HashSet<String>(
							compareeSet);
					commonElements.retainAll(comparedSet);
					commonElements.removeAll(disallowedCommonElements);
					commonElements.removeAll(Arrays.asList("", null));

//					System.out.print("\nCommon elements Set is: "
//							+ commonElements.toString());

					// Find union
					comparedSet.addAll(compareeSet);
					comparedSet.removeAll(disallowedCommonElements);
					comparedSet.removeAll(Arrays.asList("", null));

//					System.out.print("\nUnion elements Set is: "
//							+ comparedSet.toString());

					if (comparedSet.size() != 0 && commonElements.size() != 0) {

						System.out.print("\nInside write");
						double jaccardSimilarity = commonElements.size()
								/ comparedSet.size();

						String edgeListEntry = new String(
								new Integer(i + 2).toString()
										+ " "
										+ new Integer(j + 1).toString()
										+ " "
										+ new Integer(k + 1).toString()
										+ " "
										+ commonElements.toString()
										+ " "
										+ new Double(jaccardSimilarity)
												.toString());

						try {
							BufferedWriter bw = new BufferedWriter(
									new FileWriter("edge_list.txt", true));
							bw.write(edgeListEntry + "\n");
							bw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

			}

		}

	}

}
