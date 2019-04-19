package com.kakao.todoapp.core.repository.board;

import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Optional<Board> findByIdAndUserId(Long id, Long userId);

	Optional<Board> findByUserIdAndOrdinal(Long userId, int ordinal);

	Optional<List<Board>> findAllByUserIdOrderByOrdinal(Long userId);

	@Query("select coalesce(max(b.ordinal),0) from Board b where b.user.id = :userId")
	int findMaxOrdinalByUserId(@Param("userId") Long userId);

	@Query("select coalesce(min(b.ordinal),0) from Board b where b.user.id = :userId")
	int findMinOrdinalByUserId(@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query("update Board b set b.ordinal = b.ordinal - 1 where b.user.id= :userId and b.ordinal > :ordinal")
	void updateOrdinalByUserIdAndOrdinalGreaterThan(@Param("userId") Long userId, @Param("ordinal") int ordinal);

	void deleteByIdAndUserId(Long id, Long userId);
}
