package demo;

import java.util.Random;
import java.util.HashMap;

public class Demo 
{
    private static HashMap<String, String[]> grammar = new HashMap<String, String[]>();

    public static void setupGrammar()  //production rules
    {
        // nouns
        
        String[] n1 = {"<person>", "<place>", "<thing>", "<idea>"};
        String[] n2 = {"<name>", "<article><occupation>", "<article><adjective><occupation>"}; // person
        String[] n3 = {"<general>", "<adjective><general>", "<article><specific>", "<article><adjective><specific>"}; // idea
        String[] n4 = {"<article><thing noun>", "<article><adjective><thing noun>"}; // thing
        String[] n5 = {"<article><place noun>", "<article><adjective><place noun>"}; // place
        String[] n6 = {"Peter ", "Lucas "}; // name
        String[] n7 = {"lecture ", "baseball ", "college "}; // general
        String[] n8 = {"Catholic University of America "}; // exact location
        String[] n9 = {"mechanic ", "professor ", "wrestler "}; // job
        String[] n10 = {"desk ", "dog ", "chair ", "book ", " "}; // object 
        String[] n11 = {"Duf ", "hospital ", "church ", "campus "}; // place 
        String[] n12 = {"<noun>"}; // pred nom
        String[] n13 = {"<noun>"}; // direct object

        grammar.put("<noun>", n1);
        grammar.put("<person>", n2);
        grammar.put("<idea>", n3);
        grammar.put("<thing>", n4);
        grammar.put("<place>", n5);
        grammar.put("<name>", n6);
        grammar.put("<general>", n7);
        grammar.put("<specific>", n8);
        grammar.put("<occupation>", n9);
        grammar.put("<thing noun>", n10);
        grammar.put("<place noun>", n11);
        grammar.put("<pred nom>", n12);
        grammar.put("<direct object>", n13);

        // verbs
        
        String[] v1 = {"<act>", "<link>"};
        String[] v2 = {"<trans>", "<intrans>"};
        String[] v3 = {"<trans verb><direct object>", "<adverb><trans verb><direct object>"};
        String[] v4 = {"<intrans verb>", "<intrans verb><adverb>"};
        String[] v5 = {"<link verb><pred nom>", "<link verb><pred adj>"};
        String[] v6 = {"was ", "seems ", "has ", "is ", "looks "}; // linking verbs
        String[] v7 = {"borrows ", "gives ", "pay "}; // transitive verbs
        String[] v8 = {"sleep ", "was sleeping ", "arrives ", "was arriving ", "appear ", "was appearing "}; // intran verbs

        grammar.put("<verb>", v1);
        grammar.put("<act>", v2);
        grammar.put("<trans>", v3);
        grammar.put("<intrans>", v4);
        grammar.put("<link>", v5);
        grammar.put("<link verb>", v6);
        grammar.put("<trans verb>", v7);
        grammar.put("<intrans verb>", v8);

        String[] art = {"a ", "the "}; // articles


        grammar.put("<article>", art);

        // adjectives
        
        String[] adj1 = {"large ", "red ", "tall ", "old "};
        String[] adj2 = {"<adjective>"}; // pred adj

        grammar.put("<adjective>", adj1);
        grammar.put("<pred adj>", adj2);

        //adverbs
        
        String[] adv1 = {"smartly ", "quickly ", "very ", "almost"};

        grammar.put("<adverb>", adv1);
    }

    public static String getRandElem(String[] arr) 
    {
        Random rand = new Random();
        return arr[rand.nextInt(arr.length)];
    }

    public static String produce(String input) 
    {
        String output = "";
        while (input.contains("<") && input.contains(">")) 
        {
            int varStart = input.indexOf("<");
            int varFinish = input.indexOf(">");
            String var = input.substring(varStart, varFinish + 1);
            input = input.substring(varFinish + 1);

            String newVar = getRandElem(grammar.get(var));
            if (!newVar.contains("<") && !newVar.contains(">")) 
            {
                output += newVar;
            }
            else {
                input = newVar + input;
            }
        }

        return output;
    }

    public static void main(String[] args) 
    {
        setupGrammar();
        System.out.println(produce("<noun><verb>"));
    }
}
