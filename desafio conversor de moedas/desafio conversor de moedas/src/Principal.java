import com.google.gson.Gson;
import modelos.Moedas;
import modelos.MoedasConvertidas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {


         String mensagem1 = """
                **********************************************************
                SEJA BEM-VINDO AO CONVERSOR DE MOEDA
                1) DOLAR =>> PESO ARGENTINO
                2) PESO ARGENTINO =>> DOLAR
                3) DOLAR =>> REAL BRASILEIRO
                4) REAL BRASILEIRO =>> DOLAR
                5) DOLAR =>> PESO COLOMBIANO
                6) PESO COLOMBIANO =>> DOLAR
                7) SAIR
                
                ESCOLHA UMA OPÇÃO VALIDA:
                **********************************************************
                
                """;


         Scanner leitura = new Scanner(System.in);


        String endereco = "";

        boolean loop = true;
        while (loop){
            System.out.println(mensagem1);
            int opcao = leitura.nextInt();
            String base = "", saida = "";
            switch (opcao) {
                case 1:
                    base = "USD";
                    saida = "ARS";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 2:
                    base = "ARS";
                    saida = "USD";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 3:
                    base = "USD";
                    saida = "BRL";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 4:
                    base = "BRL";
                    saida = "USD";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 5:
                    base = "USD";
                    saida = "CUP";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 6:
                    base = "CUP";
                    saida = "USD";
                    endereco = "https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/latest/" + base;
                    break;
                case 7:
                    loop = false;
                    System.out.println("Você saiu.");
                    break;

            }

            if (loop == true) {

                String mensagem2 = String.format("""
                **************************************************************
                 DIGITE O VALOR EM %s A SER CONVERTIDO
                **************************************************************
                """, base);

                System.out.println(mensagem2);
                double valorDigitado = leitura.nextDouble();

                Moedas minhaMoeda = new Moedas(base, valorDigitado);
                System.out.println("Moeda base= " + minhaMoeda.getNome()+ ", valor: " + minhaMoeda.getValor());
                endereco ="https://v6.exchangerate-api.com/v6/09a87d16aaef1d92fee16545/pair/" + base  + "/" + saida;


                //biblioteca para requerir da api
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                //biblioteca para responder da api
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //a string json recebe O CORPO DA resposta da API
                String json = response.body();

                Gson gson = new Gson();
                MoedasConvertidas moedaConvertida = gson.fromJson(json, MoedasConvertidas.class);
                System.out.println(moedaConvertida);
                opcao = 0;




            }

        }
    }
}