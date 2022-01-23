public class Term {

    private String word;
    private String documentIn;
    private int frequency;
    private double tf_idf;

    public Term(String word, String doc, double tf_idf)
    {
        this.word = word;
        this.documentIn = doc;
        this.frequency = 1;
    }

    public void setTF_IDF(double newTf_idf)
    {
        this.tf_idf = newTf_idf;
    }

    public int getFrequency()
    { return this.frequency; }

    public void incrementFrequency()
    {
        this.frequency++;
    }

    public String getWord()
    { return this.word; }

    public double getTf_idf()
    { return tf_idf; }

    public String getDocumentIn()
    { return this.documentIn; }

}
