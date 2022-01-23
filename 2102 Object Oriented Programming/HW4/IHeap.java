import java.util.Random;

interface IHeap extends IBinTree {
 // adds given element to the heap without removing other elements
 IHeap addElt(int e);
 
 // removes one occurrence of the smallest element from the heap
 IHeap remMinElt();
 
 // Merge the current heap with another heap
 IHeap merge(IHeap withHeap);
 
 // Determine if the root of this heap is bigger than the given element
 boolean isBigger(int e);
}

class MtHeap extends MtBT implements IHeap {
 MtHeap(){}

 // An MtHeap is valid as it has no values to break the invariant
 public boolean isValidHeap() {
  return true;
 }

 @Override
 // If you add an element to a MtHeap, you create a new DataHeap
 public IHeap addElt(int e) {
  return new DataHeap(e, new MtHeap(), new MtHeap());
 }

 @Override
 // The min element of an empty heap returns an empty heap.
 public IHeap remMinElt() {
  return new MtHeap();
 }

 @Override
 // An empty heap should always lose a competition for which root is smaller (for the merge function)
 public boolean isBigger(int e) {
  return true;
 }

 @Override
 // An empty heap merged with another heap is the other heap (identity).
 public IHeap merge(IHeap withHeap) {
  return withHeap;
 }
}

class DataHeap extends DataBT implements IHeap {
 IHeap left;
 IHeap right;

 DataHeap(int data, IHeap left, IHeap right) {
  super (data, left, right);
  this.left = left;
  this.right = right;
 }

  // an alternate constructor for when both subheaps are empty
 DataHeap(int data) {
   super(data);
   this.left = new MtHeap();
   this.right = new MtHeap();
 }
 
 public IHeap merge(IHeap withHeap) {
  int newRoot;
  IHeap H1, H2, H3;

  // make sure withHeap isn't empty
  // we'd rather not use instanceOf here, but it makes the merge algorithm easier to see
  if (withHeap instanceof MtHeap) { return this; }
  // determine the new root value and the three subtrees to consider merging
  else if (withHeap.isBigger(this.data)) {
   newRoot = this.data;
   H1 = this.left;
   H2 = this.right;
   H3 = withHeap;
  } else {
   // Since a MtHeap will always return true on isBigger, satisfying
   //  the first condition, we know that withHeap is a DataHeap.
   //  Therefore, we can cast it.
   newRoot = ((DataHeap) withHeap).data;
   H1 = ((DataHeap) withHeap).left;
   H2 = ((DataHeap) withHeap).right;
   H3 = this;
  }
  
  // choose which trees to merge and construct the new tree
  if (H1.height() > H2.height() && H1.height() > H3.height()) {
   return new DataHeap (newRoot, H1, H2.merge (H3));
  } else if (H2.height() > H1.height() && H2.height() > H3.height()) {
   return new DataHeap (newRoot, H2, H1.merge (H3));
  } else if (H3.height() > H1.height() && H3.height() > H2.height()){
   return new DataHeap (newRoot, H3, H1.merge (H2));
  } else {
   // If the two bigger heaps are of the same size, choose one at random.
   Random coinFlip = new Random();
   if (H1.height() == H2.height()) {
    if (coinFlip.nextInt() % 2 == 1) {
     return new DataHeap (newRoot, H1, H2.merge(H3));
    } else {
     return new DataHeap (newRoot, H2, H1.merge(H3));
    }
   } else if (H2.height() == H3.height()) {
    if (coinFlip.nextInt() % 2 == 1) {
     return new DataHeap (newRoot, H2, H3.merge(H1));
    } else {
     return new DataHeap (newRoot, H3, H2.merge(H1));
    }
   } else {
    if (coinFlip.nextInt() %2 == 1) {
     return new DataHeap (newRoot, H3, H1.merge(H2));
    } else {
     return new DataHeap (newRoot, H1, H3.merge(H2));
    }
   }
  }
 }


 @Override
 public IHeap addElt(int e) {
  return this.merge(new DataHeap(e, new MtHeap(), new MtHeap()));
 }

 @Override
 public IHeap remMinElt() {
  return this.right.merge(this.left);
 }

 @Override
 public boolean isBigger(int e) {
  return (this.data >= e);
 }
}
