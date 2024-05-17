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

package com.binarybuds.km.dao;

import com.binarybuds.km.dao.model.AppointmentModel;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentDao extends JpaRepository<AppointmentModel, Integer> {

  @Query(value = "SELECT * from km_appointment limit 1", nativeQuery = true)
  List<AppointmentModel> getAppointmentsBySlot();

  @Query(value = "insert into km_appointment(_id_facility, _id_business, _id_service, _id_pet, _nu_appointment_status, _ts_appointment, _ts_created)"
      + "values = (:facilityId, :businessId, :serviceId, :petId, :appointmentStatus,  :appointmentStart, :appointmentCreated)", nativeQuery = true)
  Integer addAppointment(@Param("facilityId") Integer facilityId, @Param("businessId") Integer businessId,
      @Param("serviceId") Integer serviceId, @Param("petId") Integer petId,
      @Param("appointmentStatus") String appointmentStatus, @Param("appointmentStart") Timestamp appointmentStart,
      @Param("appointmentCreated") Timestamp appointmentCreated);

}
