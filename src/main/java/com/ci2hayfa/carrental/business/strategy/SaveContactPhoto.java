package com.ci2hayfa.carrental.business.strategy;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ci2hayfa.carrental.business.services.CustomerService;
import com.ci2hayfa.carrental.business.services.FlickrService;
import com.ci2hayfa.carrental.dao.entities.Customer;
import com.flickr4java.flickr.FlickrException;

import lombok.extern.slf4j.Slf4j;

@Service("contactStrategy")
@Slf4j
public class SaveContactPhoto implements Strategy<Customer> {

  private final FlickrService flickrService;
  private final CustomerService customerService;

  public SaveContactPhoto(FlickrService flickrService, CustomerService customerService) {
    this.flickrService = flickrService;
    this.customerService = customerService;
  }

  @Override
  public Customer savePhoto(Long id, InputStream photo, String titre) throws FlickrException, Exception {
    Customer customer = customerService.getCustomerById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new Exception("Error saving contact photo");
    }
    customer.setImage(urlPhoto);
    return customerService.createCustomer(customer);
  }
}