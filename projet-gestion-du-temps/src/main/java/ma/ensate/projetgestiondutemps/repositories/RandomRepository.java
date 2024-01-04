package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.Random;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomRepository extends JpaRepository<Random, Long> {
}
