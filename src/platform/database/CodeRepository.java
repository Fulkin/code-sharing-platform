package platform.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.bean.CodeSnippet;

import java.util.List;
import java.util.UUID;

/**
 * @author Fulkin
 * Created on 27.02.2022
 */

@Repository
public interface CodeRepository extends CrudRepository<CodeSnippet, UUID>{
    List<CodeSnippet> findAllByOrderByDateDesc();
    List<CodeSnippet> findByTimeRestrictionsAndViewsRestrictionsOrderByDateDesc(boolean timeRestrictions, boolean viewsRestrictions);
}
