package proj.nikhil.urlshortner.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "URL_SEQ")
public class Sequence {
	@Id
    @SequenceGenerator(name = "urlseq", sequenceName = "url_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yourName")
    @Column(name = "id", updatable = false)
    protected Long id;


}