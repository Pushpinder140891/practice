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

import com.binarybuds.km.dao.IPetDao;
import com.binarybuds.km.dao.model.PetModel;
import com.binarybuds.km.dto.AppointmentForm;
import com.binarybuds.km.dto.PetDto;
import com.binarybuds.km.service.IPetService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetService implements IPetService {

  private final IPetDao petDao;

  @Override
  public List<PetDto> addPet(AppointmentForm appointmentForm) {
    List<PetDto> savedPets = new ArrayList<>();
    List<PetDto> listOfPets = appointmentForm.getPets();
    for (PetDto petDto : listOfPets) {
      PetModel petModel = new PetModel();
      petModel.setPetName(petDto.getPetName());
      petModel.setPetBreed(petDto.getPetBreed());
      petModel.setPetColour(petDto.getPetColour());
      petModel.setPetDOB(petDto.getPetDOB());
      if (isValidPetSex(petDto.getPetSex())) {
        petModel.setPetSex(petDto.getPetSex());
      }
      petModel.setRabbiesExpirationDate(petDto.getRabbiesExpirationDate());
      petModel.setDhppiExpirationDate(petDto.getDhppiExpirationDate());
      petModel.setLeptoExpirationDate(petDto.getLeptoExpirationDate());
      petModel.setBordettelaExpirationDate(petDto.getBordettelaExpirationDate());
      petModel.setDewormingExpirationDate(petDto.getDewormingExpirationDate());
      petDao.save(petModel);
      savedPets.add(convertToPetDto(petModel));
    }
    return savedPets;
  }

  private boolean isValidPetSex(String petSex) {
    return petSex != null && (petSex.equals("Unfixed Male") || petSex.equals("Neutered Male")
        || petSex.equals("Unfixed Female") || petSex.equals("Spayed Female"));
  }

  private PetDto convertToPetDto(PetModel petModel) {
    PetDto petDto = new PetDto();
    petDto.setPetName(petModel.getPetName());
    petDto.setPetBreed(petModel.getPetBreed());
    petDto.setPetColour(petModel.getPetColour());
    petDto.setPetDOB(petModel.getPetDOB());
    petDto.setPetSex(petModel.getPetSex());
    petDto.setRabbiesExpirationDate(petModel.getRabbiesExpirationDate());
    petDto.setDhppiExpirationDate(petModel.getDhppiExpirationDate());
    petDto.setLeptoExpirationDate(petModel.getLeptoExpirationDate());
    petDto.setBordettelaExpirationDate(petModel.getBordettelaExpirationDate());
    petDto.setDewormingExpirationDate(petModel.getDewormingExpirationDate());
    return petDto;
  }
}
