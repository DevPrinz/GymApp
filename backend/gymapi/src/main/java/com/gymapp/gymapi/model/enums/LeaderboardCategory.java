package com.gymapp.gymapi.model.enums;

import com.gymapp.gymapi.Constants.RedisKeyConstants;

public enum LeaderboardCategory {
    BIKE(RedisKeyConstants.LEADERBOARD_BIKE_KEY),
    TREADMILL(RedisKeyConstants.LEADERBOARD_TREADMILL_KEY),
    RUNNING(RedisKeyConstants.LEADERBOARD_RUNNING_KEY),
    CYCLING(RedisKeyConstants.LEADERBOARD_CYCLING_KEY),
    STRENGTH(RedisKeyConstants.LEADERBOARD_STRENGTH_KEY);

    private final String redisKey;

    LeaderboardCategory(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getRedisKey() {
        return redisKey;
    }
}
