package com.ci2hayfa.carrental.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ci2hayfa.carrental.business.services.CustomerService;
import com.ci2hayfa.carrental.business.services.FilesStorageService;
import com.ci2hayfa.carrental.business.services.VehicleService;
import com.ci2hayfa.carrental.dao.entities.Customer;
import com.ci2hayfa.carrental.dao.entities.Vehicle;
import com.ci2hayfa.carrental.web.messages.ResponseMessage;
import com.ci2hayfa.carrental.web.models.FileInfo;

@Controller
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class FilesController {

  @Autowired
  FilesStorageService storageService;
  @Autowired
  CustomerService customerService;
  @Autowired
    VehicleService vehicleService;

  @PostMapping("/upload/{type}/{id}")
  public ResponseEntity<ResponseMessage> uploadFile(@PathVariable Long id,@PathVariable String type, @RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.save(file);
    //  Handle file upload based on the type
      if (type.equalsIgnoreCase("Customer")) {
      //update contact
      Customer customer=customerService.getCustomerById(id);
      customer.setImage("files/"+id.toString()+"-"+type.toLowerCase()+"-"+file.getOriginalFilename());
      customerService.updateCustomer(id, customer);
    }
      else if (type.equalsIgnoreCase("Vehicle")) {
        Vehicle vehicle=vehicleService.getVehicleById(id);
        vehicle.setImage("files/"+file.getOriginalFilename());
        vehicleService.updateVehicle(id, vehicle);
      }
      message = "Uploaded the file successfully: " + file.getOriginalFilename();

      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}