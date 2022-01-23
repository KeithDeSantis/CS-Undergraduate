public class Documents {

    private String name;
    private LinearProbingHashST<String, Term>[] tables;
    private BST<String, Term>[] bsts;

    public Documents(String name)
    {
        this.name = name;
        this.tables = new LinearProbingHashST[14];
    }

    public LinearProbingHashST[] getTables()
    { return this.tables; }

    public String getString()
    { return this.name; }

    public void setTables(LinearProbingHashST[] HTS)
    { this.tables = HTS; }

    public void addTable(LinearProbingHashST HT, int i)
    { this.tables[i] = HT; }

    public LinearProbingHashST[] search(String key) // FIGURE OUT HOW TO LOOK AT HT's
    {
        boolean found = false;
        int numOfDocWithWord = 0;
        LinearProbingHashST[] returnedArray = new LinearProbingHashST[14];
        for(LinearProbingHashST currentHT : this.tables)
        {
            if(currentHT.contains(key))
            {
                returnedArray[numOfDocWithWord] = currentHT;
                numOfDocWithWord++;
                Term val = (Term) currentHT.get(key);
                found = true;
                System.out.println(key + " found in " + val.getDocumentIn() + " " + val.getFrequency() + " time[s], with a TF-IDF of " + val.getTf_idf());
            }
        }
        if(!found) System.out.println("No such terms found.");
        return returnedArray;
    }

    public Term[] top10(String doc)
    {
        Term[] top10 = new Term[10];
        LinearProbingHashST<String, Term> HT = new LinearProbingHashST<String, Term>(0, "fake");
        for(LinearProbingHashST currentHT : this.tables)
        { if (currentHT.getName() == doc) HT = currentHT; } // Get the right HT
        for(int i = 0; i < 10; i++) //Find the relative max 10 times
        {
            top10[i] = HT.getMaxTFIDF(top10);
            System.out.println("In " + (i+1) + " place '" + top10[i].getWord() + "' with TF-IDF of " + top10[i].getTf_idf());
        }
        return top10;
    }

    public BST[] getBSTs()
    { return this.bsts; }

    public void setTables(BST[] BTSs)
    { this.bsts = BTSs; }

    public void addTable(BST BST, int i)
    { this.bsts[i] = BST; }

    public BST[] searchBSTs(String key) // FIGURE OUT HOW TO LOOK AT HT's
    {
        int numOfDocWithWord = 0;
        boolean found = false;
        BST[] returnedArray = new BST[14];
        for(BST currentBST : this.bsts)
        {
            if(currentBST.contains(key))
            {
                returnedArray[numOfDocWithWord] = currentBST;
                numOfDocWithWord++;
                Term val = (Term) currentBST.get(key);
                found = true;
                System.out.println(key + " found in " + val.getDocumentIn() + " " + val.getFrequency() + " time[s], with a TF-IDF of " + val.getTf_idf());
            }
        }
        if(!found) System.out.println("No such word found.");
        return returnedArray;
    }

    public Term[] top10BSTs(String doc)
    {
        Term[] top10 = new Term[10];
        BST<String, Term> BST = new BST<String, Term>("fake");
        for(BST currentBST : this.bsts)
        { if (currentBST.getName() == doc) BST = currentBST; } // Get the right HT
        for(int i = 0; i < 10; i++) //Find the relative max 10 times
        {
            top10[i] = BST.getMaxTFIDF(top10);
            System.out.println("In " + (i+1) + " place '" + top10[i].getWord() + "' with TF-IDF of " + top10[i].getTf_idf());
        }
        return top10;
    }


    public LinearProbingHashST[] searchNoPrint(String key) // FIGURE OUT HOW TO LOOK AT HT's
    {
        int numOfDocWithWord = 0;
        LinearProbingHashST[] returnedArray = new LinearProbingHashST[14];
        for(LinearProbingHashST currentHT : this.tables)
        {
            if(currentHT.contains(key))
            {
                returnedArray[numOfDocWithWord] = currentHT;
                numOfDocWithWord++;
                Term val = (Term) currentHT.get(key);
            }
        }
        return returnedArray;
    }

    public BST[] searchBSTsNoPrint(String key) // FIGURE OUT HOW TO LOOK AT HT's
    {
        int numOfDocWithWord = 0;
        BST[] returnedArray = new BST[14];
        for(BST currentBST : this.bsts)
        {
            if(currentBST.contains(key))
            {
                returnedArray[numOfDocWithWord] = currentBST;
                numOfDocWithWord++;
                Term val = (Term) currentBST.get(key);
            }
        }
        return returnedArray;
    }


}
