package arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {

    private static PrintWriter writer;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void init(String nomeArquivo) {
        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo, false); 
            writer = new PrintWriter(fileWriter);
            log("--- Sessão de Log iniciada ---");
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o Logger: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void log(String mensagem) {
        if (writer != null) {
            String timestamp = dateFormat.format(new Date());
            writer.println("[" + timestamp + "] " + mensagem);
            writer.flush(); 
        }
    }
    public static void close() {
        if (writer != null) {
            log("--- Sessão de Log finalizada ---");
            writer.close();
        }
    }
}