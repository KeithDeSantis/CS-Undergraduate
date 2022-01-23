import java.util.Random;

public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private String name;

    public BST(String name)
    {
        this.name = name;
    }

    public class Node
    {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; }

        public int size()
        { return this.N; }

        public Key getKey() { return this.key; }
    }

    public int size()
    { return size(root); }

    private int size(Node x)
    {
        if(x==null) return 0;
        else return x.N;
    }

    public Term get(Key key) // Return null if key not present
    { return get(root, key); }

    private Term get(Node x, Key key) // Return null if key not present
    {
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0) return get(x.left, key);
        else if (cmp>0) return get(x.right,key);
        else return (Term) x.val;
    }

    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val)
    {
        // Change key's value to val if key in subtree rooted at x
        if(x==null)
        {
            this.root = new Node(key, val, 1); //TODO KEITH ADJUSTED THIS PART FROM THE BOOK IMPLEMENTATION
            return root;
        }
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = put(x.left, key, val);
        else if (cmp>0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key word)
    { return contains(root, word); }

    private boolean contains(Node x, Key word)
    {
        if(x==null) return false;
        int cmp = word.compareTo(x.key);
        if (cmp<0) return contains(x.left, word);
        else if (cmp>0) return contains(x.right, word);
        else return true;
    }

    public String getName()
    { return this.name; }

    public Term getMaxTFIDF(Term[] notAllowed)
    {
        //Get left max, Get right max, get current node, compare the three
        return getMaxTFIDF(root, notAllowed);
    }

    private Term getMaxTFIDF(Node x, Term[] notAllowed)
    {
        Term[] tfidfs = new Term[3];

        if(x.left == null)
        { tfidfs[0] = null; }
        else {
                Term leftMax = getMaxTFIDF(x.left, notAllowed);
                tfidfs[0] = leftMax;
            }
        if(x.right == null)
        { tfidfs[1] = null; }
        else {
            Term rightMax = getMaxTFIDF(x.right, notAllowed);
            tfidfs[1] = rightMax;
        }

        Term currentValue = (Term) x.val;
        boolean isAllowed = true;
        for(Term t : notAllowed)
        {
            if(t == null) break;
            if(currentValue.getWord() == t.getWord()) isAllowed = false;
        }
        if(isAllowed) tfidfs[2] = currentValue;

        Term maxTerm = new Term("placeholder", "no", 0);
        for(Term currentTerm : tfidfs)
        {
            if(currentTerm!=null) {
                if (currentTerm.getTf_idf() >= maxTerm.getTf_idf()) maxTerm = currentTerm;
            }
        }
        return maxTerm;
    }

    public void updateTFIDFs(Node x, BST allBST)
    {
        String currentWord = (String) x.key;

        double tfidf = ((Term) x.val).getFrequency() * Math.log(14.0/(double) allBST.get(currentWord).getFrequency());
        ((Term) x.val).setTF_IDF(tfidf);

        if(x.left != null) updateTFIDFs(x.left, allBST);
        if(x.right != null) updateTFIDFs(x.right, allBST);

        return;
    }

    public Node getRoot()
    { return root; }

    public Node getRandomNode(Node x)
    {
        int r = new Random().nextInt(x.size());
        if(r==0) return x;
        else if (x.left != null && 1<=r && r<=x.left.size()) return getRandomNode(x.left);
        else  return getRandomNode(x.right);
    }
}
