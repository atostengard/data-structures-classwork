import edu.princeton.cs.algs4.TrieST;

public class SuffixTrie {
	
	public static void main(String[] args) {
		TrieST suffixTrie = new TrieST();
		In S = new In(args[0]);
		In Q = new In(args[1]);
		String wholeText = S.readString();
		String suffix = "";
		for (int i = wholeText.length()-1; i >= 0; i--) {
			char charToAdd = wholeText.charAt(i);
			suffix = charToAdd + suffix;
			suffixTrie.put(suffix, i);
		}
		while(Q.hasNextLine()) {
			String query = Q.readLine();
			Iterable<String> keysWithPrefixes = suffixTrie.keysWithPrefix(query);
			for (String q : (Iterable<String>)keysWithPrefixes) {
				int suffixPosition = ((int)suffixTrie.get(q)+1);
				StdOut.println("Query " + query + " is in the string at position " + suffixPosition + ".");
			}
			if (!keysWithPrefixes.iterator().hasNext()) {
				StdOut.println("Query " + query + " is not in the string.");
			}
		}
	}
}
