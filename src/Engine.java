
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Engine {	
	
	private Trie<ArrayList<Integer>> trie;
	private String [] totalpages;
	
	public Engine(String filename) {
		this.trie = new Trie<ArrayList<Integer>>();
		HashSet<String> filedata = readfile(filename);
		this.totalpages = filedata.toArray(new String[0]);
		filedata = null;
		String text;
		String word;
		String[] words;
		Iterator<String> iter = null;
		for (int pageindex = 0; pageindex< this.totalpages.length; ++pageindex) {
			try {
				text = readUrl(this.totalpages[pageindex]);
				text = text.toLowerCase();
				words = text.split(" ");
				ArrayList<String> wordsafterremovingstopwords = new ArrayList<>();
				for(String w : words) {
					if(!Stopwords.Isstopword(w)) {
						wordsafterremovingstopwords.add(w);
					}
				}
				filedata = new HashSet<String>(wordsafterremovingstopwords);
//				temp.removeAll(Stopwords); // remove all stop words from the page
				
				iter = filedata.iterator();
				while(iter.hasNext()) {
					word = (String) iter.next();
					ArrayList<Integer> array = this.trie.search(word);
					if (array == null) {
						// insert a new word referencing the current page
						this.trie.insert(word, new ArrayList<Integer>(Arrays.asList(pageindex)));
					} else {
						array.add(pageindex);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String readUrl(String path) throws IOException {
		// TODO Auto-generated method stub
		Document document = Jsoup.connect(path).get();
		String text = document.body().text();
		return text;
	}

	private HashSet<String> readfile(String filename){
		HashSet<String> hash = new HashSet<String>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				hash.add(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	public String[] search (String[] query) {
		int[] votes = new int[this.totalpages.length];
		ArrayList<Integer> temp = null;
		for (int i = 0; i < query.length; ++i) {
			temp = this.trie.search(query[i].toLowerCase().trim());
			if (temp != null) {
				for (int k = 0; k < temp.size(); k++) {
					votes[temp.get(k)]++;
				}
			} else {
				System.out.println("The word '"+ query[i] + "' is not in any file!");
				return null;
			}
		}
		
		// answers stores the indexes of the pages
		ArrayList<String> pages = new ArrayList<String>();
		for (int p = 0; p < votes.length; ++p) {
			if (votes[p] == query.length) {
//				System.out.println(p);
				pages.add(this.totalpages[p]);
			}
		}

		return pages.toArray(new String[0]);
	}
	
}
