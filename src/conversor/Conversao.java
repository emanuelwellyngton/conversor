package conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

public class Conversao {
	String origem;
	String destino;
	double valor;
	String key;
	
	Conversao(String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
		this.origem = moedaOrigem;
		this.destino = moedaDestino;
		this.key = moedaOrigem + "-" + moedaDestino;
		this.pegaCotacao();
	}
	
	public double pegaCotacao() throws IOException, InterruptedException {
		
		
		String endereco = "https://economia.awesomeapi.com.br/last/" + this.key;
		URI url = URI.create(endereco);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(url).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		JSONObject json = new JSONObject(body);
		JSONObject cotacao = (JSONObject) json.get(this.origem + this.destino);
		
		this.valor = cotacao.getDouble("low");
		
		return this.valor;
	}
	
	public double converte(double valorParaConverter) {
		
		return this.valor * valorParaConverter;
	}
	
}
