import java.io.*;
public class ValidNumber {
    public static void writeFileWithOutputStream(String nameFile, String text) {
        //String text = "987-123-4567\n123 456 7890\n(123) 456-7890\n";
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameFile)){
            byte[] buffer = text.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile(String nameFile,String text) {
        File file = new File(nameFile);
        //try-with-resources
        try (FileWriter writer = new FileWriter(file, true))
        {
            // String text = "987-123-4567\n345 678 8902234";
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readFile(String nameFile) {
        try (FileReader reader = new FileReader(nameFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }
    public static void readValidNumbersPhone(String nameFile) {
        try (FileReader read = new FileReader(nameFile)){
            BufferedReader reader = new BufferedReader(read);
            //reader.readLine(); //пропускає першу строку
            String line = reader.readLine();

            while ( line != null){
                String format1 = "(xxx) xxx-xxxx";
                String format2 = "xxx-xxx-xxxx";
                String lineFormat = "";
                for (int i = 0; i < line.length(); i++) {

                    if ((int)line.charAt(i) <= 57 && (int)line.charAt(i) >= 48){
                        lineFormat = lineFormat + 'x';
                    }else {
                        lineFormat = lineFormat + line.charAt(i);
                    }
                }
                if (lineFormat.equals(format1) || lineFormat.equals(format2)){
                    System.out.println(line);
                }
                line = reader.readLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException{
        ValidNumber.writeFileWithOutputStream("test.txt","987-321-9999\n123 456 7890\n(123) 456-7890\n");
        ValidNumber.writeFile("test.txt","987-123-4567\n3456788902234");
        //Main.readFile("test.txt");
        ValidNumber.readValidNumbersPhone("test.txt");
        //new  FileWriter("test.txt", false).close();

    }
}
