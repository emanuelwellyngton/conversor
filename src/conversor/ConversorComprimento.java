package conversor;

import javax.swing.JOptionPane;

public class ConversorComprimento extends Conversor {
	private static String origem;
	private static String destino;
	private static JOptionPane menu;
	
	public ConversorComprimento(JOptionPane menu) {
		this.menu = menu;
		main(null);
	}
	
	public static void main(String[] args) {
		Object[] itens = {"km", "hm", "dam", "m", "dm", "cm", "mm"};
		origem = (String) menu.showInputDialog(menu, "Escolha a medida da qual você deseja converter:", "Conversor de comprimento", menu.INFORMATION_MESSAGE, null, itens, itens[0]);
		destino = (String) menu.showInputDialog(menu, "Escolha a medida para a qual você deseja converter:", "Conversor de comprimento", menu.INFORMATION_MESSAGE, null, itens, itens[0]);
		
		double valor = getValue(menu);
		System.out.println(valor);
		double valorMetros = converterParaMetros(valor);
		double valorConvertido = converterDeMetros(valorMetros);
		App.showResults(menu, valorConvertido, destino);
	}
	
	public static double converterParaMetros(double valor) {
			
		switch (origem) {
			
			case "km":
				valor *= 1000;
				return valor;
			case "hm":
				valor *= 100;
				return valor;
			case "dam":
				valor *= 10;
				return valor;
			case "m":
				return valor;
			case "dm":
				valor /= 10;
				return valor;
			case "cm":
				valor /= 100;
				return valor;
			case "mm":
				valor /= 1000;
				return valor;
			default:
				throw new NullPointerException();
		}
			
	}
	
	public static double converterDeMetros(double valor) {
		
		switch (destino) {
			
			case "km":
				valor /= 1000;
				return valor;
			case "hm":
				valor /= 100;
				return valor;
			case "dam":
				valor /= 10;
				return valor;
			case "m":
				return valor;
			case "dm":
				valor *= 10;
				return valor;
			case "cm":
				valor *= 100;
				return valor;
			case "mm":
				valor *= 1000;
				return valor;
			default:
				throw new NullPointerException();
		}
			
	}

}
