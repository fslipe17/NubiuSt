package nubist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String inicio(){
        return "NubiSt - Backend Funcional";
    }

    @GetMapping("/api/arquivos")
    public String arquivos(){
        Path pasta = Path.of("../armazenamento/arquivos");

        try{
            return Files.list(pasta)
                    .map(path -> path.getFileName().toString())
                    .toList()
                    .toString();
        } catch (Exception e) {
            return " Erro ao ler os arquivos.";
        }

        // try {
        //     if (Files.exists(pasta)) {
        //         StringBuilder arquivos = new StringBuilder();
        //         Files.list(pasta).forEach(arquivo -> arquivos.append(arquivo.getFileName()).append("\n"));
        //         return arquivos.toString();
        //     } else {
        //         return "A pasta de arquivos não existe.";
        //     }
        // } catch (Exception e) {
        //     return "Erro ao listar os arquivos: " + e.getMessage();
        // }
    }

    @PostMapping("/api/arquivos")
    public String upload(@RequestParam("arquivo") MultipartFile arquivo){
        
        Path pasta = Path.of("../armazenamento/arquivos");

        Path destino = pasta.resolve(arquivo.getOriginalFilename());

        try {
            arquivo.transferTo(destino);
            return "Arquivo Salvo com Sucesso: ";
        } catch (Exception e) {
            return "Erro ao salvar o arquivo: ";
        }
    }
}