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

import com.binarybuds.km.dao.model.ServModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IServDao extends JpaRepository<ServModel, Integer> {

  @Query(value = "SELECT * from km_service", nativeQuery = true)
  List<ServModel> getServices();

  @Modifying
  @Query(value = "Insert into km_service (_tx_service_name)" + "values (:serviceName)", nativeQuery = true)
  Integer addService(@Param("serviceName") String serviceName);

  @Modifying
  @Query(value = "update km_service u set u._tx_service_name = :serviceName", nativeQuery = true)
  void updateService(@Param(value = "serviceName") String serviceName);

  @Modifying
  @Query(value = "delete from km_service u where u._id_service = :serviceId", nativeQuery = true)
  void deleteService(@Param("serviceId") Integer serviceId);

}
