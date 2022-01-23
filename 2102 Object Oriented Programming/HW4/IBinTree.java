import java.lang.Math;
import java.util.LinkedList;

interface IBinTree {
 // determines whether element is in the tree
 boolean hasElt(int e);
 // returns number of nodes in the tree; counts duplicate elements as separate items
 int size();
 // returns depth of longest branch in the tree
 int height();

 //Student Added Methods
 /**
  * Tests if our object is a valid heap.
  * @return True if it is valid.
  */
 boolean isValidHeap();

 boolean isBigger(int e);

 /**
  * Produces a list of all elements in a BT.
  * @return The list of all elements.
  */
 LinkedList<Integer> listElements();
}

class MtBT implements IBinTree {
 MtBT(){}

 // returns false since empty tree has no elements
 public boolean hasElt(int e) {
  return false;
 }

 // returns 0 since enpty tree has no elements
 public int size() {
  return 0;
 }

 // returns 0 since empty tree has no branches
 public int height() {
  return 0;
 }


 //Student Added Methods

 // Empty is a heap
 public boolean isValidHeap() {return true;};

 // Should always lose a competition of size
 public boolean isBigger(int e) {
  return true;
 }

 // Has no elements so it produces an empty list.
 public LinkedList<Integer> listElements() {return new LinkedList<Integer>();}
}

class DataBT implements IBinTree {
 int data;
 IBinTree left;
 IBinTree right;

 DataBT(int data, IBinTree left, IBinTree right) {
  this.data = data;
  this.left = left;
  this.right = right;
 }
 
 // an alternate constructor for when both subtrees are empty
 DataBT(int data) {
   this.data = data;
   this.left = new MtBT();
   this.right = new MtBT();
 }

 // determines whether this node or node in subtree has given element
 public boolean hasElt(int e) {
  return this.data == e || this.left.hasElt(e) || this.right.hasElt(e) ;
 }

 // adds 1 to the number of nodes in the left and right subtrees
 public int size() {
  return 1 + this.left.size() + this.right.size();
 }

 // adds 1 to the height of the taller subtree
 public int height() {
  return 1 + Math.max(this.left.height(), this.right.height());
 }

 // check if this data is bigger than a number
 public boolean isBigger(int e) {
  return (this.data >= e);
 }

 public boolean isValidHeap() {
  if(this.left.isBigger(this.data) && this.right.isBigger(this.data) && this.left.isValidHeap() && this.right.isValidHeap()) {
   return true;
  }
  else {
   return false;
  }
 }

 public LinkedList<Integer> listElements() {
  LinkedList<Integer> list = new LinkedList<Integer>();
  list.add(this.data);
  for(int i = 0; i < this.left.listElements().size(); i++) {
   list.add(this.left.listElements().get(i));
  }
  for(int i = 0; i < this.right.listElements().size(); i++) {
   list.add(this.right.listElements().get(i));
  }
  return list;
 }
}