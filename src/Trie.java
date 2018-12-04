import java.util.*;
public class Trie<T> { 
    
    // Alphabet size (# of symbols) 
	public int size; 
	private TrieNode<T> root;
    // trie node 
    public Trie(){ 
    	this.root = new TrieNode<T>();
    	this.size = 0;
    }
       
//    static TrieNode root;  
      
    // If not present, inserts key into trie 
    // If the key is prefix of trie node,  
    // just marks leaf node 
    public void insert(String key, T value) 
    { 
        
    	HashMap<Character, TrieNode<T>> children = this.root.children;
        TrieNode<T> node = null;
        int length = key.length(); 
       
        for (int level = 0; level < length; level++) 
        { 
            char index = key.charAt(level); 
            if (!children.containsKey(index)) {
            	node = new TrieNode<T>(index);
            	children.put(index, node);
            }
            else {
            	node = children.get(index);
            }
                
            if(level == key.length()-1) {
            	node.value = value;
            }
            
            children = node.children; 
        } 
       
        this.size+=1;
    } 
       
    // Returns true if key presents in trie, else false 
    public T search(String key) 
    { 
        int level; 
        int length = key.length(); 
        char index; 
        HashMap<Character,TrieNode<T>> children = this.root.children;
        TrieNode<T> node = null;
        T result = null;
        
        for (level = 0; level < length; level++) 
        { 
            index = key.charAt(level); 
       
            if (!children.containsKey(index)) {
            	return null;
            }
            else {
            	node = children.get(index);
            }
                
            if(level == key.length()-1) {
            	result = node.value;
            }
            
            children = node.children;
        } 
       
        return result; 
    } 
}