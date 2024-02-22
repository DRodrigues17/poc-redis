package com.drodrigues17.pocredis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring", name = "cache.type", havingValue = "redis")
public class RedisConfig implements CachingConfigurer {

  private final RedisProperties redisProperties;
  @Value("${spring.cache.redis.time-to-live}")
  private long redisTimeToLive;

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {

    RedisSentinelConfiguration config = new RedisSentinelConfiguration().master(redisProperties.getSentinel().getMaster());
    redisProperties.getSentinel().getNodes().forEach(s -> config.sentinel(s, redisProperties.getPort()));
    config.setSentinelPassword(RedisPassword.of(redisProperties.getPassword()));
    config.setPassword(RedisPassword.of(redisProperties.getPassword()));
    config.setDatabase(this.redisProperties.getDatabase());

    return new JedisConnectionFactory(config);
  }

  @Override
  @Bean
  public RedisCacheManager cacheManager() {
    return RedisCacheManager.builder(this.redisConnectionFactory()).cacheDefaults(this.cacheConfiguration()).build();
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(redisTimeToLive))
        .disableCachingNullValues()
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

}
