import java.util.*;

public class TrieNode<T>{
	char key;
	T value;
	HashMap<Character, TrieNode<T>> children;
	
	public TrieNode(char key,T value){
		this.key = key;
		this.value = value;
		this.children = new HashMap<Character,TrieNode<T>>();
	}
	public TrieNode(char key){
		this.key = key;
		this.children = new HashMap<Character,TrieNode<T>>();
	}
	public TrieNode(){
		this.children = new HashMap<Character,TrieNode<T>>();
	}
	
}