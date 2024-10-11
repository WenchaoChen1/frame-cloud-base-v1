//package com.gstdev.cloud.commons.utils;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Slf4j
//@Configuration
//public class RedisConfig {
//  /**
//   * redisTemplate 序列化使用的jdk Serializable, 存储二进制字节码, 所以自定义序列化类
//   */
//  @Bean
//  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//    log.info("Redis Template init");
////    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
////    redisTemplate.setConnectionFactory(redisConnectionFactory);
////
////    // 使用Jackson2JsonRedisSerialize 替换默认序列化
////    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
////
////    ObjectMapper objectMapper = new ObjectMapper();
////    objectMapper.registerModule(new JavaTimeModule());
////
////    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////    objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
////    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
////
////    // 设置value的序列化规则和 key的序列化规则
////    redisTemplate.setKeySerializer(new StringRedisSerializer());
////    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
////    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//////    redisTemplate.set
////    redisTemplate.afterPropertiesSet();
////    return redisTemplate;
//    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//    // 配置连接工厂
//    redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//    // 使用StringRedisSerializer来序列化和反序列化Redis的key值
//    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//    // 使用Jackson2JsonRedisSerializer来序列化和反序列化Redis的value值
//    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//    // 配置对象映射器
//    JacksonObjectMapper objectMapper = new JacksonObjectMapper();
//    // 指定要序列化的域，field，get和set，以及修饰符范围。ANY指包括private和public修饰符范围
//    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//    // 指定序列化输入类型，类的信息也将添加到json中，这样才可以根据类名反序列化。
//    objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
//    // 将对象映射器添加到序列化器中
//    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//    // 配置key，value，hashKey，hashValue的序列化方式
//    redisTemplate.setKeySerializer(stringRedisSerializer);
//    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//    redisTemplate.setHashKeySerializer(stringRedisSerializer);
//    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//    return redisTemplate;
//  }
//}
