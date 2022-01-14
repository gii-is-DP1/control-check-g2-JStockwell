package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FeedingRepository extends CrudRepository<Feeding, Integer> {
    List<Feeding> findAll();

    @Query("SELECT feedtype FROM FeedingType feedtype")
    List<FeedingType> findAllFeedingTypes() throws DataAccessException;

    @Query("SELECT feedtype FROM FeedingType feedtype WHERE feedtype.name LIKE :name")
    FeedingType findFeedingTypeByName(@Param("name") String name);

    Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
}
