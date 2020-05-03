package learn.redis.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ControlledCacheService {

    private static final String CONTROLLED_PREFIX = "myControlledPrefix_";

    @SuppressWarnings("unused")//used in spel to define cache keys
    public static String getCacheKey(String relevant) {
        return CONTROLLED_PREFIX + relevant;
    }

    @Cacheable(cacheNames = "myControlledCache", key = "T(learn.redis.cache.ControlledCacheService).getCacheKey(#relevant)")
    public String getFromCache(String relevant) {
        return null;
    }

    @CachePut(cacheNames = "myControlledCache", key = "T(learn.redis.cache.ControlledCacheService).getCacheKey(#relevant)")
    public String populateCache(String relevant, String unrelevantTrackingId) {
        return String.format("this is %s again!", relevant);
    }

    @CacheEvict(cacheNames = "myControlledCache", key = "T(learn.redis.cache.ControlledCacheService).getCacheKey(#relevant)")
    public void removeFromCache(String relevant) {
    }

}
