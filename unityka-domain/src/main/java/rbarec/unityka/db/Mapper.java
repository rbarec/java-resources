package rbarec.unityka.db;

import java.util.ArrayList;
import java.util.List;

import rbarec.unityka.db.KnowledgeModel.LineKnowModel;
import rbarec.unityka.domain.Knowledge;
import rbarec.unityka.domain.KnowledgeTypeEnum;
import rbarec.unityka.domain.LineKnow;

/**
 * Mapper de Knowledge and LineKnow<br>
 * en dos vias
 * @author Ronald
 *
 */
public class Mapper {

	public static Knowledge toKnowledge(KnowledgeModel model) {
		return Knowledge.builder().storeId(model.getId()) //
				.topicId(model.getTopicId()) //
				.type(KnowledgeTypeEnum.valueOf(model.getType())) //
				.principalImage(model.getPrincipalImage()) //
				.version(model.getVersion()) //
				.lines(toLineKnowList(model.getLines())) //
				.build();

	}

	public static LineKnow toLineKnow(LineKnowModel m) {
		return LineKnow.builder().rawOriginal(m.getRawOriginal()).build();
	}

	public static List<LineKnow> toLineKnowList(List<LineKnowModel> list) {
		List<LineKnow> l2 = new ArrayList<>();
		for (LineKnowModel line : list) {
			l2.add(LineKnow.builder().rawOriginal(line.getRawOriginal()).build());
		}
		return l2;
	}

	
	public static KnowledgeModel toKnowledgeModel(Knowledge domObj) {
		return KnowledgeModel.builder().id(domObj.getStoreId()) //
				.topicId(domObj.getTopicId()) //
				.type(domObj.getType().name()) //
				.principalImage(domObj.getPrincipalImage()) //
				.version(domObj.getVersion()) //
				.lines(toLineKnowModelList(domObj.getLines())) //
				.build();

	}
	
	
	public static LineKnowModel toLineKnowModel(LineKnow lk) {
		return LineKnowModel.builder().rawOriginal(lk.getRawOriginal()).build();
	}
	
	public static List<LineKnowModel> toLineKnowModelList(List<LineKnow> list) {
		List<LineKnowModel> l2 = new ArrayList<>();
		for (LineKnow line : list) {
			l2.add(LineKnowModel.builder().rawOriginal(line.getRawOriginal()).build());
		}
		return l2;
	}
}
