package proj.nikhil.urlshortner.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "shortUrl", "url" })
public class UrlRequest
{

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("Url")
    private String url;

    @JsonProperty("ShortUrl")
    private String shorturl;

    @JsonProperty("Url")
    public String getUrl()
    {
        return url;
    }

    @JsonProperty("Url")
    public void setUrl(String url)
    {
        this.url = url;
    }

    @JsonProperty("Id")
    public Long getId()
    {
        return id;
    }

    @JsonProperty("Id")
    public void setId(Long id)
    {
        this.id = id;
    }

    @JsonProperty("ShortUrl")
    public String getShorturl()
    {
        return shorturl;
    }

    @JsonProperty("ShortUrl")
    public void setShorturl(String shorturl)
    {
        this.shorturl = shorturl;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
