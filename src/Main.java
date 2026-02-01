import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Scanner scan = new Scanner(System.in);
        ArrayList<String> moedas = new ArrayList<>(
                Arrays.asList("USD", "BRL", "EUR", "ARS", "BOB", "CLP", "COP", "CAD", "JPY")
        );
        String apiKey = System.getenv("API_KEY");

        if (apiKey == null) {
            throw new IllegalStateException("API_KEY não configurada, adicione uma variável de ambiente com sua chave da API do OMDB.");
        }

        System.out.println("Digite o valor a ser convertido(ex: 150,30):");
        Double dinheiro = scan.nextDouble();


        JsonObject urlObject = retornaURL(moedas, scan, apiKey);
        String url = urlObject.get("url").getAsString();
        String origem = urlObject.get("origem").getAsString();
        String destino = urlObject.get("destino").getAsString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            JsonElement cotacao = gson.fromJson(json, JsonElement.class)
                .getAsJsonObject()
                    .get("conversion_rate");

            System.out.println("Seus " + dinheiro+ " " + origem + " dão " + " = " + dinheiro * cotacao.getAsDouble() + " " + destino);
        }catch (Exception e){
            System.err.println("Erro ao buscar a cotação: " + e.getMessage());
        }
    }

    static void calculaMoeda(Double Dinheiro, String origem, String destino) {

    }


    static JsonObject retornaURL(ArrayList<String> moedas, Scanner scan, String apiKey) {
        System.out.println("Lista de moedas:");
        for (int i = 0; i < moedas.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, moedas.get(i));
        }

        int origemIdx = lerOpcao(scan, "Escolha a moeda de origem (número): ", moedas.size()) - 1;
        int destinoIdx = lerOpcao(scan, "Escolha a moeda de destino (número): ", moedas.size()) - 1;

        String origem = moedas.get(origemIdx);
        String destino = moedas.get(destinoIdx);

        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + origem + "/" + destino;

        JsonObject obj = new JsonObject();
        obj.addProperty("url", url);
        obj.addProperty("origem", origem);
        obj.addProperty("destino", destino);
        return obj;

    }

    static int lerOpcao(Scanner scan, String prompt, int max) {
        int opcao;
        while (true) {
            System.out.print(prompt);
            String line = scan.nextLine().trim();
            try {
                opcao = Integer.parseInt(line);
                if (opcao >= 1 && opcao <= max) {
                    return opcao;
                } else {
                    System.out.println("Opção inválida. Digite um número entre 1 e " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
}