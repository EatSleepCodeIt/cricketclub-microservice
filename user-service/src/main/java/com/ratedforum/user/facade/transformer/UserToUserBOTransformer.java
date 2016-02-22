package com.ratedforum.user.facade.transformer;

import org.springframework.stereotype.Service;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.model.UserBO;

@Service
public interface UserToUserBOTransformer {

	public UserBO transform(final User user);
}
