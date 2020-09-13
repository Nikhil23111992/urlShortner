package proj.nikhil.urlshortner.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "shortUrl", "url", "errors" })
public class UrlResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 714375606986363651L;

	@JsonProperty("ShortUrl")
	private String shortUrl;

	@JsonProperty("Url")
	private String url;

	@JsonProperty("Errors")
	private List<Error> errors;

	@JsonProperty("Url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("Url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("ShortUrl")
	public String getShortUrl() {
		return shortUrl;
	}

	@JsonProperty("ShortUrl")
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@JsonProperty("Errors")
	public List<Error> getErrors() {
		return errors;
	}

	@JsonProperty("Errors")
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
