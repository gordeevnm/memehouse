package ru.kek.memehouse.dao.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.kek.memehouse.models.Meme;

/**
 * gordeevnm@gmail.com
 * 18.05.18
 */
public interface MemesElasticRepo extends ElasticsearchRepository<Meme, Long> {
}
