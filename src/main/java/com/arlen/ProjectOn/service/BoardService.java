package com.arlen.ProjectOn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arlen.ProjectOn.domain.board.Post;
import com.arlen.ProjectOn.domain.board.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	private final PostRepository postRepository;
	
	public Page<Post> list(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
    @Transactional
    public Long write(String title, String content, String author) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        return postRepository.save(post).getId();
    }
	
    @Transactional(readOnly = true)
    public Post get(Long id) {
        return postRepository.findById(id).orElseThrow();
    }
    
    @Transactional
    public Long update(String title, String content, Long id){
        Post board = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + id));

        board.setTitle(title);
        board.setContent(content);

        return board.getId();
    }
    
    @Transactional
    public Long delete(Long id) {
    	postRepository.deleteById(id);
    	return id;
    }
}
