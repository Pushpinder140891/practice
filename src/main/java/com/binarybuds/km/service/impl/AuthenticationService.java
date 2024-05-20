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

import com.binarybuds.km.dao.IUserDao;
import com.binarybuds.km.dao.model.UserModel;
import com.binarybuds.km.dto.AuthenticationRequest;
import com.binarybuds.km.dto.AuthenticationResponse;
import com.binarybuds.km.service.IAuthenticationService;
import com.binarybuds.km.util.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final JwtUtil jwtUtil;

  private final IUserDao userDao;

  @Override
  public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));
    UserModel userModel = userDao.findByEmailAddress(authentication.getName());
    String token = jwtUtil.createToken(userModel);
    return new AuthenticationResponse(token);
  }

}