package application.data.repository;

import application.data.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRoleRepository extends CrudRepository<UserRole,Integer> {

    @Transactional
    @Query("select u from tbl_userrole u where u.userId = :id")
    Iterable<UserRole> findRolesOfUser(@Param("id")int userId);
}
