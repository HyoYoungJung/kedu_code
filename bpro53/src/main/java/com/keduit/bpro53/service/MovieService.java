package com.keduit.bpro53.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.MovieImageDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.dto.PageResultDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;

public interface MovieService {

	Long register(MovieDTO movieDTO);
	
	PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	default Map<String, Object> dtoToEntity(MovieDTO movieDTO){
		Map<String, Object> entityMap = new HashMap<>();
		
		Movie movie = Movie.builder()
				.mno(movieDTO.getMno())
				.title(movieDTO.getTitle())
				.build();
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
		
		if(imageDTOList != null && imageDTOList.size() >0) { // 비어있지 않거나, 사이즈가 있으면
			
		 List<MovieImage> movieImageList = imageDTOList.stream()
				 .map(movieImageDTO -> {
					 MovieImage movieImage = MovieImage.builder()
							 .path(movieImageDTO.getPath())
							 .imgName(movieImageDTO.getImgName())
							 .uuid(movieImageDTO.getUuid())
							 .movie(movie)
							 .build();
					 return movieImage;
				 }).collect(Collectors.toList());
				 
				 entityMap.put("imgList", movieImageList);
		}
		return entityMap;
	}
	
	default MovieDTO entitiesToDTO(
			Movie movie, List<MovieImage> movieImages, Double avg,
			Long reviewCnt) {
		
		MovieDTO movieDTO = MovieDTO.builder()
				.mno(movie.getMno())
				.title(movie.getTitle())
				.regDate(movie.getRegDate())
				.updateDate(movie.getUpdateDate())
				.build();
		
		  List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage
		  -> { if(movieImage != null) { return
		  MovieImageDTO.builder().imgName(movieImage.getImgName())
		  .path(movieImage.getPath()) .uuid(movieImage.getUuid()) .build(); }else {
		  return null; } }).collect(Collectors.toList());
		 
				
		movieDTO.setImageDTOList(movieImageDTOList);
		movieDTO.setAvg(avg);
		movieDTO.setReviewCnt(reviewCnt.intValue());
		
		return movieDTO;
	}

	MovieDTO getMovie(Long mno);
			
	
	
}
