import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.Merge;

public class BinarySearchDeluxe {

    public static <Key> int firstIndexOf (Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
        	throw new NullPointerException();
        }
        
        int lo = 0, hi = a.length-1;
        while (lo + 1 < hi) {
        	int mid = lo + (hi-lo)/2;
        	if (comparator.compare(a[mid], key) >= 0) {
        		hi = mid;
        	}
        	else {
        		lo = mid +1;
        	}
        	
        }
        
        if (comparator.compare(a[lo], key) == 0) {
        	return lo;
        }
        if (comparator.compare(a[hi], key) == 0) {
        	return hi;
        }
        
        return -1;      
    }
    
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) {
        	throw new NullPointerException();
        }
    	
    	int lo = 0, hi = a.length-1;
        while (lo + 1 < hi) {
        	int mid = lo + (hi-lo)/2;
        	if (comparator.compare(a[mid], key) <= 0) {
        		lo = mid;
        	}
        	else {
        		hi = mid - 1;
        	}
        	
        }
        
        if (comparator.compare(a[hi], key) == 0) {
        	return hi;
        }
        if (comparator.compare(a[lo], key) == 0) {
        	return lo;
        }
        
        return -1;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	if (args == null) {
    		throw new NullPointerException();
    	}       
    }

}
