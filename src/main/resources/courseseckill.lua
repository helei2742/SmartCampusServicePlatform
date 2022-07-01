-- 1 参数列表
-- 1。1优惠券id
local courseId = ARGV[1]
local userId = ARGV[2]


--2 数据key
local stockKey = 'seckill:course:stock:' .. courseId
local orderKey = 'seckill:course:order:' .. courseId

local stock = tonumber(redis.call('get', stockKey))

if(stock) then
    --3、判断课余量是否充足
    if(stock <= 0 ) then
        -- 库存不足返回1
        return 1
    end

    --4.判断用户是否选过

    if(redis.call('sismember', orderKey, userId) == 1) then
        -- 存在，返回2
        return 2
    end

    -- 扣课余量，保存订单
    redis.call('incrby', stockKey, -1)
    redis.call('sadd', orderKey, userId)
    return 0
end
return -1;

