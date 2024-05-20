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

import com.binarybuds.km.dao.IBusinessDao;
import com.binarybuds.km.dao.model.BusinessModel;
import com.binarybuds.km.dao.model.UserModel;
import com.binarybuds.km.service.IBusinessService;
import com.binarybuds.km.service.IUserService;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessService implements IBusinessService {

  @Autowired
  private final IUserService userService;

  private final IBusinessDao businessDao;

  @Override
  public BusinessModel addBusiness(BusinessModel businessModel) {

    String regex = "^(.+)@(.+)$";
    // Check whether emailAddress is valid or not
    if (Pattern.compile(regex).matcher(businessModel.getEmailAddress()).matches()) {
      BusinessModel business = businessDao.save(businessModel);
      UserModel userModel = new UserModel();
      userModel.setEmailAddress(business.getEmailAddress());
      userModel.setPassword("pw999");
      userModel.setBusinessId(business.getBusinessId());
      userService.addUser(userModel);
      return business;
    }
    return null;
  }

  @Override
  public List<BusinessModel> getBusinesses() {
    return businessDao.getBusinesses();
  }

  @Override
  public BusinessModel updateBusiness(Integer businessId, BusinessModel businessNewData) {
    BusinessModel business = businessDao.findById(businessId).orElse(null);
    if (business != null) {
      business.setBusinessName(businessNewData.getBusinessName());
      business.setEmailAddress(businessNewData.getEmailAddress());
      business.setBillingAddress(businessNewData.getBillingAddress());
      return businessDao.save(business);
    }
    return null;
  }

  @Override
  public void deleteBusiness(Integer businessId) {
    businessDao.deleteById(businessId);
  }

}
