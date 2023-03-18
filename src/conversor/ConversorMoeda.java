package conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConversorMoeda extends Conversor {
	static String origem;
	static String destino;
	static double valor;
	static String key;
	static JOptionPane menu;
	
	ConversorMoeda(JOptionPane menu) throws IOException, InterruptedException {
		this.menu = menu;
		main(null);
	}
	
	public static void main(String[] args) {
		Object[] itens = {"De Reais a Dólares", "De Reais a Euros", "De Reais a Libras Esterlinas", "De Reais a Peso argentino", "De Reais a Peso Chileno",
				"De Dólar a Reais", "De Euro a Reais", "De Libras Esterlinas a Reais", "De Peso argentino a  Reais", "De Peso Chileno a Reais"};
		String selectedValue = (String) menu.showInputDialog(menu, "Escolha a moeda para a qual você deseja converter o valor", "Moedas", menu.INFORMATION_MESSAGE, null, itens, itens[0]);
		
		String moedaOrigem = null;
		String moedaDestino = null;
		
		switch (selectedValue) {
			case "De Reais a Dólares":
				moedaOrigem = "BRL";
				moedaDestino = "USD";
				break;
			case "De Reais a Euros":
				moedaOrigem = "BRL";
				moedaDestino = "EUR";
				break;
			case "De Reais a Libras Esterlinas":
				moedaOrigem = "BRL";
				moedaDestino = "GBP";
				break;
			case "De Reais a Peso argentino":
				moedaOrigem = "BRL";
				moedaDestino = "ARS";
				break;
			case "De Reais a Peso Chileno":
				moedaOrigem = "BRL";
				moedaDestino = "CLP";
				break;
			case "De Dólar a Reais":
				moedaOrigem = "USD";
				moedaDestino = "BRL";
				break;
			case "De Euro a Reais":
				moedaOrigem = "EUR";
				moedaDestino = "BRL";
				break;
			case "De Libras Esterlinas a Reais":
				moedaOrigem = "GBP";
				moedaDestino = "BRL";
				break;
			case "De Peso argentino a  Reais":
				moedaOrigem = "ARS";
				moedaDestino = "BRL";
				break;
			case "De Peso Chileno a Reais":
				moedaOrigem = "CLP";
				moedaDestino = "BRL";
				break;
		}
		
		origem = moedaOrigem;
		destino = moedaDestino;
		
		key = origem + "-" + destino;
		
		try {
			pegaCotacao();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		double valorConvertdo = converte(getValue(menu));
		App.showResults(menu, valorConvertdo,  destino);
	}
	
	public static double pegaCotacao() throws IOException, InterruptedException {
		
		
		String endereco = "https://economia.awesomeapi.com.br/last/" + key;
		URI url = URI.create(endereco);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(url).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		JSONObject json = new JSONObject(body);
		JSONObject cotacao = (JSONObject) json.get(origem + destino);
		
		valor = cotacao.getDouble("low");
		
		return valor;
	}
	
	public static double converte(double valorParaConverter) {
		
		return valor * valorParaConverter;
	}
	
}
