import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Term implements Comparable<Term>{

    //Term term;
    long weight;
    String query;
    
    public Term(String query, long weight) {
        //Initializes a term with given string and weight
        if (query == null) {
            throw new NullPointerException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        
        this.weight = weight;
        this.query = query;
    }
    
    public static Comparator<Term> byReverseWeightOrder(){
        
        return new Comparator<Term>() {
            public int compare(Term term1, Term term2) {
                if (term1.getWeight() > term2.getWeight()) {
                    return -1;
                }
                else if (term1.getWeight() < term2.getWeight()) {
                    return 1;
                }
                else {
                    return 0;
                }
                
            }
        };
        
    }
    
    public static Comparator<Term> byPrefixOrder(int r){
    	if (r < 0) {
    		throw new IllegalArgumentException();
    	}
    	final int rr = r;
        return new Comparator<Term>() {
        	
            public int compare(Term term1, Term term2) {
            	
            	int len = Math.min(rr,Math.min(term1.getQuery().length(),term2.getQuery().length()));
            	String term1Query = term1.getQuery().substring(0,len);
            	String term2Query = term2.getQuery().substring(0,len);
            	return term1Query.compareTo(term2Query);
            }	
        };	
    }
    
    public long getWeight() {
        return weight;
    }
    
    public String getQuery() {
        return query;
    }
    
    public int compareTo(Term that) {      
    	return this.query.compareTo(that.getQuery());
    }
    
    public String toString() {
        //returns a string representation of the term in format: weight, followed by a tab, followed by the query
        return weight + "\t" + query;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Annika\\eclipse-workspace\\ProgrammingAssignment2\\wiktionary.txt");
        ArrayList<Term> termArray = new ArrayList<Term>();
        Scanner scan = new Scanner(file);
        scan.nextLine();
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] term = line.split("\t");
            String mainQuery = term[1];
            String weightString = term[0];
            weightString = weightString.trim();
            long mainWeight = Long.parseLong(weightString,10);
            Term newTerm = new Term(mainQuery, mainWeight);
            termArray.add(newTerm);
        }
        scan.close();
    }

}
