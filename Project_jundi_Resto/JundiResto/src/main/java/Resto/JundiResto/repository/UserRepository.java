package Resto.JundiResto.repository;

import Resto.JundiResto.entity.Userss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Userss, String> {

    @Query(value = "SELECT * \n" +
            "FROM \"user\" mu\n" +
            "WHERE \n" +
            "  (:name IS NULL OR :name = '' \n" +
            "  OR LOWER(mu.name) LIKE LOWER(CONCAT('%', :name, '%')))\n" +
            "  AND mu.is_deleted = false;", nativeQuery = true)
    List<Userss> findAllActiveUsersNative(@Param("name") String name);

    @Query(value = "select * from \"user\" u where is_deleted is false and user_id = :userId;", nativeQuery = true)
    Optional<Userss> findByUserIdAndIsDeletedIsFalse(@Param("userId") String userId);
}
