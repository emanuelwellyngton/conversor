package conversor;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					
					var menu = new JOptionPane();
					
					telaInical(menu);
				} catch (Exception e) {
					String error = e.toString();
					e.printStackTrace();
					if (error == "java.lang.NullPointerException")
						System.exit(ABORT);
				}
			}
		});
	}
	
	public static void telaInical(JOptionPane menu) throws IOException, InterruptedException {
		
		Object[] itens = {"Conversor de Moeda", "Conversor de Temperatura"};
		Object selectedValue = menu.showInputDialog(menu, "Escolha uma opção", "Menu", menu.INFORMATION_MESSAGE, null, itens, itens[0]);
		
		if (selectedValue == null)
			encerrar(menu);
		
		String tipoConversor = (String) selectedValue;
		if (tipoConversor == "Conversor de Moeda")
			conversorMoedas(menu);
	}
	
	public static void conversorMoedas(JOptionPane menu) throws IOException, InterruptedException {
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
		
		Conversao conversao = new Conversao(moedaOrigem, moedaDestino);
		double valorConvertdo = conversao.converte(getValue(menu));
		showResults(menu, valorConvertdo,  conversao.destino);
	}
	
	public static double getValue(JOptionPane menu) {
		double valor = 0;
		try {
			String valorParaConverter = menu.showInputDialog("Insira um valor");
			
			if (valorParaConverter == null)
				System.exit(ABORT);
			
			valor = Double.parseDouble(valorParaConverter);
			return valor;
		} catch (Exception e) {
			menu.showMessageDialog(menu, "Valor inválido", "Messege", menu.OK_OPTION);
			valor = getValue(menu);
			return valor;
		}
	}
	
	public static void showResults(JOptionPane menu, double valorConvertido, String simbolo) {
		menu.showMessageDialog(menu, "O valor da conversão é de " + valorConvertido + " " + simbolo);
		continuar(menu);
	}
	
	public static void continuar(JOptionPane menu) {
		int input = menu.showConfirmDialog(menu, "Deseja continuar?", "Escolha uma opção", menu.YES_NO_OPTION);
		
		switch (input) {
		case 0:
			main(null);
			break;

		case 1:
			encerrar(menu);
		case -1:
			encerrar(menu);;
		}
	}
	
	public static void encerrar(JOptionPane menu) {
		menu.showMessageDialog(menu, "Programa finalizado");
		System.exit(ABORT);
	}

}
