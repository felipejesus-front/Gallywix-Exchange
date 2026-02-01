import com.google.gson.Gson;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite a moeda de origem (ex: USD): ");
        String origem = scan.nextLine().trim().toUpperCase();

        System.out.println("Digite a moeda de origem (ex: BRL): ");
        String destino = scan.nextLine().trim().toUpperCase();

        String apiKey = System.getenv("API_KEY");

        if (apiKey == null) {
            throw new IllegalStateException("API_KEY não configurada, adicione uma variável de ambiente com sua chave da API do OMDB.");
        }

        String url = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+origem+"/"+destino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            System.out.println(json);
        }catch (Exception e){
            System.err.println("Erro ao buscar a cotação: " + e.getMessage());
        }
    }
}