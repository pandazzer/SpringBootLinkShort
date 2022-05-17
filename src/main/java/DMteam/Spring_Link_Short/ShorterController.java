package DMteam.Spring_Link_Short;

import DMteam.Spring_Link_Short.Component.CodeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping
class ShorterController {

    Logger log = LogManager.getLogger();

    private final ShorterRepository repository;
    @Autowired
    public ShorterController(ShorterRepository repository) {
        this.repository = repository;
    }


    @PostMapping(path = "/cut")
    public String createShortUrl(@RequestBody String url){
        Shorter shorter = repository.findByurl(url);
        Date date = new Date();
        String homeURL = "http://localhost:8080/";
        if (shorter == null){
            log.info("Ссылка не записана");
            ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
            String short_url = context.getBean("gen", CodeGenerator.class).getShortUrl();

            shorter = new Shorter(url, short_url, date);
            repository.save(shorter);
            return homeURL+short_url;
        }else {
            log.info("Ссылка уже записана");
            shorter.setTime(date);
            repository.save(shorter);
            return homeURL+shorter.getShort_url();
        }
    }

    @GetMapping(path = "/{short_link}")
    public ResponseEntity redirectShorter(@PathVariable("short_link") String short_link){
        Shorter shorter = repository.findByshorturl(short_link);
        if (shorter != null){
            HttpHeaders headers = new HttpHeaders();
            if (!isLiveURL(shorter)){
                log.info("Ссылка истекла");
                return ResponseEntity.ok("Ссылка больше не активна");
            }
            log.info(shorter.getUrl());
            headers.add("Location", shorter.getUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }else {
            return ResponseEntity.ok("Ссылка не записана");
        }
    }

    @GetMapping(path = "/key/{short_link}")
    private String shelfLife(@PathVariable("short_link") String short_link){
        Shorter shorter = repository.findByshorturl(short_link);
        if (shorter != null){
            if (!isLiveURL(shorter)){
                return "Ссылка больше не активна";
            }else return "Ссылка активна";
        }else return "Ссылка не записана";
    }

    public boolean isLiveURL(Shorter shorter){
        log.info(shorter.getTime());
        long dateNow = new Date().getTime();
        long dateWrite = shorter.getTime().getTime();
        int shelfLife = Integer.parseInt(System.getenv("TIME"))*1000;
        log.info(dateNow+"\n"+dateWrite);
        log.info(dateNow-dateWrite);
        if (dateNow - dateWrite > shelfLife){
            return false;
        }else {
            return true;
        }
    }
}
