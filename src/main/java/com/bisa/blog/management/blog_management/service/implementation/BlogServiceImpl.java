package com.bisa.blog.management.blog_management.service.implementation;

import static com.bisa.blog.management.blog_management.utils.AppConstants.AUTHOR;
import static com.bisa.blog.management.blog_management.utils.AppConstants.BLOG;
import static com.bisa.blog.management.blog_management.utils.AppConstants.ID;
import static com.bisa.blog.management.blog_management.utils.AppConstants.CREATED_AT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bisa.blog.management.blog_management.exception.ResourceNotFoundException;
import com.bisa.blog.management.blog_management.model.Author;
import com.bisa.blog.management.blog_management.model.Blog;
import com.bisa.blog.management.blog_management.model.BlogHistory;
import com.bisa.blog.management.blog_management.model.Comment;
import com.bisa.blog.management.blog_management.payload.request.BlogRequest;
import com.bisa.blog.management.blog_management.payload.response.BlogResponse;
import com.bisa.blog.management.blog_management.payload.response.PagedResponse;
import com.bisa.blog.management.blog_management.repository.AuthorRepository;
import com.bisa.blog.management.blog_management.repository.BlogHistoryRepository;
import com.bisa.blog.management.blog_management.repository.BlogRepository;
import com.bisa.blog.management.blog_management.service.BlogService;
import com.bisa.blog.management.blog_management.utils.AppConstants;
import com.bisa.blog.management.blog_management.exception.BadRequestException;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  BlogHistoryRepository blogHistoryRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Override
  public ResponseEntity<Blog> addBlog(BlogRequest blogRequest) {
    Author author = authorRepository.findById(blogRequest.getAuthorId())
        .orElseThrow(() -> new ResourceNotFoundException(AUTHOR, ID, blogRequest.getAuthorId()));
    Blog blog = new Blog();
    blog.setAuthor(author);
    blog.setAllowComments(blogRequest.getAllowComments());
    blog.setContent(blogRequest.getContent());
    blog.setPeriodicity(blogRequest.getPeriodicity());
    blog.setTitle(blogRequest.getTitle());
    blog.setTopic(blogRequest.getTopic());
    Blog newBlog = blogRepository.save(blog);
    return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<BlogResponse> getBlog(Long id) {
    Blog blog = blogRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(BLOG, ID, id));
    return new ResponseEntity<>(getBlogResponse(blog), HttpStatus.OK);
  }

  @Override
  public PagedResponse<BlogResponse> getAllBlogs(int page, int size) {
    validatePageNumberAndSize(page, size);
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
    Page<Blog> posts = blogRepository.findAll(pageable);
    List<BlogResponse> response = new ArrayList<BlogResponse>();
    for (Blog blog : posts.getContent()) {
      response.add(getBlogResponse(blog));
    }
    List<BlogResponse> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : response;
    return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
        posts.getTotalPages(), posts.isLast());
  }

  @Override
  public ResponseEntity<Blog> updateBlog(Long id, BlogRequest blogRequest) {
    authorRepository.findById(blogRequest.getAuthorId())
        .orElseThrow(() -> new ResourceNotFoundException(AUTHOR, ID, id));
    Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(BLOG, ID, id));
    BlogHistory blogHistory = new BlogHistory();
    blogHistory.setBlog(blog);
    blogHistory.setAllowComments(blog.getAllowComments());
    blogHistory.setContent(blog.getContent());
    blogHistory.setPeriodicity(blog.getPeriodicity());
    blogHistory.setTitle(blog.getTitle());
    blogHistory.setTopic(blog.getTopic());
    blogHistoryRepository.save(blogHistory);
    blog.setTitle(blogRequest.getTitle());
    blog.setAllowComments(blogRequest.getAllowComments());
    blog.setContent(blogRequest.getContent());
    blog.setPeriodicity(blogRequest.getPeriodicity());
    blog.setTopic(blogRequest.getTopic());
    Blog updatedBlog = blogRepository.save(blog);
    return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
  }

  private void validatePageNumberAndSize(int page, int size) {
    if (page < 0) {
      throw new BadRequestException("Page number cannot be less than zero.");
    }

    if (size < 0) {
      throw new BadRequestException("Size number cannot be less than zero.");
    }

    if (size > AppConstants.MAX_PAGE_SIZE) {
      throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
    }
  }

  private BlogResponse getBlogResponse(Blog blog) {
    BlogResponse response = new BlogResponse();
    response.setAllowComments(blog.getAllowComments());
    response.setAuthor(blog.getAuthor());
    response.setComments(blog.getComments());
    response.setContent(blog.getContent());
    response.setId(blog.getId());
    response.setAverageScore(blog.getComments().stream().mapToInt(Comment::getScore).average().orElse(0));
    response.setMaxScore(blog.getComments().stream().mapToInt(Comment::getScore).max().orElse(0));
    response.setMinScore(blog.getComments().stream().mapToInt(Comment::getScore).min().orElse(0));
    response.setPeriodicity(blog.getPeriodicity());
    response.setTitle(blog.getTitle());
    response.setTopic(blog.getTopic());
    return response;
  }
}
