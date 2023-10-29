package rbarec.unityka.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Ronald
 *
 */
@Repository
public interface KnowledgeRepo extends MongoRepository<KnowledgeModel, Long> //
{
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	public List<KnowledgeModel> findByTopicId(String topicId);
}
