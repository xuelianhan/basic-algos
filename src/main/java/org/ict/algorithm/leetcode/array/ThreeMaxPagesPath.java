package org.ict.algorithm.leetcode.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ict.algorithm.util.MapUtil;

/**
 * Amazon written test
 *
 */

/**
 * U1,/
 * U1,subscribers
 * U2,/
 * U2,subscribers
 * U1,filter
 * U1,export
 * U2,filter
 * U2,export
 * U3,/
 * u3,subscribers 
 * U3,filter
 * U3,export
 * U4,/
 * U4,subscribers 
 * U4,filter
 * U5,/
 * U5,subscribers 
 * U5,filter
 * The most frequent 3 page sequence:
 * results:
 * /-->subscribers-->filter
 * subscribers-->filter-->export
 * 
 * @author hanxuelian
 *
 */
public class ThreeMaxPagesPath {
	
	public static File createRandomAccessLog() {
		File f = new File("/home/hanxuelian/Desktop/test-access.log");
		if (!f.exists()) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				f.createNewFile();
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				
				for (int i = 0; i < 100000; i++) {
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null) {
						bw.close();
					}
					if (fw != null) {
						fw.close();
					}
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return f;
	}
	
	public static Map<String, Integer> findThreeMaxPagesPathV1(String file, String separator, int depth) {
		Map<String, Integer> pageVisitCounts = new HashMap<String, Integer>();
		if (file == null || "".equals(file)) {
			return pageVisitCounts;
		}
		try {
			File f = new File(file);
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			
			Map<String, List<String>> userUrls = new HashMap<String, List<String>>();
			String currentLine = "";
			while ((currentLine = bf.readLine()) != null) {
				String[] lineArr = currentLine.split(separator);
				if (lineArr == null || lineArr.length != (depth - 1)) {
					continue;
				}
				String user = lineArr[0];
				String page = lineArr[1];
				List<String> urlLinkedList = null;
				if (userUrls.get(user) == null) {
					urlLinkedList = new LinkedList<String>();
				} else {
					urlLinkedList = userUrls.get(user);
					String pages = "";
					if (urlLinkedList.size() == (depth - 1)) {
						pages = urlLinkedList.get(0).trim() + separator + urlLinkedList.get(1).trim() + separator + page;
					} else if (urlLinkedList.size() > (depth - 1)) {
						urlLinkedList.remove(0);
						pages = urlLinkedList.get(0).trim() + separator + urlLinkedList.get(1).trim() + separator + page;
					}
					if (!"".equals(pages) && null != pages) {
						Integer count = (pageVisitCounts.get(pages) == null ? 0 : pageVisitCounts.get(pages))  + 1;
					    pageVisitCounts.put(pages, count);
					}
				}
				urlLinkedList.add(page);
				System.out.println("user:" + user + ", urlLinkedList:" + urlLinkedList);
				userUrls.put(user, urlLinkedList);
			}
		    bf.close();
		    fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageVisitCounts;
	}
	
	public static void main(String[] args) {
		String file = "/home/hanxuelian/Desktop/test-access.log";
		String separator = ",";
		Map<String, Integer> pageVisitCounts = findThreeMaxPagesPathV1(file, separator, 3);
		System.out.println(pageVisitCounts.size());
		Map<String, Integer>  result = MapUtil.sortByValueDescendOrder(pageVisitCounts);
		System.out.println(result);
	}
}
