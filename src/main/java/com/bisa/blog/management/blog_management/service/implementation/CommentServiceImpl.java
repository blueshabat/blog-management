package com.bisa.blog.management.blog_management.service.implementation;

import static com.bisa.blog.management.blog_management.utils.AppConstants.COMMENT;
import static com.bisa.blog.management.blog_management.utils.AppConstants.ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bisa.blog.management.blog_management.exception.BadRequestException;
import com.bisa.blog.management.blog_management.exception.ResourceNotFoundException;
import com.bisa.blog.management.blog_management.model.Blog;
import com.bisa.blog.management.blog_management.model.Comment;
import com.bisa.blog.management.blog_management.payload.request.CommentRequest;
import com.bisa.blog.management.blog_management.repository.BlogRepository;
import com.bisa.blog.management.blog_management.repository.CommentRepository;
import com.bisa.blog.management.blog_management.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

  @Autowired
  BlogRepository blogRepository;

  @Autowired
  CommentRepository commentRepository;

  @Override
  public ResponseEntity<Comment> addComment(CommentRequest commentRequest) {
    Blog blog = blogRepository.findById(commentRequest.getBlogId())
      .orElseThrow(() -> new ResourceNotFoundException(COMMENT, ID, commentRequest.getBlogId()));
    if(!blog.getAllowComments()){
      throw new BadRequestException("This blog does not allow to post comments."); 
    }
    Comment comment = new Comment();
    comment.setAuthorCountry(commentRequest.getAuthorCountry());
    comment.setAuthorEmail(commentRequest.getAuthorEmail());
    comment.setAuthorName(commentRequest.getAuthorName());
    comment.setBlog(blog);
    comment.setScore(commentRequest.getScore());
    comment.setText(commentRequest.getText());
    Comment newComment = commentRepository.save(comment);
    return new ResponseEntity<>(newComment, HttpStatus.CREATED);
  }
  
}
