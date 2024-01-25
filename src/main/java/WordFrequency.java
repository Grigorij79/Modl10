import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WordFrequency {
    public static   void wordFrequency(String nameFile) {
        try (FileReader read = new FileReader(nameFile)){
            BufferedReader reader = new BufferedReader(read);
            String lineAll = "";
            String line = reader.readLine();

            while ( line != null){
                lineAll = lineAll + line +" ";
                line = reader.readLine();
            }
            lineAll = lineAll.strip();
            lineAll = lineAll.replaceAll("\\s+"," ");
            lineAll = lineAll + " ";
            //System.out.println(lineAll);
            TreeMap<Integer, String>  wordAndFrequency  =  new TreeMap<Integer, String  >(new Comparator<Integer>() {
                public int compare(Integer coin1, Integer coin2) {
                    return (coin2.compareTo(coin1));
                }
            });
            while (lineAll.length() > 1){
                String worldsI = "";
                String worldsIplus = "";
                int i =0;
                while ((int) lineAll.charAt(i) != 32){
                    worldsI = worldsI + lineAll.charAt(i);
                    i++;
                }
                //System.out.println(worldsI);
                worldsIplus = worldsI + " ";
                int n, lengthwordsIplus, lengthlineAll = 0;
                lengthlineAll = lineAll.length();
                lengthwordsIplus = worldsIplus.length();
                lineAll = lineAll.replaceAll(worldsIplus,"");
                n = (lengthlineAll - lineAll.length()) / lengthwordsIplus;
                //System.out.println(n);
                wordAndFrequency.put(n, worldsI);
            }
            //System.out.println(wordAndFrequency);
            print(wordAndFrequency);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void print (Map<Integer, String> map)
    {
        for(Map.Entry<Integer, String> pair : map.entrySet())
        {

            Integer f = pair.getKey();
            String value = pair.getValue();
            System.out.print(value + " ");
            System.out.println(f);

        }
    }
    public static void main(String[] args) throws IOException {
        ValidNumber.writeFileWithOutputStream("words.txt","the   day   is sunny t t  the the   \nthe  sunny t t    t t t is  is\n the  sunny is is\nthe sunny is is");

        wordFrequency("words.txt");
    }
}


