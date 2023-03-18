package conversor;

import javax.swing.JOptionPane;

public abstract class Conversor {

	public static double getValue(JOptionPane menu) {
		double valor = 0;
		try {
			String valorParaConverter = menu.showInputDialog("Insira um valor");
			
			if (valorParaConverter == null)
				System.exit(0);
			
			valor = Double.parseDouble(valorParaConverter);
			return valor;
		} catch (Exception e) {
			menu.showMessageDialog(menu, "Valor inválido", "Messege", menu.OK_OPTION);
			valor = getValue(menu);
			return valor;
		}
	}
	
}
