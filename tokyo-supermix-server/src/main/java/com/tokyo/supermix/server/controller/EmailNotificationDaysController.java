package com.tokyo.supermix.server.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.NotificationDaysRequestDto;
import com.tokyo.supermix.data.dto.NotificationDaysResponseDto;
import com.tokyo.supermix.data.dto.emailNotificationDto;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailNotificationDaysService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;;

@RestController
@CrossOrigin
public class EmailNotificationDaysController {
  @Autowired
  private EmailNotificationDaysService emailNotificationDaysService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  private static final Logger logger =
      LoggerFactory.getLogger(EmailNotificationDaysController.class);

  @PostMapping(value = EndpointURI.EMAIL_NOTIFICATION)
  public ResponseEntity<Object> createEmailNotificationDays(
      @RequestBody List<NotificationDaysRequestDto> notificationDaysRequestDtos) {
    for (NotificationDaysRequestDto notificationDaysRequestDto : notificationDaysRequestDtos) {

      if (notificationDaysRequestDto.getDays() == null) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_NOTIFICATION_DAY,
            validationFailureStatusCodes.getDaysIsnull()), HttpStatus.BAD_REQUEST);
      }
      if (emailNotificationDaysService.isDuplicateExists(
          notificationDaysRequestDto.getEmailGroupId(), notificationDaysRequestDto.getDays())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.EMAIL_NOTIFICATION_DAY,
                validationFailureStatusCodes.getEmailNotificationDaysAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      emailNotificationDaysService
          .createEmailNotification(mapper.map(notificationDaysRequestDto, NotificationDays.class));
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMAIL_NOTIFICATION_DAYS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EMAIL_NOTIFICATIONS_BY_GROUP)
  public ResponseEntity<Object> getAllEmailNotificationDaysByGroup() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_NOTIFICATION_DAYS,
        mapper.map(emailNotificationDaysService.getAllEmailNotificationDaysByGroup(),
            NotificationDaysResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.EMAIL_NOTIFICATION)
  public ResponseEntity<Object> editAllEmailNotificationDays(
      @RequestBody NotificationDaysRequestDto notificationDaysRequestDto) {
    if (emailNotificationDaysService.isDuplicateExists(notificationDaysRequestDto.getEmailGroupId(),
        notificationDaysRequestDto.getDays())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMAIL_NOTIFICATION_DAY,
              validationFailureStatusCodes.getEmailNotificationDaysAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    emailNotificationDaysService
        .createEmailNotification(mapper.map(notificationDaysRequestDto, NotificationDays.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMAIL_NOTIFICATION_DAYS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EMAIL_NOTIFICATIONS)
  public ResponseEntity<Object> getAllEmailNotificationDays() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_NOTIFICATION_DAYS,
        mapper.map(emailNotificationDaysService.getAllEmailNotificationDays(),
            emailNotificationDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.EMAIL_NOTIFICATION_BY_ID)
  public ResponseEntity<Object> deleteEmailNotificationDays(@PathVariable Long id) {
    if (emailNotificationDaysService.isEmailNotificationDaysExist(id)) {
      emailNotificationDaysService.deleteEmailNotificationDays(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMAIL_NOTIFICATION_DAY_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EMAIL_NOTIFICATION_DAY_ID,
            validationFailureStatusCodes.getEmailNotificationDaysNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.EMAIL_NOTIFICATIONS_BY_PLANT_CODE)
  public ResponseEntity<Object> getAllEmailNotificationDaysByPlantCode(
      @PathVariable String plantCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_NOTIFICATION_DAYS, mapper
        .map(emailNotificationDaysService.getByPlantCode(plantCode), emailNotificationDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
