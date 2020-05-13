package br.com.boavista;

import java.util.ArrayList;
import br.com.boavista.dao.ParticipanteDao;
import br.com.boavista.domain.Participante;
import br.com.boavista.service.DataReaderService;

public class CargaTreinamento {
	public static void main(String[] args) {		
		System.out.println("Iniciando processamento...");
		ParticipanteDao participantes = null;
		DataReaderService reader = null;
		try {
			reader = new DataReaderService("c:/temp/Treinamentos Realizados.CSV");
			ArrayList<String> dados = reader.getData();
			
			participantes = new ParticipanteDao();

			for (String registro: dados) {
				Participante participante = Participante.fromCSV(registro); 
				participantes.save(participante);
			}
		} catch(Exception e) {
			System.out.println("Erro ao processar arquivo!\n" + e.getMessage());
		} finally {
			participantes.cleanUp();
			System.out.println("Processamento concluído!");
		}			
	}
}
