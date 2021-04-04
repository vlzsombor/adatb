package hu.szte.fenykepalbumok.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.szte.fenykepalbumok.model.Felhasznalo;
@Repository
public interface FelhasznaloRepository extends CrudRepository<Felhasznalo, Long>
{
}