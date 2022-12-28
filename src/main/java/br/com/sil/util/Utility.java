package br.com.sil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class Utility {
	
	public LocalDateTime getDateTimeFuso() {
		return  LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	}
	
	public String gerarSenha(int tamanho) {
		Random ran = new Random();
		String[] letras = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z" };
		String b = "";
		for (int i = 0; i < tamanho; i++) {
			int a = ran.nextInt(letras.length);
			b += letras[a];
		}
		return b;
	}
	
	public void compactarArquivoMult(List<String> arquivosOrigem, String ArquivoDestino) throws Exception {
		String[] origem = new String[arquivosOrigem.size()];
		for (int i = 0; i < arquivosOrigem.size(); i++) {
			origem[i] = arquivosOrigem.get(i);
		}
		List<String> srcFiles = Arrays.asList(origem);
        FileOutputStream fos = new FileOutputStream(ArquivoDestino);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
 
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
	}
	
}
