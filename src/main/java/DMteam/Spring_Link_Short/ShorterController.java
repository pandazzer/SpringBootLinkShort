package DMteam.Spring_Link_Short;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
class ShorterController {

    Logger log = LogManager.getLogger();

    private final ShorterRepository repository;
    @Autowired
    public ShorterController(ShorterRepository repository) {
        this.repository = repository;
    }


    @PostMapping(path = "/")
    public String createShortUrl(String original){

        return null;
    }

    @GetMapping(path = "/{hash}")
    public ResponseEntity redirectShorter(@PathVariable("hash") String hash){
        Shorter shorter = repository.findByHash(hash);
        if (shorter != null){
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
