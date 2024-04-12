package com.ci2hayfa.carrental.web.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ci2hayfa.carrental.business.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/photos")

public class PhotoController {

  private final StrategyPhotoContext strategyPhotoContext;

  public PhotoController(StrategyPhotoContext strategyPhotoContext) {
    this.strategyPhotoContext = strategyPhotoContext;
  }

  @PostMapping("/save/{id}/{title}/{context}")
  public ResponseEntity<?> savePhoto(@PathVariable("context") String context, @PathVariable("id") Long id,
      @RequestPart("file") MultipartFile photo, @PathVariable("title") String title)
      throws IOException, FlickrException, Exception {
        return new ResponseEntity<>(strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title), HttpStatus.OK);
  }
}