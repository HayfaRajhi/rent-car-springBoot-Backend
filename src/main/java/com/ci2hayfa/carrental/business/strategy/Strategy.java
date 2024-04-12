package com.ci2hayfa.carrental.business.strategy;

import java.io.InputStream;

import com.flickr4java.flickr.FlickrException;

public interface Strategy<T> {

    T savePhoto(Long id, InputStream photo, String titre) throws FlickrException, Exception;
  
  }