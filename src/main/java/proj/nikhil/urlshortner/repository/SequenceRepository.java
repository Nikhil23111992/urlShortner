package proj.nikhil.urlshortner.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proj.nikhil.urlshortner.dto.Sequence;

	@Repository
	public interface SequenceRepository extends JpaRepository<Sequence, Long> {

	    @Query(value = "SELECT url_sequence.nextval FROM dual", nativeQuery = true)
	    public BigDecimal getNextValSequence();
	}


