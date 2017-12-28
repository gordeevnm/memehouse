package ru.kek.memehouse.models;

import org.springframework.data.mongodb.core.MongoOperations;
import ru.kek.memehouse.MemeHouseApplication;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
abstract class MongoDoc<T> {
	private static MongoOperations mongoOperations;
	private Class<T> instanceClass;
	
	public void save(T doc) {
		getMongoOperations().save(doc);
	}
	
	protected static MongoOperations getMongoOperations() {
		if (mongoOperations == null)
			mongoOperations = MemeHouseApplication.applicationContext.getBean(MongoOperations.class);
		
		return mongoOperations;
	}
}
