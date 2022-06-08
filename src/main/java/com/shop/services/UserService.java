package com.shop.services;

import com.shop.dto.user.UserRequestDto;
import com.shop.domain.User;
import com.shop.dto.exceptions.BusinessCustomException;
import com.shop.dto.exceptions.RequiredParamException;
import com.shop.dto.exceptions.ValidationCustomException;
import com.shop.dto.exceptions.auth.AuthenticationsException;
import com.shop.repository.UserRepository;
import com.shop.utils.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


public interface UserService {

    User save(UserRequestDto userRequest);
    User findUserByEmail(String email);

    @Service
    class UserServiceImpl implements UserService {

        @Autowired
        protected UserRepository userRepository;

        @Override
        public User save(UserRequestDto userRequest) {
            // validation field required
            validationDataRequest(userRequest);
            // check user is existed in system
            User user = userRepository.findByEmail(userRequest.getEmail());
            if (Objects.nonNull(user)) {
                throw new BusinessCustomException(BusinessStatus.USER_IS_EXISTED);
            }
            return userRepository.save(user);
        }

        @Override
        public User findUserByEmail(String email) {
            User user = userRepository.findByEmail(email);
            if (Objects.isNull(user)) {
                throw new AuthenticationsException(ErrorCodes.ERROR_USER_NOT_EXIST);
            }
            return user;
        }

        private void validationDataRequest(UserRequestDto userRequest) {
            validationEmail(userRequest.getEmail());
            validationPhoneNumber(userRequest.getMobileNumber());
            validationPassword(userRequest.getPassword());
        }

        private void validationEmail(String email) {
            if (StringUtils.isNotEmpty(email) && (email.length() > Constants.MAX_LENGTH_EMAIL)) {
                throw new ValidationCustomException(ErrorCodes.ERROR_MAXIMUM_EMAIL);
            } else if (!BusinessUtils.checkedEmail(email)) {
                throw new ValidationCustomException(ErrorCodes.ERROR_INVALID_EMAIL);
            }
        }

        private void validationPhoneNumber(String phoneNumber) {
            if (StringUtils.isEmpty(phoneNumber)) {
                throw new RequiredParamException(FieldName.MOBILE_PHONE);
            } else if (!BusinessUtils.checkedPhoneNumber(phoneNumber)) {
                throw new ValidationCustomException(ErrorCodes.ERROR_INVALID_PHONE_NUMBER);
            }
        }

        private void validationPassword(String password) {
            if (StringUtils.isEmpty(password)) {
                throw new RequiredParamException(FieldName.PASSWORD);
            } else if (!BusinessUtils.checkedPassword(password)) {
                throw new ValidationCustomException(ErrorCodes.ERROR_INVALID_PASSWORD);
            }
        }
    }
}
