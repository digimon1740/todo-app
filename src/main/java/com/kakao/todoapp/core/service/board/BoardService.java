package com.kakao.todoapp.core.service.board;

import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.repository.board.BoardRepository;
import com.kakao.todoapp.core.service.message.MessageSourceService;
import com.kakao.todoapp.web.error.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    private MessageSourceService messageSourceService;

    @Autowired
    public BoardService(BoardRepository boardRepository,
                        MessageSourceService messageSourceService) {
        this.boardRepository = boardRepository;
        this.messageSourceService = messageSourceService;
    }

    @Transactional(readOnly = true)
    public Board findByIdAndUserId(Long id, Long userId) {
        return boardRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    @Transactional(readOnly = true)
    public Board findByIdAndUserIdOrElseThrow(Long id, Long userId) {
        return boardRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(messageSourceService.getMessage("board.not.exist")));
    }

    @Transactional(readOnly = true)
    public List<Board> findAllByUserIdOrderByOrdinal(Long userId) {
        return boardRepository.findAllByUserIdOrderByOrdinal(userId).orElse(Collections.emptyList());
    }

    @Transactional
    public Board create(Board board, User user) {
        Optional.ofNullable(board).orElseThrow(
                () -> new ResourceNotFoundException(messageSourceService.getMessage("board.not.exist")));

        LocalDateTime now = LocalDateTime.now();
        board.setUser(user);
        board.setRegTime(now);
        board.setModifiedTime(now);

        int maxOrdinal = boardRepository.findMaxOrdinalByUserId(user.getId());
        board.setOrdinal(maxOrdinal + 1);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public Board modify(Board board, Long userId) {
        Optional.ofNullable(board).orElseThrow(
                () -> new ResourceNotFoundException(messageSourceService.getMessage("board.not.exist")));

        Board old = findByIdAndUserIdOrElseThrow(board.getId(), userId);
        old.setContent(board.getContent());
        board.setModifiedTime(LocalDateTime.now());
        return board;
    }

    @Transactional
    public void delete(Long id, Long userId) {
        Board board = findByIdAndUserIdOrElseThrow(id, userId);
        // 처음~중간요소 삭제시 후행 요소들의 ordinal 을 한칸씩앞으로 당긴다.
        int maxOrdinal = boardRepository.findMaxOrdinalByUserId(userId);
        if (board.getOrdinal() < maxOrdinal) {
            boardRepository.updateOrdinalByUserIdAndOrdinalGreaterThan(userId, board.getOrdinal());
        }
        boardRepository.deleteByIdAndUserId(id, userId);
    }

    @Transactional(readOnly = true)
    public Board findByUserIdAndOrdinalOrElseThrow(Long userId, int ordinal) {
        return boardRepository.findByUserIdAndOrdinal(userId, ordinal)
                .orElseThrow(() -> new ResourceNotFoundException(messageSourceService.getMessage("board.invalid")));
    }

    @Transactional
    public void up(Long id, Long userId) {
        Board board = findByIdAndUserIdOrElseThrow(id, userId);

        List<Board> boards = findAllByUserIdOrderByOrdinal(userId);
        // 등록된 보드가 없거나 한개뿐인경우
        if (boards.size() <= 1) {
            return;
        }
        // 이미 최상위라면
        int minOrdinal = boardRepository.findMinOrdinalByUserId(userId);
        if (board.getOrdinal() <= minOrdinal) {
            return;
        }

        int toBeOrdinal = board.getOrdinal() - 1;
        Board upper = findByUserIdAndOrdinalOrElseThrow(userId, toBeOrdinal);
        upper.setOrdinal(board.getOrdinal());
        board.setOrdinal(toBeOrdinal);
    }

    @Transactional
    public void down(Long id, Long userId) {
        Board board = findByIdAndUserIdOrElseThrow(id, userId);

        List<Board> boards = findAllByUserIdOrderByOrdinal(userId);
        // 등록된 보드가 없거나 한개뿐인경우
        if (boards.size() <= 1) {
            return;
        }
        // 이미 최하위라면
        int maxOrdinal = boardRepository.findMaxOrdinalByUserId(userId);
        if (board.getOrdinal() >= maxOrdinal) {
            return;
        }

        int toBeOrdinal = board.getOrdinal() + 1;
        Board lower = findByUserIdAndOrdinalOrElseThrow(userId, toBeOrdinal);
        lower.setOrdinal(board.getOrdinal());
        board.setOrdinal(toBeOrdinal);
    }

}
