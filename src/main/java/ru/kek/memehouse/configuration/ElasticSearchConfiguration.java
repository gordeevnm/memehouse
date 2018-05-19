package ru.kek.memehouse.configuration;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * gordeevnm@gmail.com
 * 18.05.18
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.kek.memehouse.dao.elastic")
public class ElasticSearchConfiguration {
	@Bean
	public TransportClient client() throws UnknownHostException {
		return new PreBuiltTransportClient(Settings.builder()
			  .put("cluster.name", "docker-cluster")
			  .put("client.transport.sniff", true)
			  .build())
			  .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate(TransportClient client) {
		return new ElasticsearchTemplate(client);
	}
}
