package com.ci2hayfa.carrental.business.strategy;

import java.io.InputStream;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.FlickrException;

import lombok.Setter;

@Service
public class StrategyPhotoContext {

  
  private Strategy strategy;
  @Setter
  private String context;

  private final BeanFactory beanFactory;
  public StrategyPhotoContext(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  public Object savePhoto(String context, Long id, InputStream photo, String title) throws FlickrException, Exception {
    determinContext(context);
    return strategy.savePhoto(id, photo, title);
  }

  private void determinContext(String context) throws Exception {
    final String beanName = context + "Strategy";
    switch (context) {
      case "contact":
        strategy = beanFactory.getBean(beanName, SaveContactPhoto.class);
        break;
     
      default: throw new Exception("Unknown context for saving the photo");
    }
  }


}