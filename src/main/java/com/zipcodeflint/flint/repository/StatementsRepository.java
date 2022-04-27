package com.zipcodeflint.flint.repository;

import com.zipcodeflint.flint.domain.Statements;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Statements entity.
 */
@Repository
public interface StatementsRepository extends JpaRepository<Statements, Long> {
    default Optional<Statements> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Statements> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Statements> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct statements from Statements statements left join fetch statements.bankAccount",
        countQuery = "select count(distinct statements) from Statements statements"
    )
    Page<Statements> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct statements from Statements statements left join fetch statements.bankAccount")
    List<Statements> findAllWithToOneRelationships();

    @Query("select statements from Statements statements left join fetch statements.bankAccount where statements.id =:id")
    Optional<Statements> findOneWithToOneRelationships(@Param("id") Long id);
}
