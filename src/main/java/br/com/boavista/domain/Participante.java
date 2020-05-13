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
		Participante p = new Participante();
		p.setId(Integer.valueOf(dados[0]));
		p.setTreinamento(dados[1]);
		p.setNome(dados[2]);
		p.setEmail(dados[3]);
		p.setArea(dados[4]);
		
		return p;
	}
}
