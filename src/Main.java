import com.google.gson.Gson;

import java.net.http.HttpClient;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
    }
}