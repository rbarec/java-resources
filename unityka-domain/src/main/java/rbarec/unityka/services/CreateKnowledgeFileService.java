package rbarec.unityka.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import rbarec.unityka.dto.CreateKnowledgeFileRequest;

@Service
public class CreateKnowledgeFileService {

	public void create(CreateKnowledgeFileRequest req) {
		BufferedReader reader;

		try {
			// "C:/ronnHistoryLog_git/5Fantasticos/@infoCasa.txt"
			reader = new BufferedReader(new FileReader(req.getFileUrl()));
			String line = reader.readLine();
			int i = 0;
			while (line != null) {

				// read next line
				line = reader.readLine();
				if (i == 0) {
					System.out.println("PRIMERA_LINEA " + line);
					i++;
					continue;
				} else {
					System.out.println("   " + line);
					i++;
					continue;
				}
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
