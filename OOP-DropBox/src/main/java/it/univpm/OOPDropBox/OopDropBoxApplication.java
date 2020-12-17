package it.univpm.OOPDropBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.OOPDropBox.exception.TokenErratoException;
import it.univpm.OOPDropBox.utils.SalvaDati;

@SpringBootApplication
public class OopDropBoxApplication {

	public static void main(String[] args) throws TokenErratoException {
		SalvaDati.setToken("ohQff05gTN0AAAAAAAAAAa4Uay_9z9ZskKpx6YO-CxcwMmaSG4XtB918GqvgJLXh");
		SalvaDati.SalvaLink(SalvaDati.SalvaApi("https://api.dropboxapi.com/2/sharing/list_shared_links","{}"));		
		SpringApplication.run(OopDropBoxApplication.class, args);
	}

}
