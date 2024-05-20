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

import com.binarybuds.km.dao.IFacilityAddressDao;
import com.binarybuds.km.dao.model.FacilityAddressModel;
import com.binarybuds.km.service.IFacilityAddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityAddressService implements IFacilityAddressService {

  @Autowired
  private final IFacilityAddressDao facilityAddressDao;

  @Override
  public FacilityAddressModel addFacilityAddress(FacilityAddressModel facilityAddressModel) {
    return facilityAddressDao.save(facilityAddressModel);
  }

  @Override
  public List<FacilityAddressModel> getFacilityAddresses() {
    return facilityAddressDao.getFacilityAddresses();
  }

  @Override
  public FacilityAddressModel updateFacilityAddress(Integer facilityAddressId,
      FacilityAddressModel facilityAddressNewData) {
    FacilityAddressModel facilityAddress = facilityAddressDao.findById(facilityAddressId).orElse(null);
    if (facilityAddress != null) {
      facilityAddress.setAddressLine1(facilityAddressNewData.getAddressLine1());
      facilityAddress.setAddressLine2(facilityAddressNewData.getAddressLine2());
      facilityAddress.setCity(facilityAddressNewData.getCity());
      facilityAddress.setState(facilityAddressNewData.getState());
      return facilityAddressDao.save(facilityAddress);
    }
    return null;
  }

  @Override
  public void deleteFacilityAddress(Integer facilityAddressId) {
    facilityAddressDao.deleteById(facilityAddressId);
  }

}
