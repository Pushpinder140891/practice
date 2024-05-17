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

import com.binarybuds.km.dao.model.UserModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<UserModel, Integer> {

  @Query(value = "SELECT * from km_user where _id_business = :businessId", nativeQuery = true)
  List<UserModel> getUsers(@Param(value = "businessId") Integer businessId);

  @Modifying
  @Query(value = "Insert into km_user (_tx_first_name, _tx_last_name, _tx_email_address, _tx_password, _id_business) "
      + "Values (:firstName, :lastName, :emailAddress, :password, :businessId)", nativeQuery = true)
  Integer addUser(@Param("firstName") String firstName, @Param("lastName") String lastName,
      @Param("emailAddress") String emailAddress, @Param("password") String password,
      @Param("businessId") Integer businessId);

  @Modifying
  @Query(value = "update km_user u set u._tx_first_name = :firstName, " + "u._tx_last_name = :lastName, "
      + "u._tx_email_address = :emailAddress, " + "u._tx_password = :password, "
      + "u._id_business = :businessId where u._id_user = :userId", nativeQuery = true)
  void updateUser(@Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName,
      @Param(value = "emailAddress") String emailAddress, @Param(value = "password") String password,
      @Param(value = "businessId") Integer businessId);

  @Modifying
  @Query(value = "delete from km_user u where u._id_user = :userId", nativeQuery = true)
  void deleteUser(@Param("userId") Long userId);

  @Query(value = "SELECT _id_user, _tx_first_name, _tx_last_name, _tx_email_address, _tx_password, _id_business from km_user u where u._tx_email_address = :emailAddress", nativeQuery = true)
  UserModel findByEmailAddress(@Param(value = "emailAddress") String emailAddress);

}