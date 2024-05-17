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

package com.binarybuds.km.controller;

import com.binarybuds.km.commons.endpoints.UsersEndpointConstants;
import com.binarybuds.km.commons.message.key.UserMessageKey;
import com.binarybuds.km.dao.model.UserModel;
import com.binarybuds.km.dto.Message;
import com.binarybuds.km.service.IUserService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  public final IUserService userService;

  @GetMapping(path = UsersEndpointConstants.GET_USERS)
  public List<UserModel> getUsers(Integer businessId) {
    return userService.getUsers(businessId);
  }

  @PostMapping(path = UsersEndpointConstants.USER)
  public Message addUser(@RequestBody UserModel userModel) {
    userService.addUser(userModel);
    Message message = new Message();
    message.setMessageKey(UserMessageKey.NEW_USER_ADDED);
    return message;
  }

  @PutMapping(path = UsersEndpointConstants.UPDATE_USER)
  public void updatUser(@PathVariable("userId") Integer userId, @RequestBody UserModel userNewData) {
    userService.updateUser(userId, userNewData);
  }

  @DeleteMapping(path = UsersEndpointConstants.USER)
  public void deleteUser(@PathVariable("userId") Integer userId) {
    userService.deleteUser(userId);
  }
}