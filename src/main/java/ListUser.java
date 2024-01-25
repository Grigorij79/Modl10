        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
public class ListUser {

    public static void readAndWriteListUser(String nameFile) {
        int count =0;
        try (FileReader read = new FileReader(nameFile)){
            BufferedReader readers = new BufferedReader(read);
            String lines;
            //System.out.println(lines);
            while ((lines = readers.readLine()) != null) {
                count++;
            }
            //System.out.println(count);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        //*************************************************************88
        try (FileReader read = new FileReader(nameFile)){
            BufferedReader reader = new BufferedReader(read);

            String coma = ",";
            String name = "\"name\"";
            String nameName = "";
            String age = "\"age\"";
            String ageAge = "";
            reader.readLine();
            int countLine =0;

            String line = reader.readLine();
            String inputWrite = "";
            ValidNumber.writeFile("user.json","[\n");
            while ( line != null){
                countLine++;
                line = line.strip();
                line = line.replaceAll("\\s+"," ");
                //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                int j = line.indexOf(' ');
                for (int i = 0; i < j; i++) {
                    nameName = nameName + line.charAt(i);
                }
                //System.out.println(nameName);
                //System.out.println(line);
                int ageInt = 0;
                int c ;
                for (int i = 0; i < (line.length()- j-1); i++) {
                    //System.out.println(line.length()- j-1);
                    //System.out.println(j);
                    c = (int)line.charAt(line.length()-1 - i);
                    //System.out.println(c);
                    int cint = switch (c) {
                        case 48 -> 0;
                        case 49 -> 1;
                        case 50 -> 2;
                        case 51 -> 3;
                        case 52 -> 4;
                        case 53 -> 5;
                        case 54 -> 6;
                        case 55 -> 7;
                        case 56 -> 8;
                        case 57 -> 9;
                        default -> 0;
                    };
                    //System.out.println(cint);
                    if (i == 0){
                        ageInt = ageInt + cint;
                    }
                    ageInt = ageInt + 10 * i * cint;

                }
                //System.out.println(ageInt);
                ageAge = line.substring(j,line.length());

                Person person = new Person(nameName,ageInt);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(person);
                // inputWrite = inputWrite + "\n    {\n        \"name\": " + "\"" + nameName +"\",\n";
                // inputWrite = inputWrite + "        \"age\":" + ageAge + "\n    }" + coma;
                if (countLine == count-1) {
                    ValidNumber.writeFile("user.json", json);
                }else {
                    ValidNumber.writeFile("user.json",json+",");
                    ValidNumber.writeFile("user.json","\n");
                }



                //System.out.println(line);
                //System.out.println(nameName);
                //System.out.println(ageAge);
                line = reader.readLine();
                inputWrite = "";
                nameName = "";
                ageAge = "";
            }
            ValidNumber.writeFile("user.json","\n]");
            // Main.writeFile("user.json",ageAge);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    static class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) throws IOException {
        ValidNumber.writeFileWithOutputStream("file.txt","name age\njon     25\nryan     30\n   alice    21\nmykola 33\n");
        new FileWriter("user.json", false).close();
        ListUser.readAndWriteListUser("file.txt");
    }
}


