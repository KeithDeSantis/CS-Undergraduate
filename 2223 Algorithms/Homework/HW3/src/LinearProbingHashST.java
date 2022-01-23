public class LinearProbingHashST<Key, Value> {

    private int N;          // number of key-value pairs in the table
    private int M; // = 16; // size of linear-probing table
    private Key[] keys;     // the keys
    private Value[] vals;   // the values
    private String name;

    public LinearProbingHashST(int cap, String name) {
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
        this.name = name;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap)
    {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap, this.name);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val)
    {
        if (N >=M/2) resize(2*M); // double M
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public boolean contains(Key word)
    {
        for(Key k : keys)
        {
            if(k != null) { // Don't attempt to see if null is equal so something (avoid null pointer)
                if (((String) k).equals(word))
                    return true;
            }
        }
        return false;
    }

    public Value[] getVals()
    { return vals; }

    public Key getKey(int index)
    { return keys[index]; }

    public String getName()
    { return this.name; }

    public Term getMaxTFIDF(Term[] notAllowed) // Used to get one max at a time
    {
        Term max = new Term("FILENAME NOT FOUND OH NO NO NO NO BAD BAD BAD (please check your spelling, all lowercase with hyphens between words)", "none", 0);
        for(Value v : this.vals)
        {
            if(v !=null) {
                Term term = (Term) v;
                if (term.getTf_idf() > max.getTf_idf()) {
                    boolean isAllowed = true;
                    for (Term t : notAllowed) {
                        if (t == null) break;
                        if (term.getWord() == t.getWord()) isAllowed = false;
                    }
                    if (isAllowed) max = term;
                }
            }
        }
        return max;
    }

}
