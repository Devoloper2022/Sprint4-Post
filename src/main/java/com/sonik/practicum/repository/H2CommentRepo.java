package com.sonik.practicum.repository;

import com.sonik.practicum.models.Entity.Comment;
import com.sonik.practicum.repository.Interface.CommentRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class H2CommentRepo implements CommentRepo {
    private final JdbcTemplate jdbcTemplate;

    public H2CommentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public List<Comment> findAll(Long postId) {
        return jdbcTemplate.query(
                "SELECT id, comment FROM comments WHERE postId = ?",
                ps -> ps.setLong(1, postId),
                (rs, rowNum) -> new Comment(
                        rs.getLong("id"),
                        rs.getString("comment")
                )
        );
    }


    @Override
    public void save(Comment comments) {
        jdbcTemplate.update("insert into comments(id, comment) values(? ,?, ?, ?, ?)",
                comments.getId(),comments.getComment());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from comments where id = ?", id);
    }


    @Override
    public void update(Comment comments) {
        jdbcTemplate.update(
                "UPDATE comments SET  comment = ? WHERE id = ?",
                comments.getComment(), comments.getId());
    }

    @Override
    public Comment findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, comment FROM comments WHERE id = ?",
                new Long[]{id},
                (rs, rowNum) -> new Comment(
                        rs.getLong("id"),
                        rs.getString("comment")
                )
        );
    }

}
