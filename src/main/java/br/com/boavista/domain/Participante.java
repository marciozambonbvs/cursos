package br.com.boavista.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Participante {
	private int id;
	private String treinamento;
	private String nome;
	private String email;
	private String area;
	
	public static Participante fromCSV(String csv) {
		String[] dados = csv.split(";");
		Participante c = new Participante();
		c.setId(Integer.valueOf(dados[0]));
		c.setTreinamento(dados[1]);
		c.setNome(dados[2]);
		c.setEmail(dados[3]);
		c.setArea(dados[4]);
		
		return c;
	}
}
