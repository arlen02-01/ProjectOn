package com.arlen.ProjectOn.web.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWriteRequest {
	@NotBlank private String title;
	@NotBlank private String body;
}
