/*
 * BinaryBuds Limited CONFIDENTIAL
 * Unpublished Copyright (c) 2008-2020 BinaryBuds, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of BinaryBuds. The intellectual and technical concepts contained
 * herein are proprietary to BinaryBuds and may be covered by U.K. and Foreign Patents, patents in process, and are protected by trade secret and copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from BinaryBuds.  Access to the source code contained herein is hereby forbidden to anyone except current BinaryBuds employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
 * information that is confidential and/or proprietary, and is a trade secret, of  BinaryBuds. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
 * OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN CONSENT OF BinaryBuds IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
 *
 */

package com.binarybuds.km.service.impl;

import com.binarybuds.km.dao.IPetOwnerDao;
import com.binarybuds.km.dao.model.PetOwnerModel;
import com.binarybuds.km.dto.AppointmentForm;
import com.binarybuds.km.dto.PetOwnerDto;
import com.binarybuds.km.service.IPetOwnerService;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetOwnerService implements IPetOwnerService {

  private final IPetOwnerDao petOwnerDao;

  private static String EMAIL_ADDRESS_REGEX = "^(.+)@(.+)$";
  private static String PHONE_NUMBER_REGEX = "^[0-9]{10}$";

  @Override
  public PetOwnerDto addPetOwner(AppointmentForm appointmentForm) {

    if (Pattern.compile(EMAIL_ADDRESS_REGEX).matcher(appointmentForm.getPetOwner().getEmailAddress()).matches()
        && Pattern.compile(PHONE_NUMBER_REGEX).matcher(String.valueOf(appointmentForm.getPetOwner().getPhoneNumber()))
            .matches()) {
      PetOwnerModel petOwnerModel = new PetOwnerModel();
      petOwnerModel.setFirstName(appointmentForm.getPetOwner().getFirstName());
      petOwnerModel.setLastName(appointmentForm.getPetOwner().getLastName());
      petOwnerModel.setPhoneNumber(appointmentForm.getPetOwner().getPhoneNumber());
      petOwnerModel.setEmailAddress(appointmentForm.getPetOwner().getEmailAddress());
      petOwnerModel.setAddress(appointmentForm.getPetOwner().getAddress());
      petOwnerModel.setCity(appointmentForm.getPetOwner().getCity());
      petOwnerModel.setState(appointmentForm.getPetOwner().getState());
      petOwnerModel.setPincode(appointmentForm.getPetOwner().getPincode());
      petOwnerDao.save(petOwnerModel);
      PetOwnerDto petOwnerDto = convertToPetOwnerDto(petOwnerModel);
      return petOwnerDto;
    }
    return null;
  }

  private PetOwnerDto convertToPetOwnerDto(PetOwnerModel petOwnerModel) {
    PetOwnerDto petOwnerDto = new PetOwnerDto();
    petOwnerDto.setFirstName(petOwnerModel.getFirstName());
    petOwnerDto.setLastName(petOwnerModel.getLastName());
    petOwnerDto.setPhoneNumber(petOwnerModel.getPhoneNumber());
    petOwnerDto.setEmailAddress(petOwnerModel.getEmailAddress());
    petOwnerDto.setAddress(petOwnerModel.getAddress());
    petOwnerDto.setCity(petOwnerModel.getCity());
    petOwnerDto.setState(petOwnerModel.getState());
    petOwnerDto.setPincode(petOwnerModel.getPincode());
    return petOwnerDto;
  }

}
