package columbo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;

public class ColumboCustomAnnotator implements Annotator {

	Set<String> jobPositions;
	
	public ColumboCustomAnnotator(String name, Properties props) {
		jobPositions = new HashSet<>(Arrays.asList(props.getProperty("JobPositions","").split(",")));
	}
	
	public void annotate(Annotation annotation) {
		for(int i=0;i<annotation.get(CoreAnnotations.TokensAnnotation.class).size();i++){
			CoreLabel token = annotation.get(CoreAnnotations.TokensAnnotation.class).get(i);
			if(jobPositions.contains(token.word())) {
				token.set(ColumboCustomAnnotation.class, TokenType.JOB_TITLE.getInt());
			}
		}
	}

	public Set<Requirement> requirementsSatisfied() {
		return Collections.unmodifiableSet(new ArraySet<>());
	}

	public Set<Requirement> requires() {
		return new HashSet<>();
	}
}

class ColumboCustomAnnotation implements CoreAnnotation<Integer>{
    @Override
    public Class<Integer> getType(){
        return Integer.class;
    }
}