local courseId = ARGV[1]
local userId = ARGV[2]

local stockKey = 'seckill:course:stock:' .. courseId
local orderKey = 'seckill:course:order:' .. courseId

if(redis.call('sismember', orderKey, userId) == 1) then
    redis.call('srem', orderKey, userId)
    redis.call('incrby', stockKey, 1)
    return 1
end
return 0