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

import java.util.List;

import org.springframework.stereotype.Service;

import com.binarybuds.km.dao.IFacilityDao;
import com.binarybuds.km.dao.model.FacilityModel;
import com.binarybuds.km.service.IFacilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService implements IFacilityService {

  private final IFacilityDao facilityDao;

  @Override
  public FacilityModel addFacility(FacilityModel facilityModel) {
    return facilityDao.save(facilityModel);
  }

  @Override
  public List<FacilityModel> getFacilities() {
    return facilityDao.getFacilities();
  }

  @Override
  public FacilityModel updateFacility(Integer facilityId, FacilityModel facilityModel) {
    FacilityModel update = facilityDao.findById(facilityId).orElse(null);
    if (facilityModel != null) {
      update.setBusinessId(facilityModel.getBusinessId());
      update.setFacilityName(facilityModel.getFacilityName());
      update.setAddressLine1(facilityModel.getAddressLine1());
      update.setAddressLine2(facilityModel.getAddressLine2());
      update.setCity(facilityModel.getCity());
      update.setState(facilityModel.getState());
      return facilityDao.save(update);
    }
    return null;
  }

  @Override
  public FacilityModel deleteFacilityModel(Integer facilityId) {
    // TODO Auto-generated method stub
    return null;
  }

}