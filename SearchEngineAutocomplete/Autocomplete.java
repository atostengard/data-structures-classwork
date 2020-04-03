//header?

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.MergeX;

public class Autocomplete {

    static Term[] terms;
    
    static BinarySearchDeluxe binarySearch = new BinarySearchDeluxe();
    
    public Autocomplete(Term[] terms) {
        this.terms = terms;
        MergeX.sort(terms); 
        
    }
    
    public static Term[] allMatches(String prefix) {
       
        
        int numberOfMatches = numberOfMatches(prefix);
        
	        Term term = new Term(prefix, 0);
	        int firstIndex = binarySearch.firstIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
	        int lastIndex = binarySearch.lastIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
	        
	        Term[] results;
	        if (firstIndex == -1) {
	        	results = new Term[0];
	        	return results;
	        }
	        results = new Term[lastIndex-firstIndex+1];
	        for (int i = firstIndex; i <= lastIndex; i++) {
	        	results[i-firstIndex] = terms[i];
	        }
	        
	        Arrays.sort(results, Term.byReverseWeightOrder());
	        
        return results;
    }
    
    public static int numberOfMatches (String prefix) {
    	Term term = new Term(prefix, 0);
        int firstIndex = binarySearch.firstIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
        int lastIndex = binarySearch.lastIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
        return lastIndex-firstIndex+1;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        System.out.println(N);
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
        	
           String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}
