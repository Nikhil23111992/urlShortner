package proj.nikhil.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.nikhil.urlshortner.dto.UrlData;

@Repository
public interface UrlCurdRepository extends JpaRepository<UrlData, Long>
{

    public UrlData getFindByUrl(String url);

    public UrlData getFindByShortUrl(String shortUrl);
}
