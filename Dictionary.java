/*
* Name: Sidharth Naik
* ID: 1647945
* Class: 12B/M
* Date: March 11,2018
* Description: This is the Dictionary ADT with all the ADT methods
* File Name: Dictionary.java
*/
public class Dictionary implements DictionaryInterface{
    // private inner Node class
    private class Node {
        Pair pair = new Pair();
        Node left;
        Node right;

        Node(String key, String value){
            pair.setKey(key);
            pair.setValue(value);
            left = null;
            right = null;
        }

        Pair getPair(){
            return pair;
        }

    }

    // private inner pair class
    private class Pair {
        String key;
        String value;

        Pair(){
            this.key = " ";
            this.value = " ";
        }

        Pair(String key, String value){
            this.key = key;
            this.value = value;
        }

        String getKey(){
            return key;
        }

        String getValue(){
            return value;
        }

        void setKey(String key){
            this.key = key;
        }

        void setValue(String value){
            this.value = value;
        }

    }

    // Fields for the Dictionary class
    private Node root;     // reference to first Node in List
    private int numPairs; // number of items in the Dictionary

    // Dicationary()
    // constructor for the Dictionary class
    public Dictionary(){
        root = null;
        numPairs = 0;
    }

    // private helper function -------------------------------------------------

    // findKey()
    // returns a reference to the Node with the value key
    private Node findKey(Node R, String key){
      if(R == null || (R.getPair().getKey().compareTo(key) == 0)){
        return R;
      }
      if(key.compareTo(R.getPair().getKey()) < 0){
        return findKey(R.left, key);
      }
      else{
        return findKey(R.right, key);
      }
    }

    // findPrevKey()
    // returns a refernce to the node before the specific key value
    private Node findParent(Node N, Node R){
      Node P = null;
      if( N!= R ){
        P = R;
        while(P.left!= N && P.right!=N){
          if((N.getPair().getKey().compareTo(P.getPair().getKey())) < 0){
            P = P.left;
          } else {
            P = P.right;
          }
        }
      }
        return P;
    }

    private Node findLeftmost(Node R){
      Node L = R;
      if (L!=null){ for(; L.left != null; L = L.left){}}
      return L;
    }

    // ADT operations ----------------------------------------------------------

    // isEmpty()
    // pre: none
    // post: returns true if the Dictionary is empty, false otherwise
    public boolean isEmpty(){
        return(numPairs == 0);
    }

    // size()
    // pre: none
    // post: returns the number of elements in the Dictionary
    public int size() {
        return numPairs;
    }

    // lookup()
    // pre:
    // post: returns value for specific key
    public String lookup(String key) {
        Node N = findKey(root, key);
        if( N == null ){
            return null;
        }
        else{
            return N.getPair().getValue();
        }
    }

    // insert()
    // inserts new item with a key and value
    // pre: Dictionary does not currenlty contain the argument key
    // post: item is in the list
    public void insert(String key, String value) throws DuplicateKeyException {
        Node N, B, A;
        if ( lookup(key) != null ) {
            throw new DuplicateKeyException(
                    "cannot insert duplicate keys ");
        }
        N = new Node(key, value);
        B = null;
        A = root;
        while( A!= null){
            B = A;
            if((key.compareTo(A.getPair().getKey()) < 0)){
              A = A.left;
            } else {
              A = A.right;
            }
          }
          if( B==null ){
            root = N;
          } else if((key.compareTo(B.getPair().getKey()) < 0)) {
            B.left = N;
          } else {
            B.right = N;
          }
          numPairs++;
    }

    // delete()
    // deletes item with key value
    // pre: key exists
    // post: item is deleted
    public void delete(String key) throws KeyNotFoundException {
        Node N, P, S;
        N = findKey(root, key);
        if( lookup(key) == null ){
            throw new KeyNotFoundException(
                    "cannot delete non-existent key ");
        }
        if( N.left == null && N.right == null){
          if( N == root ){
            root = null;
          } else {
            P = findParent(N, root);
            if( P.right == N ){
              P.right = null;
            } else{
              P.left = null;
            }
          }
        } else if(N.right == null ){
          if(N == root){
            root = N.left;
          } else {
            P = findParent(N, root);
            if(P.right == N){
              P.right = N.left;
            } else {
              P.left = null;
            }
          }
        } else if( N.left == null ){
          if( N == root ){
            root = N.right;
          } else {
            P = findParent(N, root);
            if(P.right == N){
              P.right = N.right;
            } else {
              P.left = N.right;
            }
        }
      } else {
          S = findLeftmost(N.right);
          N.getPair().setKey(S.getPair().getKey());
          N.getPair().setValue(S.getPair().getValue());
          P = findParent(S,N);
          if(P.right == S){
            P.right = S.right;
          } else {
            P.left = S.right;
          }
        }
        numPairs--;
    }

    // MakeEmpty()
    // pre: none
    // post: isEmpty()
    public void makeEmpty(){
        root = null;
        numPairs = 0;
    }

    // toString()
    // pre: none
    // post:  prints current state to stdout
    // Overrides Object's toString() method
    public String toString(){
        return printInOrder(root);
    }

    private String printInOrder(Node R){
        String N = "";
      if (R == null){
          return "";
        }
      N += printInOrder(R.left);
      N += (R.getPair().getKey() + " " + R.getPair().getValue() + "\n");
      N += printInOrder(R.right);
      return N;
    }
}
