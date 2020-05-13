package br.com.boavista;

import java.util.ArrayList;
import br.com.boavista.dao.ParticipanteDao;
import br.com.boavista.domain.Participante;
import br.com.boavista.service.DataReaderService;

public class CargaTreinamento {
	public static void main(String[] args) {		
		System.out.println("Iniciando processamento...");
		ParticipanteDao participanteDao = null;
		DataReaderService reader = null;
		try {
			reader = new DataReaderService("c:/temp/Treinamentos Realizados.CSV");
			ArrayList<String> dados = reader.getData();
			
			participanteDao = new ParticipanteDao();

			for (String registro: dados) {
				Participante participante = Participante.fromCSV(registro); 
				participanteDao.save(participante);
			}
		} catch(Exception e) {
			System.out.println("Erro ao processar arquivo!\n" + e.getMessage());
		} finally {
			participanteDao.cleanUp();
			System.out.println("Processamento concluído!");
		}			
	}
}
