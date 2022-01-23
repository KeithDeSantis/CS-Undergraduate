import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("\nWelcome to the Beta for Google-But-Better-Incorporated's first search engine!  Hope you enjoy.\n\n");

        //TODO Task Set 1 Numbers 1-9)

        System.out.println("Initializing Linear Probing Hash Symbol Table implementation...\n");

        Stopwatch constructionTimerHT = new Stopwatch();

        File readDirectory = new File("input");
        File[] filesList = readDirectory.listFiles();
        LinearProbingHashST<String, Term> HTBustopher_Jones_The_Cat_About_Town = new LinearProbingHashST<String, Term>(16, "bustopher-jones-the-cat-about-town");
        LinearProbingHashST<String, Term> HTGrowlTigers_Last_Stand = new LinearProbingHashST<String, Term>(16, "growltigers-last-stand");
        LinearProbingHashST<String, Term> HTGus_The_Theather_Cat = new LinearProbingHashST<String, Term>(16, "gus-the-theater-cat");
        LinearProbingHashST<String, Term> HTMacavity_The_Mystery_Cat = new LinearProbingHashST<String, Term>(16, "macavity-the-mystery-cat");
        LinearProbingHashST<String, Term> HTMr_Mistoffelees = new LinearProbingHashST<String, Term>(16, "mr-mistoffelees");
        LinearProbingHashST<String, Term> HTMungojerrie_And_Rumpelteazer = new LinearProbingHashST<String, Term>(16, "mungojerrie-and-rumpelteazer");
        LinearProbingHashST<String, Term> HTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles = new LinearProbingHashST<String, Term>(16, "of-the-awefull-battle-of-the-pekes-and-the-pollicles");
        LinearProbingHashST<String, Term> HTOld_Deuteronomy = new LinearProbingHashST<String, Term>(16, "old-deuteronomy");
        LinearProbingHashST<String, Term> HTSkimbleshanks_The_Railway_Cat = new LinearProbingHashST<String, Term>(16, "skimbleshanks-the-railway-cat");
        LinearProbingHashST<String, Term> HTThe_Ad_Dressing_Of_Cats = new LinearProbingHashST<String, Term>(16, "the-ad-dressing-of-cats");
        LinearProbingHashST<String, Term> HTThe_Naming_Of_Cats = new LinearProbingHashST<String, Term>(16, "the-naming-of-cats");
        LinearProbingHashST<String, Term> HTThe_Old_Gumbie_Cat = new LinearProbingHashST<String, Term>(16, "the-old-gumbie-cat");
        LinearProbingHashST<String, Term> HTThe_Rum_Tum_Tugger = new LinearProbingHashST<String, Term>(16, "the-rum-tum-tugger");
        LinearProbingHashST<String, Term> HTThe_Song_Of_The_Jellicles = new LinearProbingHashST<String, Term>(16, "the-song-of-the-jellicles");
        LinearProbingHashST<String, Term> HTAllWords = new LinearProbingHashST<String, Term>(16, "all-documents");


        assert filesList != null;
        for (File currentFile : filesList) {

            Scanner currentScanner = new Scanner(currentFile);

            while (currentScanner.hasNext()) {
                String currentString = currentScanner.next();
                if(HTAllWords.contains(currentString)) HTAllWords.get(currentString).incrementFrequency();
                else HTAllWords.put(currentString, new Term(currentString, "all", 0));
                switch(currentFile.getName()) {
                    case "bustopher-jones-the-cat-about-town.txt":
                        if(HTBustopher_Jones_The_Cat_About_Town.contains(currentString)) HTBustopher_Jones_The_Cat_About_Town.get(currentString).incrementFrequency();
                        else HTBustopher_Jones_The_Cat_About_Town.put(currentString, new Term(currentString, "bustopher-jones-the-cat-about-town.txt", 0));
                        break;
                    case "growltigers-last-stand.txt":
                        if(HTGrowlTigers_Last_Stand.contains(currentString)) HTGrowlTigers_Last_Stand.get(currentString).incrementFrequency();
                        else HTGrowlTigers_Last_Stand.put(currentString, new Term(currentString, "growltigers-last-stand.txt", 0));
                        break;
                    case "gus-the-theater-cat.txt":
                        if(HTGus_The_Theather_Cat.contains(currentString)) HTGus_The_Theather_Cat.get(currentString).incrementFrequency();
                        else HTGus_The_Theather_Cat.put(currentString, new Term(currentString, "gus-the-theater-cat.txt", 0));
                        break;
                    case "macavity-the-mystery-cat.txt":
                        if(HTMacavity_The_Mystery_Cat.contains(currentString)) HTMacavity_The_Mystery_Cat.get(currentString).incrementFrequency();
                        else HTMacavity_The_Mystery_Cat.put(currentString, new Term(currentString, "macavity-the-mystery-cat.txt", 0));
                        break;
                    case "mr-mistoffelees.txt":
                        if(HTMr_Mistoffelees.contains(currentString)) HTMr_Mistoffelees.get(currentString).incrementFrequency();
                        else HTMr_Mistoffelees.put(currentString, new Term(currentString, "mr-mistoffelees.txt", 0));
                        break;
                    case "mungojerrie-and-rumpelteazer.txt":
                        if(HTMungojerrie_And_Rumpelteazer.contains(currentString)) HTMungojerrie_And_Rumpelteazer.get(currentString).incrementFrequency();
                        else HTMungojerrie_And_Rumpelteazer.put(currentString, new Term(currentString, "mungojerrie-and-rumpelteazer.txt", 0));
                        break;
                    case "of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt":
                        if(HTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.contains(currentString)) HTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.get(currentString).incrementFrequency();
                        else HTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.put(currentString, new Term(currentString, "of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt", 0));
                        break;
                    case "old-deuteronomy.txt":
                        if(HTOld_Deuteronomy.contains(currentString)) HTOld_Deuteronomy.get(currentString).incrementFrequency();
                        else HTOld_Deuteronomy.put(currentString, new Term(currentString, "old-deuteronomy.txt", 0));
                        break;
                    case "skimbleshanks-the-railway-cat.txt":
                        if(HTSkimbleshanks_The_Railway_Cat.contains(currentString)) HTSkimbleshanks_The_Railway_Cat.get(currentString).incrementFrequency();
                        else HTSkimbleshanks_The_Railway_Cat.put(currentString, new Term(currentString, "skimbleshanks-the-railway-cat.txt", 0));
                         break;
                    case "the-ad-dressing-of-cats.txt":
                        if(HTThe_Ad_Dressing_Of_Cats.contains(currentString)) HTThe_Ad_Dressing_Of_Cats.get(currentString).incrementFrequency();
                        else HTThe_Ad_Dressing_Of_Cats.put(currentString, new Term(currentString, "the-ad-dressing-of-cats.txt", 0));
                        break;
                    case "the-naming-of-cats.txt":
                        if(HTThe_Naming_Of_Cats.contains(currentString)) HTThe_Naming_Of_Cats.get(currentString).incrementFrequency();
                        else HTThe_Naming_Of_Cats.put(currentString, new Term(currentString, "the-naming-of-cats.txt", 0));
                        break;
                    case "the-old-gumbie-cat.txt":
                        if(HTThe_Old_Gumbie_Cat.contains(currentString)) HTThe_Old_Gumbie_Cat.get(currentString).incrementFrequency();
                        else HTThe_Old_Gumbie_Cat.put(currentString, new Term(currentString, "the-old-gumbie-cat.txt", 0));
                        break;
                    case "the-rum-tum-tugger.txt":
                        if(HTThe_Rum_Tum_Tugger.contains(currentString)) HTThe_Rum_Tum_Tugger.get(currentString).incrementFrequency();
                        else HTThe_Rum_Tum_Tugger.put(currentString, new Term(currentString, "the-rum-tum-tugger.txt", 0));
                        break;
                    case "the-song-of-the-jellicles.txt":
                        if(HTThe_Song_Of_The_Jellicles.contains(currentString)) HTThe_Song_Of_The_Jellicles.get(currentString).incrementFrequency();
                        else HTThe_Song_Of_The_Jellicles.put(currentString, new Term(currentString, "the-song-of-the-jellicles.txt", 0));
                        break;
                    default:
                        break;
                }

            }

        }

        double timeConstructionHT = constructionTimerHT.elapsedTime();

        LinearProbingHashST[] HTArray = new LinearProbingHashST[14];
        HTArray[0] = HTBustopher_Jones_The_Cat_About_Town;
        HTArray[1] = HTGrowlTigers_Last_Stand;
        HTArray[2] = HTGus_The_Theather_Cat;
        HTArray[3] = HTMacavity_The_Mystery_Cat;
        HTArray[4] = HTMr_Mistoffelees;
        HTArray[5] = HTMungojerrie_And_Rumpelteazer;
        HTArray[6] = HTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles;
        HTArray[7] = HTOld_Deuteronomy;
        HTArray[8] = HTSkimbleshanks_The_Railway_Cat;
        HTArray[9] = HTThe_Ad_Dressing_Of_Cats;
        HTArray[10] = HTThe_Naming_Of_Cats;
        HTArray[11] = HTThe_Old_Gumbie_Cat;
        HTArray[12] = HTThe_Rum_Tum_Tugger;
        HTArray[13] = HTThe_Song_Of_The_Jellicles;

        // Updating TF-IDF's in individual HT's
        for(int i = 0; i < HTArray.length; i ++) // For each HT
        {
            for( Object v : HTArray[i].getVals() )  // For each Term in the given HT
            {
                if (v != null) { // Only do this for parts of the array that have data
                    v = (Term) v;
                    double tfidf = ((Term) HTArray[i].get(((Term) v).getWord())).getFrequency() * Math.log(14.0 / (double) HTAllWords.get(((Term) v).getWord()).getFrequency()); // Multiply TF by IDF
                    ((Term) v).setTF_IDF(tfidf); // Setting Term's TF-IDF
                }
            }
        }

        Documents theDocument = new Documents("allFiles"); // Putting all out HT's into a document class to perform searches
        theDocument.setTables(HTArray);

        //TODO Task Set 2 Number 10a)
        System.out.println("Running search for words 'Bustopher' and 'look'...\n");
        theDocument.search("Bustopher"); // Examples of search running
        System.out.println("\n");
        theDocument.search("look");
        System.out.println("\n");

        //TODO Task Set 2 Number 10b)
        System.out.println("Running top10 on bustopher-jones-the-cat-about-town...");
        theDocument.top10("bustopher-jones-the-cat-about-town");
        System.out.println("\n");


        //TODO Task Set 3 Number 12)

        Random rand = new Random();
        System.out.println("Time for construction of Linear Probing Hash Tables: " + timeConstructionHT + " seconds.");
        System.out.println("Running search on a small sample size of words..."); // Get 4096/10 ~~ 410 words to search
        int[] indicesTaken = new int[410];
        for(int i = 0; i < 410; i++)
        {
            boolean isInvalid = true;
            int currentIndex = 0;
            while(isInvalid)
            {
                int index = rand.nextInt(4096);
                boolean isAlreadyTaken = false;
                for(int j : indicesTaken)
                { if(j == index) isAlreadyTaken = true; }
                boolean isNull = HTAllWords.getKey(index) == null;
                isInvalid = isAlreadyTaken || isNull;
                currentIndex = index;
            }
            indicesTaken[i] = currentIndex;
        }

        Stopwatch searchTimerHT = new Stopwatch();
        for(int item: indicesTaken)
        { theDocument.searchNoPrint(HTAllWords.getKey(item)); }
        double timeSearchHT = searchTimerHT.elapsedTime();
        System.out.println("Time for HT search of 410 words: " + timeSearchHT + " seconds.");



        //TODO Task Set 3 Number 13)

        Stopwatch constructionTimerBST = new Stopwatch();

        BST BSTBustopher_Jones_The_Cat_About_Town = new BST("bustopher-jones-the-cat-about-town");
        BST BSTGrowlTigers_Last_Stand = new BST("growltigers-last-stand");
        BST BSTGus_The_Theater_Cat = new BST("gus-the-theater-cat");
        BST BSTMacavity_The_Mystery_Cat = new BST("macavity-the-mystery-cat");
        BST BSTMr_Mistoffelees = new BST("mr-mistoffelees");
        BST BSTMungojerrie_And_Rumpelteazer = new BST("mungo-and-rumpelteazer");
        BST BSTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles = new BST("of-the-awefull-battle-of-the-pekes-and-the-pollicles");
        BST BSTOld_Deuteronomy = new BST("old-deuteronomy");
        BST BSTSkimbleshanks_The_Railway_Cat = new BST("skimbleshanks-the-railway-cat");
        BST BSTThe_Ad_Dressing_Of_Cats = new BST("the-ad-dressing-of-cats");
        BST BSTThe_Naming_Of_Cats = new BST("the_naming_of_cats");
        BST BSTThe_Old_Gumbie_Cat = new BST("the-old-gumbie-cats");
        BST BSTThe_Rum_Tum_Tugger = new BST("the-rum-tum-tugger");
        BST BSTThe_Song_Of_The_Jellicles = new BST("the-song-of-the-jellicles");
        BST BSTAllWords = new BST("all-documents");

        assert filesList != null;
        for (File currentFile : filesList) {

            Scanner currentScanner = new Scanner(currentFile);

            while (currentScanner.hasNext()) {
                String currentString = currentScanner.next();
                if(BSTAllWords.contains(currentString)) BSTAllWords.get(currentString).incrementFrequency();
                else BSTAllWords.put(currentString, new Term(currentString, "all", 0));
                switch(currentFile.getName()) {
                    case "bustopher-jones-the-cat-about-town.txt":
                        if(BSTBustopher_Jones_The_Cat_About_Town.contains(currentString)) BSTBustopher_Jones_The_Cat_About_Town.get(currentString).incrementFrequency();
                        else BSTBustopher_Jones_The_Cat_About_Town.put(currentString, new Term(currentString, "bustopher-jones-the-cat-about-town.txt", 0));
                        break;
                    case "growltigers-last-stand.txt":
                        if(BSTGrowlTigers_Last_Stand.contains(currentString)) BSTGrowlTigers_Last_Stand.get(currentString).incrementFrequency();
                        else BSTGrowlTigers_Last_Stand.put(currentString, new Term(currentString, "growltigers-last-stand.txt", 0));
                        break;
                    case "gus-the-theater-cat.txt":
                        if(BSTGus_The_Theater_Cat.contains(currentString)) BSTGus_The_Theater_Cat.get(currentString).incrementFrequency();
                        else BSTGus_The_Theater_Cat.put(currentString, new Term(currentString, "gus-the-theater-cat.txt", 0));
                        break;
                    case "macavity-the-mystery-cat.txt":
                        if(BSTMacavity_The_Mystery_Cat.contains(currentString)) BSTMacavity_The_Mystery_Cat.get(currentString).incrementFrequency();
                        else BSTMacavity_The_Mystery_Cat.put(currentString, new Term(currentString, "macavity-the-mystery-cat.txt", 0));
                        break;
                    case "mr-mistoffelees.txt":
                        if(BSTMr_Mistoffelees.contains(currentString)) BSTMr_Mistoffelees.get(currentString).incrementFrequency();
                        else BSTMr_Mistoffelees.put(currentString, new Term(currentString, "mr-mistoffelees.txt", 0));
                        break;
                    case "mungojerrie-and-rumpelteazer.txt":
                        if(BSTMungojerrie_And_Rumpelteazer.contains(currentString)) BSTMungojerrie_And_Rumpelteazer.get(currentString).incrementFrequency();
                        else BSTMungojerrie_And_Rumpelteazer.put(currentString, new Term(currentString, "mungojerrie-and-rumpelteazer.txt", 0));
                        break;
                    case "of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt":
                        if(BSTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.contains(currentString)) BSTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.get(currentString).incrementFrequency();
                        else BSTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles.put(currentString, new Term(currentString, "of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt", 0));
                        break;
                    case "old-deuteronomy.txt":
                        if(BSTOld_Deuteronomy.contains(currentString)) BSTOld_Deuteronomy.get(currentString).incrementFrequency();
                        else BSTOld_Deuteronomy.put(currentString, new Term(currentString, "old-deuteronomy.txt", 0));
                        break;
                    case "skimbleshanks-the-railway-cat.txt":
                        if(BSTSkimbleshanks_The_Railway_Cat.contains(currentString)) BSTSkimbleshanks_The_Railway_Cat.get(currentString).incrementFrequency();
                        else BSTSkimbleshanks_The_Railway_Cat.put(currentString, new Term(currentString, "skimbleshanks-the-railway-cat.txt", 0));
                        break;
                    case "the-ad-dressing-of-cats.txt":
                        if(BSTThe_Ad_Dressing_Of_Cats.contains(currentString)) BSTThe_Ad_Dressing_Of_Cats.get(currentString).incrementFrequency();
                        else BSTThe_Ad_Dressing_Of_Cats.put(currentString, new Term(currentString, "the-ad-dressing-of-cats.txt", 0));
                        break;
                    case "the-naming-of-cats.txt":
                        if(BSTThe_Naming_Of_Cats.contains(currentString)) BSTThe_Naming_Of_Cats.get(currentString).incrementFrequency();
                        else BSTThe_Naming_Of_Cats.put(currentString, new Term(currentString, "the-naming-of-cats.txt", 0));
                        break;
                    case "the-old-gumbie-cat.txt":
                        if(BSTThe_Old_Gumbie_Cat.contains(currentString)) BSTThe_Old_Gumbie_Cat.get(currentString).incrementFrequency();
                        else BSTThe_Old_Gumbie_Cat.put(currentString, new Term(currentString, "the-old-gumbie-cat.txt", 0));
                        break;
                    case "the-rum-tum-tugger.txt":
                        if(BSTThe_Rum_Tum_Tugger.contains(currentString)) BSTThe_Rum_Tum_Tugger.get(currentString).incrementFrequency();
                        else BSTThe_Rum_Tum_Tugger.put(currentString, new Term(currentString, "the-rum-tum-tugger.txt", 0));
                        break;
                    case "the-song-of-the-jellicles.txt":
                        if(BSTThe_Song_Of_The_Jellicles.contains(currentString)) BSTThe_Song_Of_The_Jellicles.get(currentString).incrementFrequency();
                        else BSTThe_Song_Of_The_Jellicles.put(currentString, new Term(currentString, "the-song-of-the-jellicles.txt", 0));
                        break;
                    default:
                        break;
                }

            }

        }

        double timeConstructionBST = constructionTimerBST.elapsedTime();

        System.out.println("\n\nInitializing BST implementation.....\n");

        BST[] BSTArray = new BST[14];
        BSTArray[0] = BSTBustopher_Jones_The_Cat_About_Town;
        BSTArray[1] = BSTGrowlTigers_Last_Stand;
        BSTArray[2] = BSTGus_The_Theater_Cat;
        BSTArray[3] = BSTMacavity_The_Mystery_Cat;
        BSTArray[4] = BSTMr_Mistoffelees;
        BSTArray[5] = BSTMungojerrie_And_Rumpelteazer;
        BSTArray[6] = BSTOf_The_Awefull_Battle_Of_The_Pekes_And_The_Pollicles;
        BSTArray[7] = BSTOld_Deuteronomy;
        BSTArray[8] = BSTSkimbleshanks_The_Railway_Cat;
        BSTArray[9] = BSTThe_Ad_Dressing_Of_Cats;
        BSTArray[10] = BSTThe_Naming_Of_Cats;
        BSTArray[11] = BSTThe_Old_Gumbie_Cat;
        BSTArray[12] = BSTThe_Rum_Tum_Tugger;
        BSTArray[13] = BSTThe_Song_Of_The_Jellicles;
        
        Documents theBSTDocument = new Documents("BSTallFiles"); // Putting all out BST's into a document class to perform searches
        theBSTDocument.setTables(BSTArray);

        for(BST currentBST : BSTArray)
        { currentBST.updateTFIDFs(currentBST.getRoot(), BSTAllWords); }

        System.out.println("Running search for words 'street' and 'cat'...\n");
        theBSTDocument.searchBSTs("street");
        System.out.println("\n");
        theBSTDocument.searchBSTs("cat");
        System.out.println("\n");

        System.out.println("Time for construction of Binary Search Trees: " + timeConstructionBST);
        System.out.println("Running search on a small sample size of words...");
        Stopwatch searchTimerBST = new Stopwatch();
        for(int num = 0; num < 410; num++)
        { theBSTDocument.searchBSTsNoPrint((String) BSTAllWords.getRandomNode(BSTAllWords.getRoot()).getKey()); }
        double timeSearchBST = searchTimerBST.elapsedTime();
        System.out.println("Time for BST search of 410 words: " + timeSearchBST + " seconds.\n");

        System.out.println("\n\nWe here at Google-But-Better-Incorporated understand if you would like to test out the search engine for yourself.  Please, have at it.");
        Scanner myThing = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to search for? (input q to quit)");
            String searchFor = myThing.nextLine();
            if(searchFor.equals("q")) break;
            theDocument.search(searchFor);
        }
        System.out.println("Thank you for using our engine!");
    }
}