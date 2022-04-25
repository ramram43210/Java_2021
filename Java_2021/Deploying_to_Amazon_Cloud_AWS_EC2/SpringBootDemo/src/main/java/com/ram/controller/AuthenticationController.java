package com.ram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.model.request.LoginRequestModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
public class AuthenticationController
{
	@ApiOperation("UserLogin")
	@ApiResponses(value = { @ApiResponse(code = 200,
			message = "Response Headers",
			responseHeaders = { @ResponseHeader(name = "Authorization",
					description = "Bearer <JWT Token>",
					response = String.class), @ResponseHeader(name = "UserId",
					description = "Bearer <Public UserId>",
					response = String.class)}) })

	@PostMapping("/login")
	public void fakeLogin(@RequestBody LoginRequestModel loginRequestModel)
	{
		throw new IllegalStateException(
				"This method should not be called and it is implemented by Spring Security");
	}

}
