import java.io.*;

public class IOStreamManager {
    public static void main(String[] args) throws IOException {
        File firstTest = new File("firstTest.txt");
        File secondTest = new File("secondTest.txt");
        OutputStream out = new FileOutputStream("firstTestOutputStream.txt");
        File zapisTest = new File("zapisiString.txt");
        String text = "Zdravo kolegi";

        copyFileByteByByte(firstTest, secondTest);
        printContentOfTxtFile(firstTest, System.out);
        //readContentFromStdInput(out);
        writeToTextFile(zapisTest, text, true);
        //memoryUnsafeTxtFileCopy(new File("testMemory.txt"), new File("memoryUnsafe.txt"));
        memorySafeTxtFileCopy(new File("testMemory.txt"), new File("memoryUnsafe.txt"));
        writeBinaryDataToBFile(new File("lineNumber.txt"));
        readBinaryDataFromBFile(new File("lineNumber.txt"));
    }

    public static void copyFileByteByByte(File from, File to) throws IOException{
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = new FileInputStream(from);
            outputStream = new FileOutputStream(to);
            int c = -1;

            while((c = inputStream.read()) != -1){
                outputStream.write(c);
                outputStream.flush();
            }

        } catch (IOException e) {
            System.out.println("Exception in method: copyFileByteByByte");
        } finally {
            if(inputStream != null) inputStream.close();
            if(outputStream != null) outputStream.close();
        }
    }

    public static void printContentOfTxtFile(File f, PrintStream printer) throws IOException{
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(f));
            String line = null;

            while((line = reader.readLine()) != null){
                printer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Exception in method: printContentOfTxtFile");
        } finally {
            if(reader != null) reader.close();
        }
    }

    public static void readContentFromStdInput(OutputStream to) throws IOException{
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(to));
            String line = null;

            while((line = reader.readLine()) != null){
                if(line.equals("KRAJ")){
                    break;
                }
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e) {
            System.out.println("Exception in method: readContentFromStdInput");
        } finally {
            if(reader != null) reader.close();
            if(writer != null) writer.close();
        }
    }

    public static void writeToTextFile(File to, String text, Boolean append) throws IOException{
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(to, append));
            writer.write(text);
            writer.flush();
        }catch (IOException e) {
            System.out.println("Exception in method: writeToTextFile");
        } finally {
            if(writer != null) writer.close();
        }
    }

    public static void memoryUnsafeTxtFileCopy(File from, File to) throws IOException{
        BufferedReader reader = null;
        BufferedWriter writer = null;
        StringBuilder builder = new StringBuilder();

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            writer.write(builder.toString());
            writer.flush();
        }catch (IOException e) {
            System.out.println("Exception in method: memoryUnsafeTxtFileCopy");
        } finally {
            if(reader != null) reader.close();
            if(writer != null) writer.close();
        }
    }

    public static void memorySafeTxtFileCopy(File from, File to) throws IOException{
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e) {
            System.out.println("Exception in method: memorySafeTxtFileCopy");
        } finally {
            if(reader != null) reader.close();
            if(writer != null) writer.close();
        }
    }

    public static void readFileWithLineNumber(File from, OutputStream os) throws IOException{
        BufferedReader reader = null;
        BufferedWriter writer = null;
        PrintWriter print = null;

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new OutputStreamWriter(os));
            print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
            String line = null;
            int lineCounter = 0;

            while ((line = reader.readLine()) != null){
                writer.write(++lineCounter + "." + line);
            }
        }catch (IOException e) {
            System.out.println("Exception in method: readFileWithLineNumber");
        } finally {
            if(reader != null) reader.close();
        }
    }

    public static void writeBinaryDataToBFile(File to)throws IOException{
        DataOutputStream out = null;

        try{
            out = new DataOutputStream(new FileOutputStream(to));
            out.writeUTF("Zdravo kolegi");
            out.writeInt(15);
            out.writeDouble(12.435);
        }catch (IOException e) {
            System.out.println("Exception in method: writeBinaryDataToBFile");
        } finally {
            if(out != null) out.close();
        }
    }

    public static void readBinaryDataFromBFile(File from)throws IOException{
        DataInputStream in = null;

        try{
            in = new DataInputStream(new FileInputStream(from));
            System.out.println(in.readUTF());
            System.out.println(in.readInt());
            System.out.println(in.readDouble());
        }catch (IOException e) {
            System.out.println("Exception in method: readBinaryDataFromBFile");
        } finally {
            if(in != null) in.close();
        }
    }

    public static void writeToRandomAccessFile(File from) throws IOException{
        RandomAccessFile rand = null;

        try{
            rand = new RandomAccessFile(from, "rw");
            for(int i=0; i<20; i++)
                rand.writeDouble(i * 1.4);
            rand.writeUTF("THE END");
        }catch (IOException e) {
            System.out.println("Exception in method: writeToRandomAccessFile");
        } finally {
            if(rand != null) rand.close();
        }
    }

    public static void readFromRandomAccessFile(File from, PrintStream out) throws IOException{
            //read from random access file
    }
}
