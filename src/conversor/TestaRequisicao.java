package conversor;

import java.io.IOException;

public class TestaRequisicao {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Conversao valor = new Conversao("BRL", "USD");
		
		valor.pegaCotacao();
		valor.converte(200);
	}

}
