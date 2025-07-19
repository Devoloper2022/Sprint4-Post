package com.sonik.practicum.repository;

import com.sonik.practicum.models.Entity.Post;

import com.sonik.practicum.repository.Interface.PostRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class H2PostRepo implements PostRepo {

    private final JdbcTemplate jdbcTemplate;

    public H2PostRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(
                "select id, title ,content, tags,likes from posts",
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("tags"),
                        rs.getInt("likes")
                ));

    }


    @Override
    public void save(Post post) {
        jdbcTemplate.update("insert into posts(id,title, content, tags,likes) values(? ,?, ?, ?, ?)",
                post.getId(),post.getTitle(), post.getContent(), post.getTags(),post.getLikes());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from posts where id = ?", id);
    }


    @Override
    public void update(Post post) {
        jdbcTemplate.update(
                "UPDATE posts SET title = ?, content = ?, tags = ?,likes = ? WHERE id = ?",
               post.getTitle(), post.getContent(), post.getTags(),post.getLikes(), post.getId());
    }

    @Override
    public Post findById(Long id) {
        String sql = "SELECT id, title, content, tags, likes FROM posts WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Long[]{id},
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("tags"),
                        rs.getInt("likes")
                )
        );
    }

}
