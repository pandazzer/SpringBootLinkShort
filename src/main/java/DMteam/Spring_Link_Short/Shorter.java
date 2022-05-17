package DMteam.Spring_Link_Short;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "shorturl")
public class Shorter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String url;
    @Column(name = "short_url")
    private String shorturl;
    @Column(name = "time", columnDefinition = "TIMESTAMP")
    private Date time;

    public Shorter() {
    }

    public Shorter(String url, String short_url, Date time) {
        this.url = url;
        this.shorturl = short_url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return shorturl;
    }

    public void setShort_url(String short_url) {
        this.shorturl = short_url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }
}
