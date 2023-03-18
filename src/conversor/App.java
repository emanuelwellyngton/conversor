package conversor;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class App extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
		
		Object[] itens = {"Conversor de Moeda", "Conversor de Comprimento"};
		Object selectedValue = menu.showInputDialog(menu, "Escolha uma opção", "Menu", menu.INFORMATION_MESSAGE, null, itens, itens[0]);
		
		ConversorMoeda conversao;
		
		if (selectedValue == null)
			encerrar(menu);
		
		String tipoConversor = (String) selectedValue;
		
		switch (tipoConversor) {
		case "Conversor de Moeda":
			conversao = new ConversorMoeda(menu);
			break;
		case "Conversor de Comprimento":
			ConversorComprimento cc = new ConversorComprimento(menu);
			break;
		default:
			break;
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
