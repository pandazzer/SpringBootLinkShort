package DMteam.Spring_Link_Short;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
public class Shorter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String url;
    @Column
    private String short_url;
    @Column(name = "time", columnDefinition = "TIMESTAMP")
    private ZonedDateTime time;

    public Shorter() {
    }

    public Shorter(String url, String short_url, ZonedDateTime time) {
        this.url = url;
        this.short_url = short_url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }
}
