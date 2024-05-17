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

import com.binarybuds.km.dao.IAppointmentDao;
import com.binarybuds.km.dao.model.AppointmentModel;
import com.binarybuds.km.service.IAppointmentService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

  private final IAppointmentDao appointmentDao;

  @Override
  public AppointmentModel addAppointment(AppointmentModel appointmentModel) {
    return appointmentDao.save(appointmentModel);
  }

  @Override
  public List<AppointmentModel> getAppointmentsBySlot() {
    return appointmentDao.getAppointmentsBySlot();
  }

  @Override
  public AppointmentModel updateAppointment(Integer appointmentId, AppointmentModel appointmentModel) {
    AppointmentModel update = appointmentDao.findById(appointmentId).orElse(null);
    if (update != null) {
      update.setFacilityId(appointmentModel.getFacilityId());
      update.setBusinessId(appointmentModel.getBusinessId());
      update.setServiceId(appointmentModel.getServiceId());
      update.setPetId(appointmentModel.getPetId());
      update.setAppointmentStatus(appointmentModel.getAppointmentStatus());
      update.setAppointmentStart(appointmentModel.getAppointmentStart());
      update.setAppointmentCreated(appointmentModel.getAppointmentCreated());
    }
    return update;
  }

  @Override
  public AppointmentModel deleteAppointment(Integer appointmentId) {
    // TODO Auto-generated method stub
    return null;
  }

}