package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Felhasznalo2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FelhasznaloRepository2 extends CrudRepository<Felhasznalo2, Long>
{
}