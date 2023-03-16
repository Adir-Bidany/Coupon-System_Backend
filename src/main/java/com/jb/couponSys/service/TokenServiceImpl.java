package com.jb.couponSys.service;

import com.jb.couponSys.beans.ClientType;
import com.jb.couponSys.beans.User;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.security.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final Map<UUID, Information> map;

    @Override
    public UUID addUser(User user) {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(user.getId())
                .type(user.getType())
                .time(LocalDateTime.now())
                .build();

        map.put(token, information);
        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(obj -> obj.getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public boolean isValid(UUID token, ClientType type) {
        Information info = map.get(token);
        if (info != null) {
            return info.getType().equals(type);
        }
        return false;
    }

    @Override
    public int getUserID(UUID token) throws CouponSysException {

        Information info = map.get(token);
        if (info != null) {
            return info.getId();
        }
        throw new CouponSysException(ErrMsg.INVALID_TOKEN);
    }
}