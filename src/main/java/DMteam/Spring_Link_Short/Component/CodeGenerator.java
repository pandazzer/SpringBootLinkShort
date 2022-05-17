package DMteam.Spring_Link_Short.Component;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component(value = "gen")
public class CodeGenerator {

    public String getShortUrl() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
