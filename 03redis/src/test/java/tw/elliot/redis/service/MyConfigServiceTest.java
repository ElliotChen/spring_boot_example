package tw.elliot.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class MyConfigServiceTest {

	@Autowired
	private MyConfigService service;

	@Autowired
	private StringRedisTemplate str;

	private int numKeys = 10000;
	@Disabled
	@Test
	public void testCacheData() {
		String key = "akey";
		String value = service.findCacheData(key);
		Assertions.assertEquals("123", value);

		value = service.findCacheData(key);
		Assertions.assertEquals("123", value);

		value = service.findCacheData(key);
		Assertions.assertEquals("123", value);
	}
	@Disabled
	@Test
	public void testCachePutAndEvict() {
		String key = "akey";
		String oldValue = "123";
		String newValue = "456";

		String value = service.findCacheData(key);
		Assertions.assertEquals(oldValue, value);

		service.updateCacheData(key, newValue);
		value = service.findCacheData(key);
		Assertions.assertEquals(newValue, value);


		service.cleanCacheData(key);
		value = service.findCacheData(key);
		Assertions.assertEquals(oldValue, value);

	}

	@Test
	public void singleThreadInert() {
		if (this.numKeys > 10000) {
			log.info("Wait too long, skip test");
			return;
		}
		Assertions.assertNotNull(this.str);

		long start = System.currentTimeMillis();


		ValueOperations<String, String> ops = this.str.opsForValue();
		for (int i = 0; i < this.numKeys; i++) {

			String kv = String.valueOf(i);
			ops.set(kv, kv);
		}


		long end = System.currentTimeMillis();
		log.info("[not pipe] singleThreadInert: Used Time {} ms", end - start);
	}

	@Test
	public void singlePipeInert() {

		long start = System.currentTimeMillis();


		String[] uuids = new String[numKeys];
		for (int ind = 0; ind < numKeys; ++ind) {
			uuids[ind] = String.valueOf(ind);
		}

		this.str.executePipelined(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
				for (int i = 0; i < numKeys; i++) {
					stringRedisConn.set(uuids[i], uuids[i]);
					//log.info("Insert[{}]", j);
				}
				return null;
			}
		});


		long end = System.currentTimeMillis();
		log.info("[pipe] singlePipeInert: Used Time {} ms", end - start);
	}

	@Test
	public void simpleMulitThreadInsert() {
		int availableProcessors = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService exec = Executors.newFixedThreadPool(availableProcessors);

		String[] uuids = new String[numKeys];
		for (int ind = 0; ind < numKeys; ++ind) {
			uuids[ind] = String.valueOf(ind);
		}

		long start = System.currentTimeMillis();
		ValueOperations<String, String> ops = this.str.opsForValue();
		for (int i = 0; i < numKeys; i++) {
			final int index = i;
			exec.submit(() -> {
				ops.set(uuids[index], uuids[index]);
				//log.info("Insert[{}]", index);
			});
		}

		exec.shutdown();
		try {
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

			long end = System.currentTimeMillis();
			log.info("[not pipe] simpleMulitThreadInsert Used Time {} ms", end - start);
		} catch (InterruptedException e) {
			System.err.println("Execution interrupted: " + e.getMessage());
		}



	}

	@Test
	public void multiThreadInsert() {
		int availableProcessors = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService exec = Executors.newFixedThreadPool(availableProcessors);

		String[] uuids = new String[numKeys];
		for (int ind = 0; ind < numKeys; ++ind) {
			uuids[ind] = String.valueOf(ind);
		}

		final int portion = numKeys / availableProcessors;
		long start = System.currentTimeMillis();
		ValueOperations<String, String> ops = this.str.opsForValue();
		for (int i = 0; i <= availableProcessors; ++i) {
			final int threadId = i;
			exec.submit(() -> {

				for (int j = threadId * portion; j < (threadId + 1) * portion; ++j) {
					ops.set(uuids[j], uuids[j]);
					//log.info("Insert[{}]", j);
				}
			});

		}


		exec.shutdown();
		try {
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

			long end = System.currentTimeMillis();
			log.info("[not pipe] mulitThreadInsert Used Time {} ms", end - start);
		} catch (InterruptedException e) {
			System.err.println("Execution interrupted: " + e.getMessage());
		}



	}

	@Test
	public void multiThreadPipeInsert() {
		int availableProcessors = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService exec = Executors.newFixedThreadPool(availableProcessors);

		String[] uuids = new String[numKeys];
		for (int ind = 0; ind < numKeys; ++ind) {
			uuids[ind] = String.valueOf(ind);
		}

		final int portion = numKeys / availableProcessors;
		long start = System.currentTimeMillis();
		for (int i = 0; i <= availableProcessors; ++i) {
			final int threadId = i;
			exec.submit(() -> {
				this.str.executePipelined(new RedisCallback<Object>() {
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
						for(int j = threadId * portion; j < (threadId + 1) * portion; ++j) {
							stringRedisConn.set(uuids[j], uuids[j]);
							//log.info("Insert[{}]", j);
						}
						return null;
					}});
			});

		}


		exec.shutdown();
		try {
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

			long end = System.currentTimeMillis();
			log.info("[pipe] multiThreadPipeInsert Used Time {} ms", end - start);
		} catch (InterruptedException e) {
			System.err.println("Execution interrupted: " + e.getMessage());
		}



	}
}