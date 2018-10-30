package ru.kek.memehouse.controllers.api.memes;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.dao.repos.MemesRepo;
import ru.kek.memehouse.dao.elastic.MemesElasticRepo;
import ru.kek.memehouse.models.Meme;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.disMaxQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * gordeevnm@gmail.com
 * 18.05.18
 */
@RestController
@RequiredArgsConstructor
public class ElasticTestController {
	private final ElasticsearchTemplate elasticsearchTemplate;
	
	@RequestMapping(value = "/memes/test", method = RequestMethod.GET)
	public List<String> testSearch(@RequestParam("query") String text) {
		
		SearchQuery query = new NativeSearchQueryBuilder()
			  .withQuery(
					 disMaxQuery()
							.add(matchQuery("name", text).boost(2).fuzziness("auto"))
							.add(matchQuery("description", text).boost(1).fuzziness("auto"))
							.add(matchQuery("tags", text))
			  )
			  .withFields("id")
			  .build();
		System.out.println(query.getQuery().toString());
		return elasticsearchTemplate.queryForIds(query);
//		return memesElasticSearchRepo.find(query, query.split(" "));
	}
	
	private final MemesRepo memesRepo;
	private final MemesDao memesDao;
	private final MemesElasticRepo memesElasticRepo;
	
	@RequestMapping(value = "/memes/reload", method = RequestMethod.GET)
	public Iterable<Meme> loadToElastic() {
		memesElasticRepo.saveAll(memesRepo.findAll());
		
		return memesElasticRepo.findAll();
	}
}