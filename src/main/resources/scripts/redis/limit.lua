local key = KEYS[1]
local now = tonumber(ARGV[1])
local limitTimeLengthMills = tonumber(ARGV[2])
local removeScore = tonumber(ARGV[3])
local max = tonumber(ARGV[4])

redis.call('ZREMRANGEBYSCORE',key,0,removeScore)
local current = tonumber(redis.call('ZCARD',key))
local next = current+1

if next>max then
    return 0
else
    redis.call('ZADD',key,now,now)
    redis.call('PEXPIRE',key,limitTimeLengthMills)
    return next
end