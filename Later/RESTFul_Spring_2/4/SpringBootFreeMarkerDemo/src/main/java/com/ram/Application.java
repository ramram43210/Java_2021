package com.ram;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ram.controller.UploadingController;

@SpringBootApplication
public class Application
{

	public static void main(String[] args)
	{
		System.out.println("Dir = "+UploadingController.uploadingDir);
		new File(UploadingController.uploadingDir).mkdirs();
		SpringApplication.run(Application.class, args);
	}

}
