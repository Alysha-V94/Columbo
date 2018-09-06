package columbo;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NLPConsumerMain {
	
	public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("customAnnotatorClass.search", "columbo.ColumboCustomAnnotator");
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, search");
        props.setProperty("JobPositions", "Mayor");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "Boris Johnson was made Mayor of London for London City Council in 2008 until 2016 and earned £143911.";

        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                
                System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));
            }
        }
    }
}
