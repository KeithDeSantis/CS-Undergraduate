public class ST<Key, Value> {

   private int N;
   private int M = 160;
   public Key[] keys;
   private Value[] vals;

    public ST()
    {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
   public ST(int cap)
   {
       keys = (Key[]) new Object[M];
       vals = (Value[]) new Object[M];
       M = cap;
   }

   private int hash(Key key)
   { return (key.hashCode() & 0x7fffffff) % M; }

   private void resize(int cap)
   {
       ST<Key, Value> t;
       t = new ST<Key, Value>(cap);
       for (int i = 0; i < M; i++)
           if (keys[i] != null)
               t.put(keys[i], vals[i]);
       keys = t.keys;
       vals = t.vals;
       M = t.M;
   }

   public void put(Key key, Value val)
   {
       if (N >= M/2) resize(2*M);

       int i;
       for (i = hash(key); keys[i] != null; i = (i + 1) % M)
           if (keys[i].equals(key)) { vals[i] = val; return; }
       keys[i] = key;
       vals[i] = val;
       N++;
   }

   public Value get(Key key)
   {
       for (int i = hash(key); keys[i] != null; i = (i +1) % M)
           if (keys[i].equals(key))
               return vals[i];
       return null;
   }

    public int size() { return N; }

    public boolean contains(Key key)
    {
        for(Key current : keys)
        if(key.equals(current))
            return true;
        return false;
    }
}
