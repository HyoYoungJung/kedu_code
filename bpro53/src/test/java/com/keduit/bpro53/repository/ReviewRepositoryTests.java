package com.keduit.bpro53.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro53.entity.Member;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.Review;

@SpringBootTest
public class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void insertMovieReviews() {
		
		//200개의 리뷰를 등록
		IntStream.rangeClosed(1, 200).forEach(i -> {
			
			//영화 번호
			Long mno = (long)(Math.random()*100) + 1;
			
			//리뷰어 번호
			Long mid = ((long)(Math.random()*100) + 1);
			
			Member member = Member.builder().mid(mid).build();
			
			Review movieReview = Review.builder()
					.member(member)
					.movie(Movie.builder().mno(mno).build())
					.grade((int)(Math.random()*5) + 1)
					.text("이 영화에 대한 느낌..." + i)
					.build();
			
			reviewRepository.save(movieReview);
					
		});
	}
	
	@Test 
	public void testGetMovieReviews() {
		
		Movie movie = Movie.builder().mno(38L).build();
		
		List<Review> result = reviewRepository.findByMovie(movie);
		
		result.forEach(movieReview -> {
			System.out.println(movieReview);
			System.out.print(movieReview.getReviewnum());
			System.out.print("    " + movieReview.getGrade());
			System.out.print("    " + movieReview.getText());
			System.out.println("    " + movieReview.getMember().getEmail());
			System.out.println("------------------------------------------------");
		});
	}
	
	
}
