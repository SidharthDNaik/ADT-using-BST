//-----------------------------------------------------------------------------
// DictionaryInterface.java
// interface for the Dictionary ADT
//-----------------------------------------------------------------------------

public interface DictionaryInterface{

   // isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty();

   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size();

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key);

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException;

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException;

   // makeEmpty()
   // pre: none
   public void makeEmpty();

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString();
}
